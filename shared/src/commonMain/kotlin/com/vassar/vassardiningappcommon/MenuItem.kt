package com.vassar.vassardiningappcommon

import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.MapSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.json.*

@Serializable
data class MenuItem (
    val label: String? = null,
    val description: String? = null,
    @Serializable(with = NullableMapSerializer::class)
    val cor_icon: Map<String, String>? = null,
    val tier: Int = 0
) {

    //public Map<String, MenuItem> IdMapper{}
    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("Menu Item: ").append(label).append("\n")
        //sb.append("ID: ").append(id).append("\n");
        sb.append("Description: ").append(description).append("\n")
        sb.append("Tier: ").append(tier).append("\n")
//        sb.append("Station: ").append(this.getStation()).append("\n")
        sb.append("Dietary Restrictions: ").append("\n")
        cor_icon?.let { cor_icon.forEach{ (_, value) -> sb.append(value).append("\n") } }
        return sb.toString()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is MenuItem) return false

        if (label != other.label) return false
        if (cor_icon != other.cor_icon) return false
        if (tier != other.tier) return false

        return true
    }
}

object NullableMapSerializer : JsonTransformingSerializer<Map<String, String>>(MapSerializer(String.serializer(), String.serializer())) {
    override fun transformDeserialize(element: JsonElement): JsonElement {
        return if (element is JsonArray) JsonObject(emptyMap()) else element
    }
}