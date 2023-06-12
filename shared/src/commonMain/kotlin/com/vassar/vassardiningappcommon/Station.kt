package com.vassar.vassardiningappcommon

import kotlinx.serialization.Serializable

@Serializable
data class Station (
    val label: String,
    val items: List<String>
) {
    val stationItems: MutableList<MenuItem> = mutableListOf()

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("Station: ").append(label).append("\n")
        sb.append("Items: ").append(items).append("\n")
        return sb.toString()
    }
}
