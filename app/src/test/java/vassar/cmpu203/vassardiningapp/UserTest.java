package vassar.cmpu203.vassardiningapp;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import vassar.cmpu203.vassardiningapp.model.DietaryRestriction;
import vassar.cmpu203.vassardiningapp.model.MealtimeItem;
import vassar.cmpu203.vassardiningapp.model.MealtimeMenu;
import vassar.cmpu203.vassardiningapp.model.User;

public class UserTest {
    User user;
    Set<DietaryRestriction> restrictions;
    MealtimeItem testItem;

    UserTest() {
        user = new User();
        restrictions = new HashSet<>(List.of(DietaryRestriction.VEGETARIAN, DietaryRestriction.KOSHER));
        user.setDietaryRestrictions(restrictions);
        testItem = new MealtimeItem("test", "123","test desc", "", restrictions);
    }

    @Test
    public void testSwitchStatus() {
        user.switchFavoriteStatus(testItem);
        assertTrue(user.getFavorites().containsValue(testItem));
        user.switchFavoriteStatus(testItem);
        assertFalse(user.getFavorites().containsValue(testItem));
        assertTrue(user.getDietaryRestrictions().contains(DietaryRestriction.VEGETARIAN));
        user.switchRestrictionStatus(DietaryRestriction.VEGETARIAN);
        assertFalse(user.getDietaryRestrictions().contains(DietaryRestriction.VEGETARIAN));
    }

    @Test
    public void testMatchRestriction() {
        assertTrue(user.matchRestriction(restrictions));
        assertTrue(user.matchRestriction(new HashSet<>(List.of(DietaryRestriction.VEGAN))));
        assertFalse(user.matchRestriction(new HashSet<>(List.of(DietaryRestriction.KOSHER))));
        assertFalse(user.matchRestriction(new HashSet<>(List.of(DietaryRestriction.GLUTEN_FREE, DietaryRestriction.KOSHER))));
    }

    @Test
    public void testFilterMenus() {
        List<MealtimeMenu> menus = new ArrayList<>();
        menus.add(new MealtimeMenu("cafe", "date", "mealtime", new ArrayList<>(List.of(testItem))));
        user.toggleFavoriteFilter();
        assertFalse(user.filterMenus(menus).get(0).getMenuItems().contains(testItem));
        user.switchFavoriteStatus(testItem);
        assertTrue(user.filterMenus(menus).get(0).getMenuItems().contains(testItem));
    }
}
