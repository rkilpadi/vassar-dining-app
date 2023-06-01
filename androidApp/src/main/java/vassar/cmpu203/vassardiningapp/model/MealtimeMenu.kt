package vassar.cmpu203.vassardiningapp.model

import java.io.Serializable

/** Represents the set of MenuItems at a given cafe at a given mealtime. */
data class MealtimeMenu(
        val cafe: String,
        val date: String,
        val label: String,
        val menuItems: List<MealtimeItem>
) : Serializable
