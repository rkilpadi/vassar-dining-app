package vassar.cmpu203.vassardiningapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import vassar.cmpu203.vassardiningapp.R
import vassar.cmpu203.vassardiningapp.model.DietaryRestriction
import vassar.cmpu203.vassardiningapp.model.MealtimeItem
import vassar.cmpu203.vassardiningapp.ui.viewmodel.UserViewModel

@Composable
fun MealtimeItemList(items: List<MealtimeItem>) {
    LazyColumn (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(3.dp)
    ) {
        items(items) {
            MealtimeItemCard(item = it)
        }
    }
}

@Composable
fun MealtimeItemCard(item: MealtimeItem) {
    val viewModel: UserViewModel = viewModel()

    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = 10.dp
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier.clickable(onClick = { viewModel.switchFavoriteStatus(item) }),
                painter = painterResource(
                    if (viewModel.inFavorites(item)) {
                        R.drawable.ic_red_filled_heart
                    } else {
                        R.drawable.ic_red_empty_heart
                    }
                ),
                contentDescription = "heart"
            )

            Text(text = item.name)

            LazyRow {
                items(item.dietaryRestrictions.toList()) { restriction ->
                    Image(
                        painter = painterResource(id = restriction.iconId),
                        contentDescription = "restriction"
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MealtimeItemListPreview() {
    val item1 = MealtimeItem("beans", "123", "lol", "home",
        mutableSetOf(DietaryRestriction.VEGAN, DietaryRestriction.GLUTEN_FREE))
    val item2 = MealtimeItem("chicken", "123", "", "home", mutableSetOf())
    MealtimeItemList(listOf(item1, item2))
}
