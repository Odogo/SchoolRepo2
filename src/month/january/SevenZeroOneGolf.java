package month.january;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFileChooser;

public class SevenZeroOneGolf {
	
	public static class Person {
		private String firstName, lastName;
		
		public Person(String firstName, String lastName) {
			this.firstName = firstName;
			this.lastName = lastName;
		}
		
		public String getFirstName() { return firstName; }
		public String getLastName() { return lastName; }
		
		@Override public String toString() { return "\"" + firstName + " " + lastName + "\""; }
	}
	
	public static class Student extends Person {
		private double gradePointAvg;
		
		public Student(String firstName, String lastName, double gradePointAvg) {
			super(firstName, lastName);
			this.gradePointAvg = gradePointAvg;
		}
		
		public double getGradePointAvg() { return gradePointAvg; }
		@Override public String toString() { return super.toString() + " " + gradePointAvg; }
	}
	
	public static class Teacher extends Person {
		private int studentCount;
		
		public Teacher(String firstName, String lastName, int studentCount) {
			super(firstName, lastName);
			this.studentCount = studentCount;
		}
		
		public int getStudentCount() { return studentCount; }
		@Override public String toString() { return super.toString() + " " + studentCount; }
	}
	
	public static class Administrator extends Person {
		private String favouriteWord;
		
		public Administrator(String firstName, String lastName, String favouriteWord) {
			super(firstName, lastName);
			this.favouriteWord = favouriteWord;
		}
		
		public String getFavouriteWord() { return favouriteWord; }
		@Override public String toString() { return super.toString() + " " + favouriteWord; }
	}
	
	public static void main(String[] args) throws FileNotFoundException, IllegalAccessException {
		
		JFileChooser chooser = new JFileChooser("C:/");
		chooser.showOpenDialog(null);

		File selection = chooser.getSelectedFile();
		if (selection == null)
			throw new FileNotFoundException("File from selection could not be found.");
		if (selection.isDirectory())
			throw new IllegalAccessException("Cannot access a directory as a file.");

		
		List<Person> peopleList = new ArrayList<Person>();
		try (Scanner scanner = new Scanner(selection)) {
			while (scanner.hasNextLine()) {
				int classIdentification = Integer.parseInt(scanner.nextLine());
				if(classIdentification == 99) break;
				
				String firstName = scanner.nextLine();
				String lastName = scanner.nextLine();
				
				if(classIdentification == 1) {
					peopleList.add(new Student(firstName, lastName, Double.parseDouble(scanner.nextLine())));
				} else if(classIdentification == 2) {
					peopleList.add(new Teacher(firstName, lastName, Integer.parseInt(scanner.nextLine())));
				} else if(classIdentification == 3) {
					peopleList.add(new Administrator(firstName, lastName, scanner.nextLine()));
				} else throw new IllegalArgumentException("Class ID must be between 1 and 3.");
			}
		}
		
		int stuCnt = 0;
		double gpa = 0;
		for(Person temp : peopleList) { if(temp instanceof Student) { gpa += ((Student) temp).getGradePointAvg(); stuCnt++; } }
		gpa /= stuCnt;
		
		int teachCnt = 0;
		double stuAvg = 0;
		for(Person temp : peopleList) { if(temp instanceof Teacher) { stuAvg += ((Teacher) temp).getStudentCount(); teachCnt++; } }
		stuAvg /= teachCnt;
		
		String smallWord = "0000000000000000000000000000000000000000000000";
		Person smallWordHolder = null;
		for(Person temp : peopleList) { 
			if(temp instanceof Administrator) {
				if(((Administrator) temp).getFavouriteWord().length() < smallWord.length()) {
					smallWord = ((Administrator) temp).getFavouriteWord();
					smallWordHolder = temp;
				}
			} 
		}
		
		String bigWord = "";
		Person bigWordHolder = null;
		for(Person temp : peopleList) { 
			if(temp instanceof Administrator) {
				if(((Administrator) temp).getFavouriteWord().length() > bigWord.length()) {
					bigWord = ((Administrator) temp).getFavouriteWord();
					bigWordHolder = temp;
				}
			} 
		}
		
		System.out.println("Average GPA: " + gpa);
		System.out.println("Average Students: " + stuAvg);
		System.out.println("Smallest Word: " + smallWord + " (" + smallWordHolder + ")");
		System.out.println("Largest Word: " + bigWord + " (" + bigWordHolder + ")");
	}
}
