package vassar.cmpu203.vassardiningapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Snackbar
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import vassar.cmpu203.vassardiningapp.model.MealtimeItem
import vassar.cmpu203.vassardiningapp.model.MealtimeMenu
import vassar.cmpu203.vassardiningapp.ui.components.MealtimeItemList
import vassar.cmpu203.vassardiningapp.ui.viewmodel.MenusViewModel
import java.time.LocalDate

@Composable
fun MenuScreen(cafe: String, date: LocalDate) {
    val viewModel: MenusViewModel = viewModel()
    val menus by viewModel.menus.collectAsState()
    val error by viewModel.error.collectAsState()

    LaunchedEffect(cafe, date) { viewModel.loadData(cafe, date) }

    error?.let { Snackbar { Text(it) } }

    if (menus.isEmpty()) CircularProgressIndicator() else MenuDisplay(mealtimes = menus)
}

@Composable
private fun MenuDisplay(mealtimes: List<MealtimeMenu>) {

    val currentMealtime = remember { mutableStateOf(mealtimes.firstOrNull()) }

    if (mealtimes.isEmpty()) {
        Snackbar { Text("No menus found") }
    }

    Column {
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .background(Color.LightGray),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            items(mealtimes) {
                Text(
                    text = it.label,
                    modifier = Modifier .clickable { currentMealtime.value = it }
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
    MenuDisplay(mealtimes = listOf(MealtimeMenu("deece", "24-05-2023", "breakfast", listOf(
        MealtimeItem("chicken", "123", "", "", mutableSetOf())
    ))))
}
