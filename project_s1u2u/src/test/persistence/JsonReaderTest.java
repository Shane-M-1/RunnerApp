package persistence;

import model.Runner;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// Tests are modelled after tests in JsonReaderTest class in JsonSerializationDemo
public class JsonReaderTest {
    Runner r;


    @Test
    void testNonExistentFile() {
        JsonReader reader = new JsonReader("./data/thisDoesNotExist.json");
        try {
            r = reader.read();
            fail("Expected an IOException");
        } catch (IOException io) {
            //expected
        }
    }

    @Test
    void testBlankRunner() {
        JsonReader reader = new JsonReader("./data/testReaderNoRunHistoryNoClubsRunner.json");
        try {
            r = reader.read();
            assertEquals("Shane", r.getUsername());
            assertEquals(0, r.getRunningHistory().getNumRuns());
            assertEquals(0, r.getClubs().size());
        } catch (IOException io) {
            fail("Unexpected error from reading file");
        }
    }

    @Test
    void testFullFieldsRunner() {
        JsonReader reader = new JsonReader("./data/testReaderRunnerWithAllFields.json");
        try {
            r = reader.read();
            assertEquals("Shane", r.getUsername());
            assertEquals(2, r.getRunningHistory().getNumRuns());
            assertEquals("Run#1", r.getRunningHistory().getNthRun(0).getName());
            assertEquals(5, r.getRunningHistory().getNthRun(0).getDistance());
            assertEquals("Run#2", r.getRunningHistory().getNthRun(1).getName());
            assertEquals(3.5, r.getRunningHistory().getNthRun(1).getDistance());

            assertEquals(2, r.getClubs().size());
            assertEquals("Shane'sClub", r.getClubs().get(0).getName());
            assertEquals(2, r.getClubs().get(0).getNumMembers());
           // assertEquals("Shane", r.getClubs().get(0).findMember("Shane").getUsername());
            assertEquals("Clubby", r.getClubs().get(1).getName());
            assertEquals(3, r.getClubs().get(1).getNumMembers());
        } catch (IOException io) {
            fail("Error reading file");
        }
    }
}
