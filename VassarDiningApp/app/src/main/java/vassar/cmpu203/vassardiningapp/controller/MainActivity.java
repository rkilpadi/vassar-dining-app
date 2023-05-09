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
import vassar.cmpu203.vassardiningapp.model.Data;
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

    private List<MealtimeMenu> currentMenu;
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
        currentMenu = new ArrayList<>();
        loadData();
    }

    private void loadData() {
        CompletableFuture.supplyAsync(() -> {
            try {
                String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                return MenuParser.MenuParserMethod("gordon", today);
            } catch (Exception e) {
                throw new RuntimeException("Error fetching menu data", e);
            }
        }).whenCompleteAsync((result, throwable) -> {
            if (throwable == null) {
                runOnUiThread(() -> {
                    try {
                        currentMenu = result.stream().map(Menu::toMealtimeMenu).collect(Collectors.toList());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    mainView.displayFragment(new MenuSelectFragment(MainActivity.this), false);
                });
            } else {
                runOnUiThread(() -> {
                    Snackbar.make(getCurrentFocus(), "Error fetching menu data", Snackbar.LENGTH_SHORT).show();
                    System.out.println("Error occurred: ");
                    throwable.printStackTrace();
                });
            }
        }, ContextCompat.getMainExecutor(this));

        System.out.println("After ");
    }

    @Override
    public void onMenuFieldSelected(String cafe, String date, IMenuSelectView view) {
        updateVisibleMenu(view);
        view.refreshMenu();
    }

    @Override
    public void onFavoriteClicked(MealtimeItem item, IFavoriteView favoriteView) {
        user.switchStatus(item, user.getFavorites());
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
