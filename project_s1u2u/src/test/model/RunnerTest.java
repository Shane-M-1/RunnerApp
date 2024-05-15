package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RunnerTest {
    private Runner testRunner;
    private Club testClub;



    @BeforeEach
    void runBefore() {
        testRunner = new Runner("test");
        Runner leader = new Runner("leader");
        testClub = new Club("testClub", 5, leader.getUsername());
    }

    @Test
    void testConstructor() {
        assertEquals("test", testRunner.getUsername());
        assertEquals(0, testRunner.getRunningHistory().getNumRuns());
        assertEquals(0, testRunner.getClubs().size());
    }

    @Test
    void testJoinClub() {
        testRunner.joinClub(testClub);
        List<Club> clubs = testRunner.getClubs();
        assertEquals(1, clubs.size());
        assertEquals(testClub, clubs.get(0));
    }

    @Test
    void testLeaveClub() {
        testRunner.joinClub(testClub);
        testRunner.leaveClub(testClub);
        List<Club> clubs = testRunner.getClubs();
        assertEquals(0, clubs.size());
    }


}

