import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Product {
    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}

class EcommerceWebsite {
    private Map<String, Map<Integer, Product>> productsByCategory;
    private Map<Integer, Product> cart;

    public EcommerceWebsite() {
        productsByCategory = new HashMap<>();
        cart = new HashMap<>();
    }

    public void addProduct(String category, Product product) {
        if (!productsByCategory.containsKey(category)) {
            productsByCategory.put(category, new HashMap<>());
        }
        productsByCategory.get(category).put(productsByCategory.get(category).size() + 1, product);
    }

    public void displayCategories() {
        System.out.println("Categories: ");
        int index = 1;
        for (String category : productsByCategory.keySet()) {
            System.out.println(index + ". " + category);
            index++;
        }
    }

    public void displayProducts(int categoryIndex) {
        String category = getCategoryByIndex(categoryIndex);
        if (category != null) {
            System.out.println("Products in " + category + ": ");
            Map<Integer, Product> products = productsByCategory.get(category);
            for (Integer index : products.keySet()) {
                Product product = products.get(index);
                System.out.println(index + ". " + product.getName() + " - " + product.getPrice());
            }
        } else {
            System.out.println("Category not found.");
        }
    }

    public void addToCart(int categoryIndex, int productIndex) {
        String category = getCategoryByIndex(categoryIndex);
        if (category != null) {
            Map<Integer, Product> products = productsByCategory.get(category);
            if (products.containsKey(productIndex)) {
                Product product = products.get(productIndex);
                cart.put(cart.size() + 1, product);
                System.out.println("Added " + product.getName() + " to cart.");
            } else {
                System.out.println("Product not found in the specified category.");
            }
        } else {
            System.out.println("Category not found.");
        }
    }

    public void displayCart() {
        if (cart.isEmpty()) {
            System.out.println("Cart is empty.");
        } else {
            System.out.println("Cart: ");
            double total = 0;
            for (Integer index : cart.keySet()) {
                Product product = cart.get(index);
                System.out.println(index + ". " + product.getName() + " - " + product.getPrice());
                total += product.getPrice();
            }
            System.out.println("Total: " + total);
        }
    }

    private String getCategoryByIndex(int index) {
        int i = 1;
        for (String category : productsByCategory.keySet()) {
            if (i == index) {
                return category;
            }
            i++;
        }
        return null;
    }
}

public class TextBasedEcommerceApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EcommerceWebsite ecommerceWebsite = new EcommerceWebsite();
        ecommerceWebsite.addProduct("Electronics", new Product("Smartphone", 9999.99));
        ecommerceWebsite.addProduct("Electronics", new Product("Laptop", 49999.99));
        ecommerceWebsite.addProduct("Books", new Product("Novel", 299.99));
        ecommerceWebsite.addProduct("Books", new Product("Textbook", 599.99));
        ecommerceWebsite.addProduct("Clothing", new Product("T-shirt", 299.99));
        ecommerceWebsite.addProduct("Clothing", new Product("Jeans", 999.99));
        System.out.println("Welcome to the Text-Based Ecommerce App!");
        while (true) {
            System.out.println("Please select an option:");
            System.out.println("1. View Categories");
            System.out.println("2. View Products in a Category");
            System.out.println("3. Add Product to Cart");
            System.out.println("4. View Cart");
            System.out.println("5. Exit");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    ecommerceWebsite.displayCategories();
                    break;
                case 2:
                    System.out.println("Enter category index:");
                    int categoryIndex = scanner.nextInt();
                    ecommerceWebsite.displayProducts(categoryIndex);
                    break;
                case 3:
                    System.out.println("Enter category index:");
                    categoryIndex = scanner.nextInt();
                    System.out.println("Enter product index:");
                    int productIndex = scanner.nextInt();
                    ecommerceWebsite.addToCart(categoryIndex, productIndex);
                    break;
                case 4:
                    ecommerceWebsite.displayCart();
                    break;
                case 5:
                    System.out.println("Thank you for using the Text-Based Ecommerce App. Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
