package vassar.cmpu203.vassardiningapp;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import vassar.cmpu203.vassardiningapp.controller.MainActivity;

@RunWith(AndroidJUnit4.class)
public class MenuSpinnersTest {

    @org.junit.Rule
    public ActivityScenarioRule<MainActivity> activityRule = new ActivityScenarioRule<>(MainActivity.class);

    // Verifies that the spinners have the correct default values
    @Test
    public void testMenuSpinners() {
        ViewInteraction cafeSpinnerVI = Espresso.onView(ViewMatchers.withId(R.id.cafe_spinner));

        cafeSpinnerVI.check(ViewAssertions.matches(ViewMatchers.withSpinnerText(R.string.default_cafe)));
    }
}
