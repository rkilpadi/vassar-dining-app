package vassar.cmpu203.vassardiningapp.model

import java.io.Serializable
import java.time.LocalDate

/**
 * Represents a user, containing all data that is variable between different users, such as favorite items.
 */
class User : Serializable {
    val favorites: MutableSet<MealtimeItem> = HashSet()
    var dietaryRestrictions: MutableSet<DietaryRestriction> = HashSet()
    var isFavoriteFiltered = false
    var isRestrictionFiltered = false
    // var date: LocalDate? = null

    /**
     * Either favorites or unfavorites an item by adding or removing it from the favorites map
     * @param item the item to either add or remove from the map
     */
    fun switchFavoriteStatus(item: MealtimeItem) {
        if (item in favorites) {
            favorites.remove(item)
        } else {
            favorites.add(item)
        }
    }

    /**
     * Adds or removes a restriction from the restrictions set
     * @param restriction the restriction to either add or remove from the map
     */
    fun switchRestrictionStatus(restriction: DietaryRestriction) {
        if (restriction in dietaryRestrictions) {
            dietaryRestrictions.remove(restriction)
        } else {
            dietaryRestrictions.add(restriction)
        }
    }

    fun toggleFavoriteFilter() {
        isFavoriteFiltered = !isFavoriteFiltered
    }

    fun toggleRestrictionFilter() {
        isRestrictionFiltered = !isRestrictionFiltered
    }

    /**
     * Checks whether an item's restrictions comply with this user's restrictions,
     * taking into account restrictions that are implied
     * @param restrictionsToMatch the item's restrictions to match
     * @return whether or not the item matches this user's restrictions
     */
    fun matchRestriction(restrictionsToMatch: MutableSet<DietaryRestriction>): Boolean {
        val effectiveRestrictions = restrictionsToMatch.toMutableSet()
        if (DietaryRestriction.VEGAN in effectiveRestrictions) {
            effectiveRestrictions += DietaryRestriction.KOSHER
            effectiveRestrictions += DietaryRestriction.VEGETARIAN
            effectiveRestrictions += DietaryRestriction.HALAL
        } else if (DietaryRestriction.VEGETARIAN in effectiveRestrictions) {
            effectiveRestrictions += DietaryRestriction.HALAL
        }
        return effectiveRestrictions.containsAll(dietaryRestrictions)
    }

    /**
     * Filter menus by items that are visible based on this user's preferences
     * @param menus the menus consisting of all items that are available
     * @return menus containing only items that are visible
     */
    fun filterMenus(menus: List<MealtimeMenu>) = menus.map { menu ->
        val visibleItems = menu.menuItems.filter { item ->
            (!isFavoriteFiltered || item in favorites) &&
                    (!isRestrictionFiltered || matchRestriction(item.dietaryRestrictions))
        }
        MealtimeMenu(menu.cafe, menu.date, menu.label, visibleItems)
    }
}
