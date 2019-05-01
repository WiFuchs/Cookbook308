package application;

import lombok.extern.slf4j.Slf4j;
import repositories.AnnotationRepository;
import repositories.JournalEntryRepository;
import repositories.RecipeRepository;
import repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
//import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import entities.*;
import java.util.*;


@Configuration
@Slf4j
class LoadDatabase {

	@Bean
	CommandLineRunner initDatabase(RecipeRepository recRepo, JournalEntryRepository journRepo, AnnotationRepository annotRepo, UserRepository userRepo) {
		
		
		Recipe rec = new Recipe("Chicken", "manual", 5, 45, true);
		recRepo.save(rec);
		
		Recipe rec2 = new Recipe("Chicken", "manual", 5, 45, true);
		Ingredient ing = new Ingredient(3, "cups", "third test");
		rec2.addIngredient(ing);
		Instruction instr = new Instruction("Do this thing");
		instr.addAnnotation(new InstructionAnnotation(1, "Im an annotations"));
		rec2.addStep(instr);
		rec2.setUserID(1);
		recRepo.save(rec2);
		
		JournalEntry journ = new JournalEntry(rec2, "vegan", 25, 35, 3, 5, "comment", new IngredientAnnotation(3, "ingredient annotation"));
		journRepo.save(journ);
		
		List<Recipe> recipes = new ArrayList<Recipe>();
		recipes.add(rec);
		recipes.add(rec2);
		List<JournalEntry> entries = new ArrayList<JournalEntry>();
		entries.add(journ);
		User user = new User("Alfredo Linguine", recipes, entries);
		userRepo.save(user);
		
		return args -> {
			log.info("\nseeded DB\n");
		};
	}
}