package vassar.cmpu203.vassardiningapp.controller;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import vassar.cmpu203.vassardiningapp.model.Data;
import vassar.cmpu203.vassardiningapp.model.MealtimeItem;
import vassar.cmpu203.vassardiningapp.model.MealtimeMenu;
import vassar.cmpu203.vassardiningapp.model.User;
import vassar.cmpu203.vassardiningapp.view.FragFactory;
import vassar.cmpu203.vassardiningapp.view.IMainView;
import vassar.cmpu203.vassardiningapp.view.IMenuSelectView;
import vassar.cmpu203.vassardiningapp.view.MainView;
import vassar.cmpu203.vassardiningapp.view.MenuSelectFragment;

public class MainActivity extends AppCompatActivity implements IMenuSelectView.Listener {

    private List<MealtimeMenu> currentMenu;
    private List<MealtimeMenu> visibleMenu;
    private MenuSelectFragment menuSelectFragment;
    private IMainView mainView;
    private final User user = new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportFragmentManager().setFragmentFactory(new FragFactory(this));
        super.onCreate(savedInstanceState);
        mainView = new MainView(this);
        setContentView(mainView.getRootView());

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        Data.populateMenus();
        currentMenu = Data.findMenus("deece", "today");
        visibleMenu = new ArrayList<>(currentMenu);

        menuSelectFragment = new MenuSelectFragment(this);
        mainView.displayFragment(menuSelectFragment, false, "menu");
    }

    @Override
    public void onMenuFieldSelected(String cafe, String date) {
        currentMenu = Data.findMenus(cafe, date);
        updateVisibleMenu();
        mainView.displayFragment(menuSelectFragment, true, "menu");
    }

    @Override
    public void onFavorite(MealtimeItem item) {
        user.switchFavoriteStatus(item);
    }

    @Override
    public User getUser() {
        return user;
    }

    @Override
    public void updateVisibleMenu() {
        List<MealtimeMenu> newVisibleMenu = new ArrayList<>();
        for (MealtimeMenu mealtimeMenu : currentMenu)  {
            List<MealtimeItem> visibleItems = new ArrayList<>();
            for (MealtimeItem mealtimeItem : mealtimeMenu.getMenuItems()) {
                boolean validFavorite = !user.isFavoriteFiltered() || user.getFavorites().contains(mealtimeItem);
                boolean validRestriction = !user.isRestrictionFiltered() || Collections.disjoint(user.getDietaryRestrictions(), mealtimeItem.getDietaryRestrictions());
                if (validFavorite && validRestriction) {
                    visibleItems.add(mealtimeItem);
                }
            }
            newVisibleMenu.add(new MealtimeMenu(mealtimeMenu.getCafe(), mealtimeMenu.getDate(), mealtimeMenu.getMealtime(), visibleItems));
        }
        this.visibleMenu = newVisibleMenu;
        menuSelectFragment.updateMenuDisplay(visibleMenu);
    }
}
