package persistence;


import model.Club;
import model.Run;
import model.Runner;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// Tests are modelled after tests in JsonReaderTest class in JsonSerializationDemo
public class JsonWriterTest {
    Runner r;


    @Test
    void testWriterInvalidFile() {
        r = new Runner("Shane");
        try {
            JsonWriter writer = new JsonWriter("./data/thisDoes\0NotExist.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException io) {
            //expected
        }
    }

    @Test
    void testWriterBlankRunner() {
        r = new Runner("Shane");
        try {
            JsonWriter writer = new JsonWriter("./data/testWriterBlankRunner.json");
            writer.open();
            writer.write(r);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterBlankRunner.json");
            r = reader.read();

            assertEquals("Shane", r.getUsername());
            assertEquals(0, r.getClubs().size());
            assertEquals(0, r.getRunningHistory().getNumRuns());
        } catch (IOException io) {
            fail("Unexpected exception");
        }
    }

    @Test
    void testWriterFullFieldRunner() {
        Run r1 = new Run(5, 22.4, "10/23/23", "Run#1");
        Run r2 = new Run(3.2, 13.07, "12/08/24", "Run#2");
        Club c1 = new Club("Shane'sClub", 4, "Shane");
        Club c2 = new Club("Clubby", 3, "Shane");
        r = new Runner("Shane");
        Runner jack = new Runner("Jack");
        Runner jill = new Runner("Jill");
        r.getRunningHistory().addRun(r1);
        r.getRunningHistory().addRun(r2);
        r.joinClub(c1);
        r.joinClub(c2);
        c2.addMember(jack.getUsername());
        c2.addMember(jill.getUsername());
        try {
            JsonWriter writer = new JsonWriter("./data/testWriterFullRunner.json");
            writer.open();
            writer.write(r);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterFullRunner.json");
            r = reader.read();

            assertEquals("Shane", r.getUsername());
            assertEquals(2, r.getClubs().size());
            assertEquals(2, r.getRunningHistory().getNumRuns());
            assertEquals(1, r.getClubs().get(0).getNumMembers());
            assertEquals(3, r.getClubs().get(1).getNumMembers());
            assertEquals("Jack", r.getClubs().get(1).getMembers().get(1));
            assertEquals(5, r.getRunningHistory().getLongestRun().getDistance());
            assertEquals(13.07, r.getRunningHistory().getFastestRun().getTime());
        } catch (IOException io) {
            fail("Unexpected exception throw");
        }
    }

}
