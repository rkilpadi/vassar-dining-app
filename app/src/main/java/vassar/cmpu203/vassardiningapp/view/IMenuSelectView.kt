package vassar.cmpu203.vassardiningapp.view

import vassar.cmpu203.vassardiningapp.model.MealtimeMenu
import java.time.LocalDate

/**
 * Interface that defines methods for a view that displays menus and allows a user to favorite items
 */
interface IMenuSelectView : IFavoriteView {
    /**
     * Interface to notify classes of events happening to the view
     */
    interface Listener : IFavoriteView.Listener {
        /**
         * Called when the menu to be displayed has changed
         * @param view the view where the event originated
         */
        fun updateVisibleMenu(view: IMenuSelectView)

        /**
         * Called when the user requests to view a different menu
         * @param cafe the name of the cafe for the HTTP request
         * @param date the date for the HTTP request
         * @param view the view where the event originated
         */
        fun loadData(cafe: String, date: LocalDate, view: IMenuSelectView)
    }

    /**
     * Has the view display a new menu
     * @param menu the menu to be displayed
     */
    fun updateMenuItems(menu: List<MealtimeMenu>)

    /**
     * Has the view refresh the displayed menu items
     */
    fun refreshMenu()

    /**
     * Has the view refresh the menu items displayed as favorites
     */
    override fun updateFavoriteDisplay() {
        refreshMenu()
    }
}
