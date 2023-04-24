package vassar.cmpu203.vassardiningapp.controller;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import vassar.cmpu203.vassardiningapp.model.Data;
import vassar.cmpu203.vassardiningapp.model.Menu;
import vassar.cmpu203.vassardiningapp.view.MainView;
import vassar.cmpu203.vassardiningapp.view.MenuSelectFragment;

public class MainActivity extends AppCompatActivity implements MenuSelectFragment.OnItemSelectedListener {

    private List<Menu> currentMenu;
    private MenuSelectFragment menuSelectFragment;
    private MainView mainView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainView = new MainView(this);
        setContentView(mainView.getRootView());
        Data.populateMenus();
        currentMenu = Data.findMenus("deece", "today");

        menuSelectFragment = new MenuSelectFragment();
        mainView.displayFragment(menuSelectFragment, false, "menu");
    }

    @Override
    public void onMenuFieldSelected(String cafe, String date) {
        currentMenu = Data.findMenus(cafe, date);
        menuSelectFragment.updateData(currentMenu);
        mainView.displayFragment(menuSelectFragment, true, "menu");
    }
}
