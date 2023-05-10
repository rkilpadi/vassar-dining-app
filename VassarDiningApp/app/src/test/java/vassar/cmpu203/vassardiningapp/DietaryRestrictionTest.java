package vassar.cmpu203.vassardiningapp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import vassar.cmpu203.vassardiningapp.model.DietaryRestriction;

public class DietaryRestrictionTest {

    @Test
    public void testGetById() {
        assertEquals(DietaryRestriction.getById("1"), DietaryRestriction.VEGETARIAN);
        assertThrows(RuntimeException.class, ()->DietaryRestriction.getById("999"));
    }
}
