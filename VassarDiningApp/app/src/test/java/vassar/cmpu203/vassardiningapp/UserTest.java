package vassar.cmpu203.vassardiningapp;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import vassar.cmpu203.vassardiningapp.model.MealtimeItem;
import vassar.cmpu203.vassardiningapp.model.User;

public class UserTest {
    User user;
    MealtimeItem testItem;

    UserTest() {
        user = new User();
        testItem = new MealtimeItem("test", "test desc", new ArrayList<>());
    }

    @Test
    public void testSwitchFavoriteStatus() {
        user.switchFavoriteStatus(testItem);
        assertTrue(user.getFavorites().contains(testItem));
        user.switchFavoriteStatus(testItem);
        assertFalse(user.getFavorites().contains(testItem));
    }
}
