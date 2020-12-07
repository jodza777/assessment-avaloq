package com.avaloq.assessment.entity;

import javax.persistence.*;

@Entity
public class DiceRoll {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private int diceNumber;
    private int sideDiceNumber;
    private int sum;
    private int simulationNumber;

    public DiceRoll(int diceNumber, int sideDiceNumber, int sum, int simulationNumber){
        this.diceNumber = diceNumber;
        this.sideDiceNumber = sideDiceNumber;
        this.sum = sum;
    }

    public DiceRoll() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getDiceNumber() {
        return diceNumber;
    }

    public void setDiceNumber(int diceNumber) {
        this.diceNumber = diceNumber;
    }

    public int getSideDiceNumber() {
        return sideDiceNumber;
    }

    public void setSideDiceNumber(int sideDiceNumber) {
        this.sideDiceNumber = sideDiceNumber;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public int getSimulationNumber() {
        return simulationNumber;
    }

    public void setSimulationNumber(int simulationNumber) {
        this.simulationNumber = simulationNumber;
    }
}
