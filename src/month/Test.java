package month;

public class Test {

	public static class Something {
		private static int count = 0;
		public Something() {
			count+=5;
		}
		public static void increment() { count++; }
	}

	public static void main(String[] args) {

		Something something = new Something();
		Something.increment();


		System.out.println(Something.count);
	}

}
