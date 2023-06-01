package vassar.cmpu203.vassardiningapp.model

import java.io.Serializable

/** Represents a food item that could appear on multiple menus. */
data class MealtimeItem(
        val name: String,
        val id: String,
        val description: String,
        val station: String,
        val dietaryRestrictions: MutableSet<DietaryRestriction>
) : Serializable {

    override fun equals(other: Any?): Boolean {
        if (other == null) return false
        return if (javaClass != other.javaClass) false else id == (other as MealtimeItem).id
    }
}
