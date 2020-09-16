

import java.util.List;
import java.util.Scanner;

import controller.PresidentHelper;
import model.President;

public class ProgramStarter {

		static Scanner in = new Scanner(System.in);
		static PresidentHelper presHelp = new PresidentHelper();

		private static void addAnItem() {
			System.out.print("Enter what number they were: ");
			int num = in.nextInt();
			in.nextLine();
			System.out.print("Enter thier name: ");
			String name = in.nextLine();
			President toAdd	= new President(num, name);//create list item
			presHelp.insertPresident(toAdd);//insert list item into the helper

		}

		private static void deleteAnItem() {
			System.out.print("Enter what number they were: ");
			int num = in.nextInt();
			in.nextLine();
			System.out.print("Enter thier name: ");
			String name = in.nextLine();
			President toDelete=new President(num, name);//create list item
			presHelp.deletePresident(toDelete);
		}

		private static void editAnItem() {
			System.out.println("How would you like to search? ");
			System.out.println("1 : Search by Number");
			System.out.println("2 : Search by Name");
			int searchBy = in.nextInt();
			List<President> foundPres;
			if (searchBy == 1) {
				System.out.print("Enter	the president number:	");
				int num = in.nextInt();
				foundPres =	presHelp.searchForPresidentByNum(num);				
				
			} else {
				System.out.print("Enter the president name:	");
				String presName = in.nextLine();
				foundPres =	presHelp.searchForPresidentByName(presName);
			}

			if (!foundPres.isEmpty()) {
				System.out.println("Found Results.");
				for (President p : foundPres) {
					System.out.println(p.getId() + " : " + p.toString());
				}
				System.out.print("Which ID to edit: ");
				int idToEdit = in.nextInt();

				President toEdit = presHelp.searchForPresidentById(idToEdit);
				System.out.println("Retrieved " + toEdit.getName() + ", #" + toEdit.getPresidencyNumber());
				System.out.println("1 : Update Name");
				System.out.println("2 : Update Number");
				int update = in.nextInt();
				in.nextLine();

				if (update == 1) {
					System.out.print("New Name: ");
					String newName = in.nextLine();
					toEdit.setName(newName);
				} else if (update == 2) {
					System.out.print("New Presidency Number: ");
					int newNum = in.nextInt();
					toEdit.setPresidencyNumber(newNum);
				}

				presHelp.updatePresident(toEdit);

			} else {
				System.out.println("---- No results found :(");
			}
		}

		public static void main(String[] args) {
			runController();
		}

		public static void runController() {
			boolean goAgain = true;
			System.out.println("--- Welcome to the national President registery ---");
			while (goAgain) {
				System.out.println("\n\n\nSelect an option:");
				System.out.println("Edit President -1");
				System.out.println("View President List -2");
				System.out.println("Register President -3");
				System.out.println("Remove President (CAUTION) -4");
				System.out.println("Exit registery -5");
				System.out.print("*  Your choice: ");
				int selection = in.nextInt();
				in.nextLine();

				if (selection == 1) {
					editAnItem();					
				} else if (selection == 2) {
					viewTheList();
				} else if (selection == 3) {
					addAnItem();
				} else if (selection == 4) {
					deleteAnItem();
				} else {
					presHelp.cleanUp();
					System.out.println("   Goodbye!   ");
					goAgain = false;
				}

			}

		}

		private static void viewTheList() {
			List<President>	allItems	=	presHelp.showAllPresidents();
			for(President singleItem :	allItems){
			System.out.println(singleItem.returnPresidentDetails());
			}			
		}

	}
