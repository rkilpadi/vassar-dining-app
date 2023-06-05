package com.vassar.vassardiningappcommon

import kotlinx.serialization.Serializable

/** Represents the set of MenuItems at a given cafe at a given mealtime. */
@Serializable
data class MealtimeMenu(
        val cafe: String,
        val date: String,
        val label: String,
        val menuItems: List<MealtimeItem>
)
