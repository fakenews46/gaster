public class pizzeria {
    public String storeAddress;
    public String storeHours;
    public long storePhone;
    public String storeEmail;
    public String storeName;
    private String pizzaPrice;
    private String orderID;
    private double orderTotal;

    public void takeOrder(String Price, String id, double total) {
        pizzaPrice = Price;
        orderID = id;
        orderTotal = total;

        System.out.println("Order accepted!");
        System.out.println("Order is being prepared");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            System.out.println("Order is ready for pickup!");
        }

        System.out.println("Your order is ready!");
        printReceipt();
    }

    private void printReceipt() {
        System.out.println("********RECEIPT********");
        System.out.println("Order Price: " + pizzaPrice);
        System.out.println("Order ID: " + orderID);
        System.out.println("Order Total: " + orderTotal);
    }

    
}

