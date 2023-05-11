package vassar.cmpu203.vassardiningapp.view;

import android.annotation.SuppressLint;
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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import vassar.cmpu203.vassardiningapp.R;
import vassar.cmpu203.vassardiningapp.databinding.FragmentMenuSelectBinding;
import vassar.cmpu203.vassardiningapp.model.MealtimeMenu;

public class MenuSelectFragment extends Fragment implements IMenuSelectView, MenuProvider {

    private final IMenuSelectView.Listener listener;
    private FragmentMenuSelectBinding binding;
    private ExpandableMealtimeAdapter itemsAdapter;
    private String cafe;
    private LocalDate date = LocalDate.now();

    public MenuSelectFragment(IMenuSelectView.Listener listener) {
        this.listener = listener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup parent, @Nullable Bundle savedInstanceState) {
        binding = FragmentMenuSelectBinding.inflate(inflater);
        requireActivity().addMenuProvider(this, getViewLifecycleOwner(), Lifecycle.State.RESUMED);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Menu");

        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        binding.mealtimeRecycler.setLayoutManager(layoutManager);
        itemsAdapter = new ExpandableMealtimeAdapter(new ArrayList<>(), getContext(), listener, this);
        binding.mealtimeRecycler.setAdapter(itemsAdapter);

        populateSpinner(view, binding.cafeSpinner, R.array.cafes);

        binding.textDate.setText(date.toString());
        refreshMenu();
    }

    /**
     * Translates the given cafe name to fit the HTTP request.
     *
     * @param cafe The name of the cafe to be translated.
     * @return The translated name for the given cafe.
     */
    private String translateCafe(String cafe) {
        String translatedCafe = cafe;
        switch (cafe) {
            case "Deece":
                translatedCafe = "gordon";
                break;
            case "Retreat":
                translatedCafe = "the-retreat";
                break;
            case "Street Eats":
                translatedCafe = "food-truck";
                break;
        }
        return translatedCafe;
    }

    /**
     * Populates a Spinner view with a given array of strings.
     *
     * @param view           The view containing the Spinner.
     * @param spinner        The Spinner to be populated.
     * @param textArrayResId The resource ID of the array of strings to be used as the Spinner's options.
     */
    private void populateSpinner(View view, Spinner spinner, int textArrayResId) {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(view.getContext(), textArrayResId, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cafe = translateCafe(binding.cafeSpinner.getSelectedItem().toString());
                listener.loadData(cafe, date, MenuSelectFragment.this);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    /**
     * Updates the menus displayed in the fragment.
     *
     * @param menus Menus to be displayed.
     */
    @Override
    public void updateMenuItems(List<MealtimeMenu> menus) {
        if (menus.isEmpty()) {
            Snackbar.make(binding.getRoot(), "Menu not found", Snackbar.LENGTH_SHORT).show();
        }
        itemsAdapter.setMenus(menus);
        refreshMenu();
    }

    /**
     * Refreshes the menu displayed in the fragment.
     */
    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void refreshMenu() {
        itemsAdapter.notifyDataSetChanged();
    }

    @Override
    public void onCreateMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
        menuInflater.inflate(R.menu.main_action_bar, menu);

        // Ensure filter buttons are displayed properly
        MenuItem favoriteFilterButton = menu.findItem(R.id.favorite_filter_button);
        favoriteFilterButton.setIcon(listener.getUser().isFavoriteFiltered()
                ? R.drawable.ic_white_filled_heart : R.drawable.ic_white_empty_heart);
        MenuItem applyFilterButton = menu.findItem(R.id.apply_filter_button);
        applyFilterButton.setIcon(listener.getUser().isRestrictionFiltered()
                ? R.drawable.ic_filled_dining : R.drawable.ic_empty_dining);
    }

    @Override
    public boolean onMenuItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.previous_day_button) {
            date = date.minusDays(1);
            binding.textDate.setText(date.toString());
            listener.loadData(cafe, date, this);
        } else if (id == R.id.next_day_button) {
            date = date.plusDays(1);
            binding.textDate.setText(date.toString());
            listener.loadData(cafe, date, this);
        } else if (id == R.id.favorite_filter_button) {
            listener.getUser().toggleFavoriteFilter();
            item.setIcon(listener.getUser().isFavoriteFiltered()
                    ? R.drawable.ic_white_filled_heart : R.drawable.ic_white_empty_heart);
            listener.updateVisibleMenu(this);
            return true;
        } else if (id == R.id.apply_filter_button) {
            listener.getUser().toggleRestrictionFilter();
            item.setIcon(listener.getUser().isRestrictionFiltered()
                    ? R.drawable.ic_filled_dining : R.drawable.ic_empty_dining);
            listener.updateVisibleMenu(this);
        }
        return false;
    }
}
