package vassar.cmpu203.vassardiningapp.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import vassar.cmpu203.vassardiningapp.R;
import vassar.cmpu203.vassardiningapp.databinding.FragmentMenuSelectBinding;
import vassar.cmpu203.vassardiningapp.model.Menu;

public class MenuSelectFragment extends Fragment {

    FragmentMenuSelectBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup parent, @Nullable Bundle savedInstanceState) {
        binding = FragmentMenuSelectBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        binding.itemRecycler.setLayoutManager(layoutManager);

        populateSpinner(view, binding.cafeSpinner, R.array.cafes);
        populateSpinner(view, binding.dateSpinner, R.array.dates);
    }

    public void updateData(List<Menu> menu) {
        Snackbar menuNotFound = Snackbar.make(binding.getRoot(), "Menu not found", Snackbar.LENGTH_SHORT);
        if (menu.isEmpty()) menuNotFound.show();

        ExpandableAdapter itemsAdapter = new ExpandableAdapter(menu, getContext());
        binding.itemRecycler.setAdapter(itemsAdapter);
    }

    private void populateSpinner(View view, Spinner spinner, int textArrayResId) {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(view.getContext(), textArrayResId, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                listener.onMenuFieldSelected(
                        binding.cafeSpinner.getSelectedItem().toString(),
                        binding.dateSpinner.getSelectedItem().toString()
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
