package vassar.cmpu203.vassardiningapp.view;

import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import vassar.cmpu203.vassardiningapp.databinding.ActivityMainBinding;

public class MainView {

    FragmentManager fragmentManager;
    ActivityMainBinding binding;

    public MainView(FragmentActivity activity) {
        fragmentManager = activity.getSupportFragmentManager();
        binding = ActivityMainBinding.inflate(activity.getLayoutInflater());
    }

    public View getRootView() {
        return binding.getRoot();
    }

    public void displayFragment(Fragment fragment, boolean reversible, String name) {
        FragmentTransaction ft = fragmentManager.beginTransaction()
                .replace(binding.menuSelectFragmentContainer.getId(), fragment);

        if (reversible) ft.addToBackStack(name);
        ft.commit();
    }
}
