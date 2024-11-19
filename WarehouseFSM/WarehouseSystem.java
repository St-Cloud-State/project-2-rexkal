public class WarehouseSystem {
    public static void main(String[] args) {
        // Create an instance of StateContext
        StateContext context = StateContext.instance();
        
        // Start with the LoginState
        context.setState(new LoginState(context));
        context.handle();
    }
}
