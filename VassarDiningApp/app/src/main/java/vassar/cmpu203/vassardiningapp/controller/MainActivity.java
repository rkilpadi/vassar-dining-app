package vassar.cmpu203.vassardiningapp.controller;

import android.os.Bundle;
import android.util.Log;
import android.widget.AdapterView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Data.populateMenus();
        currentMenu = Data.findMenu(cafe, mealtime);

        menuView = findViewById(R.id.item_recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        menuView.setLayoutManager(layoutManager);
    }

    private void populateMenuView() {
        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(currentMenu);
        menuView.setAdapter(recyclerAdapter);
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
            populateMenuView();
        } catch (Exception e) {
            Log.e("Error: menu not found", e.getMessage(), e);
        }
    }
}
