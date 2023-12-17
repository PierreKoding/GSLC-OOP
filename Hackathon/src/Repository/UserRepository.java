package Repository;

import java.util.*;

import Models.Models;
import Models.Team;
import Models.User;
import Utama.Connection;

public class UserRepository implements Repository {

	@Override
	public ArrayList<Models> find(String column, String[] condition, Boolean join, String joinTable, Connection connection) {
	
		if (column == null && condition != null) {
            System.err.println("Error: Column is null, but conditions are specified.");
            return null;
        }

        if (condition == null && column != null) {
            System.err.println("Error: Conditions are null, but column is specified.");
            return null;
        }

        if (joinTable == null && Boolean.TRUE.equals(join)) {
            System.err.println("Error: Join table is null, but join condition is true.");
            return null;
        }

        if (joinTable != null && join == null) {
            System.err.println("Error: Join condition is null, but join table is specified.");
            return null;
        }

        if ((column != null && column.isEmpty()) || (joinTable != null && joinTable.isEmpty())) {
            System.err.println("Error: Column or join table name is empty.");
            return null;
        }

        ArrayList<Models> result = new ArrayList<>();
        ArrayList<String> userData = connection.ReadFile("User");

        for (String line : userData) {
            String[] fields = line.split(",");

            if (condition == null || (fields.length > 0 && fields[0].equals(condition[1]))) {
                User user = new User(fields[1], fields[0], Integer.parseInt(fields[2]));

                if (join != null && join && joinTable != null && joinTable.equals("Team")) {
                    ArrayList<Models> teams = new TeamRepository().find("NamaTeam", new String[]{"=", fields[2]}, false, null, connection);
                    if (!teams.isEmpty()) {
                        user.Nama = ((Team) teams.get(0)).NamaTeam;
                    }
                }
                result.add(user);
            }
        }

        return result.isEmpty() ? null : result;
    }


	@Override
	public Models findOne(String col, String[] condition, boolean join, String table, Connection conn) {
		// Check for errors and return null if conditions are not met
	    if (col == null && condition != null) {
	        System.err.println("Error: Column is null, but conditions are specified.");
	        return null;
	    }

	    if (condition == null && col != null) {
	        System.err.println("Error: Conditions are null, but column is specified.");
	        return null;
	    }

	    if (table == null && join) {
	        System.err.println("Error: Join table is null, but join condition is true.");
	        return null;
	    }

	    if (table != null && !join) {
	        System.err.println("Error: Join condition is false, but join table is specified.");
	        return null;
	    }

	    if ((col != null && col.isEmpty()) || (table != null && table.isEmpty())) {
	        System.err.println("Error: Column or join table name is empty.");
	        return null;
	    }

	    ArrayList<String> userData = conn.ReadFile("User");

	    for (String line : userData) {
	        String[] fields = line.split(",");

	        if (condition == null || (fields.length > 0 && fields[0].equals(condition[1]))) {
	            User user = new User(fields[1], fields[0], Integer.parseInt(fields[2]));

	            if (join && table != null && table.equals("Team")) {
	                ArrayList<Models> teams = new TeamRepository().find("NamaTeam", new String[]{"=", fields[2]}, false, null, conn);
	                if (!teams.isEmpty()) {
	                    user.Nama = ((Team) teams.get(0)).NamaTeam;
	                }
	            }

	            return user;
	        }
	    }

	    return null;
	}

	@Override
	public void insert(String[] userData, Connection conn) {
	    if (userData == null || userData.length != 3) {
	        System.err.println("Error: Invalid user data. Must provide an array with three elements.");
	        return;
	    }

	    ArrayList<String> currentData = conn.ReadFile("User");

	    int currentMaxTeamID = 0;
	    for (String line : currentData) {
	        String[] fields = line.split(",");
	        if (fields.length > 2) {
	            int teamID = Integer.parseInt(fields[2]);
	            if (teamID > currentMaxTeamID) {
	                currentMaxTeamID = teamID;
	            }
	        }
	    }

	    int newTeamID = currentMaxTeamID + 1;

	    String newUserLine = String.join(",", userData[1], userData[0], Integer.toString(newTeamID));

	    conn.WriteFile("User", newUserLine);

	    System.out.println("User inserted successfully.");
	}
	
	

}