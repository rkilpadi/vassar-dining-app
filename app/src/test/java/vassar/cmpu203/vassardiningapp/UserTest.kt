package vassar.cmpu203.vassardiningapp

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import vassar.cmpu203.vassardiningapp.model.DietaryRestriction
import vassar.cmpu203.vassardiningapp.model.MealtimeItem
import vassar.cmpu203.vassardiningapp.model.MealtimeMenu
import vassar.cmpu203.vassardiningapp.model.User

class UserTest internal constructor() {
    private var user: User = User()
    private var testItem: MealtimeItem
    private var restrictions: MutableSet<DietaryRestriction> =
            HashSet(listOf(DietaryRestriction.VEGETARIAN, DietaryRestriction.KOSHER))

    init {
        user.dietaryRestrictions = restrictions
        testItem = MealtimeItem("test", "123", "test desc", "", restrictions)
    }

    @Test
    fun testSwitchStatus() {
        user.switchFavoriteStatus(testItem)
        Assertions.assertTrue(user.favorites.containsValue(testItem))
        user.switchFavoriteStatus(testItem)
        Assertions.assertFalse(user.favorites.containsValue(testItem))
        Assertions.assertTrue(user.dietaryRestrictions.contains(DietaryRestriction.VEGETARIAN))
        user.switchRestrictionStatus(DietaryRestriction.VEGETARIAN)
        Assertions.assertFalse(user.dietaryRestrictions.contains(DietaryRestriction.VEGETARIAN))
    }

    @Test
    fun testMatchRestriction() {
        Assertions.assertTrue(user.matchRestriction(restrictions))
        Assertions.assertTrue(user.matchRestriction(HashSet(listOf(DietaryRestriction.VEGAN))))
        Assertions.assertFalse(user.matchRestriction(HashSet(listOf(DietaryRestriction.KOSHER))))
        Assertions.assertFalse(user.matchRestriction(HashSet(listOf(DietaryRestriction.GLUTEN_FREE, DietaryRestriction.KOSHER))))
    }

    @Test
    fun testFilterMenus() {
        val menus: MutableList<MealtimeMenu> = ArrayList()
        menus.add(MealtimeMenu("cafe", "date", "mealtime", ArrayList(listOf(testItem))))
        user.toggleFavoriteFilter()
        Assertions.assertFalse(user.filterMenus(menus)[0].menuItems.contains(testItem))
        user.switchFavoriteStatus(testItem)
        Assertions.assertTrue(user.filterMenus(menus)[0].menuItems.contains(testItem))
    }
}