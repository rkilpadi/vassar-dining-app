package vassar.cmpu203.vassardiningapp.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import vassar.cmpu203.vassardiningapp.databinding.MealtimeMenuBinding;
import vassar.cmpu203.vassardiningapp.model.MealtimeMenu;

public class ExpandableMealtimeAdapter extends RecyclerView.Adapter<ExpandableMealtimeAdapter.ViewHolder> {

    private List<MealtimeMenu> menus;
    private final Context context;
    private final IMenuSelectView.Listener listener;
    private MealtimeMenuBinding binding;

    public ExpandableMealtimeAdapter(List<MealtimeMenu> menus, Context context, IMenuSelectView.Listener listener) {
        this.menus = menus;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = MealtimeMenuBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public final void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MealtimeMenu menu = menus.get(position);
        TextView mealtimeTitle = holder.binding.mealtimeTitle;
        RecyclerView itemContainer = holder.binding.itemContainer;

        mealtimeTitle.setText(menu.getMealtime());

        itemContainer.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        itemContainer.setAdapter(new MealtimeItemAdapter(menu.getMenuItems(), context, listener));

        mealtimeTitle.setOnClickListener(view -> {
            itemContainer.setVisibility(
                    itemContainer.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE
            );
            if (menu.getMenuItems().isEmpty()) {
                Snackbar.make(view, "No items to display", Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return menus.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setMenus(List<MealtimeMenu> menus) {
        this.menus = menus;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final MealtimeMenuBinding binding;

        public ViewHolder(@NonNull MealtimeMenuBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            this.binding.mealtimeTitle.setTypeface(null, Typeface.BOLD);
        }
    }
}
