package com.avaloq.assessment.service;

import com.avaloq.assessment.entity.DiceRoll;
import com.avaloq.assessment.repository.DiceRollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;

import java.util.List;
import java.util.Optional;

public class DiceRollServiceImpl implements DiceRollService {

    @Autowired
    private DiceRollRepository diceRollRepository;


    @Override
    public DiceRoll createDiceRoll(DiceRoll diceRoll) {
        Optional<DiceRoll> diceRollExsist = diceRollRepository.findById(diceRoll.getId());
        if (diceRollExsist.isPresent()) {
            DiceRoll newDiceRoll = diceRollExsist.get();
            newDiceRoll.setDiceNumber(diceRoll.getDiceNumber());
            newDiceRoll.setSideDiceNumber(diceRoll.getSideDiceNumber());
            newDiceRoll.setSum(diceRoll.getSum());
            newDiceRoll.setSimulationNumber(diceRoll.getSimulationNumber());

            newDiceRoll = diceRollRepository.save(newDiceRoll);

            return newDiceRoll;
        } else {
            diceRoll = diceRollRepository.save(diceRoll);
            return diceRoll;
        }
    }

    @Override
    public List<DiceRoll> getByDiceAndSide(int diceNumber, int sideDice) {
        return diceRollRepository.findByDiceNumberAndSideDiceNumber(diceNumber, sideDice);
    }

    @Override
    public int getLastSimulationValue() {
        DiceRoll diceRoll = diceRollRepository.findTopByOrderByIdDesc();
        if (diceRoll != null) {
            return diceRoll.getSimulationNumber();
        } else {
            return 0;
        }
    }
}
