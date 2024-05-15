package model;


import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// Represents a running club having a name and a list of members, and a leader (the runner who created the club)
public class Club implements Writable {
    private String clubName;
    //private List<Runner> members;
    private List<String> members;
    private String leader;
    private int maxNumMembers;

    // EFFECTS: creates a new club with given name, given max number of members, its list of members consisting only
    // of the given leader, and list of member names consisting only of leader name
    public Club(String clubName, int maxNumMembers, String leader) {
        this.clubName = clubName;
        this.maxNumMembers = maxNumMembers;
        members = new ArrayList<>();
        this.leader = leader;
        members.add(leader);
    }

    public String getName() {
        return clubName;
    }

    public List<String> getMembers() {
        return members;
    }

    public int getMaxNumMembers() {
        return maxNumMembers;
    }

    // EFFECTS: returns number of members in this.members
    public int getNumMembers() {
        return members.size();
    }

//    public List<String> getMemberNames() {
//        List<String> memberNames = new ArrayList<>();
//        for (Runner r : members) {
//            memberNames.add(r.getUsername());
//        }
//        return memberNames;
//    }

    // MODIFIES: this
    // EFFECTS: adds given runner to list of members and list of memberNames
    public void addMember(String newMember) {
        members.add(newMember);
    }

    // REQUIRES: given runner exists in the club's list of members
    // MODIFIES: this
    // EFFECTS: removes given member from list of members
    public void removeMember(String leavingMember) {
        members.remove(leavingMember);

    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("clubName", clubName);
        json.put("members", stringsToJson());
        json.put("maxNumMembers", maxNumMembers);
        json.put("leader", leader);
        return json;
    }

    // Method was modelled after workroom.thingiesToJson() method in JsonSerializationDemo
    // EFFECTS: returns members of the club as a JSON array
    private JSONArray stringsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (String s : members) {
            jsonArray.put(s);
        }
        return jsonArray;
    }

//    // REQUIRES: given name must correspond to a runner that exists inside members
//    // EFFECTS: finds the matching runner given the name of the member
//    public Runner findMember(String member) {
//        boolean isFound = false;
//        Runner foundMember = null;
//        int index = 0;
//
//        while (!isFound) {
//            Runner currentMember = members.get(index);
//            if (member.equals(currentMember.getUsername())) {
//                foundMember = currentMember;
//                isFound = true;
//            } else {
//                index++;
//            }
//        }
//        return foundMember;
//    }


}
