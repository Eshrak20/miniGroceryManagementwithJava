import javax.swing.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class GroceryApp {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Grocery Management");

        JLabel nameLabel = new JLabel("Product Name:");
        nameLabel.setBounds(20, 30, 100, 30);
        JTextField nameField = new JTextField();
        nameField.setBounds(130, 30, 200, 30);

        JLabel priceLabel = new JLabel("Price:");
        priceLabel.setBounds(20, 80, 100, 30);
        JTextField priceField = new JTextField();
        priceField.setBounds(130, 80, 200, 30);

        JLabel quantityLabel = new JLabel("Quantity:");
        quantityLabel.setBounds(20, 130, 100, 30);
        JTextField quantityField = new JTextField();
        quantityField.setBounds(130, 130, 200, 30);

        JButton saveButton = new JButton("Save Product");
        saveButton.setBounds(130, 180, 150, 40);

        frame.add(nameLabel);
        frame.add(nameField);
        frame.add(priceLabel);
        frame.add(priceField);
        frame.add(quantityLabel);
        frame.add(quantityField);
        frame.add(saveButton);

        frame.setSize(400, 300);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Event handling for save button
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                double price = Double.parseDouble(priceField.getText());
                int quantity = Integer.parseInt(quantityField.getText());

                try {
                    Connection con = DBConnection.getConnection();
                    String query = "INSERT INTO products (name, price, quantity) VALUES (?, ?, ?)";
                    PreparedStatement pst = con.prepareStatement(query);
                    pst.setString(1, name);
                    pst.setDouble(2, price);
                    pst.setInt(3, quantity);

                    int inserted = pst.executeUpdate();
                    if (inserted > 0) {
                        JOptionPane.showMessageDialog(frame, "Product Saved Successfully!");
                        nameField.setText("");
                        priceField.setText("");
                        quantityField.setText("");
                    } else {
                        JOptionPane.showMessageDialog(frame, "Failed to save product.");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage());
                }
            }
        });
    }
}
