package vassar.cmpu203.vassardiningapp.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.MenuProvider;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import vassar.cmpu203.vassardiningapp.R;
import vassar.cmpu203.vassardiningapp.databinding.FragmentMenuSelectBinding;
import vassar.cmpu203.vassardiningapp.model.MealtimeMenu;

public class MenuSelectFragment extends Fragment implements IMenuSelectView, MenuProvider {

    private final Listener listener;
    private FragmentMenuSelectBinding binding;
    private ExpandableMealtimeAdapter itemsAdapter;

    public MenuSelectFragment(Listener listener) {
        this.listener = listener;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup parent, @Nullable Bundle savedInstanceState) {
        binding = FragmentMenuSelectBinding.inflate(inflater);
        requireActivity().addMenuProvider(this, getViewLifecycleOwner(), Lifecycle.State.RESUMED);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        binding.itemRecycler.setLayoutManager(layoutManager);

        populateSpinner(view, binding.cafeSpinner, R.array.cafes);
        populateSpinner(view, binding.dateSpinner, R.array.dates);
    }

    @Override
    public void updateMenuDisplay(List<MealtimeMenu> menu) {
        if (menu.isEmpty()) {
            Snackbar.make(binding.getRoot(), "Menu not found", Snackbar.LENGTH_SHORT).show();
        }
        if (itemsAdapter == null) {
            itemsAdapter = new ExpandableMealtimeAdapter(menu, getContext(), listener);
            binding.itemRecycler.setAdapter(itemsAdapter);
        } else {
            itemsAdapter.setMenus(menu);
        }
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

    @Override
    public void onCreateMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
        menuInflater.inflate(R.menu.main_action_bar, menu);
    }

    @Override
    public boolean onMenuItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.favorite_filter_button) {
            listener.getUser().toggleFavoriteFilter();
            item.setIcon(listener.getUser().isFavoriteFiltered() ? R.drawable.ic_white_filled_heart : R.drawable.ic_white_empty_heart);
            listener.updateVisibleMenu();
            return true;
        } else if (id == R.id.apply_filter_button) {
            listener.getUser().toggleRestrictionFilter();
            item.setIcon(listener.getUser().isRestrictionFiltered() ? R.drawable.ic_filled_dining : R.drawable.ic_empty_dining);
            listener.updateVisibleMenu();
        }
        return false;
    }
}
