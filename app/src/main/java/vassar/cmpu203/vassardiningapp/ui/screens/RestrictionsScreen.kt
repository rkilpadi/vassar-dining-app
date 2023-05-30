package vassar.cmpu203.vassardiningapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import vassar.cmpu203.vassardiningapp.model.DietaryRestriction
import vassar.cmpu203.vassardiningapp.model.User
import vassar.cmpu203.vassardiningapp.ui.viewmodel.UserViewModel

@Composable
fun RestrictionsScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        DietaryRestriction::class.java.enumConstants?.forEach { restriction ->
            RestrictionItem(restriction = restriction)
        }
    }
}

@Composable
private fun RestrictionItem(restriction: DietaryRestriction) {
    val viewModel: UserViewModel = viewModel()
    val user by viewModel.userData.observeAsState(User())
    val isChecked = remember {
        mutableStateOf(restriction in user.dietaryRestrictions)
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Checkbox(
            checked = isChecked.value,
            onCheckedChange = {
                isChecked.value = it
                viewModel.switchRestrictionStatus(restriction)
            }
        )
        Image(
            painter = painterResource(id = restriction.iconId),
            contentDescription = "restriction icon",
            modifier = Modifier.size(30.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(text = restriction.label, fontSize = 15.sp)
    }
}

@Preview(showBackground = true)
@Composable
private fun RestrictionsPreview() = RestrictionsScreen()
