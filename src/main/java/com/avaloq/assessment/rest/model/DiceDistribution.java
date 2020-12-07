package com.avaloq.assessment.rest.model;

public class DiceDistribution {

    private final int sum;
    private final double distribution;

    public DiceDistribution(int sum, double distribution){
        this.sum = sum;
        this.distribution = distribution;
    }

    public int getSum() {
        return sum;
    }

    public double getDistribution() {
        return distribution;
    }
}
