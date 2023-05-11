package vassar.cmpu203.vassardiningapp.view;

import java.time.LocalDate;
import java.util.List;

import vassar.cmpu203.vassardiningapp.model.MealtimeMenu;

/**
 * Interface that defines methods for a view that displays menus and allows a user to favorite items
 */
public interface IMenuSelectView extends IFavoriteView {

    /**
     * Interface to notify classes of events happening to the view
     */
    interface Listener extends IFavoriteView.Listener {

        /**
         * Called when the menu to be displayed has changed
         * @param view the view where the event originated
         */
        void updateVisibleMenu(IMenuSelectView view);

        /**
         * Called when the user requests to view a different menu
         * @param cafe the name of the cafe for the HTTP request
         * @param date the date for the HTTP request
         * @param view the view where the event originated
         */
        void loadData(String cafe, LocalDate date, IMenuSelectView view);
    }

    /**
     * Has the view display a new menu
     * @param menu the menu to be displayed
     */
    void updateMenuItems(List<MealtimeMenu> menu);

    /**
     * Has the view refresh the displayed menu items
     */
    void refreshMenu();

    /**
     * Has the view refresh the menu items displayed as favorites
     */
    @Override
    default void updateFavoriteDisplay() {
        refreshMenu();
    }
}
