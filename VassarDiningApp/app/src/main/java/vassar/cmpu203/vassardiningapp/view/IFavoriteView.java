package vassar.cmpu203.vassardiningapp.view;

import vassar.cmpu203.vassardiningapp.model.MealtimeItem;
import vassar.cmpu203.vassardiningapp.model.User;

public interface IFavoriteView {

    interface Listener {

        void onFavoriteClicked(MealtimeItem item, IFavoriteView view);

        User getUser();
    }

    default void updateFavoriteDisplay() {}
}
