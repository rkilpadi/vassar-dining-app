package vassar.cmpu203.vassardiningapp.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import vassar.cmpu203.vassardiningapp.databinding.RestrictionCheckBinding
import vassar.cmpu203.vassardiningapp.model.DietaryRestriction
import vassar.cmpu203.vassardiningapp.model.User

class RestrictionCheckAdapter(
        private val restrictions: Array<DietaryRestriction>,
        private val user: User
) : RecyclerView.Adapter<RestrictionCheckAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(RestrictionCheckBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(restrictions[position], user)
    }

    override fun getItemCount(): Int = restrictions.size

    class ViewHolder(var binding: RestrictionCheckBinding) : RecyclerView.ViewHolder(binding.root) {
        fun setData(restriction: DietaryRestriction, user: User) {
            binding.restrictionIcon.setImageResource(restriction.iconId)
            binding.restrictionCheck.isChecked = user.dietaryRestrictions.contains(restriction)
            binding.restrictionCheck.setOnClickListener { user.switchRestrictionStatus(restriction) }
            binding.restrictionName.text = restriction.label
        }
    }
}
