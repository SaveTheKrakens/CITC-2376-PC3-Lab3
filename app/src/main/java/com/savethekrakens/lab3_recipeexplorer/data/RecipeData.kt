package com.savethekrakens.lab3_recipeexplorer.data

import com.savethekrakens.lab3_recipeexplorer.model.Recipe

object RecipeData {
    val defaultRecipe = getRecipeData()[0]

    fun getRecipeData(): List<Recipe> {
        return listOf(
            Recipe(
                id = 1,
                title = "Beef Tacos",
                description = "Mexican-style tacos with seasoned beef, fresh toppings, and soft tortillas. \n" +
                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, " +
                        "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. " +
                        "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris " +
                        "nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in " +
                        "reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. " +
                        "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia " +
                        "deserunt mollit anim id est laborum."
            ),
            Recipe(
                id = 2,
                title = "Chicken Enchiladas",
                description = "Corn tortillas filled with shredded chicken, topped with a rich enchilada sauce and melted cheese. \n" +
                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, " +
                        "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. " +
                        "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris " +
                        "nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in " +
                        "reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. " +
                        "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia " +
                        "deserunt mollit anim id est laborum."
            ),
            Recipe(
                id = 3,
                title = "Guacamole",
                description = "A creamy avocado dip mixed with lime, cilantro, onion, and a hint of jalapeño. \n" +
                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, " +
                        "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. " +
                        "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris " +
                        "nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in " +
                        "reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. " +
                        "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia " +
                        "deserunt mollit anim id est laborum."
            ),
            Recipe(
                id = 4,
                title = "Chiles Rellenos",
                description = "Poblano peppers stuffed with cheese or meat, battered, and fried to perfection. \n" +
                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, " +
                        "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. " +
                        "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris " +
                        "nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in " +
                        "reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. " +
                        "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia " +
                        "deserunt mollit anim id est laborum."
            ),
            Recipe(
                id = 5,
                title = "Pozole",
                description = "A traditional Mexican soup made with hominy, pork, and a flavorful red chili broth. \n" +
                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, " +
                        "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. " +
                        "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris " +
                        "nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in " +
                        "reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. " +
                        "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia " +
                        "deserunt mollit anim id est laborum."
            )
        )
    }
}