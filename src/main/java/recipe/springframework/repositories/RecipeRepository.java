package recipe.springframework.repositories;

import org.springframework.data.repository.CrudRepository;

import recipe.springframework.domain.Recipe;

/**
 * Created by jt on 6/13/17.
 */
public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}
