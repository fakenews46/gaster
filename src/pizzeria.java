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

    public void processCardPayment(String cardNumber, String expiryDate, int cvv) {
        int cardLength = cardNumber.length();
        if (cardLength == 14) {
            System.out.println("Card accepted");
        } else {
            System.out.println("Invalid card");
        }

        int firstCardDigit = Integer.parseInt(cardNumber.substring(0, 1));
        String blacklistedNumber = "12345678901234";
        if (cardNumber.equals(blacklistedNumber)) {
            System.out.println("Card is blacklisted. Please use another card");
            return;
        }

        int lastFourDigits = Integer.parseInt(cardNumber.substring(cardLength - 4));
        StringBuilder cardNumberToDisplay = new StringBuilder(cardNumber.substring(0, 1));
        for (int i = 1; i < cardLength - 4; i++) {
            cardNumberToDisplay.append('*');
        }
        cardNumberToDisplay.append(cardNumber.substring(cardLength - 4));

        System.out.println("Processing payment with card number: " + cardNumberToDisplay);
        System.out.println("Expiry date: " + expiryDate);
        System.out.println("CVV: " + cvv);
    }

    public void specialOfTheDay(String pizzaOfTheDay, String sideOfTheDay, String specialPrice) {
        System.out.println("Today's Special:");
        System.out.println("Pizza: " + pizzaOfTheDay);
        System.out.println("Side: " + sideOfTheDay);
        System.out.println("Special Price: " + specialPrice);
    }
}

