package com.vassar.vassardiningapp

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json


class MenuParser2 (private val url: String) {
    private val client = HttpClient()

    suspend fun getMenu(): List<MenuItem2> {
        // Perform asynchronous initialization here
        val response = client.get(url)
        var html = response.bodyAsText()
        val jsonPattern = "Bamco\\.menu_items = .*?(?=\\r?\\n)".toRegex()
        val jsonLine = jsonPattern.find(html)?.value
        val json = jsonLine?.substringAfter("Bamco.menu_items = ")?.dropLast(1)

        json?.let {
            val menuItemsMap = Json { ignoreUnknownKeys = true }.decodeFromString<Map<String, MenuItem2>>(json)
            return menuItemsMap.values.toList()
        }

        throw Exception("Error retrieving json")
    }
}