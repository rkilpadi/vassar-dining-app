package vassar.cmpu203.vassardiningapp.controller;

import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import vassar.cmpu203.vassardiningapp.R;
import vassar.cmpu203.vassardiningapp.model.*;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private final User user = new User();

    private String cafe = "deece";
    private String mealtime = "breakfast";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Data.populateMenus();

        populateSpinner(findViewById(R.id.cafe_spinner), R.array.cafes);
        populateSpinner(findViewById(R.id.mealtime_spinner), R.array.mealtimes);
    }

    private void populateSpinner(Spinner spinner, int textArrayResId) {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, textArrayResId, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        TextView outText = findViewById(R.id.outtext);

        String itemSelected = String.valueOf(parent.getItemAtPosition(position));
        if (parent.getId() == R.id.cafe_spinner) {
            cafe = itemSelected;
        } else if (parent.getId() == R.id.mealtime_spinner) {
            mealtime = itemSelected;
        }

        try {
            Menu currentMenu = Data.findMenu(cafe, mealtime);
            outText.setText(currentMenu.toString(user));
        } catch (Exception e) {
            Log.e("Error: menu not found", e.getMessage(), e);
            outText.setText("Error: menu not found");
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {}
}
