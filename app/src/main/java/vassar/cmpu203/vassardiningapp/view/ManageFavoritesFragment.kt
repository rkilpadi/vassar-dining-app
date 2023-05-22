package vassar.cmpu203.vassardiningapp.view

import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import vassar.cmpu203.vassardiningapp.databinding.FragmentManageFavoritesBinding

private const val TITLE = "Favorites"

class ManageFavoritesFragment(private val listener: IMenuSelectView.Listener) :
        Fragment(), IFavoriteView {

    private lateinit var binding: FragmentManageFavoritesBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentManageFavoritesBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.title = TITLE

        val layoutManager = LinearLayoutManager(view.context).apply {
            orientation = RecyclerView.VERTICAL
        }
        binding.favoritesRecycler.layoutManager = layoutManager

        val favorites = listener.user.favorites.values.toList()
        binding.favoritesRecycler.adapter = MealtimeItemAdapter(favorites, requireContext(), listener, this)
    }
}
