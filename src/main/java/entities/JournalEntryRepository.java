package entities;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JournalEntryRepository extends JpaRepository<JournalEntry, Long> {
	Optional<JournalEntry> findByUserAndTimestamp(Long id, Date timestamp);
	Optional<JournalEntry> findFirst1ByUserOrderByTimestampDesc(Long id);

}