package com.vassar.vassardiningappcommon


/**
 * Represents dietary restrictions and relates them to symbols to be displayed on the menu.
 */
enum class DietaryRestriction(val restrictionId: String, val iconId: String, val label: String) {
    VEGETARIAN("1", "ic_vegetarian", "Vegetarian"),
    VEGAN("4", "ic_vegan", "Vegan"),
    GLUTEN_FREE("9", "ic_gluten", "Made without Gluten-Containing Ingredients"),
    BALANCE("7", "ic_balance", "In Balance"),
    HALAL("10", "ic_halal", "Halal"),
    KOSHER("11", "ic_kosher", "Kosher"),
    FARM("6", "ic_farm", "Farm to Fork"),
    SEAFOOD("3", "ic_seafood", "Seafood Watch"),
    HUMANE("18", "ic_humane", "Humane"),
    ORGANIC("8", "ic_organic", "Organic"),
    LOCALLY_CRAFTED("55", "ic_locally_crafted", "Locally Crafted"),
    RAW_UNDERCOOKED("228", "ic_raw", "Raw/Undercooked");

    companion object {
        /**
         * return the DietaryRestriction with a given ID, throw an error if it doesn't exist
         * @param id the ID to match with a restriction
         * @return the DietaryRestriction with the given ID
         */
        fun getById(id: String): DietaryRestriction {
            return values().find { it.restrictionId == id }
                    ?: throw RuntimeException("No known dietary restriction with that ID")
        }

        fun createArray(): List<DietaryRestriction> {
            return values().toList()
        }
    }
}
