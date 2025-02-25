public class pizzeria {
    public String storeAddress;
    public String storeHours;
    public long storePhone;
    public String storeEmail;
    public String storeName;
    private String pizzaPrice;
    private String pizzaIngredients;
    private String sides;
    private String drinks;
    private String orderID;
    private double orderTotal;

    private static final String DEF_ORDER_ID = "DEF-SOH-099";
    private static final String DEF_PIZZA_INGREDIENTS = "Mozzarella Cheese";
    private static final double DEF_ORDER_TOTAL = 15.00;

    public pizzeria() {
        this.orderID = DEF_ORDER_ID;
        this.pizzaIngredients = DEF_PIZZA_INGREDIENTS;
        this.orderTotal = DEF_ORDER_TOTAL;
        this.sides = "Garlic Bread";
        this.drinks = "Coke";
    }

    public pizzeria(String orderID, String pizzaIngredients, double orderTotal) {
        this.orderID = orderID;
        this.pizzaIngredients = pizzaIngredients;
        this.orderTotal = orderTotal;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public double getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(double orderTotal) {
        this.orderTotal = orderTotal;
    }

    public String getPizzaIngredients() {
        return pizzaIngredients;
    }

    public void setPizzaIngredients(String pizzaIngredients) {
        this.pizzaIngredients = pizzaIngredients;
    }

    public void takeOrder(String Price, String id, double total) {
        pizzaPrice = Price;
        orderID = id;
        orderTotal = total;

        System.out.println("Order accepted!");
        System.out.println("Order is being prepared");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            System.out.println("Order preparation was interrupted!");
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

