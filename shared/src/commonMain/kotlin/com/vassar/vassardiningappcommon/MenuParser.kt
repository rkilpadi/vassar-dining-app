package com.vassar.vassardiningappcommon

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class MenuParser {
    var menuItems: Map<String, MenuItem> = emptyMap()
    var dayParts: MutableList<DayPart> = mutableListOf()
    var cafes: MutableSet<String> = mutableSetOf()
    var schools: List<String> = listOf("vassar", "mit")
    private val client = HttpClient()

    suspend fun initialize(url: String) {
        // Perform asynchronous initialization here
        val response = client.get(url)
        val html = response.bodyAsText()

        val menuItemsRegex = "Bamco\\.menu_items = .*?(?=\\r?\\n)".toRegex()
        val menuItemsLine = menuItemsRegex.find(html)?.value
        val menuItemsJson = menuItemsLine?.substringAfter("Bamco.menu_items = ")?.dropLast(1)

        val dayPartsRegex = "Bamco\\.dayparts\\['\\d+'\\] = .*?(?=\\r?\\n)".toRegex()
        val dayPartsLines = dayPartsRegex.findAll(html).map { it.value }.toList()
        val dayPartsJsonList = dayPartsLines.map { line ->
            val dayPartsJson = line.substringAfter(" = ").dropLast(1)
            dayPartsJson
        }

        menuItemsJson?.let {
            menuItems = Json { ignoreUnknownKeys = true }.decodeFromString(menuItemsJson)
        }

        dayPartsJsonList.let {
            for (dayPartInstance in dayPartsJsonList) {
                dayParts.add(Json { ignoreUnknownKeys = true }.decodeFromString(dayPartInstance))
            }
        }

//        if (dayParts.isEmpty() || menuItems.isEmpty()) {
//            throw Exception("Unable to Retrieve Information From Website")
//        }

        for (time in dayParts) {
            for (station in time.stations) {
                for (id in station.items) {
                    menuItems[id]?.let { station.stationItems.add(it) }
                }
            }
        }

        val regex = "\\.cafebonappetit\\.com/cafe/(.*?)/".toRegex()
        val matchResult = regex.findAll(html)
        val capturedTextList = matchResult.map { it.groupValues.getOrNull(1) }.toList()

        val dateregex = "([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))".toRegex()

        for (result in capturedTextList) {
            if (result != null && dateregex.matches(result)) {
                cafes.add("All Cafes")
            } else if (result != null && result != "feed") {
                cafes.add(result)
            }
        }
    }

    fun toMealtimeMenu(cafe: String, date: String): MutableList<MealtimeMenu> {

        val result = mutableListOf<MealtimeMenu>()

        for (daypart in dayParts) {
            val mealtimeMenuItems = mutableListOf<MealtimeItem>()
            for (station in daypart.stations) {
                for (id in station.items) {
                    val item = menuItems[id]

                    if (item != null) {
                        val dietaryRestrictions: MutableSet<DietaryRestriction> = mutableSetOf()

                        val restrictionIds = item.cor_icon?.keys

                        if (restrictionIds != null) {
                            for (restrictionId in restrictionIds) {
                                dietaryRestrictions.add(DietaryRestriction.getById(restrictionId))
                            }

                            if ((item.label != null) && (item.description != null)) {
                                mealtimeMenuItems.add(
                                    MealtimeItem(
                                        item.label,
                                        id,
                                        item.description,
                                        station.label,
                                        dietaryRestrictions
                                    )
                                )
                            }
                        }
                    }
                }
            }
            result.add(MealtimeMenu(cafe, date, daypart.label, mealtimeMenuItems))
        }

        return result
    }
}