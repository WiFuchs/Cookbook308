package repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import entities.Instruction;

public interface InstructionRepository extends JpaRepository<Instruction, Long> {

}