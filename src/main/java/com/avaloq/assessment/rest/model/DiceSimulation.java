package com.avaloq.assessment.rest.model;

import com.avaloq.assessment.entity.DiceRoll;

import java.util.List;

public class DiceSimulation {

    private final long totalCount;
    private final List<DiceRoll> diceRollList;

    public DiceSimulation(long totalCount, List<DiceRoll> diceRollList){
        this.totalCount = totalCount;
        this.diceRollList = diceRollList;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public List<DiceRoll> getDiceRollList() {
        return diceRollList;
    }
}
