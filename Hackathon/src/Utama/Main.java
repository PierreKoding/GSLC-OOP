package Utama;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import Models.User;
import Repository.UserRepository;
import Repository.TeamRepository;

public class Main {

	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Connection conn = new Connection();
//		
//		UserRepository urep = new UserRepository();
//		
//		urep.find(null, null, null, null, conn);
		
		
//		conn.WriteFile("Team", "6,Hehehe");
		
		
		
		
//		ArrayList<User> userList = new ArrayList<User>();
//		
//		ArrayList<String> userCSV = new ArrayList<String>();
//		userCSV = conn.ReadFile("User");
//
//
//		ArrayList<User> result = new ArrayList<User>();
//	    
//	    for (String userData : userCSV) {
//	        String[] splitData = userData.split(",");
//	        result.add(new User(splitData[1], splitData[0], Integer.parseInt(splitData[2])));
//	    }
//
//	    System.out.println(result);
//	    
//	    userList = result;
//		
//		System.out.println("ID| User NIM   | User Name");
//		System.out.println("-----------------------------");
//		for (User user : userList) {
//			System.out.print(user.TeamID);
//			System.out.print(" | " + user.NIM);
//			System.out.print(" | " + user.Nama);
//			System.out.println();
//		}
		
		
		
		
		
		
		// Read 1
//		Connection connection = new Connection();
//		
//		ArrayList<String> teamFileContents = connection.ReadFile("Team");
//       System.out.println("Team file contents:");
//       for (String line : teamFileContents) {
//           System.out.println(line);
//       }
		
		// Read 2
	// 	ArrayList<String> rawData = new ArrayList<String>();
	// 	try (BufferedReader reader = new BufferedReader(new FileReader("teams.csv"))) {
    //        String line;
    //        while ((line = reader.readLine()) != null) {
    //            rawData.add(line);
    //        }
    //        for(String a : rawData) {
    //        	System.out.println(a);
    //        }
    //    } catch (Exception e) {
    //    	System.out.println("Empty team");
    //    }
		
		 MainMenu();
	}
	
	public static void InsertMenu() {
		int table;
		Connection conn = new Connection();
		System.out.println("Which table to insert? 1. User, 2. Team");
		table = sc.nextInt();
		sc.nextLine();
		
		
		if(table==1) {
			String newnama;
			String newNIM;
			String newTeam;
			System.out.println("add name: ");
			newnama = sc.nextLine();
			System.out.println("add nim: ");
			newNIM = sc.nextLine();
			System.out.println("add team: ");
			newTeam = sc.nextLine();
			
			//validasi disini
			conn.WriteFile("User", newnama +","+ newNIM +","+ newTeam);
			System.out.println("User created!");
			
		}
		else if(table == 2) {
			String newteam;
			System.out.println("add team name: ");
			newteam = sc.next();
			conn.WriteFile("Team", newteam);
			System.out.println("Team created!");
		}
		
	}
	
	public static void ShowMenu() {
		int TShow;
		int FCon;
		System.out.print("Which table to show? 1. User, 2. Team.\n>> ");
		TShow = sc.nextInt();
		System.out.print("Want to filter by condition? 1. Yes, 2. No.\n>> ");
		FCon = sc.nextInt();
		
		
		if(FCon==1) {
			if(TShow == 1) {
				ShowUserCond();
			}
			else if(TShow == 2) {
				ShowTeamCond();
			}
		}
		else if(FCon == 2) {
			if(TShow == 1) {
				ShowUserTemp();
			}
			else if(TShow == 2) {
				ShowTeamTemp();
			}
		}
		
	}
	
	public static void ShowUserTemp() {
		Connection connection = new Connection();
		
		ArrayList<String> teamFileContents = connection.ReadFile("User");
       System.out.println("Team file contents:");
       for (String line : teamFileContents) {
           System.out.println(line);
       }
	}

	public static void ShowTeamTemp() {
		Connection connection = new Connection();
		
		ArrayList<String> teamFileContents = connection.ReadFile("Team");
       System.out.println("Team file contents:");
       for (String line : teamFileContents) {
           System.out.println(line);
       }
	}
	
	
	public static void ShowUserCond() {
		
	}
	
	public static void ShowUser() {
		UserRepository UserShow = new UserRepository();
		
//		UserShow.find(null,null, join, "Team", conn)
		
	}
	
	public static void ShowTeamCond() {
		
	}

	public static void ShowTeam() {
		
	}
	
	public static void MainMenu() {
		
		int menu;
		do {
			System.out.print("1. Menu Utama\n2. Insert Data\n3. Show\n4. Exit\n>> ");
			menu = sc.nextInt();
			sc.nextLine();
			
			if(menu == 1) {
				MainMenu();
			}
			else if(menu == 2) {
				InsertMenu();
			}
			else if(menu == 3) {
				ShowMenu();
			}
		}while(menu != 4);
		
	}

}
