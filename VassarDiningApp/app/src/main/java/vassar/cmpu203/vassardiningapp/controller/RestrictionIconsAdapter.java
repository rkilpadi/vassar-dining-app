package vassar.cmpu203.vassardiningapp.controller;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vassar.cmpu203.vassardiningapp.R;
import vassar.cmpu203.vassardiningapp.model.DietaryRestriction;

public class RestrictionIconsAdapter extends RecyclerView.Adapter<RestrictionIconsAdapter.ViewHolder> {

    private final List<DietaryRestriction> restrictions;

    public RestrictionIconsAdapter(List<DietaryRestriction> restrictions) {
        this.restrictions = restrictions;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.restriction_icon, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setData(restrictions.get(position));
    }

    @Override
    public int getItemCount() {
        return restrictions.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView icon;

        public ViewHolder(View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.restriction_icon);
        }

        public void setData(DietaryRestriction restriction) {
            icon.setImageResource(restriction.getIconId());
        }
    }
}
