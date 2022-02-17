package month.january;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JFileChooser;

import month.january.farm.FarmClasses.Cow;
import month.january.farm.FarmClasses.Farm;
import month.january.farm.FarmClasses.Horse;

public class FiveZeroFiveTango {

	public static void main(String[] args) throws FileNotFoundException, IllegalAccessException {
		JFileChooser chooser = new JFileChooser("C:/");
		chooser.showOpenDialog(null);

		File selection = chooser.getSelectedFile();
		if (selection == null)
			throw new FileNotFoundException("File from selection could not be found.");
		if (selection.isDirectory())
			throw new IllegalAccessException("Cannot access a directory as a file.");

		Farm farm = null;
		try (Scanner scanner = new Scanner(selection)) {

			String[] sHay = scanner.nextLine().split(" ");
			String[] sCorn = scanner.nextLine().split(" ");

			int hayAmount = Integer.parseInt(sHay[0]), cornAmount = Integer.parseInt(sCorn[0]);
			double hayCost = Double.parseDouble(sHay[1]), cornCost = Double.parseDouble(sCorn[1]);

			farm = new Farm(cornAmount, hayAmount, cornCost, hayCost);

			int cowCount = Integer.parseInt(scanner.nextLine());
			for (int i = 0; i < cowCount; i++) {
				if (!scanner.hasNextLine())
					throw new IllegalAccessException("End of unexpected data file.");
				String[] cowTemp = scanner.nextLine().split(" ");

				int weight = Integer.parseInt(cowTemp[0]);
				int hayPerDay = Integer.parseInt(cowTemp[1]);
				int cornPerDay = Integer.parseInt(cowTemp[2]);
				int milkPerDay = Integer.parseInt(cowTemp[3]);

				farm.addCows(new Cow(weight, hayPerDay, cornPerDay, milkPerDay));
			}

			int horseCount = Integer.parseInt(scanner.nextLine());
			for (int i = 0; i < horseCount; i++) {
				if (!scanner.hasNextLine())
					throw new IllegalAccessException("End of unexpected data file.");
				String[] cowTemp = scanner.nextLine().split(" ");

				int weight = Integer.parseInt(cowTemp[0]);
				int hayPerDay = Integer.parseInt(cowTemp[1]);
				int cornPerDay = Integer.parseInt(cowTemp[2]);
				int ridePerDay = Integer.parseInt(cowTemp[3]);
				double rideCost = Double.parseDouble(cowTemp[4]);

				farm.addHorse(new Horse(weight, hayPerDay, cornPerDay, ridePerDay, rideCost));
			}
		}

		System.out.println("Today's Income: " + farm.farmIncome(0.20D));
		System.out.println("Cost to feed: " + farm.fetchCost());
		System.out.println("Total Weight: " + farm.fetchWeight());

		boolean feed = farm.feedAllAnimals(false);
		System.out.println("Is enough food? " + feed);
		if (!feed) {
			System.err.println("Missing requirements: ");
			System.err.println(" - Hay needed: " + farm.needFeedTotal()[0]);
			System.err.println(" - Corn needed: " + farm.needFeedTotal()[1]);
		}
	}
}
