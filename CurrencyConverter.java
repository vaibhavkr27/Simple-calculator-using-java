import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CurrencyConverter extends JFrame {
    private final double[] rates = {
            1.0,
            0.91,
            147.50,
            90.0,
            1.26
    };
    private final String[] currencies = {
            "USD", "EUR", "JPY", "INR", "CAD"
    };

    private JTextField amountField;
    private JComboBox<String> fromCurrency, toCurrency;
    private JLabel resultLabel;

    public CurrencyConverter() {
        setTitle("Currency Converter");
        setLayout(new FlowLayout());
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        amountField = new JTextField(10);
        fromCurrency = new JComboBox<>(currencies);
        toCurrency = new JComboBox<>(currencies);
        JButton convertButton = new JButton("Convert");
        resultLabel = new JLabel("Result: ");

        add(new JLabel("Amount:"));
        add(amountField);
        add(new JLabel("From:"));
        add(fromCurrency);
        add(new JLabel("To:"));
        add(toCurrency);
        add(convertButton);
        add(resultLabel);

        convertButton.addActionListener(e -> convertCurrency());
    }

    private void convertCurrency() {
        try {
            double amount = Double.parseDouble(amountField.getText());
            int fromIndex = fromCurrency.getSelectedIndex();
            int toIndex = toCurrency.getSelectedIndex();

            double convertedAmount = amount * (rates[toIndex] / rates[fromIndex]);
            resultLabel.setText(String.format("Result: %.2f %s",
                    convertedAmount, currencies[toIndex]));
        } catch (NumberFormatException ex) {
            resultLabel.setText("Invalid input");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new CurrencyConverter().setVisible(true);
        });
    }
}