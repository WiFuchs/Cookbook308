package littlechef.repositories;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import littlechef.entities.JournalEntry;

@Repository
public interface JournalEntryRepository extends JpaRepository<JournalEntry, Long> {
	Optional<JournalEntry> findByUserAndTimestamp(Long id, Date timestamp);
	Optional<JournalEntry> findFirst1ByUserOrderByTimestampDesc(Long id);

}