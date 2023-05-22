package vassar.cmpu203.vassardiningapp

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import vassar.cmpu203.vassardiningapp.model.DietaryRestriction
import vassar.cmpu203.vassardiningapp.model.DietaryRestriction.Companion.getById

class DietaryRestrictionTest {
    @Test
    fun testGetById() {
        Assertions.assertEquals(getById("1"), DietaryRestriction.VEGETARIAN)
        Assertions.assertThrows(RuntimeException::class.java) { getById("999") }
    }
}