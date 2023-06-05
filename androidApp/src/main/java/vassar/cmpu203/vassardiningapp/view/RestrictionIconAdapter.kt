package vassar.cmpu203.vassardiningapp.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import vassar.cmpu203.vassardiningapp.databinding.RestrictionIconBinding
import com.vassar.vassardiningappcommon.DietaryRestriction
import vassar.cmpu203.vassardiningapp.R

class RestrictionIconAdapter(private val restrictions: Set<DietaryRestriction>) :
        RecyclerView.Adapter<RestrictionIconAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(RestrictionIconBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(ArrayList(restrictions)[position])
    }

    override fun getItemCount(): Int = restrictions.size

    class ViewHolder(private val binding: RestrictionIconBinding) : RecyclerView.ViewHolder(binding.root) {
        fun setData(restriction: DietaryRestriction) {
            binding.restrictionIcon.setImageResource(binding.root.context.resources.getIdentifier(restriction.iconId, "drawable", binding.root.context.packageName))
        }
    }
}
