package vassar.cmpu203.vassardiningapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import vassar.cmpu203.vassardiningapp.CafeBonHTTPRequest.Menu
import vassar.cmpu203.vassardiningapp.CafeBonHTTPRequest.MenuParser
import vassar.cmpu203.vassardiningapp.model.MealtimeMenu
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class MenusViewModel : ViewModel() {
    private val _menus = MutableStateFlow<List<MealtimeMenu>>(listOf())
    val menus: StateFlow<List<MealtimeMenu>> = _menus

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun loadData(cafe: String, date: LocalDate) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val strDate = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                val result = MenuParser.MenuParserMethod(cafe, strDate)
                withContext(Dispatchers.Main) {
                    _menus.value = result.map(Menu::toMealtimeMenu)
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    _error.value = "Error fetching menu data"
                }
                e.printStackTrace()
            }
        }
    }
}
