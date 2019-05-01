package repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import entities.Ingredient;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

}