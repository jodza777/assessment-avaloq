package com.avaloq.assessment.rest;

import com.avaloq.assessment.entity.DiceRoll;
import com.avaloq.assessment.rest.model.DiceDistribution;
import com.avaloq.assessment.rest.model.DiceSimulation;
import com.avaloq.assessment.rest.model.DiceTotal;
import com.avaloq.assessment.service.DiceRollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@RestController
@Validated
@RequestMapping("/api")
public class DiceRollController {

    @Autowired
    private DiceRollService diceRollService;

    @GetMapping("/simulate")
    public DiceSimulation simulate(@RequestParam(value = "diceNumber", defaultValue = "3") @Min(1) int diceNumber,
                                   @RequestParam(value = "sideDiceNumber", defaultValue = "6")@Min(4) int sideDiceNumber,
                                   @RequestParam(value = "rollNumber", defaultValue = "100") @Min(1) int rollNumber){

        List<DiceRoll> diceRollList = new ArrayList<>();
        int simulationValue = diceRollService.getLastSimulationValue();
        simulationValue++;
        while(rollNumber > 0) {
            List<Integer> diceValueList = new ArrayList<>();
            for (int i = 0; i<diceNumber; i++) {
                int dice = (int)(Math.random()*sideDiceNumber) + 1;
                diceValueList.add(dice);
            }
            int sum = 0;
            for(int i = 0; i<diceValueList.size(); i++) {
                sum += diceValueList.get(i);
            }
            DiceRoll diceRoll = new DiceRoll(diceNumber, sideDiceNumber, sum, simulationValue);

            diceRoll = diceRollService.createDiceRoll(diceRoll);
            diceRollList.add(diceRoll);

            rollNumber--;
        }

        return new DiceSimulation(rollNumber, diceRollList);
    }

    @GetMapping("/searchAllSimulations")
    public DiceTotal searchAllSimulations(@RequestParam("diceNumber") @NonNull int diceNumber,
                                   @RequestParam("sideDice") @NonNull int sideDice){

        List<DiceRoll> diceRollList = diceRollService.getByDiceAndSide(diceNumber, sideDice);
        int totalCount = diceRollList.size();
        int simulationCount = diceRollList.stream()
                .filter(distinctByKey(dice -> dice.getSimulationNumber())).collect(Collectors.toList()).size();

        return  new DiceTotal(totalCount, simulationCount);
    }

    @GetMapping("/searchDistribution")
    public List<DiceDistribution> searchDistribution(@RequestParam("diceNumber") @NonNull int diceNumber,
                                                     @RequestParam("sideDice") @NonNull int sideDice) {

        List<DiceRoll> diceRollList = diceRollService.getByDiceAndSide(diceNumber, sideDice);
        int totalCount = diceRollList.size();

        List<DiceDistribution> diceDistributionList = new ArrayList<>();
        Set<Integer> sumValues = new HashSet<>();
        for(DiceRoll diceRoll : diceRollList) {
            sumValues.add(diceRoll.getSum());
        }

        for (int sum : sumValues) {
            List<DiceRoll> listBySum = diceRollList.stream()
                    .filter(distinctByKey(dice -> dice.getSum())).collect(Collectors.toList());
            double distribution = (listBySum.size()/totalCount)*100;
            DiceDistribution diceDistribution = new DiceDistribution(sum, distribution);
            diceDistributionList.add(diceDistribution);
        }


        return diceDistributionList;
    }

    public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor)
    {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }
}
