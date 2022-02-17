package month.february;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JFileChooser;

/* 100 195 182 225  
 * 83 125 235 67  
 * 129 42 100 750  
 * Sum: 1873.0 */
public class FourSixFiveCharlie {

	public static void main(String[] args) throws FileNotFoundException, IllegalAccessException {
		JFileChooser chooser = new JFileChooser("C:/");
		chooser.showOpenDialog(null);

		File selection = chooser.getSelectedFile();
		if (selection == null)
			throw new FileNotFoundException("File from selection could not be found.");
		if (selection.isDirectory())
			throw new IllegalAccessException("Cannot access a directory as a file.");

		int[][] array;
		try (Scanner scanner = new Scanner(selection)) {
			int sizeY = Integer.parseInt(scanner.nextLine());
			int sizeX = Integer.parseInt(scanner.nextLine());

			array = new int[sizeY][sizeX];

			int[] pos = {0, 0};
			while(scanner.hasNextLine()) {
				if(pos[0] >= sizeX) { pos[1]++; pos[0] = 0; }
				array[pos[1]][pos[0]] = Integer.parseInt(scanner.nextLine());
				pos[0]++;
			}
		}

		for (int i = 0; i < array.length; i++) {
			for (int x = 0; x < array[i].length; x++)
				System.out.print(array[i][x] + " ");
			System.out.println(" ");
		}

		double sum = 0;
		for (int x = 0; x < array[0].length; x++) sum+=array[0][x];
		for (int x = 0; x < array[array.length-1].length; x++) sum+=array[array.length-1][x];
		for (int i = 1; i < array.length-1; i++)
			for (int x = 0; x < array[i].length; x++)
				if(x == 0 || x == array[i].length - 1)
					sum+=array[i][x];

		System.out.println("Sum: " + sum);
	}
}
