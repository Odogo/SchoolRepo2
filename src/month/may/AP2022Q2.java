package month.may;

public class AP2022Q2 {

    public static class Book {

        private String title;
        private double price;

        public Book(String title, double price) {
            this.title = title;
            this.price = price;
        }

        public String getTitle() { return title; }
        public double getPrice() { return price; }
        public String getBookInfo() { return title + "-" + price; }
    }

    public static class Textbook extends Book {

        private int edition;

        public Textbook(String title, double price, int edition) {
            super(title, price);
            this.edition = edition;
        }

        public int getEdition() { return edition; }
        @Override public String getBookInfo() { return super.getBookInfo() + "-" + edition; }

        public boolean canSubstitueFor(Textbook other) {
            if(!other.getTitle().equals(this.getTitle())) return false;
            if (other.getEdition() >= this.getEdition()) return false;
            return true;
        }
    }

    public static void main(String[] args) {
        Textbook bio2015 = new Textbook("Biology", 49.75, 2015);
        Textbook bio2019 = new Textbook("Biology", 39.75, 2019);

        System.out.println(bio2015.getBookInfo());
        System.out.println(bio2019.getBookInfo());
        System.out.println(bio2019.canSubstitueFor(bio2015));
        System.out.println(bio2015.canSubstitueFor(bio2019));

        Textbook math = new Textbook("Math", 49.75, 2019);
        System.out.println(math.getBookInfo());
        System.out.println(math.canSubstitueFor(bio2019));
    }
}
