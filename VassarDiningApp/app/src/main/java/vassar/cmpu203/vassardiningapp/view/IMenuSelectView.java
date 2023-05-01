package vassar.cmpu203.vassardiningapp.view;

import java.util.List;

import vassar.cmpu203.vassardiningapp.model.MealtimeMenu;
import vassar.cmpu203.vassardiningapp.model.MealtimeItem;
import vassar.cmpu203.vassardiningapp.model.User;

public interface IMenuSelectView {

    interface Listener {

        void onMenuFieldSelected(String cafe, String mealtime);

        void onFavorite(MealtimeItem item);

        void updateVisibleMenu();

        User getUser();
    }

    void updateMenuDisplay(List<MealtimeMenu> menu);
}
