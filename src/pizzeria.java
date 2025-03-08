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
    private static final long BLACKLISTED_NUMBER = 12345678901234L;

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

        String[] ingredients = {"Mushroom", "Paprika", "Sun-dried tomatoes", "Chicken", "Pineapple"};
        String ing1 = "", ing2 = "", ing3 = "";

        while (true) {
            System.out.println("Please pick any three of the following ingredients:");
            for (int i = 0; i < ingredients.length; i++) {
                System.out.println((i + 1) + ". " + ingredients[i]);
            }
            System.out.println("Enter any three choices (1, 2, 3,...) separated by spaces:");
            int ingChoice1 = scanner.nextInt();
            int ingChoice2 = scanner.nextInt();
            int ingChoice3 = scanner.nextInt();

            if (ingChoice1 >= 1 && ingChoice1 <= 5 && ingChoice2 >= 1 && ingChoice2 <= 5 && ingChoice3 >= 1 && ingChoice3 <= 5) {
                ing1 = ingredients[ingChoice1 - 1];
                ing2 = ingredients[ingChoice2 - 1];
                ing3 = ingredients[ingChoice3 - 1];
                break;
            } else {
                System.out.println("Invalid choice(s). Please pick only from the given list:");
            }
        }
        pizzaIngredients = ing1 + ", " + ing2 + ", " + ing3;

        String[] sizes = {"Large", "Medium", "Small"};
        String pizzaSize = "";

        while (true) {
            System.out.println("What size should your pizza be?");
            for (int i = 0; i < sizes.length; i++) {
                System.out.println((i + 1) + ". " + sizes[i]);
            }
            System.out.println("Enter only one choice (1, 2, or 3):");
            int sizeChoice = scanner.nextInt();

            if (sizeChoice >= 1 && sizeChoice <= 3) {
                pizzaSize = sizes[sizeChoice - 1];
                break;
            } else {
                System.out.println("Invalid choice. Please pick only from the given list:");
            }
        }

        System.out.println("Do you want extra cheese (Y/N):");
        String extraCheese = scanner.next();

        String[] sideDishes = {"Calzone", "Garlic bread", "Chicken puff", "Muffin", "Nothing for me"};
        String sideDish = "";

        while (true) {
            System.out.println("Following are the side dish that go well with your pizza:");
            for (int i = 0; i < sideDishes.length; i++) {
                System.out.println((i + 1) + ". " + sideDishes[i]);
            }
            System.out.println("What would you like? Pick one (1, 2, 3,...):");
            int sideDishChoice = scanner.nextInt();

            if (sideDishChoice >= 1 && sideDishChoice <= 5) {
                sideDish = sideDishes[sideDishChoice - 1];
                break;
            } else {
                System.out.println("Invalid choice. Please pick only from the given list:");
            }
        }
        sides = sideDish;

        String[] drinkOptions = {"Coca Cola", "Cold coffee", "Cocoa Drink", "No drinks for me"};
        String drink = "";

        while (true) {
            System.out.println("Choose from one of the drinks below. We recommend Coca Cola:");
            for (int i = 0; i < drinkOptions.length; i++) {
                System.out.println((i + 1) + ". " + drinkOptions[i]);
            }
            System.out.println("Enter your choice:");
            int drinkChoice = scanner.nextInt();

            if (drinkChoice >= 1 && drinkChoice <= 4) {
                drink = drinkOptions[drinkChoice - 1];
                break;
            } else {
                System.out.println("Invalid choice. Please pick only from the given list:");
            }
        }
        drinks = drink;

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

        while (true) {
            System.out.println("Enter your birthdate (yyyy-MM-dd):");
            String birthdateStr = scanner.next();
            LocalDate birthdate = LocalDate.parse(birthdateStr, DateTimeFormatter.ISO_LOCAL_DATE);
            LocalDate today = LocalDate.now();
            long age = ChronoUnit.YEARS.between(birthdate, today);

            if (age >= 5 && age <= 120) {
                if (age < 18 && birthdate.getMonth() == today.getMonth() && birthdate.getDayOfMonth() == today.getDayOfMonth()) {
                    System.out.println("Congratulations! You pay only half the price for your order");
                    orderTotal /= 2;
                } else {
                    System.out.println("Too bad! You do not meet the conditions to get our 50% discount");
                }
                break;
            } else {
                System.out.println("Invalid date. You are either too young or too dead to order. Please enter a valid date:");
            }
        }
    }

    public void makeCardPayment() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter your card number:");
        long cardNumber = scanner.nextLong();

        while (true) {
            System.out.println("Enter the card's expiry date (yyyy-MM):");
            String expiryDate = scanner.next();
            LocalDate expiry = LocalDate.parse(expiryDate + "-01", DateTimeFormatter.ISO_LOCAL_DATE);
            LocalDate today = LocalDate.now();

            if (expiry.isAfter(today)) {
                System.out.println("Enter the card's CVV:");
                int cvv = scanner.nextInt();
                processCardPayment(cardNumber, expiryDate, cvv);
                break;
            } else {
                System.out.println("Invalid expiry date. Please enter a future date:");
            }
        }
    }

    public void processCardPayment(long cardNumber, String expiryDate, int cvv) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String cardNumberStr = Long.toString(cardNumber);
            int cardLength = cardNumberStr.length();

            if (cardLength == 14 && cardNumber != BLACKLISTED_NUMBER) {
                System.out.println("Card accepted");

                int firstCardDigit = Integer.parseInt(cardNumberStr.substring(0, 1));
                int lastFourDigits = Integer.parseInt(cardNumberStr.substring(cardLength - 4));
                StringBuilder cardNumberToDisplay = new StringBuilder(cardNumberStr.substring(0, 1));
                for (int i = 1; i < cardLength - 4; i++) {
                    cardNumberToDisplay.append('*');
                }
                cardNumberToDisplay.append(cardNumberStr.substring(cardLength - 4));

                System.out.println("Processing payment with card number: " + cardNumberToDisplay);
                System.out.println("Expiry date: " + expiryDate);
                System.out.println("CVV: " + cvv);
                break;
            } else {
                System.out.println("Invalid card number or card is blacklisted. Please enter a valid card number:");
                cardNumber = scanner.nextLong();
            }
        }
    }

    public void specialOfTheDay(String pizzaOfTheDay, String sideOfTheDay, String specialPrice) {
        System.out.println("Today's Special:");
        System.out.println("Pizza: " + pizzaOfTheDay);
        System.out.println("Side: " + sideOfTheDay);
        System.out.println("Special Price: " + specialPrice);
    }

    @Override
    public String toString() {
        return "********RECEIPT********\n" +
                "Order Price: " + pizzaPrice + "\n" +
                "Order ID: " + orderID + "\n" +
                "Order Total: " + orderTotal;
    }

    public static void main(String[] args) {
        pizzeria pizzeria = new pizzeria();
        pizzeria.takeOrder();
        System.out.println(pizzeria);
    }
}