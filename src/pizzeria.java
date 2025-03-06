import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

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

    public void takeOrder() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter three ingredients for your pizza (use spaces to separate ingredients):");
        String ing1 = scanner.next();
        String ing2 = scanner.next();
        String ing3 = scanner.next();
        pizzaIngredients = ing1 + ", " + ing2 + ", " + ing3;

        System.out.println("Enter size of pizza (Small, Medium, Large):");
        String pizzaSize = scanner.next();

        System.out.println("Do you want extra cheese (Y/N):");
        String extraCheese = scanner.next();

        System.out.println("Enter one side dish (Calzone, Garlic bread, None):");
        sides = scanner.next();

        System.out.println("Enter drinks (Cold Coffee, Cocoa drink, Coke, None):");
        drinks = scanner.next();

        System.out.println("Would you like the chance to pay only half for your order? (Y/N):");
        String wantDiscount = scanner.next();

        if (wantDiscount.equalsIgnoreCase("Y")) {
            isItYourBirthday();
        } else {
            makeCardPayment();
        }
    }

    public void isItYourBirthday() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter your birthdate (yyyy-MM-dd):");
        String birthdateStr = scanner.next();
        LocalDate birthdate = LocalDate.parse(birthdateStr, DateTimeFormatter.ISO_LOCAL_DATE);
        LocalDate today = LocalDate.now();
        long age = ChronoUnit.YEARS.between(birthdate, today);

        if (age < 18 && birthdate.getMonth() == today.getMonth() && birthdate.getDayOfMonth() == today.getDayOfMonth()) {
            System.out.println("Congratulations! You pay only half the price for your order");
            orderTotal /= 2;
        } else {
            System.out.println("Too bad! You do not meet the conditions to get our 50% discount");
        }
    }

    public void makeCardPayment() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter your card number:");
        long cardNumber = scanner.nextLong();

        System.out.println("Enter the card's expiry date (yyyy-MM):");
        String expiryDate = scanner.next();

        System.out.println("Enter the card's CVV:");
        int cvv = scanner.nextInt();

        processCardPayment(cardNumber, expiryDate, cvv);
    }

    public void processCardPayment(long cardNumber, String expiryDate, int cvv) {
        String cardNumberStr = Long.toString(cardNumber);
        int cardLength = cardNumberStr.length();
        if (cardLength == 14) {
            System.out.println("Card accepted");
        } else {
            System.out.println("Invalid card");
        }

        int firstCardDigit = Integer.parseInt(cardNumberStr.substring(0, 1));
        long blacklistedNumber = 12345678901234L;
        if (cardNumber == blacklistedNumber) {
            System.out.println("Card is blacklisted. Please use another card");
            return;
        }

        int lastFourDigits = Integer.parseInt(cardNumberStr.substring(cardLength - 4));
        StringBuilder cardNumberToDisplay = new StringBuilder(cardNumberStr.substring(0, 1));
        for (int i = 1; i < cardLength - 4; i++) {
            cardNumberToDisplay.append('*');
        }
        cardNumberToDisplay.append(cardNumberStr.substring(cardLength - 4));

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

    private void printReceipt() {
        System.out.println("********RECEIPT********");
        System.out.println("Order Price: " + pizzaPrice);
        System.out.println("Order ID: " + orderID);
        System.out.println("Order Total: " + orderTotal);
    }
}

