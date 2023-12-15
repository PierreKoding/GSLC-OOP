package Utama;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import Repository.UserRepository;
import Repository.TeamRepository;

public class Main {

	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// Read 1
//		Connection connection = new Connection();
//		
//		ArrayList<String> teamFileContents = connection.ReadFile("Team");
//        System.out.println("Team file contents:");
//        for (String line : teamFileContents) {
//            System.out.println(line);
//        }
		
		// Read 2
		ArrayList<String> rawData = new ArrayList<String>();
		try (BufferedReader reader = new BufferedReader(new FileReader("teams.csv"))) {
           String line;
           while ((line = reader.readLine()) != null) {
               rawData.add(line);
           }
           for(String a : rawData) {
           	System.out.println(a);
           }
       } catch (Exception e) {
       	System.out.println("Empty team");
       }
		
		// MainMenu();
	}
	
	public static void InsertMenu() {
		
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
				ShowUser();
			}
			else if(TShow == 2) {
				ShowTeam();
			}
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
		
		
		
		
		
		
		String filePath = "user.csv"; // Update this with the correct file path

        // Reading from CSV
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "UTF-8"))) {
            String line;
            while ((line = br.readLine()) != null) {
                List<String> values = Arrays.asList(line.split(","));
                System.out.println("" + values);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
	}

}
