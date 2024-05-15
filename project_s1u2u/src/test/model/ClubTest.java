package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClubTest {
    private Club testClub;
    private Runner leader;
    private Runner r1;
    private Runner r2;

    @BeforeEach
    void runBefore() {
        leader = new Runner("leader");
        testClub = new Club("testClub", 4, leader.getUsername());
        r1 = new Runner("r1");
        r2 = new Runner("r2");
    }

    @Test
    void testConstructor() {
        assertEquals(4, testClub.getMaxNumMembers());
        assertEquals(1, testClub.getNumMembers());
        assertEquals(leader.getUsername(), testClub.getMembers().get(0));
        assertEquals("testClub", testClub.getName());
        //assertEquals(1, testClub.getMemberNames().size());
        //assertEquals("leader", testClub.getMemberNames().get(0));

    }


    @Test
    void testAddOneMember() {
        testClub.addMember(r1.getUsername());
        List<String> members = testClub.getMembers();

        assertEquals(r1.getUsername(), members.get(1));
        assertEquals(2, members.size());
    }


    @Test
    void testAddMultipleMembers() {
        testClub.addMember(r1.getUsername());
        testClub.addMember(r2.getUsername());
        List<String> members = testClub.getMembers();

        assertEquals(r1.getUsername(), members.get(1));
        assertEquals(r2.getUsername(), members.get(2));
        assertEquals(3, members.size());
    }

    @Test
    void testRemoveOneMember() {
        testClub.addMember(r1.getUsername());
        testClub.removeMember(r1.getUsername());

        assertEquals(1, testClub.getNumMembers());
        assertEquals(leader.getUsername(), testClub.getMembers().get(0));
    }

    @Test
    void testRemoveMultipleMembers() {
        testClub.addMember(r1.getUsername());
        testClub.addMember(r2.getUsername());
        testClub.removeMember(r1.getUsername());
        testClub.removeMember(r2.getUsername());

        assertEquals(1, testClub.getNumMembers());
        assertEquals(leader.getUsername(), testClub.getMembers().get(0));
    }

//    @Test
//    void testFindMemberInSmallList() {
//        Runner found = testClub.findMember("leader");
//        assertEquals(leader, found);
//    }
//
//    @Test
//    void testFindMember() {
//        testClub.addMember(r1);
//        testClub.addMember(r2);
//        Runner found = testClub.findMember("r2");
//        assertEquals(r2, found);
//    }
}
