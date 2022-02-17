package month.january;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFileChooser;

/* Green/Gold Computers: 1
 * Windows 7 Computers: 2
 * Linux ID ending 5: 4 */
public class SevenZeroThreeSierra {

	public interface Computer {
		String fetchName();
		int getComputerID();
		double getComputerValue();
	}

	public static class WindowsComp implements Computer {

		// Variable dependents from implementation
		private String name;
		private int compId;
		private double compVal;

		private double winVer;

		public WindowsComp(String name, int compId, double compVal, double winVer) {
			this.name = name;
			this.compId = compId;
			this.compVal = compVal;
			this.winVer = winVer;
		}

		// Method dependents from implementation
		@Override public String fetchName() { return name; }
		@Override public int getComputerID() { return compId; }
		@Override public double getComputerValue() { return compVal; }

		public double fetchWindowsVersion() { return winVer; }
		@Override public String toString() { return "[WinOS." + winVer + "-\"" + name + "\"-" + compId + "-" + compVal + "]"; }
	}

	public static class AppleComp implements Computer {

		// Variable dependents from implementation
		private String name;
		private int compId;
		private double compVal;

		private String color;

		public AppleComp(String name, int compId, double compVal, String color) {
			this.name = name;
			this.compId = compId;
			this.compVal = compVal;
			this.color = color;
		}

		// Method dependents from implementation
		@Override public String fetchName() { return name; }
		@Override public int getComputerID() { return compId; }
		@Override public double getComputerValue() { return compVal; }

		public String fetchColor() { return color; }
		@Override public String toString() { return "[Apple." + color + "-\"" + name + "\"-" + compId + "-" + compVal + "]"; }
	}

	public static class LinuxComp implements Computer {

		// Variable dependents from implementation
		private String name;
		private int compId;
		private double compVal;

		private int hiddenCode;

		public LinuxComp(String name, int compId, double compVal, int hiddenCode) {
			this.name = name;
			this.compId = compId;
			this.compVal = compVal;
			this.hiddenCode = hiddenCode;
		}

		// Method dependents from implementation
		@Override public String fetchName() { return name; }
		@Override public int getComputerID() { return compId; }
		@Override public double getComputerValue() { return compVal; }

		public int fetchHiddenCode() { return hiddenCode; }
		@Override public String toString() { return "[Linux." + hiddenCode + "-\"" + name + "\"-" + compId + "-" + compVal + "]"; }
	}

	public static void main(String[] args) throws FileNotFoundException, IllegalAccessException {
		JFileChooser chooser = new JFileChooser("C:/");
		chooser.showOpenDialog(null);

		File selection = chooser.getSelectedFile();
		if (selection == null)
			throw new FileNotFoundException("File from selection could not be found.");
		if (selection.isDirectory())
			throw new IllegalAccessException("Cannot access a directory as a file.");

		List<Computer> computers = new ArrayList<>();
		try (Scanner scanner = new Scanner(selection)) {
			while (scanner.hasNextLine()) {
				int incomingCode = Integer.parseInt(scanner.nextLine());

				String compName = scanner.nextLine();
				int compId = Integer.parseInt(scanner.nextLine());
				double compVal = Double.parseDouble(scanner.nextLine());

				// 1=Apple - 2=Windows - 3=Linux
				if(incomingCode == 1) {
					String color = scanner.nextLine();
					computers.add(new AppleComp(compName, compId, compVal, color));
				} else if(incomingCode == 2) {
					double winVer = Double.parseDouble(scanner.nextLine());
					computers.add(new WindowsComp(compName, compId, compVal, winVer));
				} else if(incomingCode == 3) {
					int hideCode = Integer.parseInt(scanner.nextLine());
					computers.add(new LinuxComp(compName, compId, compVal, hideCode));
				}
			}
		}

		int countOfGreen = fetchCountOf(computers, "apple", "Green");
		int countOfGold = fetchCountOf(computers, "apple", "Gold");

		System.out.println("Green/Gold Computers: " + (countOfGreen + countOfGold));
		System.out.println("Windows 7 Computers: " + fetchCountOf(computers, "window", 7D));
		System.out.println("Linux ID ending 5: " + fetchCountOf(computers, "linux", 5));
	}

	private static Integer fetchCountOf(List<Computer> data, String cot, Object check) {
		int cnt = 0;
		if(cot.equals("apple")) {
			if(!(check instanceof String)) return null;
			String sCheck = (String) check;

			for(Computer comps : data) {
				if(!(comps instanceof AppleComp)) continue;
				if(((AppleComp) comps).fetchColor().equals(sCheck)) cnt++;
			}
		} else if(cot.equals("window")) {
			if(!(check instanceof Double)) return null;
			Double dCheck = (Double) check;

			for(Computer comps : data) {
				if(!(comps instanceof WindowsComp)) continue;
				if(((WindowsComp) comps).fetchWindowsVersion() == dCheck) cnt++;
			}
		} else if(cot.equals("linux")) { 
			if(!(check instanceof Integer)) return null;
			Integer iCheck = (Integer) check;

			for(Computer comps : data) {
				if(!(comps instanceof LinuxComp)) continue;
				if(endsIn(((LinuxComp) comps).fetchHiddenCode(), iCheck)) cnt++;
			}
		}

		return cnt;
	}

	private static boolean endsIn(int check, int checkFor) {
		String string = check + "", num = string.substring(string.length()-1, string.length());
		if(Integer.parseInt(num) == 5) return true;
		return false;
	}
}
