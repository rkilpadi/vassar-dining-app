package vassar.cmpu203.vassardiningapp.controller;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import vassar.cmpu203.vassardiningapp.R;
import vassar.cmpu203.vassardiningapp.model.Data;
import vassar.cmpu203.vassardiningapp.model.Menu;
import vassar.cmpu203.vassardiningapp.view.MenuSelectFragment;

public class MainActivity extends AppCompatActivity implements MenuSelectFragment.OnItemSelectedListener {

    private List<Menu> currentMenu;
    private MenuSelectFragment menuSelectFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Data.populateMenus();
        currentMenu = Data.findMenu("deece", "today");

        menuSelectFragment = new MenuSelectFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.menu_select_fragment_container, menuSelectFragment)
                .commit();
    }

    @Override
    public void onMenuFieldSelected(String cafe, String date) {
        currentMenu = Data.findMenu(cafe, date);
        if (currentMenu.isEmpty()) {
            Toast.makeText(this, "no menu to display", Toast.LENGTH_SHORT).show();
        }
        menuSelectFragment.updateData(currentMenu);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.menu_select_fragment_container, menuSelectFragment)
                .commit();

    }
}
