package vassar.cmpu203.vassardiningapp.controller;

import android.os.Bundle;
import android.util.Log;
import android.widget.AdapterView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import vassar.cmpu203.vassardiningapp.R;
import vassar.cmpu203.vassardiningapp.model.Data;
import vassar.cmpu203.vassardiningapp.model.Menu;
import vassar.cmpu203.vassardiningapp.model.User;
import vassar.cmpu203.vassardiningapp.view.MenuSelectFragment;

public class MainActivity extends AppCompatActivity implements MenuSelectFragment.OnItemSelectedListener {

    private final User user = new User();
    private Menu currentMenu;
    private String cafe = "deece";
    private String mealtime = "breakfast";
    private RecyclerView menuView;
    private MenuSelectFragment menuSelectFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Data.populateMenus();
        currentMenu = Data.findMenu(cafe, mealtime);

        menuSelectFragment = new MenuSelectFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.menu_select_fragment_container, menuSelectFragment);
        ft.commit();
    }

    @Override
    public void onMenuFieldSelected(AdapterView<?> parent, int position) {

        String itemSelected = String.valueOf(parent.getItemAtPosition(position));
        if (parent.getId() == R.id.cafe_spinner) {
            cafe = itemSelected;
        } else if (parent.getId() == R.id.mealtime_spinner) {
            mealtime = itemSelected;
        }

        try {
            currentMenu = Data.findMenu(cafe, mealtime);
            menuSelectFragment.updateData(currentMenu);
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.menu_select_fragment_container, menuSelectFragment)
                    //.addToBackStack(null)
                    .commit();
        } catch (IllegalArgumentException e) {
            Log.e("Error: menu not found", e.getMessage(), e);
        }
    }
}
