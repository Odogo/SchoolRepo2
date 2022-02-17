package month.february;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JFileChooser;

public class FourSixFiveAlpha {

	public static void main(String[] args) throws FileNotFoundException, IllegalAccessException {
		JFileChooser chooser = new JFileChooser("C:/");
		chooser.showOpenDialog(null);

		File selection = chooser.getSelectedFile();
		if (selection == null)
			throw new FileNotFoundException("File from selection could not be found.");
		if (selection.isDirectory())
			throw new IllegalAccessException("Cannot access a directory as a file.");

		int[][] arrayA = new int[4][4], arrayB = new int[4][4];
		int concurrentSlot = 0;
		boolean nextArray = false;
		try (Scanner scanner = new Scanner(selection)) {
			while (scanner.hasNextLine()) {
				String[] in = scanner.nextLine().split(" ");

				for (int i = 0; i < in.length; i++)
					if (!nextArray)
						arrayA[concurrentSlot][i] = Integer.parseInt(in[i]);
					else
						arrayB[concurrentSlot][i] = Integer.parseInt(in[i]);

				concurrentSlot++;
				if (concurrentSlot == 4) {
					concurrentSlot = 0;
					nextArray = true;
				}
			}
		}

		int[][] large = new int[4][4];
		for (int i = 0; i < large.length; i++)
			for (int x = 0; x < large[i].length; x++)
				large[i][x] = Math.max(arrayA[i][x], arrayB[i][x]);

		for (int i = 0; i < large.length; i++) {
			for (int x = 0; x < large[i].length; x++)
				System.out.print(large[i][x]);
			System.out.println(" ");
		}
	}
}
