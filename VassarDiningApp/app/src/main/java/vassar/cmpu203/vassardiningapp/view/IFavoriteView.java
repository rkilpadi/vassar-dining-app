package vassar.cmpu203.vassardiningapp.view;

import vassar.cmpu203.vassardiningapp.model.MealtimeItem;
import vassar.cmpu203.vassardiningapp.model.User;

/**
 * Interface that defines methods for a view that allows users to favorite items
 */
public interface IFavoriteView {

    /**
     * Interface to notify classes of events happening in the view
     */
    interface Listener {

        /**
         * Called when an item is favorited or unfavorited
         * @param item the item that has been updated
         * @param view the view where the event originated
         */
        void onFavoriteClicked(MealtimeItem item, IFavoriteView view);

        /**
         * @return the user whose favorites are to be updated
         */
        User getUser();
    }

    /**
     * Has the view refresh items to display the correct favorite status if necessary
     */
    default void updateFavoriteDisplay() {}
}
