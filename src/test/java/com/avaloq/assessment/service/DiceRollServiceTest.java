package com.avaloq.assessment.service;

import com.avaloq.assessment.entity.DiceRoll;
import com.avaloq.assessment.repository.DiceRollRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class DiceRollServiceTest {

    @TestConfiguration
    static class DiceRollServiceImplTestContextConfiguration {

        @Bean
        public DiceRollService diceRollService() {
            return new DiceRollServiceImpl();
        }
    }

    @Autowired
    private DiceRollService diceRollService;

    @MockBean
    private DiceRollRepository diceRollRepository;

    @Before
    public void setUp() {
        List<DiceRoll> testDiceRollList = new ArrayList<>();
        DiceRoll testDiceRoll = new DiceRoll(3, 6, 9, 1);
        testDiceRollList.add(testDiceRoll);

        Mockito.when(diceRollRepository.findByDiceNumberAndSideDiceNumber(3, 6))
                .thenReturn(testDiceRollList);
    }

    @Test
    public void getByDiceAndSide() {
        List<DiceRoll> found = diceRollService.getByDiceAndSide(3, 6);

        assertEquals(found.get(0).getDiceNumber(), 3);
        assertEquals(found.get(0).getSideDiceNumber(), 6);
    }

}
