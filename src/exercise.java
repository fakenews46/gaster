
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
    private static final double PIZZA_BASE_PRICE = 10.0;

    private String[] pizzasOrdered = new String[10];
    private String[] pizzaSizesOrdered = new String[10];
    private String[] sideDishesOrdered = new String[20];
    private String[] drinksOrdered = new String[20];
    private double totalOrderPrice = 0.0;

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

        while (true) {
            System.out.println("Welcome to Slice-o-Heaven Pizzeria. Here’s what we serve:");
            for (PizzaSelection pizza : PizzaSelection.values()) {
                System.out.println(pizza);
            }
            System.out.println("6. Custom Pizza with a maximum of 10 toppings that you choose");
            System.out.println("Please enter your choice (1 - 6):");
            int pizzaChoice = scanner.nextInt();

            if (pizzaChoice >= 1 && pizzaChoice <= 5) {
                PizzaSelection selectedPizza = PizzaSelection.values()[pizzaChoice - 1];
                pizzasOrdered[0] = selectedPizza.toString();
                totalOrderPrice += selectedPizza.getPrice();
            } else if (pizzaChoice == 6) {
                StringBuilder customPizza = new StringBuilder("Custom Pizza with ");
                double customPizzaPrice = PIZZA_BASE_PRICE;

                System.out.println("Please pick up to 10 toppings from the following list:");
                for (PizzaToppings topping : PizzaToppings.values()) {
                    System.out.println(topping);
                }
                System.out.println("Enter your choices (1, 2, 3,...) separated by spaces:");
                for (int i = 0; i < 10; i++) {
                    int toppingChoice = scanner.nextInt();
                    if (toppingChoice >= 1 && toppingChoice <= PizzaToppings.values().length) {
                        PizzaToppings selectedTopping = PizzaToppings.values()[toppingChoice - 1];
                        customPizza.append(selectedTopping.getTopping()).append(", ");
                        customPizzaPrice += selectedTopping.getToppingPrice();
                    } else {
                        break;
                    }
                }
                customPizza.append("for €").append(customPizzaPrice);
                pizzasOrdered[0] = customPizza.toString();
                totalOrderPrice += customPizzaPrice;
            } else {
                System.out.println("Invalid choice. Please pick only from the given list:");
                continue;
            }

            System.out.println("What size should your pizza be?");
            for (PizzaSize size : PizzaSize.values()) {
                System.out.println(size);
            }
            System.out.println("Enter only one choice (1, 2, or 3):");
            int sizeChoice = scanner.nextInt();
            PizzaSize selectedSize = PizzaSize.values()[sizeChoice - 1];
            pizzaSizesOrdered[0] = selectedSize.getPizzaSize();
            totalOrderPrice += selectedSize.getAddToPizzaPrice();

            System.out.println("Do you want extra cheese (Y/N):");
            String extraCheese = scanner.next();

            System.out.println("Following are the side dish that go well with your pizza:");
            for (SideDish side : SideDish.values()) {
                System.out.println(side);
            }
            System.out.println("What would you like? Pick one (1, 2, 3,...):");
            int sideDishChoice = scanner.nextInt();
            SideDish selectedSide = SideDish.values()[sideDishChoice - 1];
            sideDishesOrdered[0] = selectedSide.getSideDishName();
            totalOrderPrice += selectedSide.getAddToPizzaPrice();

            System.out.println("Choose from one of the drinks below. We recommend Coca Cola:");
            for (Drinks drink : Drinks.values()) {
                System.out.println(drink);
            }
            System.out.println("Enter your choice:");
            int drinkChoice = scanner.nextInt();
            Drinks selectedDrink = Drinks.values()[drinkChoice - 1];
            drinksOrdered[0] = selectedDrink.getDrinkName();
            totalOrderPrice += selectedDrink.getAddToPizzaPrice();

            System.out.println("Would you like the chance to pay only half for your order? (Y/N):");
            String wantDiscount = scanner.next();

            if (wantDiscount.equalsIgnoreCase("Y")) {
                isItYourBirthday();
            } else {
                makeCardPayment();
            }

            System.out.println("Would you like to order more? (Y/N):");
            String orderMore = scanner.next();
            if (!orderMore.equalsIgnoreCase("Y")) {
                break;
            }
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
        StringBuilder receipt = new StringBuilder("Thank you for dining with Slice-o-Heaven Pizzeria. Your order details are as follows:\n");
        for (int i = 0; i < pizzasOrdered.length && pizzasOrdered[i] != null; i++) {
            receipt.append(i + 1).append(". ").append(pizzasOrdered[i]).append("\n");
            receipt.append("   ").append(pizzaSizesOrdered[i]).append("\n");
            receipt.append("   ").append(sideDishesOrdered[i]).append("\n");
            receipt.append("   ").append(drinksOrdered[i]).append("\n");
        }
        receipt.append("ORDER TOTAL: €").append(totalOrderPrice);
        return receipt.toString();
    }

    public static void main(String[] args) {
        pizzeria pizzeria = new pizzeria();
        pizzeria.takeOrder();
        System.out.println(pizzeria);
    }
}

enum PizzaSelection {
    PEPPERONI("Pepperoni", "Lots of pepperoni and extra cheese", 18),
    HAWAIIAN("Hawaiian", "Pineapple, ham, and extra cheese", 22),
    VEGGIE("Veggie", "Green pepper, onion, tomatoes, mushroom, and black olives", 25),
    BBQ_CHICKEN("BBQ Chicken", "Chicken in BBQ sauce, bacon, onion, green pepper, and cheddar cheese", 35),
    EXTRAVAGANZA("Extravaganza", "Pepperoni, ham, Italian sausage, beef, onions, green pepper, mushrooms, black olives, and extra cheese", 45);

    private final String pizzaName;
    private final String pizzaToppings;
    private final double price;

    PizzaSelection(String pizzaName, String pizzaToppings, double price) {
        this.pizzaName = pizzaName;
        this.pizzaToppings = pizzaToppings;
        this.price = price;
    }

    public String getPizzaName() {
        return pizzaName;
    }

    public String getPizzaToppings() {
        return pizzaToppings;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return pizzaName + " Pizza with " + pizzaToppings + ", for €" + price;
    }
}

enum PizzaToppings {
    HAM("Ham", 2),
    PEPPERONI("Pepperoni", 2),
    BEEF("Beef", 2),
    CHICKEN("Chicken", 2),
    SAUSAGE("Sausage", 2),
    PINEAPPLE("Pineapple", 1),
    ONION("Onion", 0.5),
    TOMATOES("Tomatoes", 0.4),
    GREEN_PEPPER("Green Pepper", 0.5),
    BLACK_OLIVES("Black Olives", 0.5),
    SPINACH("Spinach", 0.5),
    CHEDDAR_CHEESE("Cheddar Cheese", 0.8),
    MOZZARELLA_CHEESE("Mozzarella Cheese", 0.8),
    FETA_CHEESE("Feta Cheese", 1),
    PARMESAN_CHEESE("Parmesan Cheese", 1);

    private final String topping;
    private final double toppingPrice;

    PizzaToppings(String topping, double toppingPrice) {
        this.topping = topping;
        this.toppingPrice = toppingPrice;
    }

    public String getTopping() {
        return topping;
    }

    public double getToppingPrice() {
        return toppingPrice;
    }

    @Override
    public String toString() {
        return topping + ": €" + toppingPrice;
    }
}

enum PizzaSize {
    LARGE("Large", 10),
    MEDIUM("Medium", 5),
    SMALL("Small", 0);

    private final String pizzaSize;
    private final double addToPizzaPrice;

    PizzaSize(String pizzaSize, double addToPizzaPrice) {
        this.pizzaSize = pizzaSize;
        this.addToPizzaPrice = addToPizzaPrice;
    }

    public String getPizzaSize() {
        return pizzaSize;
    }

    public double getAddToPizzaPrice() {
        return addToPizzaPrice;
    }

    @Override
    public String toString() {
        return pizzaSize + ": €" + addToPizzaPrice;
    }
}

enum SideDish {
    CALZONE("Calzone", 15),
    CHICKEN_PUFF("Chicken Puff", 20),
    MUFFIN("Muffin", 12),
    NOTHING("No side dish", 0);

    private final String sideDishName;
    private final double addToPizzaPrice;

    SideDish(String sideDishName, double addToPizzaPrice) {
        this.sideDishName = sideDishName;
        this.addToPizzaPrice = addToPizzaPrice;
    }

    public String getSideDishName() {
        return sideDishName;
    }

    public double getAddToPizzaPrice() {
        return addToPizzaPrice;
    }

    @Override
    public String toString() {
        return sideDishName + ": €" + addToPizzaPrice;
    }
}

enum Drinks {
    COCA_COLA("Coca Cola", 8),
    COCOA_DRINK("Cocoa Drink", 10),
    NOTHING("No drinks", 0);

    private final String drinkName;
    private final double addToPizzaPrice;

    Drinks(String drinkName, double addToPizzaPrice) {
        this.drinkName = drinkName;
        this.addToPizzaPrice = addToPizzaPrice;
    }

    public String getDrinkName() {
        return drinkName;
    }

    public double getAddToPizzaPrice() {
        return addToPizzaPrice;
    }

    @Override
    public String toString() {
        return drinkName + ": €" + addToPizzaPrice;
    }
}
    

