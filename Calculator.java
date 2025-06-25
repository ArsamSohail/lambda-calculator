import javax.swing.JOptionPane;

@FunctionalInterface
interface Operation {
    double calculate(double a, double b);
}

public class Calculator {
    public static void main(String[] args) {
        Operation add = (a, b) -> a + b;
        Operation subtract = (a, b) -> a - b;
        Operation multiply = (a, b) -> a * b;
        Operation divide = (a, b) -> {
            if (b == 0) throw new ArithmeticException("Cannot divide by zero.");
            return a / b;
        };

        String[] options = {"Addition", "Subtraction", "Multiplication", "Division"};
        int choice = JOptionPane.showOptionDialog(
                null,
                "Choose an operation:",
                "Simple Calculator",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                options,
                options[0]
        );

        if (choice == JOptionPane.CLOSED_OPTION) return;

        try {
            double num1 = Double.parseDouble(JOptionPane.showInputDialog("Enter first number:"));
            double num2 = Double.parseDouble(JOptionPane.showInputDialog("Enter second number:"));

            double result = switch (choice) {
                case 0 -> add.calculate(num1, num2);
                case 1 -> subtract.calculate(num1, num2);
                case 2 -> multiply.calculate(num1, num2);
                case 3 -> divide.calculate(num1, num2);
                default -> throw new IllegalStateException("Invalid operation.");
            };

            JOptionPane.showMessageDialog(null, "Result: " + result);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid number format.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (ArithmeticException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Math Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Unexpected error.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}