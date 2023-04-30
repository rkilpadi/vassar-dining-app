package vassar.cmpu203.vassardiningapp.view;

import java.util.List;

import vassar.cmpu203.vassardiningapp.model.Menu;
import vassar.cmpu203.vassardiningapp.model.MenuItem;

public interface IMenuSelectView {

    interface Listener {

        void onMenuFieldSelected(String cafe, String mealtime);

        void onFavorite(MenuItem item);
    }

    void updateMenuDisplay(List<Menu> menu);
}
