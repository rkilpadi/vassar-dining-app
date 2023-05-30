package vassar.cmpu203.vassardiningapp.controller

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import vassar.cmpu203.vassardiningapp.ui.components.Screen
import vassar.cmpu203.vassardiningapp.ui.components.AppBarPage

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
           AppBarPage(currentScreen = Screen.Menu)
        }
    }
}
