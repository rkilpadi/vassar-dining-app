package vassar.cmpu203.vassardiningapp.view;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import vassar.cmpu203.vassardiningapp.R;
import vassar.cmpu203.vassardiningapp.controller.RecyclerAdapter;
import vassar.cmpu203.vassardiningapp.model.Menu;

public class MenuSelectFragment extends Fragment {

    private RecyclerView menuView;
    Spinner cafeSpinner, mealtimeSpinner;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup parent, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_menu_select, parent, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
       cafeSpinner = view.findViewById(R.id.cafe_spinner);
       mealtimeSpinner = view.findViewById(R.id.mealtime_spinner);
       populateSpinner(view, cafeSpinner, R.array.cafes);
       populateSpinner(view, mealtimeSpinner, R.array.mealtimes);

        menuView = view.findViewById(R.id.item_recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        menuView.setLayoutManager(layoutManager);
    }

    public void updateData(Menu menu) {
        RecyclerAdapter adapter = new RecyclerAdapter(menu);
        menuView.setAdapter(adapter);
    }

    private void populateSpinner(View view, Spinner spinner, int textArrayResId) {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(view.getContext(), textArrayResId, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                listener.onMenuFieldSelected(
                        cafeSpinner.getSelectedItem().toString(),
                        mealtimeSpinner.getSelectedItem().toString()
                );
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    private OnItemSelectedListener listener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.listener = (OnItemSelectedListener) context;
    }

    public interface OnItemSelectedListener {
        void onMenuFieldSelected(String cafe, String mealtime);
    }
}
