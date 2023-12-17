package Repository;

import Models.Models;
import Models.Team;
import Utama.Connection;

import java.util.ArrayList;

public class TeamRepository implements Repository {

	@Override
	public ArrayList<Models> find(String column, String[] condition, Boolean join, String joinTable, Connection connection) {
		ArrayList<Models> result = new ArrayList<>();

	    ArrayList<String> teamData = connection.ReadFile("Team");

	    for (String line : teamData) {
	        String[] fields = line.split(",");

	        if (condition == null || (fields.length > 0 && fields[0].equals(condition[1]))) {
	            Team team = new Team(Integer.parseInt(fields[0]), fields[1]);
	            result.add(team);
	        }
	    }

	    return result.isEmpty() ? null : result;
	}

	@Override
	public Models findOne(String col, String[] condition, boolean join, String table, Connection conn) {
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

	    ArrayList<String> teamData = conn.ReadFile("Team");

	    for (String line : teamData) {
	        String[] fields = line.split(",");

	        if (condition == null || (fields.length > 0 && fields[0].equals(condition[1]))) {
	            return new Team(Integer.parseInt(fields[0]), fields[1]);
	        }
	    }

	    return null;
	}

	@Override
	public void insert(String[] teamName, Connection conn) {
		Connection connection = new Connection();
        if (teamName == null || teamName.length != 1) {
            System.err.println("Error: Invalid team data. Must provide an array with one element.");
            return;
        }

        String newTeamLine = teamName[0];

        connection.WriteFile("Team", newTeamLine);

        System.out.println("Team inserted successfully.");
	}
}