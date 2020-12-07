package com.avaloq.assessment.repository;

import com.avaloq.assessment.entity.DiceRoll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiceRollRepository extends JpaRepository<DiceRoll, Long> {

    List<DiceRoll> findByDiceNumberAndSideDiceNumber(int diceNumber, int sideDiceNumber);

    DiceRoll findTopByOrderByIdDesc();
}
