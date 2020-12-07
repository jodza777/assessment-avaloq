package com.avaloq.assessment.service;

import com.avaloq.assessment.entity.DiceRoll;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DiceRollService {

    public DiceRoll createDiceRoll(DiceRoll diceRoll);

    public List<DiceRoll> getByDiceAndSide(int diceNumber, int sideDice);

    public int getLastSimulationValue();
}
