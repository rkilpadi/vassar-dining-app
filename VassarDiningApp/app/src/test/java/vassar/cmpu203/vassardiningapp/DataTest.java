package vassar.cmpu203.vassardiningapp;

import static org.junit.jupiter.api.Assertions.*;

import vassar.cmpu203.vassardiningapp.model.Data;

public class DataTest {

    @org.junit.jupiter.api.Test
    public void testFindMenu() {
        Data.populateMenus();
        assertFalse(Data.findMenu("deece", "today").isEmpty());
    }
}
