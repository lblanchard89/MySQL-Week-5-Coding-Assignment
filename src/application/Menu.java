package application;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import dao.KettlebellDao;
import entity.Kettlebell;

public class Menu {
	
	private KettlebellDao kettlebellDao = new KettlebellDao();
	private Scanner scanner = new Scanner(System.in);
	private List<String> options = Arrays.asList(
			"Display Kettlebells",
			"Display A Kettlebell",
			"Create A Kettlebell",
			"Delete A Kettlebell",
			"Update A Kettlebell");
	
	public void start() {
		String selection = "";
		
		do {
			printMenu();
			selection = scanner.nextLine();
			
			try {
				if (selection.equals("1")) {
					displayKettlebells();
				}else if (selection.equals("2")) {
					displayKettlebell();
				}else if (selection.equals("3")) {
					createKettlebell();
				}else if (selection.equals("4")) {
					deleteKettlebell();
				}else if (selection.equals("5")) {
					updateKettlebell();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
	
			System.out.println("Press enter to continue....");
			scanner.nextLine();
		}while (!selection.equals("-1"));
	}
	
	private void printMenu() {
		System.out.println("Select an Option:\n----------------------------------------------");
		for (int i=0; i < options.size(); i++) {
			System.out.println(i + 1 + ") " + options.get(i));
		}
	}
	
	private void displayKettlebells() throws SQLException {
		List<Kettlebell> kettlebells = kettlebellDao.getKettlebells();
		for (Kettlebell kettlebell : kettlebells) {
		System.out.println(kettlebell.getSerial_no() +": " + kettlebell.getWeight() + ": " + kettlebell.getColor());
		}
	}
	
	private void displayKettlebell() throws SQLException {
		System.out.println("Enter Kettlebell Serial Number: ");
		int serial_no = Integer.parseInt(scanner.nextLine());
		Kettlebell kettlebell = kettlebellDao.getKettlebellBySerialNo(serial_no);
		System.out.println(kettlebell.getSerial_no() + ": " + kettlebell.getWeight() + ": " + kettlebell.getColor());
	}
	
	private void createKettlebell() throws SQLException {
		System.out.println("Enter new kettlebell weight:");
		String kettlebellWeight = scanner.nextLine();
		System.out.println("Enter new kettlebell color:");
		String kettlebellColor = scanner.nextLine();
		kettlebellDao.createKettlebell(kettlebellWeight, kettlebellColor);
	}
	
	private void deleteKettlebell() throws SQLException {
		System.out.println("Enter kettlebell serial number to delete:");
		int serial_no = Integer.parseInt(scanner.nextLine());
		kettlebellDao.deleteKettlebell(serial_no);
	}
	
	private void updateKettlebell() throws SQLException {
		System.out.println("Enter updated kettlebell weight:");
		String kettlebellWeight = scanner.nextLine();
		System.out.println("Enter updated kettlebell color:");
		String kettlebellColor = scanner.nextLine();
		System.out.println("Enter the serial number of the kettlebell you want to update:");
		int serial_no = Integer.parseInt(scanner.nextLine());
		kettlebellDao.updateKettlebell(kettlebellWeight, kettlebellColor, serial_no);
	}
}
