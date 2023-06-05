package vassar.cmpu203.vassardiningapp.controller

//import vassar.cmpu203.vassardiningapp.CafeBonHTTPRequest.Menu
//import vassar.cmpu203.vassardiningapp.CafeBonHTTPRequest.MenuParser
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import com.vassar.vassardiningappcommon.MealtimeItem
import com.vassar.vassardiningappcommon.MealtimeMenu
import com.vassar.vassardiningappcommon.MenuParser
import kotlinx.coroutines.*
import vassar.cmpu203.vassardiningapp.R
//import com.vassar.vassardiningapp.MealtimeItem
//import com.vassar.vassardiningapp.MealtimeMenu
import vassar.cmpu203.vassardiningapp.model.User
import vassar.cmpu203.vassardiningapp.view.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter

private const val DEFAULT_CAFE = "gordon"

class MainActivity : AppCompatActivity(), IMenuSelectView.Listener, NavigationView.OnNavigationItemSelectedListener {
    private var currentMenu: List<MealtimeMenu> = listOf()
    private lateinit var mainView: IMainView
    private lateinit var localStorageFacade: LocalStorageFacade
    override lateinit var user: User

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        supportFragmentManager.fragmentFactory = FragFactory(this)
        super.onCreate(savedInstanceState)

        localStorageFacade = LocalStorageFacade(filesDir)
        user = localStorageFacade.retrieveLedger() ?: User()

        mainView = MainView(this)
        setContentView(mainView.rootView)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeButtonEnabled(true)
        }

        val menuSelectFragment = MenuSelectFragment(this)
        loadData(DEFAULT_CAFE, LocalDate.now(), menuSelectFragment)
        mainView.displayFragment(menuSelectFragment, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun loadData(cafe: String, date: LocalDate, view: IMenuSelectView) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val strDate = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                val newMenu = MenuParser()
                newMenu.initialize("https://vassar.cafebonappetit.com/cafe/$cafe/$strDate/")
                currentMenu = newMenu.toMealtimeMenu(cafe, strDate)
                withContext(Dispatchers.Main) {
                    updateVisibleMenu(view)
                    view.refreshMenu()
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Snackbar.make(currentFocus!!, "Error fetching menu data", Snackbar.LENGTH_SHORT).show()
                }
            }
       }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        localStorageFacade.saveUser(user)
    }

    override fun onFavoriteClicked(item: MealtimeItem, favoriteView: IFavoriteView) {
        user.switchFavoriteStatus(item)
        favoriteView.updateFavoriteDisplay()
    }

    override fun updateVisibleMenu(view: IMenuSelectView) {
        view.updateMenuItems(user.filterMenus(currentMenu))
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return mainView.setupActionBar().onOptionsItemSelected(item) || super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.navigation_menu -> mainView.displayFragment(MenuSelectFragment(this), true)
            R.id.navigation_favorites -> mainView.displayFragment(ManageFavoritesFragment(this), true)
            R.id.navigation_restrictions -> mainView.displayFragment(ManageRestrictionsFragment(user), true)
        }
        mainView.closeDrawer()
        return true
    }
}
