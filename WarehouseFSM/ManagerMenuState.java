import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ManagerMenuState implements State {
    private StateContext context;

    public ManagerMenuState(StateContext context) {
        this.context = context;
    }

    @Override
    public void handle(StateContext context) {
        JFrame frame = new JFrame("Manager Menu");
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        JButton addProductButton = new JButton("Add Product");
        JButton displayWaitlistButton = new JButton("Display Waitlist");
        JButton receiveShipmentButton = new JButton("Receive Shipment");
        JButton becomeClerkButton = new JButton("Become Clerk");
        JButton logoutButton = new JButton("Logout");

        addProductButton.addActionListener(e -> {
            // Implement Add Product functionality
            JOptionPane.showMessageDialog(frame, "Add Product functionality");
        });

        displayWaitlistButton.addActionListener(e -> {
            // Implement Display Waitlist functionality
            JOptionPane.showMessageDialog(frame, "Display Waitlist functionality");
        });

        receiveShipmentButton.addActionListener(e -> {
            // Implement Receive Shipment functionality
            JOptionPane.showMessageDialog(frame, "Receive Shipment functionality");
        });

        becomeClerkButton.addActionListener(e -> {
            context.setState(new ClerkMenuState(context));  // Transition to Clerk Menu
            context.handle();
        });

        logoutButton.addActionListener(e -> {
            context.setState(new LoginState(context));  // Transition back to LoginState
            context.handle();
        });

        panel.add(addProductButton);
        panel.add(displayWaitlistButton);
        panel.add(receiveShipmentButton);
        panel.add(becomeClerkButton);
        panel.add(logoutButton);

        frame.add(panel);
        frame.setSize(500, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
