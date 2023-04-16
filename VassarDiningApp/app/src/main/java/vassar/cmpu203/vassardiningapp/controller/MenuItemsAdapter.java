package vassar.cmpu203.vassardiningapp.controller;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vassar.cmpu203.vassardiningapp.R;
import vassar.cmpu203.vassardiningapp.model.DietaryRestriction;
import vassar.cmpu203.vassardiningapp.model.Menu;
import vassar.cmpu203.vassardiningapp.model.MenuItem;

public class MenuItemsAdapter extends RecyclerView.Adapter<MenuItemsAdapter.ViewHolder> {

    private final Menu menu;

    public MenuItemsAdapter(Menu menu) {
        this.menu = menu;
    }

    @NonNull
    @Override
    public MenuItemsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_item, parent, false));
    }

    @Override
    public final void onBindViewHolder(@NonNull MenuItemsAdapter.ViewHolder holder, int position) {
        MenuItem item = menu.getMenuItems().get(position);
        holder.setData(item.getName(), item.getDescription(), item.getDietaryRestrictions());
    }

    @Override
    public int getItemCount() {
        return menu.getMenuItems().size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView itemName;
        private final TextView itemDesc;
        private final RecyclerView restrictionIconRecycler;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.item_text);
            itemDesc = itemView.findViewById(R.id.item_desc);
            restrictionIconRecycler = itemView.findViewById(R.id.restriction_icon_recycler);
            restrictionIconRecycler.setLayoutManager(new LinearLayoutManager(itemView.getContext(), LinearLayoutManager.HORIZONTAL, false));
        }

        public void setData(String name, String desc, List<DietaryRestriction> dietaryRestrictions) {
            itemName.setText(name);
            itemDesc.setText(desc);
            restrictionIconRecycler.setAdapter(new RestrictionIconsAdapter(dietaryRestrictions));
        }
    }
}
