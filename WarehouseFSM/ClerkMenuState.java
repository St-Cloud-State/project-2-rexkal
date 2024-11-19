import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Iterator;

public class ClerkMenuState implements State {
    private StateContext context;

    public ClerkMenuState(StateContext context) {
        this.context = context;
    }

    @Override
    public void handle(StateContext context) {
        JFrame frame = new JFrame("Clerk Menu");
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        // Existing buttons
        JButton addClientButton = new JButton("Add Client");
        JButton showProductsButton = new JButton("Show Products");
        JButton showClientsButton = new JButton("Show Clients");
        JButton logoutButton = new JButton("Logout");

        // New buttons for additional functionalities
        JButton addProductButton = new JButton("Add Product");
        JButton removeProductButton = new JButton("Remove Product");
        JButton updateProductButton = new JButton("Update Product");

        // Add Client functionality
        addClientButton.addActionListener(e -> {
            String clientID = IdServer.instance().getClientId();
            String firstName = JOptionPane.showInputDialog(frame, "Enter first name:");
            String lastName = JOptionPane.showInputDialog(frame, "Enter last name:");
            String address = JOptionPane.showInputDialog(frame, "Enter address:");
            String phone = JOptionPane.showInputDialog(frame, "Enter phone number:");

            Client newClient = new Client(clientID, firstName, lastName, address, phone);
            Warehouse.instance().addClient(clientID, firstName, lastName, address, phone);
            JOptionPane.showMessageDialog(frame, "New client added: " + newClient.toString());
        });

        // Show Products functionality with debugging
        showProductsButton.addActionListener(e -> {
            // Fetch the products iterator
            Iterator<Product> productIterator = Warehouse.instance().getProducts();
            
            // Check if there are any products in the iterator
            if (!productIterator.hasNext()) {
                JOptionPane.showMessageDialog(frame, "No products available.");
                return;
            }

            // Build a list of products to display
            StringBuilder products = new StringBuilder("Products List:\n");
            while (productIterator.hasNext()) {
                Product product = productIterator.next();
                products.append(product.toString()).append("\n");
            }
            JOptionPane.showMessageDialog(frame, products.toString());
        });


        // Show Clients functionality
        showClientsButton.addActionListener(e -> {
            Iterator<Client> clientIterator = Warehouse.instance().getClients();
            StringBuilder clients = new StringBuilder("Clients List:\n");
            while (clientIterator.hasNext()) {
                Client client = clientIterator.next();
                clients.append(client.toString()).append("\n");
            }
            JOptionPane.showMessageDialog(frame, clients.toString());
        });

        // Add Product functionality
        addProductButton.addActionListener(e -> {
            String productID = JOptionPane.showInputDialog(frame, "Enter Product ID:");
            String name = JOptionPane.showInputDialog(frame, "Enter Product Name:");
            String priceStr = JOptionPane.showInputDialog(frame, "Enter Product Price:");
            String quantityStr = JOptionPane.showInputDialog(frame, "Enter Product Quantity:");
            
            try {
                double price = Double.parseDouble(priceStr);
                int quantity = Integer.parseInt(quantityStr);
                Product newProduct = new Product(productID, name, price, quantity);
                Warehouse.instance().addProduct(newProduct);
                JOptionPane.showMessageDialog(frame, "New product added: " + newProduct.toString());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid input for price or quantity.");
            }
        });

        // Remove Product functionality
        removeProductButton.addActionListener(e -> {
            String productID = JOptionPane.showInputDialog(frame, "Enter Product ID to Remove:");
            if (Warehouse.instance().removeProduct(productID)) {
                JOptionPane.showMessageDialog(frame, "Product removed successfully.");
            } else {
                JOptionPane.showMessageDialog(frame, "Product not found.");
            }
        });

        // Update Product functionality
        updateProductButton.addActionListener(e -> {
            String productID = JOptionPane.showInputDialog(frame, "Enter Product ID to Update:");
            Product product = Warehouse.instance().findProduct(productID);
            if (product != null) {
                String newPriceStr = JOptionPane.showInputDialog(frame, "Enter new Price (leave blank to keep current):", product.getPrice());
                String newQuantityStr = JOptionPane.showInputDialog(frame, "Enter new Quantity (leave blank to keep current):", product.getQuantity());
                
                if (!newPriceStr.isEmpty()) {
                    product.setPrice(Double.parseDouble(newPriceStr));
                }
                if (!newQuantityStr.isEmpty()) {
                    product.setQuantity(Integer.parseInt(newQuantityStr));
                }
                JOptionPane.showMessageDialog(frame, "Product updated: " + product.toString());
            } else {
                JOptionPane.showMessageDialog(frame, "Product not found.");
            }
        });

        // Logout functionality
        logoutButton.addActionListener(e -> {
            context.setState(new LoginState(context));
            context.handle();
        });

        // Add all buttons to the panel
        panel.add(addClientButton);
        panel.add(showProductsButton);
        panel.add(showClientsButton);
        panel.add(addProductButton);
        panel.add(removeProductButton);
        panel.add(updateProductButton);
        panel.add(logoutButton);

        // Set up the frame
        frame.add(panel);
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
