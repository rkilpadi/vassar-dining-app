package vassar.cmpu203.vassardiningapp.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import vassar.cmpu203.vassardiningapp.databinding.FragmentManageFavoritesBinding;
import vassar.cmpu203.vassardiningapp.model.MealtimeItem;

public class ManageFavoritesFragment extends Fragment implements IFavoriteView {

    IMenuSelectView.Listener listener;
    FragmentManageFavoritesBinding binding;

    public ManageFavoritesFragment(IMenuSelectView.Listener listener) {
        this.listener = listener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentManageFavoritesBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        binding.favoritesRecycler.setLayoutManager(layoutManager);

        ArrayList<MealtimeItem> favorites = new ArrayList<>(listener.getUser().getFavorites());
        binding.favoritesRecycler.setAdapter(new MealtimeItemAdapter(favorites, getContext(), listener, this));
    }
}
