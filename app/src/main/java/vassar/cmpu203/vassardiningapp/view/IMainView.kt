package vassar.cmpu203.vassardiningapp.view

import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.fragment.app.Fragment

/**
 * An interface for the application screen template.
 */
interface IMainView {
    /**
     * Retrieve the graphical widget (android view) at the root of the screen hierarchy/
     * @return the screen's root android view (widget)
     */
    val rootView: View

    /**
     * Sets up an ActionBar with a drawer in an activity
     * @return An ActionBarDrawerToggle for the drawer
     */
    fun setupActionBar(): ActionBarDrawerToggle

    /**
     * Closes the drawer in the activity
     */
    fun closeDrawer()

    /**
     * Replaces the contents of the screen's fragment container with the one passed in as an argument.
     *
     * @param fragment The fragment to be displayed.
     * @param reversible true if this transaction should be reversible, false otherwise
     */
    fun displayFragment(fragment: Fragment, reversible: Boolean)
}
