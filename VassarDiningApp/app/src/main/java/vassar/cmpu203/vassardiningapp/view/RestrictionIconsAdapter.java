package vassar.cmpu203.vassardiningapp.view;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vassar.cmpu203.vassardiningapp.databinding.RestrictionIconBinding;
import vassar.cmpu203.vassardiningapp.model.DietaryRestriction;

public class RestrictionIconsAdapter extends RecyclerView.Adapter<RestrictionIconsAdapter.ViewHolder> {

    private final List<DietaryRestriction> restrictions;

    public RestrictionIconsAdapter(List<DietaryRestriction> restrictions) {
        this.restrictions = restrictions;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(RestrictionIconBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
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
        private final RestrictionIconBinding binding;

        public ViewHolder(@NonNull RestrictionIconBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void setData(DietaryRestriction restriction) {
            this.binding.restrictionIcon.setImageResource(restriction.getIconId());
        }
    }
}
