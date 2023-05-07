package vassar.cmpu203.vassardiningapp.controller;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import vassar.cmpu203.vassardiningapp.R;
import vassar.cmpu203.vassardiningapp.model.Data;
import vassar.cmpu203.vassardiningapp.model.MealtimeItem;
import vassar.cmpu203.vassardiningapp.model.MealtimeMenu;
import vassar.cmpu203.vassardiningapp.model.User;
import vassar.cmpu203.vassardiningapp.view.FragFactory;
import vassar.cmpu203.vassardiningapp.view.IMainView;
import vassar.cmpu203.vassardiningapp.view.IMenuSelectView;
import vassar.cmpu203.vassardiningapp.view.MainView;
import vassar.cmpu203.vassardiningapp.view.MenuSelectFragment;

public class MainActivity extends AppCompatActivity implements
        IMenuSelectView.Listener, NavigationView.OnNavigationItemSelectedListener {

    private List<MealtimeMenu> currentMenu;
    private List<MealtimeMenu> visibleMenu;
    private MenuSelectFragment menuSelectFragment;
    private IMainView mainView;
    private final User user = new User();
    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportFragmentManager().setFragmentFactory(new FragFactory(this));
        super.onCreate(savedInstanceState);
        mainView = new MainView(this);
        setContentView(mainView.getRootView());

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        drawerToggle = mainView.setupActionBar();

        Data.populateMenus();
        currentMenu = Data.findMenus("deece", "today");
        visibleMenu = new ArrayList<>(currentMenu);

        menuSelectFragment = new MenuSelectFragment(this);
        mainView.displayFragment(menuSelectFragment, false);
    }

    @Override
    public void onMenuFieldSelected(String cafe, String date) {
        currentMenu = Data.findMenus(cafe, date);
        updateVisibleMenu();
        mainView.displayFragment(menuSelectFragment, true);
    }

    @Override
    public void onFavorite(MealtimeItem item) {
        user.switchFavoriteStatus(item);
        menuSelectFragment.refreshFavoriteIcons();
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
                boolean validRestriction = !user.isRestrictionFiltered() || mealtimeItem.getDietaryRestrictions().containsAll(user.getDietaryRestrictions());
                if (validFavorite && validRestriction) {
                    visibleItems.add(mealtimeItem);
                }
            }
            newVisibleMenu.add(new MealtimeMenu(mealtimeMenu.getCafe(), mealtimeMenu.getDate(), mealtimeMenu.getMealtime(), visibleItems));
        }
        this.visibleMenu = newVisibleMenu;
        menuSelectFragment.updateMenuDisplay(visibleMenu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.navigation_menu) {
            mainView.displayFragment(menuSelectFragment, true);
        } else if (id == R.id.navigation_favorites) {
            System.out.println("favorites click");
        } else if (id == R.id.navigation_restrictions) {
            System.out.println("restrictions click");
        }
        item.setChecked(true);
        mainView.closeDrawer();
        return true;
    }
}
