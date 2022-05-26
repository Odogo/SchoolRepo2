package month.may;

import java.io.IOException;
import java.time.DayOfWeek;
import java.util.*;

public class ThreeTenTengo {

    public static class WorkDayData {

        private double wholeSale;
        private DayOfWeek dayOfWeek;

        public WorkDayData(DayOfWeek dayOfWeek, double wholeSale) {
            this.wholeSale = wholeSale;
            this.dayOfWeek = dayOfWeek;
        }

        public double fetchWholeSale() { return wholeSale; }
        public DayOfWeek fetchDayOfWeek() { return dayOfWeek; }

        @Override public String toString() { return "WholeSale{$" + wholeSale + " on " + dayOfWeek.name() + "}"; }

        public static DayOfWeek next(DayOfWeek dayOfWeek) {
            if (dayOfWeek == DayOfWeek.SUNDAY) return DayOfWeek.MONDAY;
            return dayOfWeek.plus(1);
        }

        public static DayOfWeek prev(DayOfWeek dayOfWeek) {
            if (dayOfWeek == DayOfWeek.MONDAY) return DayOfWeek.SUNDAY;
            return dayOfWeek.minus(1);
        }
    }

    public static void main(String[] args) {
        try(Scanner scanner = new Scanner(System.in)) {
            System.out.print("What is the first day type [Monday, Tuesday, etc] in the month?\nEnter response: ");
            DayOfWeek dayInput;

            try { dayInput = DayOfWeek.valueOf(scanner.nextLine().toUpperCase()); }
            catch(IllegalArgumentException e) { System.err.println("Not a valid day type. Try 'Monday' or 'Friday'"); return; }

            System.out.println("\nEnter month length, default to 31 if no response.\nEnter response: ");
            int monthLength;

            try { monthLength = Integer.parseInt(scanner.nextLine()); }
            catch(NumberFormatException e) { System.err.println("Not a valid number, do not use letters, symbols, or special characters."); return; }

            long startTime = System.currentTimeMillis();
            System.out.println("[Generation] Generating entire month.");

            List<WorkDayData> data = new ArrayList<>();
            for(int i=0; i<monthLength; i++) {
                if(dayInput == DayOfWeek.SUNDAY) dayInput = WorkDayData.next(dayInput); else {
                    data.add(new WorkDayData(dayInput, new Random().nextDouble(10000, 40000)));
                    dayInput = WorkDayData.next(dayInput);
                }
            }
        }
    }
}