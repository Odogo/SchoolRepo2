package month.january;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFileChooser;

public class SevenZeroTwoPapa {

	public interface IAnimal {
		public String getName();
		public String getWord();
	}
	
	public static class Hiccas implements IAnimal {
		
		private String name, word;
		private double furWorth;
		
		@Deprecated
		public Hiccas() {}
		
		public Hiccas(String name, String word, double furWorth) {
			this.name = name; this.word = word;
			this.furWorth = furWorth;
		}
		
		@Override public String getName() { return name; }
		@Override public String getWord() { return word; }
		public double getFurWorth() { return furWorth; }
	}
	
	public static class Wallies implements IAnimal {
		private String name, word;
		private int steps;
		
		@Deprecated
		public Wallies() {}
		
		public Wallies(String name, String word, int steps) {
			this.name = name; this.word = word;
			this.steps = steps;
		}
		
		@Override public String getName() { return name; }
		@Override public String getWord() { return word; }
		public int getSteps() { return steps; }
	}
	
	public static class Beepers implements IAnimal {
		private String name, word, extraWord;
		
		@Deprecated
		public Beepers() {}
		
		public Beepers(String name, String word, String extraWord) {
			this.name = name; this.word = word;
			this.extraWord = extraWord;
		}
		
		@Override public String getName() { return name; }
		@Override public String getWord() { return word; }
		public String getExtraWord() { return extraWord; }
	}
	
	public static void main(String[] args) throws FileNotFoundException, IllegalAccessException {
		JFileChooser chooser = new JFileChooser("C:/");
		chooser.showOpenDialog(null);

		File selection = chooser.getSelectedFile();
		if (selection == null)
			throw new FileNotFoundException("File from selection could not be found.");
		if (selection.isDirectory())
			throw new IllegalAccessException("Cannot access a directory as a file.");

		List<IAnimal> animalList = new ArrayList<IAnimal>();
		try (Scanner scanner = new Scanner(selection)) {
			while (scanner.hasNextLine()) {
				int classIdentification = Integer.parseInt(scanner.nextLine());
				if(classIdentification == 99) break;
				
				String firstName = scanner.nextLine();
				String lastName = scanner.nextLine();
				
				if(classIdentification == 1) {
					animalList.add(new Hiccas(firstName, lastName, Double.parseDouble(scanner.nextLine())));
				} else if(classIdentification == 2) {
					animalList.add(new Wallies(firstName, lastName, Integer.parseInt(scanner.nextLine())));
				} else if(classIdentification == 3) {
					animalList.add(new Beepers(firstName, lastName, scanner.nextLine()));
				} else throw new IllegalArgumentException("Class ID must be between 1 and 3.");
			}
		}
		
		int cnt = 0;
		double avg = 0, avgVal, avgSteps, avgWordSize;
		
		for(IAnimal animal : animalList) {
			if(animal instanceof Hiccas) { avg += ((Hiccas) animal).getFurWorth(); cnt++; }
		}
		
		avgVal = avg / cnt;
		cnt = 0; avg = 0;
		
		for(IAnimal animal : animalList) {
			if(animal instanceof Wallies) { avg += ((Wallies) animal).getSteps(); cnt++; }
		}
		
		avgSteps = avg / cnt;
		cnt = 0; avg = 0;
		
		for(IAnimal animal : animalList) {
			if(animal instanceof Beepers) { avg += ((Beepers) animal).getExtraWord().length(); cnt++; }
		}
		
		avgWordSize = avg / cnt;
		cnt = 0; avg = 0;
		
		DecimalFormat format = new DecimalFormat("#.##");
		System.out.println("Average Value: $" + format.format(avgVal));
		System.out.println("Average Steps: " + format.format(avgSteps));
		System.out.println("Average Favourite Word Size: " + format.format(avgWordSize));
	}
}
