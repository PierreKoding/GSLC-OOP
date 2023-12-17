package Utama;
import java.util.ArrayList;
import java.util.Scanner;

import Models.Models;
import Repository.UserRepository;
import Repository.TeamRepository;

public class Main {

	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		 MainMenu();
	}
	
	public static void InsertMenu() {
		int table;
		Connection conn = new Connection();
		TeamRepository teamrepository = new TeamRepository();
		UserRepository userrepository = new UserRepository();
		
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
			
			userrepository.insert(new String[]{newnama + "," + newNIM + "," + newTeam}, conn);
			System.out.println("User created!");
			
		}
		else if(table == 2) {
			String newteam;
			System.out.println("add team name: ");
			newteam = sc.next();
			teamrepository.insert(new String[]{newteam}, conn);
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
		
		
		if(TShow == 1) {
			ShowUser(FCon);
		}
		else if(TShow == 2) {
			ShowTeam(FCon);
		}
	}
	
	
	public static void ShowUser(int condition) {
		UserRepository UserShow = new UserRepository();
		Connection connection = new Connection();
		
		if(condition == 1) {
			String kolom;
			sc.nextLine();
		    System.out.println("Kolom yang mana: ");
		    kolom = sc.nextLine();

		    String kondisi[];
		    System.out.println("add condition, separate by semicolon.");
		    System.out.println("Format [=/!=, nama] : ");
		    kondisi = sc.nextLine().split(";");

		    String joinkah;
		    System.out.println("Apakah ingin join table?[Y/N]");
		    joinkah = sc.next();
		    boolean joinkah2 = joinkah.equalsIgnoreCase("Y");

		    ArrayList<Models> Displae = new ArrayList<Models>();
		    Displae = UserShow.find(kolom, kondisi, joinkah2, "Team", connection);
			
		}
		
		// jika tidak ada kondisi
		String joinkah;
		System.out.println("Apakah ingin join table?[Y/N]");
		joinkah = sc.next();
		
		if(joinkah.equals("Y")) {
			ArrayList<Models> Displae = new ArrayList<Models>();
			Displae = UserShow.find(null, null, true, "Team", connection);
			
		}
		else {
			ArrayList<Models> Displae = new ArrayList<Models>();
			
			Displae = UserShow.find(null, null, null, null, connection);
			
			for(Models temp : Displae) {
				System.out.println(temp);
			}
		}
	}

	public static void ShowTeam(int condition) {
		TeamRepository TeamShow = new TeamRepository();
		Connection connection = new Connection();
		
		if(condition == 1) {
			String kolom;
			sc.nextLine();
		    System.out.println("Kolom yang mana: ");
		    kolom = sc.nextLine();

		    String kondisi[];
		    System.out.println("add condition, separate by semicolon.");
		    System.out.println("Format [=/!=, nama] : ");
		    kondisi = sc.nextLine().split(";");

		    String joinkah;
		    System.out.println("Apakah ingin join table?[Y/N]");
		    joinkah = sc.next();
		    boolean joinkah2 = joinkah.equalsIgnoreCase("Y");

		    ArrayList<Models> Displae = new ArrayList<Models>();
		    Displae = TeamShow.find(kolom, kondisi, joinkah2, "Team", connection);
			
		}
		
		// jika tidak ada kondisi
		String joinkah;
		System.out.println("Apakah ingin join table?[Y/N]");
		joinkah = sc.next();
		
		if(joinkah.equals("Y")) {
			ArrayList<Models> Displae = new ArrayList<Models>();
			Displae = TeamShow.find(null, null, true, "Team", connection);
			
		}
		else {
			ArrayList<Models> Displae = new ArrayList<Models>();
			
			Displae = TeamShow.find(null, null, null, null, connection);
			
			for(Models temp : Displae) {
				System.out.println(temp);
			}
		}
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
