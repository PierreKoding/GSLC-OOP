package Utama;
import java.io.*;
import java.util.ArrayList;

/*
Try opening file like this
FileWriter pw = new FileWriter("F:\\data.csv",true); 
Pass true argument for appending.
 */

public class Connection {
	
	
	public void WriteFile(String option, String insert) {
		FileWriter file = null;
		PrintWriter print = null;
		
		
		if(option.equals("User")) {
			try {
				file = new FileWriter("user.csv", true);
				print = new PrintWriter(file, true);
				
				print.println(insert);
				
				file.close();
				print.close();
			} catch (Exception e) {
				System.out.println("Failed");
			}
		}else if(option.equals("Team")) {
			try {
				file = new FileWriter("teams.csv", true);
				print = new PrintWriter(file, true);
				
				print.println(insert);
				
				file.close();
				print.close();
			} catch (Exception e) {
				System.out.println("Failed");
			}
		}	
	}
	
	public ArrayList<String> ReadFile(String option) {
		
		ArrayList<String> RepoReadFile = new ArrayList<String>();
		
		if(option.equals("User")) {
			try (BufferedReader reader = new BufferedReader(new FileReader("user.csv"))) {
			
	            String line;
	            while ((line = reader.readLine()) != null) {
	            	RepoReadFile.add(line);
	            }
	        } catch (Exception e) {
	        	System.out.println("Empty team");
	        }
		}else if(option.equals("Team")) {
			try (BufferedReader reader = new BufferedReader(new FileReader("teams.csv"))) {	
	            String line;
	            while ((line = reader.readLine()) != null) {
	            	RepoReadFile.add(line);
	            }
	        } catch (Exception e) {
	        	System.out.println("Empty team");
	        }
		}

		return RepoReadFile;
	}
}
