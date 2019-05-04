package littlechef.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import littlechef.entities.Instruction;

@Repository
public interface InstructionRepository extends JpaRepository<Instruction, Long> {

}