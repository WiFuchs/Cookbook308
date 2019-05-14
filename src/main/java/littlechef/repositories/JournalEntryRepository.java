package littlechef.repositories;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import littlechef.entities.JournalEntry;

@Repository
public interface JournalEntryRepository extends JpaRepository<JournalEntry, Long> {
	List<JournalEntry> findByUserID(Long id);
	Optional<JournalEntry> findByUserIDAndTimestamp(Long id, Date timestamp);
	Optional<JournalEntry> findFirst1ByUserIDOrderByTimestampDesc(Long id);

}