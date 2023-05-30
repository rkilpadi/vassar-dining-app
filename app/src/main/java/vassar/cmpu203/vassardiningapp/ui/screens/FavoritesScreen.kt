package vassar.cmpu203.vassardiningapp.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import vassar.cmpu203.vassardiningapp.model.User
import vassar.cmpu203.vassardiningapp.ui.components.MealtimeItemList
import vassar.cmpu203.vassardiningapp.ui.viewmodel.UserViewModel

@Composable
fun FavoritesScreen() {
    val viewModel: UserViewModel = viewModel()
    val user by viewModel.userData.observeAsState()

    // TODO: Don't use !!
    MealtimeItemList(items = user!!.favorites.toList())
}

@Preview
@Composable
private fun FavoritesPreview() = FavoritesScreen()
