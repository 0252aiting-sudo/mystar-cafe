import java.util.ArrayList;
import java.util.Scanner;

class MenuItem { // class definition
    String name;    // instance variable
    float price;

    public MenuItem(String n, float p) {
        this.name = n;
        this.price = p;
    }

    void display() {
        System.out.println(name + " --- RM " + price);
    }

    // Getter methods
    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }
}

// for store order
class OrderItem {
    MenuItem item;
    int quantity;

    public OrderItem(MenuItem item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public float getSubtotal() {
        return item.getPrice() * quantity;
    }
}

public class App {
    public static void main(String[] args) {
        Scanner sca = new Scanner(System.in);   // input user
        System.out.println("Welcome to the MyStar Café!");
        System.out.println("This is our menu");
        System.out.println("----MENU----");

        // Create an array of MenuItem obj
        MenuItem[] menu = {
            new MenuItem("Espresso", 10),
            new MenuItem("Green Tea", 6),
            new MenuItem("Milk", 5),
            new MenuItem("Mocha", 8),
            new MenuItem("Latte", 9),
            new MenuItem("Iced Coffee", 7),
        };

        // Display Menu Item
        for (MenuItem item : menu) {
            item.display();
        }

        // store order
        ArrayList<OrderItem> orders = new ArrayList<>();

        // recurring order
        while (true) {
            System.out.println("\nWhat would you like to order? (type 'done' to finish)");
            String itemName = sca.nextLine();

            if (itemName.equalsIgnoreCase("done")) {
                break; // finish order
            }

            // Find the item in the Menu
            MenuItem selectedItem = null;
            for (MenuItem item : menu) {
                if (item.getName().equalsIgnoreCase(itemName)) {
                    selectedItem = item;
                    break;
                }
            }

            if (selectedItem != null) {
                System.out.println("How many cups/pieces would you like?");
                int quantity = sca.nextInt();
                sca.nextLine(); 
                orders.add(new OrderItem(selectedItem, quantity));
                System.out.println("Added " + quantity + " " + selectedItem.getName() + "(s) to your order.");
            } else {
                System.out.println("Sorry, we don't have that item.");
            }
        }

        // print receipt
        System.out.println("\n----- RECEIPT -----");
        float grandTotal = 0;
        for (OrderItem order : orders) {
            float subtotal = order.getSubtotal();
            System.out.println(order.quantity + " x " + order.item.getName() + " @ RM " 
            + order.item.getPrice() + " = RM " + subtotal);
            grandTotal += subtotal;
        }
        System.out.println("-------------------");
        System.out.println("TOTAL: RM " + grandTotal);
        System.out.println("Thank you for visiting MyStar Café!");

        sca.close();
    }
}

