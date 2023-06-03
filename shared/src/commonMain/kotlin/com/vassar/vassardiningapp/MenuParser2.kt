package com.vassar.vassardiningapp

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import android.util.Log
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.Serializable

class MenuParser2 (private val url: String) {
    var menuItems: Map<String, MenuItem2> = emptyMap()
    var dayParts: MutableList<DayPart> = mutableListOf()
    private val client = HttpClient()

    suspend fun initialize() {
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

        dayPartsJsonList?.let {
            for (dayPartInstance in dayPartsJsonList) {
                dayParts.add(Json { ignoreUnknownKeys = true }.decodeFromString(dayPartInstance))
            }
        }

        if (dayParts.isEmpty() || menuItems.isEmpty()) {
            throw Exception("Unable to Retrieve Information From Website")
        }
    }
}