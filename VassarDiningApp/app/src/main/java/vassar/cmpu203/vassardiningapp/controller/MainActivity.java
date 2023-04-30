package vassar.cmpu203.vassardiningapp.controller;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import vassar.cmpu203.vassardiningapp.model.Data;
import vassar.cmpu203.vassardiningapp.model.Menu;
import vassar.cmpu203.vassardiningapp.model.MenuItem;
import vassar.cmpu203.vassardiningapp.model.User;
import vassar.cmpu203.vassardiningapp.view.FragFactory;
import vassar.cmpu203.vassardiningapp.view.IMainView;
import vassar.cmpu203.vassardiningapp.view.IMenuSelectView;
import vassar.cmpu203.vassardiningapp.view.MainView;
import vassar.cmpu203.vassardiningapp.view.MenuSelectFragment;

public class MainActivity extends AppCompatActivity implements IMenuSelectView.Listener {

    private List<Menu> currentMenu;
    private MenuSelectFragment menuSelectFragment;
    private IMainView mainView;
    private final User user = new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportFragmentManager().setFragmentFactory(new FragFactory(this));
        super.onCreate(savedInstanceState);
        mainView = new MainView(this);
        setContentView(mainView.getRootView());
        Data.populateMenus();
        currentMenu = Data.findMenus("deece", "today");

        menuSelectFragment = new MenuSelectFragment(this);
        mainView.displayFragment(menuSelectFragment, false, "menu");
    }

    @Override
    public void onMenuFieldSelected(String cafe, String date) {
        currentMenu = Data.findMenus(cafe, date);
        menuSelectFragment.updateMenuDisplay(currentMenu);
        mainView.displayFragment(menuSelectFragment, true, "menu");
    }

    @Override
    public void onFavorite(MenuItem item) {
        user.switchFavoriteStatus(item);
    }
}
