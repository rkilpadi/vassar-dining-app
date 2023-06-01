package vassar.cmpu203.vassardiningapp.view

import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.google.android.material.navigation.NavigationView
import vassar.cmpu203.vassardiningapp.R
import vassar.cmpu203.vassardiningapp.databinding.ActivityMainBinding

class MainView(private val activity: FragmentActivity) : IMainView {
    private lateinit var drawerLayout: DrawerLayout
    private val binding = ActivityMainBinding.inflate(activity.layoutInflater)

    override val rootView = binding.root

    override fun setupActionBar(): ActionBarDrawerToggle {
        val navView = binding.drawer
        drawerLayout = binding.drawerLayout
        val drawerToggle = ActionBarDrawerToggle(activity, drawerLayout, R.string.drawer_open, R.string.drawer_close)
        drawerLayout.addDrawerListener(drawerToggle)
        drawerToggle.syncState()
        navView.setNavigationItemSelectedListener(activity as NavigationView.OnNavigationItemSelectedListener)
        return drawerToggle
    }

    override fun closeDrawer() = drawerLayout.closeDrawer(GravityCompat.START)

    override fun displayFragment(fragment: Fragment, reversible: Boolean) {
        val ft = activity.supportFragmentManager.beginTransaction()
                .replace(binding.fragmentContainer.id, fragment)
        if (reversible) ft.addToBackStack(null)
        ft.commit()
    }
}
