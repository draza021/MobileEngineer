
/**
 * Created by Drago on 6/30/2017.
 */

package com.development.id.ns.mobileengineer;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.development.id.ns.mobileengineer.activity.MainActivity;
import com.development.id.ns.mobileengineer.helper.RecyclerViewMatcher;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;


@RunWith(AndroidJUnit4.class)
public class DemoItemTest extends ActivityTestRule<MainActivity> {

    public DemoItemTest() {
        super(MainActivity.class);
    }

    @Rule
    public final ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void shouldBeAbleToClickOnItemAndShowDetailsActivity() {
        clickOnItemInARecyclerViewAtGivenPosition(R.id.recycler_view, 0);
        checkIfIdDisplayed(R.id.tv_details_description);
        sleep(2000);
    }

    public void clickOnItemInARecyclerViewAtGivenPosition(int recyclerViewID, int position) {
        onView(withRecyclerView(recyclerViewID).atPosition(position)).perform(click());
    }

    private void checkIfIdDisplayed(int id){
        onView(withId(id)).check(matches(isDisplayed()));
    }

    public static RecyclerViewMatcher withRecyclerView(final int recyclerViewId) {
        return new RecyclerViewMatcher(recyclerViewId);
    }

    private void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
