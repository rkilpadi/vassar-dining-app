package vassar.cmpu203.vassardiningapp.view;

import android.view.View;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.fragment.app.Fragment;

/**
 * An interface for the application screen template.
 */
public interface IMainView {

    /**
     * Retrieve the graphical widget (android view) at the root of the screen hierarchy/
     * @return the screen's root android view (widget)
     */
    View getRootView();

    ActionBarDrawerToggle setupActionBar();

    /**
     * Replaces the contents of the screen's fragment container with the one passed in as an argument.
     *
     * @param fragment The fragment to be displayed.
     * @param reversible true if this transaction should be reversible, false otherwise
     * @param name the name this transaction can be referred by.
     */
    void displayFragment(Fragment fragment, boolean reversible, String name);

}
