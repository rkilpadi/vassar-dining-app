package vassar.cmpu203.vassardiningapp.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import vassar.cmpu203.vassardiningapp.databinding.FragmentManageRestrictionsBinding;
import vassar.cmpu203.vassardiningapp.model.DietaryRestriction;
import vassar.cmpu203.vassardiningapp.model.User;

public class ManageRestrictionsFragment extends Fragment {

    User user;
    FragmentManageRestrictionsBinding binding;

    public ManageRestrictionsFragment(User user) {
        this.user = user;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentManageRestrictionsBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Dietary Restrictions");

        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        binding.restrictionCheckRecycler.setLayoutManager(layoutManager);

        DietaryRestriction[] restrictions = DietaryRestriction.class.getEnumConstants();
        binding.restrictionCheckRecycler.setAdapter(new RestrictionCheckAdapter(restrictions, user));
    }
}
