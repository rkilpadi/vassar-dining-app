package vassar.cmpu203.vassardiningapp.controller

import android.os.Build
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import vassar.cmpu203.vassardiningapp.ui.components.Screen
import vassar.cmpu203.vassardiningapp.ui.components.AppBarPage

class MainActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AppBarPage(currentScreen = Screen.Menu)
        }
    }
}
