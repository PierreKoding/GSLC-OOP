package Repository;

import java.util.*;

import Models.Team;
import Models.User;
import Utama.Connection;

public class UserRepository implements Repository {
	
	public ArrayList<User> userList = new ArrayList<User>();
	public ArrayList<String> teamInfo = new ArrayList<String>();
	
	public void stringToObject(ArrayList<String> dataUser) {
	    ArrayList<User> result = new ArrayList<User>();
	    Iterator<String> iterator = dataUser.iterator();

	    while (iterator.hasNext()) {
	        String user = iterator.next();
	        String[] splitData = user.split(",");
	        result.add(new User(splitData[1], splitData[0], Integer.parseInt(splitData[2])));
	    }

	    this.userList = result;
	    return;
	}
	
	public void getInfo_Team(Connection conn) {

		ArrayList<String> rawTeam = conn.ReadFile("Team");

		for (String team : rawTeam) {
			String[] splitData = team.split(",");
			Integer idTeam = Integer.parseInt(splitData[0]);
			while (teamInfo.size() <= idTeam) {
				teamInfo.add(new String());
			}
			this.teamInfo.add(idTeam, splitData[1]);
		}
	}
	
	public void getDataUser(Connection conn) {
		ArrayList<String> userCSV = new ArrayList<String>();
		userCSV = conn.ReadFile("User");
		stringToObject(userCSV);
	}
	
	public int getSign(String[] condition) {
	    return ("=".equals(condition[0])) ? 1 : 0;
	}
	
	public ArrayList<User> filterUser(String col, String[] condition) {
	    ArrayList<User> answer = new ArrayList<>();

	    for (User user : this.userList) {
	        if ("name".equals(col)) {
	            if (checkCondition(user.Nama, condition)) {
	                answer.add(user);
	            }
	        } else if ("id".equals(col)) {
	            if (checkCondition(String.valueOf(user.TeamID), condition)) {
	                answer.add(user);
	            }
	        } else if ("nim".equals(col)) {
	            if (checkCondition(user.NIM, condition)) {
	                answer.add(user);
	            }
	        }
	    }

	    return answer;
	}

	private boolean checkCondition(String actualValue, String[] condition) {
	    int sign = getSign(condition);
	    String requiredValue = condition[1];

	    if (sign == 1) {
	        return actualValue.equals(requiredValue);
	    } else if (sign == 0) {
	        return !actualValue.equals(requiredValue);
	    }

	    return false;
	}

	public Boolean validate(String col, String[] condition, Boolean join, String tableJoin, Connection conn) {
	    boolean isValid = true;
	    
	    while (isValid) {
	        if (col == null && condition != null) {
	            isValid = false;
	            break;
	        }
	        
	        if (col != null && condition == null) {
	            isValid = false;
	            break;
	        }
	        
	        if (col != null && (!col.equals("id") && !col.equals("name") && !col.equals("nim"))) {
	            isValid = false;
	            break;
	        }
	        
	        if (condition != null) {
	            if (condition.length != 2) {
	                isValid = false;
	                break;
	            }
	            
	            if (condition[0] == null || condition[1] == null) {
	                isValid = false;
	                break;
	            }
	            
	            if ((!condition[0].equals("=") && !condition[0].equals("!="))) {
	                isValid = false;
	                break;
	            }
	        }
	        
	        if (!join && tableJoin != null) {
	            isValid = false;
	            break;
	        }
	        
	        if (join) {
	            if (tableJoin == null || !tableJoin.equals("Team")) {
	                isValid = false;
	                break;
	            }
	        }
	        
	        if (conn == null) {
	            isValid = false;
	            break;
	        }
	        
	        break; // Exit the loop if all conditions pass
	    }
	    
	    return isValid;
	}


    public void find(String col, String[] condition, Boolean join, String tableJoin, Connection conn) {
//        if(validate(col, condition, join, tableJoin, conn).equals(false)){
//			System.out.println("Error: Wrong Condition or Parameter");
//			return;
//		}

        this.getDataUser(conn);
        this.getInfo_Team(conn);
		if(condition == null) {
			if(join.equals(false)){
				System.out.println("ID| User NIM   | User Name");
				System.out.println("-----------------------------");
				for (User user : userList) {
					System.out.print(user.TeamID);
					System.out.print(" | " + user.NIM);
					System.out.print(" | " + user.Nama);
					System.out.println();
				}
			}else{
				User user_curr = new User(null, null, null);
				for(int i=0; i < userList.size(); i++){
					user_curr = userList.get(i);
					if(i==0) {
						System.out.println("ID| User NIM   | User Name   | Team Name");
						System.out.println("-----------------------------------------------");
					}
					System.out.print(user_curr.TeamID);
					System.out.print(" | " + user_curr.NIM);
					System.out.print(" | " + user_curr.Nama);
					System.out.print(" | " + teamInfo.get(user_curr.TeamID));
					System.out.println();
				}
			}
		}
		else if(condition != null){
			ArrayList<User> Answer = filterUser(col, condition);
			User user_curr = new User(null, null, null);
			for(int i = 0; i < Answer.size(); i++){
				user_curr = Answer.get(i);
				if(join == true && validate(col, condition, join, tableJoin, conn).equals(true)) {
					if(i==0) {
						System.out.println("ID| User NIM   | User Name   | Team Name");
						System.out.println("-----------------------------------------------");
					}
					System.out.print(user_curr.TeamID);
					System.out.print(" | " + user_curr.NIM);
					System.out.print(" | " + user_curr.Nama);
					System.out.print(" | " + teamInfo.get(user_curr.TeamID));
					System.out.println();
				}else { 
					if(i == 0) {
						System.out.println("ID| User NIM   | User Name ");
						System.out.println("------------------------------");
					}
					System.out.print(user_curr.TeamID);
					System.out.print(" | " + user_curr.NIM);
					System.out.print(" | " + user_curr.Nama);
					System.out.println();
				}	
			}
		}
        return;
    }

    public User findOne(String col, String[] condition, Boolean join, String tableJoin, Connection conn) {
		if(validate(col, condition, join, tableJoin, conn).equals(false)){
			System.out.println("Error: Wrong Condition");
			return null;
		}

	    this.getDataUser(conn);
	    ArrayList<User> Answer = filterUser(col, condition);
	    if(Answer.size() == 0) return null;
	    
	    User userResult = Answer.get(0);
	    return userResult;
	}

	
	public void insert(String[] dataUser, Connection conn) {
    	
    	TeamRepository teamRepo = new TeamRepository();
    	teamRepo.getDataTeam(conn);
    	teamRepo.Fill_TeamMember(conn);
    	
    	Integer idTim = 0;
    	for (Team team : teamRepo.teamList) {
			if(team.NamaTeam.equals(dataUser[2])) {
				idTim = team.TeamID;
			}
		}
    	
    	if(idTim.equals(0)) { 
    		String[] teamData = new String[]{dataUser[2]};
    		teamRepo.insert(teamData, conn); 
    		
    		teamRepo.getDataTeam(conn); 
    		
    		Integer newTeamID=0; 
    		for (Team team : teamRepo.teamList) {
				if(team.NamaTeam.equals(dataUser[2])) {
					newTeamID = team.TeamID;
				}
			}
    		
    		String passInsert = dataUser[1] + "," + dataUser[0] + "," + newTeamID.toString();
    		conn.WriteFile("User", passInsert);
    		
    		System.out.println("New User: Registered");
    		
    	}else {
    		
    		if(teamRepo.teamMember.get(idTim).size() >= 3 ) {
    			System.out.println("The team is full.");
    			return;
    		}
    		
    		for (Team team : teamRepo.teamList) {
				if(team.NamaTeam.equals(dataUser[2])) {
					idTim = team.TeamID;
				}
			}
    		String passInsert = dataUser[1] + "," + dataUser[0] + "," + idTim.toString();
    		conn.WriteFile("User", passInsert);
    		
    		System.out.println("New User: Registered");
    	}
    	
    }




}