package application;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import entities.Ingredient;
import entities.IngredientRepository;
import entities.Recipe;
import entities.RecipeRepository;


@Configuration
@Slf4j
class LoadDatabase {

	@Bean
	CommandLineRunner initDatabase(RecipeRepository recRepo) {
		
		Recipe rec = new Recipe("Chicken", "manual", 5, 45, new Ingredient(3, "cups", "testing"), new Ingredient(1, "Tbsp", "something"));
		recRepo.save(rec);
		
		Recipe rec2 = new Recipe("Chicken", "manual", 5, 45);
		Ingredient ing = new Ingredient(3, "cups", "third test");
		rec2.addIngredient(ing);
		recRepo.save(rec2);
		
		return args -> {
			log.info("\nseeded DB\n");
		};
	}
}