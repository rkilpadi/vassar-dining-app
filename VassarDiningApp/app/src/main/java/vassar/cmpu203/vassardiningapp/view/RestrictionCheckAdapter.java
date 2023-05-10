package vassar.cmpu203.vassardiningapp.view;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import vassar.cmpu203.vassardiningapp.databinding.RestrictionCheckBinding;
import vassar.cmpu203.vassardiningapp.model.DietaryRestriction;
import vassar.cmpu203.vassardiningapp.model.User;

public class RestrictionCheckAdapter extends RecyclerView.Adapter<RestrictionCheckAdapter.ViewHolder> {

    private final DietaryRestriction[] restrictions;
    private final User user;

    public RestrictionCheckAdapter(DietaryRestriction[] restrictions, User user) {
        this.restrictions = restrictions;
        this.user = user;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(RestrictionCheckBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setData(restrictions[position], user);
    }

    @Override
    public int getItemCount() {
        return restrictions.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        RestrictionCheckBinding binding;

        public ViewHolder(@NonNull RestrictionCheckBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void setData(DietaryRestriction restriction, User user) {
            binding.restrictionIcon.setImageResource(restriction.getIconId());
            binding.restrictionCheck.setChecked(user.getDietaryRestrictions().contains(restriction));
            binding.restrictionCheck.setOnClickListener(v ->
                    user.switchRestrictionStatus(restriction)
            );
            binding.restrictionName.setText(restriction.getName());
        }
    }
}
