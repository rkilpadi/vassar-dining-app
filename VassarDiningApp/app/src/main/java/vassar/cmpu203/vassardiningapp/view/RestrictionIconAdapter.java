package vassar.cmpu203.vassardiningapp.view;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Set;

import vassar.cmpu203.vassardiningapp.databinding.RestrictionIconBinding;
import vassar.cmpu203.vassardiningapp.model.DietaryRestriction;

public class RestrictionIconAdapter extends RecyclerView.Adapter<RestrictionIconAdapter.ViewHolder> {

    private final Set<DietaryRestriction> restrictions;

    public RestrictionIconAdapter(Set<DietaryRestriction> restrictions) {
        this.restrictions = restrictions;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(RestrictionIconBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setData(new ArrayList<>(restrictions).get(position));
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
            binding.restrictionIcon.setImageResource(restriction.getIconId());
        }
    }
}
