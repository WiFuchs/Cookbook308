package littlechef.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import littlechef.entities.Recipe;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
	List<Recipe> findByUserID(long id);
	Optional<Recipe> findByUserIDAndId(Long id, Long rid);
}