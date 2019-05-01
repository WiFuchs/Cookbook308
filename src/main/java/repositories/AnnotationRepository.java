package repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import entities.Ingredient;

public interface AnnotationRepository extends JpaRepository<Ingredient, Long> {

}