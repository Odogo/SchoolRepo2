package month.february;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JFileChooser;

/* 45 -3 96 14 19 96  
 * 67 -6 81 -7 43 81  
 * 89 -7 -8 72 28 89  
 * 12 -4 52 29 63 63  
 * -3 -9 12 -1 87 87 */
public class FourSixFourDelta {

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
					array[i][concurrentSlot] = Integer.parseInt(in[i]);

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
