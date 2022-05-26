package month.january.farm;

import java.util.ArrayList;
import java.util.List;

public class FarmClasses {

	public static class Farm {

		private List<Horse> horses;
		private List<Cow> cows;

		private int cornCount, hayCount;
		private double cornCost, hayCost;

		public Farm(int cornCount, int hayCount, double cornCost, double hayCost) {
			this.cornCost = cornCost;
			this.hayCost = hayCost;
			this.cornCount = cornCount;
			this.hayCount = hayCount;

			this.cows = new ArrayList<>();
			this.horses = new ArrayList<>();
		}

		public int fetchCornCount() {
			return cornCount;
		}

		public int fetchHayCount() {
			return hayCount;
		}

		public double fetchCornCost() {
			return cornCost;
		}

		public double fetchHayCost() {
			return hayCost;
		}

		public List<Horse> fetchHorses() {
			return horses;
		}

		public List<Cow> fetchCows() {
			return cows;
		}

		/**
		 * Generates a new {@code List<Animal>} by combining the two Lists.
		 * @return a combined list of cows and horses;
		 */
		public List<Animal> fetchAllAnimals() {
			return new ArrayList<Animal>() {
				private static final long serialVersionUID = 1L;
				{
					addAll(cows);
					addAll(horses);
				}
			};
		}

		public int fetchHayTotal() {
			int total = 0;
			for (Animal temp : fetchAllAnimals())
				total += temp.fetchHayConsumption();
			return total;
		}

		public int fetchCornTotal() {
			int total = 0;
			for (Animal temp : fetchAllAnimals())
				total += temp.fetchCornConsumption();
			return total;
		}

		public boolean feedCows() {
			double hayNeeded = 0, cornNeeded = 0;
			for (Cow tempCow : fetchCows()) {
				hayNeeded += tempCow.fetchHayConsumption();
				cornNeeded += tempCow.fetchCornConsumption();
			}

			if (fetchHayCount() >= hayNeeded && fetchCornCount() >= cornNeeded) {
				hayCount -= hayNeeded;
				cornCost -= cornNeeded;
				return true;
			}
			return false;
		}

		public boolean feedHorses() {
			double hayNeeded = 0, cornNeeded = 0;
			for (Horse tempHorse : fetchHorses()) {
				hayNeeded += tempHorse.fetchHayConsumption();
				cornNeeded += tempHorse.fetchCornConsumption();
			}

			if (fetchHayCount() >= hayNeeded && fetchCornCount() >= cornNeeded) {
				hayCount -= hayNeeded;
				cornCost -= cornNeeded;
				return true;
			}
			return false;
		}

		public boolean feedAllAnimals(boolean doNotRemove) {
			if (fetchCornCount() >= fetchCornTotal() && fetchHayCount() >= fetchHayTotal()) {
				if (!doNotRemove) {
					hayCount -= fetchHayTotal();
					cornCount -= fetchCornTotal();
				}
				return true;
			}
			return false;
		}

		public int[] needFeedTotal() {
			if (feedAllAnimals(true))
				return new int[] { 0, 0 };

			int hay = hayCount - fetchHayTotal();
			int corn = cornCount - fetchCornTotal();

			if (hay > 0)
				hay = 0;
			if (corn > 0)
				corn = 0;

			return new int[] { Math.abs(hay), Math.abs(corn) };
		}

		public double cowIncome(double perPound) {
			double total = 0;
			for (Cow temp : fetchCows())
				total += temp.fetchMilkValue(perPound);
			return total;
		}

		public double horseIncome() {
			double total = 0;
			for (Horse temp : fetchHorses())
				total += temp.fetchValue();
			return total;
		}

		public double farmIncome(double perPound) {
			return horseIncome() + cowIncome(perPound);
		}

		public int fetchWeight() {
			int total = 0;
			for (Animal temp : fetchAllAnimals())
				total += temp.fetchWeight();
			return total;
		}

		public double fetchCost() {
			double total = 0;
			for (Animal temp : fetchAllAnimals())
				total += temp.fetchFoodCost(cornCost, hayCost);
			return total;
		}

		// "int..." turns it into an Int Array
		// functions normally as expected
		public void killCows(int... indexs) {
			for (int i = 0; i < indexs.length; i++)
				cows.remove(indexs[i]);
		}

		public void addCows(Cow... cows) {
			for (int i = 0; i < cows.length; i++)
				this.cows.add(cows[i]);
		}

		public void killHorse(int... indexs) {
			for (int i = 0; i < indexs.length; i++)
				horses.remove(indexs[i]);
		}

		public void addHorse(Horse... horses) {
			for (int i = 0; i < horses.length; i++)
				this.horses.add(horses[i]);
		}
	}

	public static class Animal {

		private int weight;
		private int hayPerDay;
		private int cornPerDay;

		public Animal(int weight, int hayPerDay, int cornPerDay) {
			this.weight = weight;
			this.hayPerDay = hayPerDay;
			this.cornPerDay = cornPerDay;
		}

		public int fetchWeight() {
			return weight;
		}

		/** @return number of hay eaten each day */
		public int fetchHayConsumption() {
			return hayPerDay;
		}

		/** @return number of corn eaten each day */
		public int fetchCornConsumption() {
			return cornPerDay;
		}

		/** @return fetches the cost of food for the animal */
		public double fetchFoodCost(double cornCost, double hayCost) {
			return cornCost * cornPerDay + hayCost * hayPerDay;
		}
	}

	public static class Cow extends Animal {

		private int milkPerDay;

		public Cow(int weight, int hayPerDay, int cornPerDay, int milkPerDay) {
			super(weight, hayPerDay, cornPerDay);
			this.milkPerDay = milkPerDay;
		}

		/** @return the amount of milk produced by the cow per day */
		public int fetchMilkCreation() {
			return milkPerDay;
		}

		public double fetchMilkValue(double perPound) {
			return milkPerDay * perPound;
		}
	}

	public static class Horse extends Animal {

		private int rideCount;
		private double costPerRide;

		public Horse(int weight, int hayPerDay, int cornPerDay, int rideCount, double costPerRide) {
			super(weight, hayPerDay, cornPerDay);
			this.rideCount = rideCount;
			this.costPerRide = costPerRide;
		}

		public int fetchRideCount() {
			return rideCount;
		}

		public double fetchCostPerRide() {
			return costPerRide;
		}

		public double fetchValue() {
			return rideCount * costPerRide;
		}
	}

}