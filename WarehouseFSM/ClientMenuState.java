import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ClientMenuState implements State {
    private StateContext context;
    private String clientId;

    // Constructor now takes clientId
    public ClientMenuState(StateContext context, String clientId) {
        this.context = context;
        this.clientId = clientId;
    }

    @Override
    public void handle(StateContext context) {
        JFrame frame = new JFrame("Client Menu");
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        JButton showClientDetailsButton = new JButton("Show Client Details");
        JButton showProductsButton = new JButton("Show Products");
        JButton showTransactionsButton = new JButton("Show Transactions");
        JButton addToWishlistButton = new JButton("Add to Wishlist");
        JButton displayWishlistButton = new JButton("Display Wishlist");
        JButton placeOrderButton = new JButton("Place Order");
        JButton logoutButton = new JButton("Logout");

        showClientDetailsButton.addActionListener(e -> {
            // Implement Show Client Details functionality
            JOptionPane.showMessageDialog(frame, "Showing details for Client ID: " + clientId);
        });

        showProductsButton.addActionListener(e -> {
            // Implement Show Products functionality
            JOptionPane.showMessageDialog(frame, "Show Products functionality");
        });

        showTransactionsButton.addActionListener(e -> {
            // Implement Show Transactions functionality
            JOptionPane.showMessageDialog(frame, "Show Transactions functionality");
        });

        addToWishlistButton.addActionListener(e -> {
            // Implement Add to Wishlist functionality
            JOptionPane.showMessageDialog(frame, "Add to Wishlist functionality");
        });

        displayWishlistButton.addActionListener(e -> {
            // Implement Display Wishlist functionality
            JOptionPane.showMessageDialog(frame, "Display Wishlist functionality");
        });

        placeOrderButton.addActionListener(e -> {
            // Implement Place Order functionality
            JOptionPane.showMessageDialog(frame, "Place Order functionality");
        });

        logoutButton.addActionListener(e -> {
            context.setState(new LoginState(context));  // Transition back to LoginState
            context.handle();
        });

        panel.add(showClientDetailsButton);
        panel.add(showProductsButton);
        panel.add(showTransactionsButton);
        panel.add(addToWishlistButton);
        panel.add(displayWishlistButton);
        panel.add(placeOrderButton);
        panel.add(logoutButton);

        frame.add(panel);
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
