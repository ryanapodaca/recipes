package com.recipes.demo.repos;

import com.recipes.demo.models.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepo extends JpaRepository<Recipe, Long> {
    public Recipe getRecipeByIngredients(String ingredients);
}
