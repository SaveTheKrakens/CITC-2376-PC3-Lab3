package com.savethekrakens.lab3_recipeexplorer.ui

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.lifecycle.ViewModel
import com.savethekrakens.lab3_recipeexplorer.data.RecipeData
import com.savethekrakens.lab3_recipeexplorer.model.Recipe
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class RecipesViewModel : ViewModel() {

    var windowSize: WindowWidthSizeClass = WindowWidthSizeClass.Compact
        private set

    fun updateWindowSize(newSize: WindowWidthSizeClass) {
        windowSize = newSize
    }

    private val _uiState = MutableStateFlow(
        RecipesUiState(
            recipesList = RecipeData.getRecipeData(),
            currentRecipe = RecipeData.getRecipeData().getOrElse(0) {
                RecipeData.defaultRecipe
            }
        )
    )

    val uiState: StateFlow<RecipesUiState> = _uiState

    fun updateCurrentRecipe(selectedRecipe: Recipe) {
        _uiState.update {
            it.copy(currentRecipe = selectedRecipe)
        }
    }

    fun updateIsShowingListPage(isShowingListPage: Boolean) {
        _uiState.update {
            it.copy(isShowingListPage = isShowingListPage)
        }
    }

}

data class RecipesUiState(
    val recipesList: List<Recipe> = emptyList(),
    val currentRecipe: Recipe = RecipeData.defaultRecipe,
    val isShowingListPage: Boolean = true
)