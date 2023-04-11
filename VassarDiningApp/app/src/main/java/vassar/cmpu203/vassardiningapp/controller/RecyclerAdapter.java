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
        holder.setData(items.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return menu.getMenuItems().size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView itemText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemText = itemView.findViewById(R.id.item_text);
        }

        public void setData(String name) {
            itemText.setText(name);
        }
    }
}
