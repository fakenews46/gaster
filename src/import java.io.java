import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

class MissingExtensionException extends Exception {
    public MissingExtensionException(String message) {
        super(message);
    }
}

class ExceptionDemo {
    private double divisor;
    private double dividend;
    private double result;
    public void divide() throws InputMismatchException, ArithmeticException {
        Scanner input = new Scanner(System.in);
        System.out.println("请输入除数:");
        divisor = input.nextDouble();
        System.out.println("请输入被除数:");
        dividend = input.nextDouble();
        result = divisor / dividend;
        System.out.println("结果是: " + result);
    }

    public void goToDivideMethod() throws InputMismatchException, ArithmeticException {
        divide();
    }

    public void displayChoices() {
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println("1. Divide");
            System.out.println("2. Read from a file");
            System.out.println("3. Exit");
            System.out.println("请输入你的选择:");
            try {
                int choice = input.nextInt();
                switch (choice) {
                    case 1:
                        try {
                            goToDivideMethod();
                        } catch (InputMismatchException e) {
                            System.out.println(" Exception " + e + " occurred. A number for expected, but wasn’t provided");
                        } catch (ArithmeticException e) {
                            System.out.println(" Exception " + e + " occurred. Division by zero was attempted");
                        } finally {
                            System.out.println("Closing all the resources");
                        }
                        break;
                    case 2:
                        try {
                            readAFile();
                        } catch (IOException | MissingExtensionException e) {
                            System.out.println(" Exception " + e + " occurred.");
                        } finally {
                            System.out.println("Closing all the resources");
                        }
                        break;
                    case 3:
                        System.exit(0);
                    default:
                        System.out.println("无效的选择，请重新输入。");
                }
            } catch (InputMismatchException e) {
                System.out.println(" Exception " + e + " occurred. A valid number for choice is expected, but wasn’t provided");
                input.nextLine(); 
            }
        }
    }

    // 读取文件的方法
    public void readAFile() throws IOException, MissingExtensionException {
        Scanner input = new Scanner(System.in);
        System.out.println("请输入文件路径:");
        String filePath = input.nextLine();
        if (!filePath.contains(".")) {
            throw new MissingExtensionException("文件路径缺少扩展名");
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line = reader.readLine();
            if (line != null) {
                System.out.println("读取的内容: " + line);
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        ExceptionDemo demo = new ExceptionDemo();
        demo.displayChoices();
    }
}     