package com.savethekrakens.lab3_recipeexplorer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import com.savethekrakens.lab3_recipeexplorer.ui.RecipesApp
import com.savethekrakens.lab3_recipeexplorer.ui.theme.Lab3_RecipeExplorerTheme

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab3_RecipeExplorerTheme {
                Surface {
                    val windowSize = calculateWindowSizeClass(this)
                    RecipesApp(
                        windowSize = windowSize.widthSizeClass,
                        onBackPressed = { finish() }
                    )
                }
            }
        }
    }
}