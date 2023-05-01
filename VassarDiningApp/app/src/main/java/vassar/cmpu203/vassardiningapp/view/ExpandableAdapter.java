package vassar.cmpu203.vassardiningapp.view;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vassar.cmpu203.vassardiningapp.R;
import vassar.cmpu203.vassardiningapp.databinding.MealtimeItemBinding;
import vassar.cmpu203.vassardiningapp.databinding.MenuItemBinding;
import vassar.cmpu203.vassardiningapp.model.MealtimeItem;
import vassar.cmpu203.vassardiningapp.model.MealtimeMenu;

public class ExpandableAdapter extends RecyclerView.Adapter<ExpandableAdapter.MenusViewHolder> {

    private List<MealtimeMenu> menus;
    private final Context context;
    private final IMenuSelectView.Listener listener;
    private MealtimeItemBinding binding;

    public ExpandableAdapter(List<MealtimeMenu> menus, Context context, IMenuSelectView.Listener listener) {
        this.menus = menus;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MenusViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = MealtimeItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MenusViewHolder(binding);
    }

    @Override
    public final void onBindViewHolder(@NonNull MenusViewHolder holder, int position) {
        MealtimeMenu menu = menus.get(position);
        TextView mealtimeTitle = holder.binding.mealtimeTitle;
        LinearLayout itemContainer = holder.binding.itemContainer;

        mealtimeTitle.setText(menu.getMealtime());
        itemContainer.removeAllViews();

        for (MealtimeItem item : menu.getMenuItems()) {
            MenuItemBinding itemBinding = MenuItemBinding.inflate(LayoutInflater.from(context), binding.itemContainer, false);
            View itemView = itemBinding.getRoot();
            RecyclerView itemRestrictions = itemBinding.restrictionIconRecycler;

            itemBinding.itemText.setText(item.getName());
            itemBinding.itemDesc.setText(item.getDescription());
            itemRestrictions.setLayoutManager(new LinearLayoutManager(itemView.getContext(), LinearLayoutManager.HORIZONTAL, false));
            itemRestrictions.setAdapter(new RestrictionIconsAdapter(item.getDietaryRestrictions()));
            itemContainer.addView(itemView);

            ToggleButton heart = itemBinding.heartToggle;
            heart.setBackgroundDrawable(ContextCompat.getDrawable(context,
                    listener.getUser().getFavorites().contains(item) ? R.drawable.ic_red_filled_heart : R.drawable.ic_red_empty_heart
            ));
            heart.setOnClickListener(v -> {
                heart.setBackgroundDrawable(ContextCompat.getDrawable(context,
                        heart.isChecked() ? R.drawable.ic_red_filled_heart : R.drawable.ic_red_empty_heart
                ));
                listener.onFavorite(item);
                this.notifyDataSetChanged();
            });
        }

        mealtimeTitle.setOnClickListener(v -> itemContainer.setVisibility(
                itemContainer.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE
        ));
    }

    @Override
    public int getItemCount() {
        return menus.size();
    }

    public void updateMenus(List<MealtimeMenu> menus) {
        this.menus = menus;
        this.notifyDataSetChanged();
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
