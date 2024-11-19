public class StateContext {
    private static StateContext instance;
    private State currentState;
    
    // Singleton Pattern
    public static StateContext instance() {
        if (instance == null) {
            instance = new StateContext();
        }
        return instance;
    }
    
    private StateContext() {
        // Initial state is LoginState
        currentState = new LoginState(this);
    }
    
    public void setState(State state) {
        currentState = state;
    }
    
    public void handle() {
        currentState.handle(this);
    }
    
    public State getCurrentState() {
        return currentState;
    }
}
