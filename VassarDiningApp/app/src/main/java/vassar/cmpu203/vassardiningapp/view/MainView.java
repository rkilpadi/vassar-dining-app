package vassar.cmpu203.vassardiningapp.view;

import android.view.View;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;

import vassar.cmpu203.vassardiningapp.R;
import vassar.cmpu203.vassardiningapp.databinding.ActivityMainBinding;

public class MainView implements IMainView {

    private final FragmentActivity activity;
    private DrawerLayout drawerLayout;
    private final ActivityMainBinding binding;

    public MainView(FragmentActivity activity) {
        this.activity = activity;
        binding = ActivityMainBinding.inflate(activity.getLayoutInflater());
    }

    @Override
    public View getRootView() {
        return binding.getRoot();
    }

    @Override
    public ActionBarDrawerToggle setupActionBar() {
        NavigationView navView = binding.drawer;
        drawerLayout = binding.drawerLayout;
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(activity, drawerLayout, R.string.drawer_open, R.string.drawer_close);
        drawerLayout.addDrawerListener(drawerToggle);

        drawerToggle.syncState();
        navView.setNavigationItemSelectedListener((NavigationView.OnNavigationItemSelectedListener) activity);
        return drawerToggle;
    }

    @Override
    public void closeDrawer() {
       drawerLayout.closeDrawer(GravityCompat.START);
    }

    @Override
    public void displayFragment(Fragment fragment, boolean reversible) {
        FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction()
                .replace(binding.fragmentContainer.getId(), fragment);

        if (reversible) ft.addToBackStack(null);
        ft.commit();
    }
}
