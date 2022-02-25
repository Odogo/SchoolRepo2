package month.february;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JFileChooser;

/* Right Diagonal (from R->L): 147
 * Left Diagonal (from L->R): -3 */
public class FourSevenZeroBeta {

	public static void main(String[] args) throws FileNotFoundException, IllegalAccessException {
		JFileChooser chooser = new JFileChooser("C:/");
		chooser.showOpenDialog(null);

		File selection = chooser.getSelectedFile();
		if (selection == null)
			throw new FileNotFoundException("File from selection could not be found.");
		if (selection.isDirectory())
			throw new IllegalAccessException("Cannot access a directory as a file.");

		int[][] array = new int[5][5];
		int concurrentSlot = 0;
		try (Scanner scanner = new Scanner(selection)) {
			while (scanner.hasNextLine()) {
				String[] in = scanner.nextLine().split(" ");

				for (int i = 0; i < in.length; i++)
					array[concurrentSlot][i] = Integer.parseInt(in[i]);

				concurrentSlot++;
			}
		}

		int rightDiagonal = 0;
		for(int i=0; i<array.length; i++) rightDiagonal += array[i][i];

		int leftDiagonal = 0;
		for(int i=array.length-1; i>=0; i--) leftDiagonal += array[i][4-i];

		System.out.println("Right Diagonal (from R->L): " + rightDiagonal);
		System.out.println("Left Diagonal (from L->R): " + leftDiagonal);
	}
}
