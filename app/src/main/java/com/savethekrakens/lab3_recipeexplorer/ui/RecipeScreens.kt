package com.savethekrakens.lab3_recipeexplorer.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.savethekrakens.lab3_recipeexplorer.data.RecipeData
import com.savethekrakens.lab3_recipeexplorer.model.Recipe
import com.savethekrakens.lab3_recipeexplorer.ui.theme.Lab3_RecipeExplorerTheme
import com.savethekrakens.lab3_recipeexplorer.util.WindowStateUtils

@Composable
fun RecipesApp(
    windowSize: WindowWidthSizeClass,
    onBackPressed: () -> Unit,
    navController: NavHostController = rememberNavController()
) {
    val viewModel: RecipesViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsState()
    val contentType = when (windowSize) {
        WindowWidthSizeClass.Compact,
        WindowWidthSizeClass.Medium -> WindowStateUtils.ListOnly

        WindowWidthSizeClass.Expanded -> WindowStateUtils.ListAndDetail
        else -> WindowStateUtils.ListOnly
    }

    Scaffold(
        topBar = {
            RecipeAppBar(
                isShowingListPage = uiState.isShowingListPage,
                onBackButtonClick = { navController.navigate(WindowStateUtils.ListOnly.name) },
                windowSize = windowSize,
                uiState = uiState
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = contentType.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = WindowStateUtils.ListOnly.name) {
                RecipeList(
                    uiState.recipesList,
                    onClick = {
                        viewModel.updateCurrentRecipe(it)
                        navController.navigate(WindowStateUtils.DetailOnly.name)
                    },
                    modifier = Modifier,
                    contentPadding = PaddingValues(16.dp)
                )
                viewModel.updateIsShowingListPage(true)
            }
            composable(route = WindowStateUtils.DetailOnly.name) {
                RecipeDetail(
                    uiState.currentRecipe,
                    onBackPressed = onBackPressed,
                    modifier = Modifier,
                    contentPadding = PaddingValues(16.dp)
                )
                viewModel.updateIsShowingListPage(false)
            }
            composable(route = WindowStateUtils.ListAndDetail.name) {
                RecipeListAndDetail(
                    uiState.recipesList,
                    uiState.currentRecipe,
                    onClick = {
                        viewModel.updateCurrentRecipe(it)
                    },
                    onBackPressed = onBackPressed,
                    modifier = Modifier,
                    contentPadding = PaddingValues(16.dp)
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeAppBar(
    onBackButtonClick: () -> Unit,
    isShowingListPage: Boolean,
    windowSize: WindowWidthSizeClass,
    modifier: Modifier = Modifier,
    uiState: RecipesUiState
) {
    val isShowingDetailPage = windowSize != WindowWidthSizeClass.Expanded && !isShowingListPage
    TopAppBar(
        title = {
            Text(
                text =
                    if (isShowingDetailPage) {
                        uiState.currentRecipe.title
                    } else {
                        "Mexican Recipes"
                    },
                fontWeight = FontWeight.Bold
            )
        },
        navigationIcon = if (isShowingDetailPage) {
            {
                IconButton(onClick = onBackButtonClick) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back Arrow"
                    )
                }
            }
        } else {
            { Box {} }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        modifier = modifier,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun RecipeListItem(
    recipe: Recipe,
    onItemClick: (Recipe) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(),
        onClick = { onItemClick(recipe) }
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
        ) {
            Text(
                text = recipe.title,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(8.dp)
            )
            Text(
                text = recipe.description,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.secondary,
                overflow = TextOverflow.Ellipsis,
                maxLines = 3,
                modifier = modifier.padding(8.dp)
            )
        }
    }
}

@Composable
private fun RecipeList(
    recipes: List<Recipe>,
    onClick: (Recipe) -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(16.dp)
) {
    LazyColumn(
        contentPadding = contentPadding,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.padding(16.dp)
    ) {
        items(recipes, key = { recipe -> recipe.id }) { recipe ->
            RecipeListItem(
                recipe = recipe,
                onItemClick = onClick
            )
        }
    }
}

@Composable
private fun RecipeDetail(
    selectedRecipe: Recipe,
    onBackPressed: () -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(16.dp)

) {
    BackHandler {
        onBackPressed
    }
    val scrollState = rememberScrollState()
    Surface(
        modifier = modifier
            .verticalScroll(state = scrollState)
            .padding(contentPadding)
            .fillMaxSize()
    ) {
        Text(
            text = selectedRecipe.description,
            style = MaterialTheme.typography.bodySmall,
            modifier = modifier.padding(16.dp)
        )
    }
}

@Composable
private fun RecipeListAndDetail(
    recipes: List<Recipe>,
    selectedRecipe: Recipe,
    onClick: (Recipe) -> Unit,
    onBackPressed: () -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(16.dp)
) {
    Row(
        modifier = modifier
    ) {
        RecipeList(
            recipes = recipes,
            onClick = onClick,
            contentPadding = contentPadding,
            modifier = Modifier
                .weight(2f)
                .padding(16.dp)
        )
        RecipeDetail(
            selectedRecipe = selectedRecipe,
            modifier = Modifier.weight(3f),
            contentPadding = PaddingValues(16.dp),
            onBackPressed = onBackPressed
        )
    }
}

@Preview(showBackground = false)
@Composable
fun RecipeItemPreview() {
    Lab3_RecipeExplorerTheme {
        RecipeListItem(
            recipe = RecipeData.defaultRecipe,
            onItemClick = {}
        )
    }
}

@Preview
@Composable
fun RecipeListPreview() {
    Lab3_RecipeExplorerTheme {
        RecipeList(
            RecipeData.getRecipeData(),
            onClick = { }
        )
    }
}

@Preview
@Composable
fun RecipeDetailPreview() {
    Lab3_RecipeExplorerTheme {
        RecipeDetail(
            selectedRecipe = RecipeData.defaultRecipe,
            onBackPressed = {}
        )
    }
}

@Preview
@Composable
fun RecipeListAndDetailPreview() {
    Lab3_RecipeExplorerTheme {
        RecipeListAndDetail(
            recipes = RecipeData.getRecipeData(),
            selectedRecipe = RecipeData.defaultRecipe,
            onClick = {},
            onBackPressed = {}
        )
    }
}