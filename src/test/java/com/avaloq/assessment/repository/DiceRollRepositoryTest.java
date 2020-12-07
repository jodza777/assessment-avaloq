package com.avaloq.assessment.repository;

import com.avaloq.assessment.entity.DiceRoll;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class DiceRollRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private DiceRollRepository diceRollRepository;

    @Test
    public void findByDiceNumberAndSideDice() {

        DiceRoll testDiceRoll = new DiceRoll(3, 6, 3, 1);
        entityManager.persist(testDiceRoll);
        entityManager.flush();


        List<DiceRoll> found = diceRollRepository.findByDiceNumberAndSideDiceNumber(3,6);

        assertEquals(found.get(0).getDiceNumber(), testDiceRoll.getDiceNumber());
    }
}
