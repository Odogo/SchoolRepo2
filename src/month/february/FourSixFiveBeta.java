package month.february;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JFileChooser;

/* 2 1  
 * 2 4  
 * 3 2 */
public class FourSixFiveBeta {

	public static void main(String[] args) throws FileNotFoundException, IllegalAccessException {
		JFileChooser chooser = new JFileChooser("C:/");
		chooser.showOpenDialog(null);

		File selection = chooser.getSelectedFile();
		if (selection == null)
			throw new FileNotFoundException("File from selection could not be found.");
		if (selection.isDirectory())
			throw new IllegalAccessException("Cannot access a directory as a file.");

		int[][] array = new int[3][4];
		int concurrentSlot = 0;
		try (Scanner scanner = new Scanner(selection)) {
			while (scanner.hasNextLine()) {
				String[] in = scanner.nextLine().split(" ");

				for (int i = 0; i < in.length; i++)
					array[concurrentSlot][i] = Integer.parseInt(in[i]);

				concurrentSlot++;
			}
		}

		int currentPos = 0;
		int[][] tempArray = new int[getHundredLess(array)][2];
		for (int i = 0; i < array.length; i++)
			for (int x = 0; x < array[i].length; x++)
				if(array[i][x] < 100) {
					tempArray[currentPos][0] = i + 1;
					tempArray[currentPos][1] = x + 1;
					currentPos++;
				}

		for (int i = 0; i < tempArray.length; i++) {
			for (int x = 0; x < tempArray[i].length; x++)
				System.out.print(tempArray[i][x] + " ");
			System.out.println(" ");
		}
	}

	private static int getHundredLess(int[][] array) {
		int count = 0;
		for(int i=0; i<array.length; i++)
			for(int x=0; x<array[i].length; x++)
				if(array[i][x] < 100) count++;
		return count;
	}
}