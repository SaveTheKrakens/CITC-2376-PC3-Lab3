package com.savethekrakens.recipeexplorer

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.compose.rememberNavController
import com.savethekrakens.recipeexplorer.ui.theme.RecipeExplorerTheme

class MainActivity : ComponentActivity() {

    private val recipes = listOf(
        Pair("Beef", "Some Beef with rice"),
        Pair("Chicken", "Some Chicken with rice"),
        Pair("Fish", "Some Fish with rice"),
        Pair("Pork", "Some Pork with rice"),
        Pair("Vegetables", "Some Vegetables with rice"),
        Pair("Rice", "Some Rice with beef, chicken, fish, pork, and vegetables."),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RecipeExplorerTheme {
                val navController = rememberNavController()

                Box(
                    modifier = Modifier.fillMaxSize().background(Color(0xFFD8E2DC)),
                    contentAlignment = Alignment.TopCenter,
                ) {

                }
            }
        }
    }
    @Composable
    fun HomeScreenVertical(navController: NavController){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Text(
                text = "Recipe Explorer",
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.headlineLarge
            )
            LazyColumn {
                items(recipes.size) { index ->
                    RecipeCard(index, recipes[index].first, recipes[index].second)
                }
            }
        }
    }
    @Composable
    fun RecipeCard(recipeID: Int, recipeName: String, recipeDescription: String) {
        Card(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .clickable { onCardClick(recipeID = recipeID) },
            shape = MaterialTheme.shapes.medium,
            elevation = CardDefaults.cardElevation(
                defaultElevation = 4.dp,
                pressedElevation = 8.dp
            )
        ) {
            Column {
                Text(
                    text = recipeName,
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.headlineLarge
                )
                Text(
                    text = recipeDescription,
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 8.dp),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }

    fun onCardClick(recipeID: Int) {
        //Navigate to the recipe details screen
        Log.d("Recipe Explorer", "Clicked: $recipeID")
    }

    @Preview(showBackground = true)
    @Composable
    fun PreviewRecipeCard() {
        RecipeCard(1, "Beef", "Beef with rice")
    }
}






