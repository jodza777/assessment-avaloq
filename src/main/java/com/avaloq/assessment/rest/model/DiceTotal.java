package com.avaloq.assessment.rest.model;

public class DiceTotal {

    private final int totalSimultaion;
    private final int totalRols;

    public DiceTotal(int totalRols, int totalSimultaion){
        this.totalRols = totalRols;
        this.totalSimultaion = totalSimultaion;
    }

    public int getTotalSimultaion() {
        return totalSimultaion;
    }

    public int getTotalRols() {
        return totalRols;
    }
}
