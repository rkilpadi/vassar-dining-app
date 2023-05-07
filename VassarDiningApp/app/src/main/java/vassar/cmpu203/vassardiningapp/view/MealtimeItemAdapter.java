package vassar.cmpu203.vassardiningapp.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vassar.cmpu203.vassardiningapp.R;
import vassar.cmpu203.vassardiningapp.databinding.MealtimeItemBinding;
import vassar.cmpu203.vassardiningapp.model.MealtimeItem;

public class MealtimeItemAdapter extends RecyclerView.Adapter<MealtimeItemAdapter.ViewHolder> {

    List<MealtimeItem> items;
    Context context;
    IMenuSelectView.Listener listener;
    RecyclerView parent;
    MealtimeItemBinding binding;

    public MealtimeItemAdapter(List<MealtimeItem> items, Context context, IMenuSelectView.Listener listener) {
        this.items = items;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MealtimeItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = MealtimeItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        this.parent = (RecyclerView) parent;
        return new ViewHolder(binding);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MealtimeItem item = items.get(position);

        View itemView = binding.getRoot();
        RecyclerView itemRestrictions = binding.restrictionIconRecycler;

        binding.itemText.setText(item.getName());
        binding.itemDesc.setText(item.getDescription());
        itemRestrictions.setLayoutManager(new LinearLayoutManager(itemView.getContext(), LinearLayoutManager.HORIZONTAL, false));
        itemRestrictions.setAdapter(new RestrictionIconAdapter(item.getDietaryRestrictions()));

        ToggleButton heart = binding.heartToggle;
        heart.setBackgroundDrawable(ContextCompat.getDrawable(context,
                listener.getUser().getFavorites().contains(item) ? R.drawable.ic_red_filled_heart : R.drawable.ic_red_empty_heart
        ));
        heart.setOnClickListener(view -> {
            heart.setBackgroundDrawable(ContextCompat.getDrawable(context,
                    heart.isChecked() ? R.drawable.ic_red_filled_heart : R.drawable.ic_red_empty_heart
            ));
            listener.onFavorite(item);
            notifyDataSetChanged();
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final MealtimeItemBinding binding;

        public ViewHolder(@NonNull MealtimeItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }
    }

}
