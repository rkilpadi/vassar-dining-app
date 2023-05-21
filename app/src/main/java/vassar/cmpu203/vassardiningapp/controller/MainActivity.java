package vassar.cmpu203.vassardiningapp.controller;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import vassar.cmpu203.vassardiningapp.CafeBonHTTPRequest.Menu;
import vassar.cmpu203.vassardiningapp.CafeBonHTTPRequest.MenuParser;
import vassar.cmpu203.vassardiningapp.R;
import vassar.cmpu203.vassardiningapp.model.MealtimeItem;
import vassar.cmpu203.vassardiningapp.model.MealtimeMenu;
import vassar.cmpu203.vassardiningapp.model.User;
import vassar.cmpu203.vassardiningapp.view.FragFactory;
import vassar.cmpu203.vassardiningapp.view.IFavoriteView;
import vassar.cmpu203.vassardiningapp.view.IMainView;
import vassar.cmpu203.vassardiningapp.view.IMenuSelectView;
import vassar.cmpu203.vassardiningapp.view.MainView;
import vassar.cmpu203.vassardiningapp.view.ManageFavoritesFragment;
import vassar.cmpu203.vassardiningapp.view.ManageRestrictionsFragment;
import vassar.cmpu203.vassardiningapp.view.MenuSelectFragment;

public class MainActivity extends AppCompatActivity implements
        IMenuSelectView.Listener, NavigationView.OnNavigationItemSelectedListener {

    private List<MealtimeMenu> currentMenu; // All available items, even those that aren't visible
    private IMainView mainView;
    private User user;
    private ActionBarDrawerToggle drawerToggle;
    private LocalStorageFacade localStorageFacade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportFragmentManager().setFragmentFactory(new FragFactory(this));
        super.onCreate(savedInstanceState);

        localStorageFacade = new LocalStorageFacade(getFilesDir());
        user = localStorageFacade.retrieveLedger();
        if (user == null) user = new User();

        mainView = new MainView(this);
        setContentView(mainView.getRootView());

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        drawerToggle = mainView.setupActionBar();

        currentMenu = new ArrayList<>();
        MenuSelectFragment menuSelectFragment = new MenuSelectFragment(this);
        loadData("gordon", LocalDate.now(), menuSelectFragment);
        mainView.displayFragment(menuSelectFragment, false);
    }

    /**
     * Asynchronously loads menu data for a given cafe and date.
     * @param cafe the name of the cafe for the HTTP request
     * @param date the date to be converted into String format for the request
     * @param view the view where the data will be displayed
     */
    public void loadData(String cafe, LocalDate date, IMenuSelectView view) {
        CompletableFuture.supplyAsync(() -> {
            try {
                String strDate = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                return MenuParser.MenuParserMethod(cafe, strDate);
            } catch (Exception e) {
                throw new RuntimeException("Error fetching menu data", e);
            }
        }).whenCompleteAsync((result, throwable) -> {
            if (throwable == null) {
                runOnUiThread(() -> {
                    try {
                        currentMenu = result.stream().map(Menu::toMealtimeMenu).collect(Collectors.toList());
                        updateVisibleMenu(view);
                        view.refreshMenu();
                        System.out.println(currentMenu.get(0).getCafe());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            } else {
                runOnUiThread(() -> {
                    Snackbar.make(getCurrentFocus(), "Error fetching menu data", Snackbar.LENGTH_SHORT).show();
                    throwable.printStackTrace();
                });
            }
        }, ContextCompat.getMainExecutor(this));
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        localStorageFacade.saveUser(user);
    }

    /**
     * Handles the event when a favorite item is clicked in the menu.
     * @param item the MealtimeItem instance representing the clicked item
     * @param favoriteView the IFavoriteView instance where the favorite items are displayed
     */
    @Override
    public void onFavoriteClicked(MealtimeItem item, IFavoriteView favoriteView) {
        user.switchFavoriteStatus(item);
        favoriteView.updateFavoriteDisplay();
    }

    @Override
    public User getUser() {
        return user;
    }

    @Override
    public void updateVisibleMenu(IMenuSelectView view) {
        view.updateMenuItems(user.filterMenus(currentMenu));
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
            mainView.displayFragment(new MenuSelectFragment(this), true);
        } else if (id == R.id.navigation_favorites) {
            mainView.displayFragment(new ManageFavoritesFragment(this), true);
        } else if (id == R.id.navigation_restrictions) {
            mainView.displayFragment(new ManageRestrictionsFragment(user), true);
        }
        mainView.closeDrawer();
        return true;
    }
}
