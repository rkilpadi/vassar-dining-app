package vassar.cmpu203.vassardiningapp.ui.screens

import android.os.Build
import android.view.Surface
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Snackbar
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.pm.ShortcutInfoCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.vassar.vassardiningappcommon.MealtimeItem
import com.vassar.vassardiningappcommon.MealtimeMenu
import vassar.cmpu203.vassardiningapp.ui.components.MealtimeItemList
import vassar.cmpu203.vassardiningapp.ui.viewmodel.MenusViewModel
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MenuScreen(cafe: String, date: LocalDate) {
    val viewModel: MenusViewModel = viewModel()
    val menus by viewModel.menus.collectAsState()
    val error by viewModel.error.collectAsState()

    LaunchedEffect(cafe, date) { viewModel.loadData(cafe, date) }

    error?.let { Snackbar { Text(it) } }

    if (menus.isEmpty()) CircularProgressIndicator() else MenuDisplay(mealtimes = menus)
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun MenuDisplay(mealtimes: List<MealtimeMenu>) {

    val currentMealtime = remember { mutableStateOf(mealtimes.firstOrNull()) }
    var selectedIndex by remember{mutableStateOf(currentMealtime.value?.label)}


    if (mealtimes.isEmpty()) {
        Snackbar { Text("No menus found") }
    }

    Column {
        LazyRow (
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .background(Color.LightGray),
            horizontalArrangement = Arrangement.SpaceEvenly
        ){
            items(mealtimes) {
                Text(
                    text = it.label,
                    modifier = Modifier
                        .padding(5.dp)
                        .selectable(
                            selected = it.label == selectedIndex,
                            onClick = {
                                selectedIndex = if (selectedIndex != it.label)
                                    it.label else ""
                                currentMealtime.value = it
                            })
                        .background(
                            if (it.label == selectedIndex)
                                Color.Gray else Color.LightGray
                        )
                )
            }
        }

        currentMealtime.value?.let {
            MealtimeItemList(items = it.menuItems)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MenuPreview() {
    val menu = listOf(
        MealtimeMenu("deece", "24-05-2023", "breakfast", listOf(
            MealtimeItem("chicken", "123", "", "", mutableSetOf()))),
        MealtimeMenu("deece", "24-05-2023", "dinner", listOf(
            MealtimeItem("pork", "123", "", "", mutableSetOf())))
    )
    MenuDisplay(mealtimes = menu)
}
