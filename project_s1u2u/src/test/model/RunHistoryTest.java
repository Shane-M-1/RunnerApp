package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RunHistoryTest {
    private RunHistory testHistory;
    private Run r1;
    private Run r2;
    private Run r3;
    private Run r4;
    private Run r5;



    @BeforeEach
    void runBefore() {
        testHistory = new RunHistory();
        r1 = new Run(3,4.5,"September 10th 2023", "r1");
        r2 = new Run(5,6,"October 10th 2023", "r2");
        r3 = new Run(3, 4.5, "January 5th 2023", "r3");
        r4 = new Run(5, 8, "September 3rd", "r4");
        r5 = new Run(10, 16, "September 23rd", "r5");
    }


    @Test
    void testConstructor() {
        assertEquals(0, testHistory.getNumRuns());
        assertEquals(0, testHistory.getNames().size());
    }

    @Test
    void testAddOneRun() {
        testHistory.addRun(r1);

        assertEquals(1, testHistory.getNumRuns());
        assertEquals(r1, testHistory.getNthRun(0));
    }

    @Test
    void testAddMultipleRuns() {
        testHistory.addRun(r1);
        testHistory.addRun(r2);

        List<String> names = new ArrayList<>();
        names.add(r1.getName());
        names.add(r2.getName());

        assertEquals(2, testHistory.getNumRuns());
        assertEquals(r1, testHistory.getNthRun(0));
        assertEquals(r2, testHistory.getNthRun(1));
        assertEquals(names, testHistory.getNames());
    }

    @Test
    void testGetFastRunTie() {
        testHistory.addRun(r1);
        testHistory.addRun(r3);

        Run fastestRun = testHistory.getFastestRun();

        assertEquals(fastestRun, r1);

    }

    @Test
    void testGetFirstFastestRun() {
        testHistory.addRun(r2);
        testHistory.addRun(r3);
        testHistory.addRun(r1);

        Run fastestRun = testHistory.getFastestRun();

        assertEquals(fastestRun, r3);

    }

    @Test
    void testGetFirstLongestRun() {
        testHistory.addRun(r1);
        testHistory.addRun(r3);
        testHistory.addRun(r2);

        Run longestRun = testHistory.getLongestRun();

        assertEquals(longestRun, r2);

    }


    @Test
    void testGetLongestRunTie() {
        testHistory.addRun(r4);
        testHistory.addRun(r2);

        Run longestRun = testHistory.getLongestRun();

        assertEquals(longestRun, r4);

    }

    @Test
    void testGetPaceTie() {
        testHistory.addRun(r5);
        testHistory.addRun(r4);

        Run pacestRun = testHistory.getFastestPaceRun();

        assertEquals(pacestRun, r5);

    }

    @Test
    void testGetPaceNotATie() {
        testHistory.addRun(r2);
        testHistory.addRun(r4);
        testHistory.addRun(r1);

        Run pacestRun = testHistory.getFastestPaceRun();

        assertEquals(r2, pacestRun);

    }

    @Test
    void testFindRun() {
        testHistory.addRun(r1);
        testHistory.addRun(r2);
        testHistory.addRun(r3);

        Run test1 = testHistory.findRun("r1");
        Run test2 = testHistory.findRun("r2");
        Run test3 = testHistory.findRun("r3");

        assertEquals(test1, r1);
        assertEquals(test2, r2);
        assertEquals(test3, r3);
    }
}
