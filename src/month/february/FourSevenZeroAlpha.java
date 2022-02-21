package month.february;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JFileChooser;

/* 45 67 89 12 -3  
 * -3 -6 -7 -4 -9  
 * 96 81 -8 52 12  
 * 14 -7 72 29 -1  
 * 19 43 28 63 87  
 * 171 178 174 152 86 */
public class FourSevenZeroAlpha {

	public static void main(String[] args) throws FileNotFoundException, IllegalAccessException {
		JFileChooser chooser = new JFileChooser("C:/");
		chooser.showOpenDialog(null);

		File selection = chooser.getSelectedFile();
		if (selection == null)
			throw new FileNotFoundException("File from selection could not be found.");
		if (selection.isDirectory())
			throw new IllegalAccessException("Cannot access a directory as a file.");

		int[][] array = new int[6][5];
		int concurrentSlot = 0;
		try (Scanner scanner = new Scanner(selection)) {
			while (scanner.hasNextLine()) {
				String[] in = scanner.nextLine().split(" ");

				for (int i = 0; i < in.length; i++)
					array[concurrentSlot][i] = Integer.parseInt(in[i]);

				concurrentSlot++;
			}
		}

		System.out.println(array.length);
		for (int i = 0; i < array.length; i++)
			System.out.println(array[i].length);

		for(int i=0; i<array.length-1; i++) {
			int[] column = getColumn(array, i);
			int total = 0;
			for(int x=0; x<column.length; x++) total += column[x];
			array[5][i] = total;
		}

		for (int i = 0; i < array.length; i++) {
			for (int x = 0; x < array[i].length; x++)
				System.out.print(array[i][x] + " ");
			System.out.println(" ");
		}
	}

	public static int[] getColumn(int[][] array, int index){
		int[] column = new int[array.length];
		for(int i=0; i<column.length; i++)
			column[i] = array[i][index];
		return column;
	}
}
