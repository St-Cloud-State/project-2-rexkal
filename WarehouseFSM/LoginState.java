import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginState implements State {
    private StateContext context;

    public LoginState(StateContext context) {
        this.context = context;
    }

    @Override
    public void handle(StateContext context) {
        JFrame frame = new JFrame("Login State");
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        JButton clerkButton = new JButton("Login as Clerk");
        JButton managerButton = new JButton("Login as Manager");
        JButton clientButton = new JButton("Login as Client");

        clerkButton.addActionListener(e -> {
            context.setState(new ClerkMenuState(context));  // Transition to Clerk Menu
            context.handle();
        });

        managerButton.addActionListener(e -> {
            context.setState(new ManagerMenuState(context));  // Transition to Manager Menu
            context.handle();
        });

        clientButton.addActionListener(e -> {
            String clientId = JOptionPane.showInputDialog(frame, "Enter Client ID:");
            if (clientId != null && !clientId.isEmpty()) {
                context.setState(new ClientMenuState(context, clientId));  // Transition to Client Menu with client ID
                context.handle();
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid Client ID");
            }
        });

        panel.add(clerkButton);
        panel.add(managerButton);
        panel.add(clientButton);

        frame.add(panel);
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
