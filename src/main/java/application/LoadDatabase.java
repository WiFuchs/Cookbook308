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
import entities.IngredientAnnotation;
import entities.IngredientRepository;
import entities.Instruction;
import entities.JournalEntry;
import entities.JournalEntryRepository;
import entities.Recipe;
import entities.RecipeRepository;


@Configuration
@Slf4j
class LoadDatabase {

	@Bean
	CommandLineRunner initDatabase(RecipeRepository recRepo, JournalEntryRepository journRepo) {
		
		Recipe rec = new Recipe("Chicken", "manual", 5, 45, true);
		recRepo.save(rec);
		
		Recipe rec2 = new Recipe("Chicken", "manual", 5, 45, true);
		Ingredient ing = new Ingredient(3, "cups", "third test");
		rec2.addIngredient(ing);
		Instruction instr = new Instruction("Do this thing");
		rec2.addStep(instr);
		recRepo.save(rec2);
		
		JournalEntry journ = new JournalEntry(rec2, "vegan", 25, 35, 3, 5, new IngredientAnnotation());
		journRepo.save(journ);
		
		return args -> {
			log.info("\nseeded DB\n");
		};
	}
}