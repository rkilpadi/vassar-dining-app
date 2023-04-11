package vassar.cmpu203.vassardiningapp.controller;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import vassar.cmpu203.vassardiningapp.R;
import vassar.cmpu203.vassardiningapp.model.Menu;
import vassar.cmpu203.vassardiningapp.model.MenuItem;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private final Menu menu;

    public RecyclerAdapter(Menu menu) {
        this.menu = menu;
    }

    @NonNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_item, parent, false));
    }

    @Override
    public final void onBindViewHolder(@NonNull RecyclerAdapter.ViewHolder holder, int position) {
        ArrayList<MenuItem> items = new ArrayList<>(menu.getMenuItems());
        MenuItem item = items.get(position);
        holder.setData(item.getName(), item.getDescription());
    }

    @Override
    public int getItemCount() {
        return menu.getMenuItems().size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView itemName;
        private final TextView itemDesc;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.item_text);
            itemDesc = itemView.findViewById(R.id.item_desc);
        }

        public void setData(String name, String desc) {
            itemName.setText(name);
            itemDesc.setText(desc);
        }
    }
}
