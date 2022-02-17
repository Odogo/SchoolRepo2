package month.february;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JFileChooser;

public class FourSixFourAlpha {

	public static void main(String[] args) throws FileNotFoundException, IllegalAccessException {
		JFileChooser chooser = new JFileChooser("C:/");
		chooser.showOpenDialog(null);

		File selection = chooser.getSelectedFile();
		if (selection == null)
			throw new FileNotFoundException("File from selection could not be found.");
		if (selection.isDirectory())
			throw new IllegalAccessException("Cannot access a directory as a file.");

		int[][] array = new int[5][6];
		int concurrentSlot = 0;
		try (Scanner scanner = new Scanner(selection)) {
			while (scanner.hasNextLine()) {
				String[] in = scanner.nextLine().split(" ");

				for (int i = 0; i < in.length; i++)
					array[concurrentSlot][i] = Integer.parseInt(in[i]);

				concurrentSlot++;
			}
		}

		for (int i = 0; i < array.length; i++)
			array[i][5] = -999;

		for (int i = 0; i < array.length; i++)
			for (int x = 0; x < array[i].length; x++)
				array[i][5] = Math.max(array[i][x], array[i][5]);

		for (int i = 0; i < array.length; i++) {
			for (int x = 0; x < array[i].length; x++)
				System.out.print(array[i][x] + " ");
			System.out.println(" ");
		}
	}

}
