package littlechef.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import littlechef.entities.Ingredient;

@Repository
public interface AnnotationRepository extends JpaRepository<Ingredient, Long> {

}