package month.march;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFileChooser;

import month.january.farm.FarmClasses.Cow;
import month.january.farm.FarmClasses.Farm;
import month.january.farm.FarmClasses.Horse;

public class FiveZeroFiveUniform {

	public static void main(String[] args) throws FileNotFoundException, IllegalAccessException {
		JFileChooser chooser = new JFileChooser("C:/");
		chooser.showOpenDialog(null);

		File selection = chooser.getSelectedFile();
		if (selection == null)
			throw new FileNotFoundException("File from selection could not be found.");
		if (selection.isDirectory())
			throw new IllegalAccessException("Cannot access a directory as a file.");

		List<Farm> farms = new ArrayList<Farm>();
		try (Scanner scanner = new Scanner(selection)) {

			int farmCount = Integer.parseInt(scanner.nextLine());

			for (int x = 0; x < farmCount; x++) {
				String[] haySplit = scanner.nextLine().split(" ");
				int hay = Integer.parseInt(haySplit[0]);
				double hayCost = Double.parseDouble(haySplit[1]);

				String[] cornSplit = scanner.nextLine().split(" ");
				int corn = Integer.parseInt(cornSplit[0]);
				double cornCost = Double.parseDouble(cornSplit[1]);

				Farm farm = new Farm(corn, hay, cornCost, hayCost);

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

				farms.add(farm);
			}
		}
	}
}
