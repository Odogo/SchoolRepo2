package month.march;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFileChooser;

public class SelectionSort {

	public static void main(String[] args) throws IllegalAccessException, IOException {
		JFileChooser chooser = new JFileChooser("C:/");
		chooser.showOpenDialog(null);

		File selection = chooser.getSelectedFile();
		if (selection == null)
			throw new FileNotFoundException("File from selection could not be found.");
		if (selection.isDirectory())
			throw new IllegalAccessException("Cannot access a directory as a file.");

		long totalBefore = System.currentTimeMillis();
		long before = System.currentTimeMillis();
		System.out.println("[Reader-1] Gathering line count, please wait..");
		System.out.println("[Reader-2] Gathering contents of file, please wait..");

		long lines = 0;
		List<Integer> integers = new ArrayList<>();
		try (Scanner scanner = new Scanner(selection)) {
			while (scanner.hasNextLine()) {
				integers.add(Integer.parseInt(scanner.nextLine()));
				lines++;
			}
		}

		long after = System.currentTimeMillis();
		System.out.println("[Reader-2] Done, terminating ghost-thread.");
		System.out.println("[Reader-1] Done, continuing..");
		System.out.println("[Operator] Took " + (after - before) + "ms");

		before = System.currentTimeMillis();
		System.out.println("[Sorter-1] Beginning sort.");

		for(int i=0; i<integers.size(); i++) {
			int small = integers.get(i);
			int slot = i;

			for(int x=i; x<integers.size(); x++)
				if(integers.get(x) < small) {
					small = integers.get(x);
					slot = x;
				}

			int temp = integers.get(i);
			integers.set(i, integers.get(slot));
			integers.set(slot, temp);
		}

		after = System.currentTimeMillis();
		System.out.println("[Sorter-1] Done, sorted " + lines + " lines. Terminating..");
		System.out.println("[Operator] Took " + (after - before) + "ms");
		System.out.println("[Operator] Total time taken: " + (after - totalBefore) + "ms");

		System.out.println("\n\n\nResult:");
		for (int i=0; i<integers.size(); i++) {
			if(i%17 == 0) System.out.print("\n");
			System.out.print(integers.get(i) + ", ");
		}
	}

}
