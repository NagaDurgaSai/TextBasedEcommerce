import java.util.HashMap;
import java.util.Scanner;

public class EcommerceAppter {
    private HashMap<String, HashMap<String, Double>> products;
    private HashMap<String, Double> cart;
    private Scanner scanner;

    public EcommerceAppter() {
        products = new HashMap<>();
        cart = new HashMap<>();
        scanner = new Scanner(System.in);


        HashMap<String, Double> fruits = new HashMap<>();
        fruits.put("apple", 10.0);
        fruits.put("banana", 6.0);
        fruits.put("pineapple", 13.0);
        fruits.put("mango",15.0);
        products.put("fruits", fruits);

        HashMap<String, Double> vegetables = new HashMap<>();
        vegetables.put("carrot", 0.5);
        vegetables.put("potato", 0.75);

        products.put("vegetables", vegetables);

        HashMap<String, Double> electronics = new HashMap<>();
        electronics.put("laptop", 10000.0);
        electronics.put("smartphone", 2800.0);
        electronics.put("smartwatch",1200.0);
        electronics.put("headphones",800.0);
        products.put("electronics", electronics);

        HashMap<String, Double> clothing = new HashMap<>();
        clothing.put("shirt", 20.0);
        clothing.put("pants", 30.0);
        clothing.put("kurta",25.0);
        clothing.put("sarees",35.0);
        products.put("clothing", clothing);

        HashMap<String, Double> books = new HashMap<>();
        books.put("novel", 10.0);
        books.put("textbook", 50.0);
        products.put("books", books);

        HashMap<String, Double> toys = new HashMap<>();
        toys.put("teddy bear", 5.0);
        toys.put("lego set", 20.0);
        products.put("toys", toys);
    }
    String un;
    public void login(){
        System.out.println("Enter your username:   ");
        un= scanner.nextLine();
        scanner.nextLine();
        System.out.println("Enter password:   ");
        int password=scanner.nextInt();

        if(un.equalsIgnoreCase("SAITEJASWI")){
            if(password==10072004){
                showHome();
            }
            else{
                System.out.println("Incorrect password");
            }
        }
        else if(un.equalsIgnoreCase("SIVARAM")){
            if(password==2705){
                showHome();
            }
            else{
                System.out.println("Incorrect password");
            }
        }
        else if(un.equalsIgnoreCase("HARSHITHA")){
            if(password==131203){
                showHome();
            }
            else{
                System.out.println("Incorrect password");
            }
        }
        else if(un.equalsIgnoreCase("AK")){
            if(password==21482){
                showHome();
            }
            else{
                System.out.println("Incorrect password");
            }
        }
        else{
            System.out.println("User doesnt exist");
        }
    }


    public void showHome() {
        System.out.println(un+"  "+"Welcome to our e-commerce store!");
        System.out.println("Please select an option:");
        System.out.println("1. View cart");
        System.out.println("2. Select category");
        System.out.println("3. Ramzan Offers");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                viewCart();
                break;
            case 2:
                showCategories();
                break;
            case 3:
                showOffers();
                break;
            default:
                System.out.println("Invalid choice.");
                showHome();
                break;
        }
    }

    public void showCategories() {
        System.out.println("Please select a category:");

        for (String category : products.keySet()) {

            System.out.println("- " + category);
        }

        String category = scanner.nextLine();

        if (products.containsKey(category)) {
            showProducts(category);
        } else {
            System.out.println("Invalid category.");
            showCategories();
        }
    }

    public void showProducts(String category) {
        System.out.println("Please select a product:");

        for (String product : products.get(category).keySet()) {
            System.out.println("- " + product + " (Rs " + products.get(category).get(product) + ")");
        }

        String product = scanner.nextLine();

        if (products.get(category).containsKey(product)) {
            System.out.println("Please select an option:");
            System.out.println("1. Add to cart");
            System.out.println("2. Buy now");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addToCart(category, product);
                    break;
                case 2:
                    buyNow();
                    break;
                default:
                    System.out.println("Invalid choice.");
                    showProducts(category);
                    break;
            }
        } else {
            System.out.println("Invalid product.");
            showProducts(category);
        }
    }

    public void addToCart(String category, String product) {
        if (cart.containsKey(product)) {
            cart.put(product, cart.get(product) + 1);
        } else {
            cart.put(product, 1.0);
        }

        System.out.println(product + " added to cart.");
        showHome();
    }

    public void buyNow() {
        double total = 0.0;

        if (cart.isEmpty()) {
            System.out.println("Your cart is empty.");
            showHome();
        } else {
            System.out.println("Your cart contains:");

            for (String product : cart.keySet()) {
                double price = products.get(getCategory(product)).get(product);
                double quantity = cart.get(product);
                double subtotal = price * quantity;
                total += subtotal;

                System.out.println("- " + product + " x " + quantity + " (Rs " + price + " each) = Rs " + subtotal);
            }

            System.out.println("Total: Rs " + total);
            System.out.println("Please select an option:");
            System.out.println("1. Exit");
            System.out.println("2. Make payment");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    cart.clear();
                    showHome();
                    break;
                case 2:
                    makePayment(total);
                    break;
                default:
                    System.out.println("Invalid choice.");
                    buyNow();
                    break;
            }
        }
    }

    public void makePayment(double total) {
        System.out.println("Payment of Rs " + total + " successful!");
        if(total>40){
            System.out.println("Congrats! Shopping more than 40 you have got a teddy bear worth RS.20 as a special gift");
        }

        cart.clear();
        showHome();
    }

    public String getCategory(String product) {
        for (String category : products.keySet()) {
            if (products.get(category).containsKey(product)) {
                return category;
            }
        }

        return null;
    }

    public void viewCart() {
        if (cart.isEmpty()) {
            System.out.println("Your cart is empty.");
        } else {
            System.out.println("Your cart contains:");

            for (String product : cart.keySet()) {
                double price = products.get(getCategory(product)).get(product);
                double quantity = cart.get(product);
                double subtotal = price * quantity;

                System.out.println("- " + product + " x " + quantity + " (Rs " + price + " each) = Rs " + subtotal);
            }

            System.out.println("Please select an option:");
            System.out.println("1. Remove item from cart");
            System.out.println("2. Buy now");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    removeItem();
                    break;
                case 2:
                    buyNow();
                    break;
                default:
                    System.out.println("Invalid choice.");
                    viewCart();
                    break;
            }
        }

        showHome();
    }

    public void removeItem() {
        System.out.println("Please enter the name of the item to remove:");

        String item = scanner.nextLine();

        if (cart.containsKey(item)) {
            cart.remove(item);
            System.out.println(item + " removed from cart.");
        } else {
            System.out.println(item + " not found in cart.");
        }

        viewCart();
    }
    public void showOffers(){
        System.out.println("1. Special discounts on clothing");
        System.out.println("2. Special discounts on electronics");
        System.out.println("3. Special discounts on books");
        System.out.println("4. Free gift on Rs. 40 worth shopping");
        int choice1 = scanner.nextInt();
        scanner.nextLine();

        switch (choice1) {
            case 1:
                showCategories();
                break;
            case 2:
                showCategories();
                break;
            case 3:
                showCategories();
                break;
            case 4:
                showCategories();
            default:
                System.out.println("Invalid choice.");
                showHome();
                break;
        }
    }

    public static void main(String[] args) {
        EcommerceAppter app = new EcommerceAppter();
        app.login();
    }
}
