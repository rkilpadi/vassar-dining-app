package vassar.cmpu203.vassardiningapp.controller;

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

import vassar.cmpu203.vassardiningapp.R;
import vassar.cmpu203.vassardiningapp.model.Menu;
import vassar.cmpu203.vassardiningapp.model.MenuItem;

public class ExpandableAdapter extends RecyclerView.Adapter<ExpandableAdapter.MenusViewHolder> {

    private final List<Menu> menus;
    private final Context context;

    public ExpandableAdapter(List<Menu> menus, Context context) {
        this.menus = menus;
        this.context = context;
    }

    @NonNull
    @Override
    public MenusViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MenusViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.mealtime_item, parent, false));
    }

    @Override
    public final void onBindViewHolder(@NonNull MenusViewHolder holder, int position) {
        Menu menu = menus.get(position);
        holder.getMenuName().setText(menu.getMealtime());

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        holder.getMenuItemsContainer().removeAllViews();

        for (MenuItem menuItem : menu.getMenuItems()) {
            View itemView = layoutInflater.inflate(R.layout.menu_item, holder.getMenuItemsContainer(), false);
            TextView itemName = itemView.findViewById(R.id.item_text);
            TextView itemDesc = itemView.findViewById(R.id.item_desc);
            RecyclerView itemRestrictions = itemView.findViewById(R.id.restriction_icon_recycler);

            itemName.setText(menuItem.getName());
            itemDesc.setText(menuItem.getDescription());
            itemRestrictions.setLayoutManager(new LinearLayoutManager(itemView.getContext(), LinearLayoutManager.HORIZONTAL, false));
            itemRestrictions.setAdapter(new RestrictionIconsAdapter(menuItem.getDietaryRestrictions()));
            holder.getMenuItemsContainer().addView(itemView);
        }

        holder.getMenuName().setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (holder.getMenuItemsContainer().getVisibility() == View.VISIBLE) {
                    holder.getMenuItemsContainer().setVisibility(View.GONE);
                } else {
                    holder.getMenuItemsContainer().setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return menus.size();
    }

    public static class MenusViewHolder extends RecyclerView.ViewHolder {
        private final TextView menuName;
        private final LinearLayout menuItemsContainer;

        public MenusViewHolder(@NonNull View itemView) {
            super(itemView);
            menuName = itemView.findViewById(R.id.mealtime_title);
            menuItemsContainer = itemView.findViewById(R.id.item_container);

            menuName.setTypeface(null, Typeface.BOLD);
        }

        public TextView getMenuName() {
            return menuName;
        }

        public LinearLayout getMenuItemsContainer() {
            return menuItemsContainer;
        }
    }
}
