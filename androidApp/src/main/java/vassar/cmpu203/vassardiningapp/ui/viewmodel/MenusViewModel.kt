package vassar.cmpu203.vassardiningapp.ui.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import com.vassar.vassardiningappcommon.MealtimeMenu
import com.vassar.vassardiningappcommon.MenuParser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class MenusViewModel : ViewModel() {
    private val _menus = MutableStateFlow<List<MealtimeMenu>>(listOf())
    val menus: StateFlow<List<MealtimeMenu>> = _menus

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    @RequiresApi(Build.VERSION_CODES.O)
    fun loadData(cafe: String, date: LocalDate) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val strDate = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                val newMenu = MenuParser()
                newMenu.initialize("https://vassar.cafebonappetit.com/cafe/$cafe/$strDate/")
                _menus.value = newMenu.toMealtimeMenu(cafe, strDate)
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    _error.value = "Error fetching menu data"
                }
                e.printStackTrace()
            }
        }
    }
}
