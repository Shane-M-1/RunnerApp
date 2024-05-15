package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class RunTest {
    private Run testRun;


    @BeforeEach
    void runBefore() {
        testRun = new Run(5.5, 10, "08-10-23", "test");

    }

    @Test
    void testConstructor() {
        assertEquals(5.5, testRun.getDistance());
        assertEquals(10, testRun.getTime());
        assertEquals("test", testRun.getName());
        assertEquals("08-10-23", testRun.getDate());

    }


}