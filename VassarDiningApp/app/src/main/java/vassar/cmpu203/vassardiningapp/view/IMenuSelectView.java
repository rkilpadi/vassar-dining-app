package vassar.cmpu203.vassardiningapp.view;

import java.time.LocalDate;
import java.util.List;

import vassar.cmpu203.vassardiningapp.model.MealtimeMenu;

public interface IMenuSelectView extends IFavoriteView {

    interface Listener extends IFavoriteView.Listener {

        void onMenuFieldSelected(String cafe, IMenuSelectView view);

        void updateVisibleMenu(IMenuSelectView view);

        void loadData(String cafe, LocalDate date, IMenuSelectView view);
    }

    void updateMenuItems(List<MealtimeMenu> menu);

    void refreshMenu();

    @Override
    default void updateFavoriteDisplay() {
        refreshMenu();
    }
}
