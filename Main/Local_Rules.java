package Main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


import Security.SHA256;

public class Local_Rules {
	
	// create a file if one doesn't exist..
	// add a local rule in csv format parsing each string by a special combination of characters: ','
	// delete a rule
	
		
	public static void addRule(String priority, String from, String subject, String content) throws IOException {
		File myFile = new File("local_rules_DO_NOT_EDIT.txt");
		if(myFile.createNewFile()) {
			System.out.println("Created a new file: " + myFile.getName());
		}
		else {
			System.out.println(myFile.getName() + " already exists");
		}
		
		
		FileWriter fw = new FileWriter(myFile, true);
		String id = SHA256.getSHA(priority + from + subject + content);
		fw.write(id + "\n");
		fw.write(priority + "\n");
		fw.write(from + "\n");
		fw.write(subject + "\n");
		fw.write(content + "\n");
		
		fw.close();
	}
	
	
	
	
	
	
	
	
	
	
	public static void deleteRule(String id) throws IOException {
		File myFile = new File("local_rules_DO_NOT_EDIT.txt");
		if(!myFile.exists()) {
			return;
		}
		
		File newFile = new File("tempFile.txt");
		if(newFile.createNewFile()) {
			System.out.println("Created a new file: " + newFile.getName());
		}
		else {
			System.out.println(myFile.getName() + " already exists");
		}
		
		
		
		Scanner scanner = new Scanner(myFile);
		
		String local_id, local_priority, local_from, local_subject, local_content;
		FileWriter fw = new FileWriter(newFile);
		while(scanner.hasNextLine()) {
			local_id = scanner.nextLine();
			local_priority = scanner.nextLine();
			local_from = scanner.nextLine();
			local_subject = scanner.nextLine();
			local_content = scanner.nextLine();
			
			if(local_id.equals(id)) {
				System.out.println("not writting: " + id);
				continue;
			}
			else {
				fw.write(local_id + "\n");
				fw.write(local_priority + "\n");
				fw.write(local_from + "\n");
				fw.write(local_subject + "\n");
				fw.write(local_content + "\n");
			}
			
			
		}
		
		
		
		fw.close();
		scanner.close();
	
		myFile.delete();
		//newFile.delete();
		newFile.renameTo(myFile);
		
		
		return;
		
		
		
	}
	
}
