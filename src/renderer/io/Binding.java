package renderer.io;

public class Binding {
    private final EventCode.EventType eventType;
    private final EventCode code;
    private final Runnable action;

    public Binding(EventCode.EventType event, EventCode code, Runnable action) {
        this.eventType = event;
        this.code = code;
        this.action = action;
    }

    public EventCode.EventType getEventType() {
        return eventType;
    }

    public EventCode getCode() {
        return code;
    }

    public void run() {
        action.run();
    }
}
