package vassar.cmpu203.vassardiningapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import vassar.cmpu203.vassardiningapp.databinding.FragmentManageRestrictionsBinding
import com.vassar.vassardiningappcommon.DietaryRestriction
import vassar.cmpu203.vassardiningapp.model.User

private const val TITLE = "Dietary Restrictions"

class ManageRestrictionsFragment(private var user: User) : Fragment() {
    private lateinit var binding: FragmentManageRestrictionsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentManageRestrictionsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.title = TITLE

        val layoutManager = LinearLayoutManager(view.context).apply {
            orientation = RecyclerView.VERTICAL
        }
        binding.restrictionCheckRecycler.layoutManager = layoutManager

        val restrictions = DietaryRestriction::class.java.enumConstants
        binding.restrictionCheckRecycler.adapter = RestrictionCheckAdapter(restrictions, user)
    }
}
