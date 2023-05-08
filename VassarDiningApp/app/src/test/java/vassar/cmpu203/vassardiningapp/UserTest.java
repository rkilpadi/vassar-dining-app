package vassar.cmpu203.vassardiningapp;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import java.util.HashSet;

import vassar.cmpu203.vassardiningapp.model.MealtimeItem;
import vassar.cmpu203.vassardiningapp.model.User;

public class UserTest {
    User user;
    MealtimeItem testItem;

    UserTest() {
        user = new User();
        testItem = new MealtimeItem("test", "test desc", new HashSet<>());
    }

    @Test
    public void testSwitchFavoriteStatus() {
        user.switchStatus(testItem, user.getFavorites());
        assertTrue(user.getFavorites().contains(testItem));
        user.switchStatus(testItem, user.getFavorites());
        assertFalse(user.getFavorites().contains(testItem));
    }
}
