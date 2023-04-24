package vassar.cmpu203.vassardiningapp.view;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vassar.cmpu203.vassardiningapp.databinding.MealtimeItemBinding;
import vassar.cmpu203.vassardiningapp.databinding.MenuItemBinding;
import vassar.cmpu203.vassardiningapp.model.Menu;
import vassar.cmpu203.vassardiningapp.model.MenuItem;

public class ExpandableAdapter extends RecyclerView.Adapter<ExpandableAdapter.MenusViewHolder> {

    private final List<Menu> menus;
    private final Context context;
    private MealtimeItemBinding binding;

    public ExpandableAdapter(List<Menu> menus, Context context) {
        this.menus = menus;
        this.context = context;
    }

    @NonNull
    @Override
    public MenusViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = MealtimeItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MenusViewHolder(binding);
    }

    @Override
    public final void onBindViewHolder(@NonNull MenusViewHolder holder, int position) {
        Menu menu = menus.get(position);
        TextView mealtimeTitle = holder.binding.mealtimeTitle;
        LinearLayout itemContainer = holder.binding.itemContainer;

        mealtimeTitle.setText(menu.getMealtime());
        itemContainer.removeAllViews();

        for (MenuItem menuItem : menu.getMenuItems()) {
            MenuItemBinding itemBinding = MenuItemBinding.inflate(LayoutInflater.from(context), binding.itemContainer, false);
            View itemView = itemBinding.getRoot();
            RecyclerView itemRestrictions = itemBinding.restrictionIconRecycler;

            itemBinding.itemText.setText(menuItem.getName());
            itemBinding.itemDesc.setText(menuItem.getDescription());
            itemRestrictions.setLayoutManager(new LinearLayoutManager(itemView.getContext(), LinearLayoutManager.HORIZONTAL, false));
            itemRestrictions.setAdapter(new RestrictionIconsAdapter(menuItem.getDietaryRestrictions()));
            itemContainer.addView(itemView);
        }

        mealtimeTitle.setOnClickListener(v -> {
            if (itemContainer.getVisibility() == View.VISIBLE) {
                itemContainer.setVisibility(View.GONE);
            } else {
                itemContainer.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public int getItemCount() {
        return menus.size();
    }

    public static class MenusViewHolder extends RecyclerView.ViewHolder {
        private final MealtimeItemBinding binding;

        public MenusViewHolder(@NonNull MealtimeItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            this.binding.mealtimeTitle.setTypeface(null, Typeface.BOLD);
        }
    }
}
