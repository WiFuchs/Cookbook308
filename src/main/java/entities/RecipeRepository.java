package entities;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
	List<Recipe> findByUserID(long id);
	Optional<Recipe> findByUserIDAndId(Long id, Long rid);
}