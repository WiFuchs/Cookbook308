package littlechef.application;

import lombok.extern.slf4j.Slf4j;


import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import littlechef.entities.*;
import littlechef.repositories.AnnotationRepository;
import littlechef.repositories.JournalEntryRepository;
import littlechef.repositories.RecipeRepository;
import littlechef.repositories.ApplicationUserRepository;

/*
 * unused imports, saved for future use?
 * 
 * import java.util.*;
 * import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
 * import org.springframework.stereotype.Component;
 * import org.slf4j.LoggerFactory;
 * import java.util.logging.Logger;
 */

@Configuration
@Slf4j
class LoadDatabase {

	@Bean
	CommandLineRunner initDatabase(RecipeRepository recRepo, JournalEntryRepository journRepo, AnnotationRepository annotRepo, ApplicationUserRepository userRepo) {
		
		
		Recipe rec = new Recipe("Chicken", "manual", 5, 45, 0, true);
		recRepo.save(rec);
		
		Recipe rec2 = new Recipe("Chicken", "manual", 5, 45, 0, true);
		Ingredient ing = new Ingredient(3, "cups", "third test");
		rec2.addIngredient(ing);
		Instruction instr = new Instruction("Do this thing");
		rec2.addStep(instr);
		rec2.setUserID(1);
		recRepo.save(rec2);
		
//		List<Recipe> recipes = new ArrayList<>();
//		recipes.add(rec);
//		recipes.add(rec2);
//		List<JournalEntry> entries = new ArrayList<>();
//		entries.add(journ);
		
		return args -> log.info("\nseeded DB\n");
	}
}