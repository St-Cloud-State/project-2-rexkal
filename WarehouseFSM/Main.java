import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Create StateContext instance
        StateContext context = StateContext.instance();

        // Initialize the LoginState and pass it to context
        new LoginState(context).handle(context);
    }
}
