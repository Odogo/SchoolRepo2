package month.january;


public class APClassWork {
	
	private static int[] samples = { 0, 0, 0, 0, -14, 0, -35, -39, 0, -7, 16, 32, 37, 29, 0, 0 };
	
	public static void main(String[] args) {
		for(int temp : samples) System.out.print(temp + " ");
		trimSilenceFromBeginning();
		System.err.println(" \n");
		for(int temp : samples) System.out.print(temp + " ");
	}
	
	public static void trimSilenceFromBeginning() {
		int startAt = 0;
		for(int i=0; i<samples.length; i++) {
			if(samples[i] != 0) break;
			startAt++;
		}
		
		int[] temp = new int[samples.length - (startAt)];
		for(int i=startAt; i<samples.length; i++) {
			temp[i-startAt] = samples[i];
		}
		samples = temp;
	}

}