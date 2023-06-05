package vassar.cmpu203.vassardiningapp.view

import com.vassar.vassardiningappcommon.MealtimeItem
import vassar.cmpu203.vassardiningapp.model.User

/**
 * Interface that defines methods for a view that allows users to favorite items
 */
interface IFavoriteView {
    /**
     * Interface to notify classes of events happening in the view
     */
    interface Listener {
        // User whose favorites are to be modified
        val user: User

        /**
         * Called when an item is favorited or unfavorited
         * @param item the item that has been updated
         * @param view the view where the event originated
         */
        fun onFavoriteClicked(item: MealtimeItem, view: IFavoriteView)
    }

    /**
     * Has the view refresh items to display the correct favorite status if necessary
     */
    fun updateFavoriteDisplay() {}
}
