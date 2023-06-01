package vassar.cmpu203.vassardiningapp.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import vassar.cmpu203.vassardiningapp.R
import vassar.cmpu203.vassardiningapp.databinding.FragmentMenuSelectBinding
import vassar.cmpu203.vassardiningapp.model.MealtimeMenu
import java.time.LocalDate

private const val TITLE = "Menu"

class MenuSelectFragment(private val listener: IMenuSelectView.Listener) :
        Fragment(), IMenuSelectView, MenuProvider {

    private lateinit var binding: FragmentMenuSelectBinding
    private lateinit var itemsAdapter: ExpandableMealtimeAdapter
    private lateinit var cafe: String
    private var date: LocalDate = LocalDate.now()

    override fun onCreateView(inflater: LayoutInflater, parent: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentMenuSelectBinding.inflate(inflater)
        requireActivity().addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.title = TITLE

        val layoutManager = LinearLayoutManager(view.context).apply {
            orientation = RecyclerView.VERTICAL
        }
        binding.mealtimeRecycler.layoutManager = layoutManager
        itemsAdapter = ExpandableMealtimeAdapter(listOf(), requireContext(), listener, this)
        binding.mealtimeRecycler.adapter = itemsAdapter

        populateSpinner(view, binding.cafeSpinner, R.array.cafes)

        binding.textDate.text = date.toString()
        refreshMenu()
    }

    private fun translateCafe(cafe: String) = when (cafe) {
        "Deece" -> "gordon"
        "Retreat" -> "the-retreat"
        "Street Eats" -> "food-truck"
        else -> cafe
    }

    private fun populateSpinner(view: View, spinner: Spinner, textArrayResId: Int) {
        ArrayAdapter.createFromResource(
                view.context,
                textArrayResId,
                android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                cafe = translateCafe(binding.cafeSpinner.selectedItem.toString())
                listener.loadData(cafe, date, this@MenuSelectFragment)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    override fun updateMenuItems(menus: List<MealtimeMenu>) {
        if (menus.isEmpty()) {
            Snackbar.make(binding.root, "Menu not found", Snackbar.LENGTH_SHORT).show()
        }
        itemsAdapter.setMenus(menus)
        refreshMenu()
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun refreshMenu() {
        itemsAdapter.notifyDataSetChanged()
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.main_action_bar, menu)

        // Ensure filter buttons are displayed properly
        val favoriteFilterButton = menu.findItem(R.id.favorite_filter_button)
        favoriteFilterButton.setIcon(
                if (listener.user.isFavoriteFiltered) R.drawable.ic_white_filled_heart else R.drawable.ic_white_empty_heart
        )
        val applyFilterButton = menu.findItem(R.id.apply_filter_button)
        applyFilterButton.setIcon(
                if (listener.user.isRestrictionFiltered) R.drawable.ic_filled_dining else R.drawable.ic_empty_dining
        )
    }

    override fun onMenuItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.previous_day_button -> {
                date = date.minusDays(1)
                binding.textDate.text = date.toString()
                listener.loadData(cafe, date, this)
                false
            }
            R.id.next_day_button -> {
                date = date.plusDays(1)
                binding.textDate.text = date.toString()
                listener.loadData(cafe, date, this)
                false
            }
            R.id.favorite_filter_button -> {
                listener.user.toggleFavoriteFilter()
                item.setIcon(
                        if (listener.user.isFavoriteFiltered) R.drawable.ic_white_filled_heart else R.drawable.ic_white_empty_heart
                )
                listener.updateVisibleMenu(this)
                true
            }
            R.id.apply_filter_button -> {
                listener.user.toggleRestrictionFilter()
                item.setIcon(
                        if (listener.user.isRestrictionFiltered) R.drawable.ic_filled_dining else R.drawable.ic_empty_dining
                )
                listener.updateVisibleMenu(this)
                false
            }
            else -> false
        }
    }
}
