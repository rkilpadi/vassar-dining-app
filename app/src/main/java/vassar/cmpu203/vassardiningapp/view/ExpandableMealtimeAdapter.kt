package vassar.cmpu203.vassardiningapp.view

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import vassar.cmpu203.vassardiningapp.model.MealtimeMenu
import vassar.cmpu203.vassardiningapp.databinding.MealtimeMenuBinding

class ExpandableMealtimeAdapter(
        private var menus: List<MealtimeMenu>,
        private val context: Context,
        private val listener: IMenuSelectView.Listener,
        private val menuSelectView: IMenuSelectView
) : RecyclerView.Adapter<ExpandableMealtimeAdapter.ViewHolder>() {

    private lateinit var binding: MealtimeMenuBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = MealtimeMenuBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val menu = menus[position]
        val mealtimeTitle: TextView = holder.binding.mealtimeTitle
        val mealtimeRecycler: RecyclerView = holder.binding.itemContainer

        mealtimeTitle.text = menu.label

        mealtimeRecycler.layoutManager = LinearLayoutManager(context).apply {
            orientation = RecyclerView.VERTICAL
        }
        mealtimeRecycler.adapter = MealtimeItemAdapter(menu.menuItems, context, listener, menuSelectView)

        mealtimeTitle.setOnClickListener { view ->
            mealtimeRecycler.visibility = if (mealtimeRecycler.visibility == View.VISIBLE) View.GONE else View.VISIBLE
            if (menu.menuItems.isEmpty()) {
                Snackbar.make(view, "No items to display", Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    override fun getItemCount(): Int = menus.size

    @SuppressLint("NotifyDataSetChanged")
    fun setMenus(menus: List<MealtimeMenu>) {
        this.menus = menus
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: MealtimeMenuBinding) : RecyclerView.ViewHolder(binding.root)
}
