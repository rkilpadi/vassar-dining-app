package vassar.cmpu203.vassardiningapp.model

import vassar.cmpu203.vassardiningapp.R

/**
 * Represents dietary restrictions and relates them to symbols to be displayed on the menu.
 */
enum class DietaryRestriction(private val restrictionId: String, val iconId: Int, val label: String) {
    VEGETARIAN("1", R.drawable.ic_vegetarian, "Vegetarian"),
    VEGAN("4", R.drawable.ic_vegan, "Vegan"),
    GLUTEN_FREE("9", R.drawable.ic_gluten, "Made without Gluten-Containing Ingredients"),
    BALANCE("7", R.drawable.ic_balance, "In Balance"),
    HALAL("10", R.drawable.ic_halal, "Halal"),
    KOSHER("11", R.drawable.ic_kosher, "Kosher"),
    FARM("6", R.drawable.ic_farm, "Farm to Fork"),
    SEAFOOD("3", R.drawable.ic_seafood, "Seafood Watch"),
    HUMANE("18", R.drawable.ic_humane, "Humane"),
    ORGANIC("8", R.drawable.ic_organic, "Organic");

    companion object {
        /**
         * return the DietaryRestriction with a given ID, throw an error if it doesn't exist
         * @param id the ID to match with a restriction
         * @return the DietaryRestriction with the given ID
         */
        @JvmStatic
        fun getById(id: String): DietaryRestriction {
            return values().find { it.restrictionId == id }
                    ?: throw RuntimeException("No known dietary restriction with that ID")
        }
    }
}
