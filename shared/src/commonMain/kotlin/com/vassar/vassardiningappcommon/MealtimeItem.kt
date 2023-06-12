package com.vassar.vassardiningappcommon

import kotlinx.serialization.Serializable

/** Represents a food item that could appear on multiple menus. */
@Serializable
data class MealtimeItem(
        val name: String,
        val id: String,
        val itemDescription: String,
        val station: String,
        val dietaryRestrictions: MutableSet<DietaryRestriction>
) {

    override fun equals(other: Any?): Boolean {
        if (other == null) return false
        return (id == (other as MealtimeItem).id) && (dietaryRestrictions == other.dietaryRestrictions)
    }
}
