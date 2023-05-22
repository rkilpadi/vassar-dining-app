package vassar.cmpu203.vassardiningapp.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import vassar.cmpu203.vassardiningapp.R
import vassar.cmpu203.vassardiningapp.databinding.MealtimeItemBinding
import vassar.cmpu203.vassardiningapp.model.MealtimeItem

class MealtimeItemAdapter(
        private var items: List<MealtimeItem>,
        private val context: Context,
        private val listener: IFavoriteView.Listener,
        private val favoriteView: IFavoriteView
) : RecyclerView.Adapter<MealtimeItemAdapter.ViewHolder>() {

    private lateinit var binding: MealtimeItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = MealtimeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        val itemRestrictions = binding.restrictionIconRecycler

        binding.itemText.text = item.name
        binding.itemDesc.text = item.description
        itemRestrictions.layoutManager = LinearLayoutManager(binding.root.context).apply {
            orientation = RecyclerView.HORIZONTAL
        }
        itemRestrictions.adapter = RestrictionIconAdapter(item.dietaryRestrictions)

        val heart = binding.heartToggle
        val isFavorited = listener.user.favorites.containsKey(item.id)
        heart.isChecked = isFavorited
        heart.background = ContextCompat.getDrawable(context,
                if (isFavorited) R.drawable.ic_red_filled_heart else R.drawable.ic_red_empty_heart)

        heart.setOnClickListener {
            heart.background = ContextCompat.getDrawable(context,
                    if (heart.isChecked) R.drawable.ic_red_filled_heart else R.drawable.ic_red_empty_heart)
            listener.onFavoriteClicked(item, favoriteView)
        }
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(binding: MealtimeItemBinding) : RecyclerView.ViewHolder(binding.root)
}
