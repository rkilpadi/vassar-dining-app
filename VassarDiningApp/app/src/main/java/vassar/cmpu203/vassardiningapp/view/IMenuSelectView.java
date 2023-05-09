package vassar.cmpu203.vassardiningapp.view;

import java.io.IOException;
import java.util.List;

import vassar.cmpu203.vassardiningapp.model.MealtimeMenu;

public interface IMenuSelectView extends IFavoriteView {

    interface Listener extends IFavoriteView.Listener {

        void onMenuFieldSelected(String cafe, String mealtime, IMenuSelectView view) throws IOException;

        void updateVisibleMenu(IMenuSelectView view);
    }

    void updateMenuItems(List<MealtimeMenu> menu);

    void refreshMenu();

    @Override
    default void updateFavoriteDisplay() {
        refreshMenu();
    }
}
