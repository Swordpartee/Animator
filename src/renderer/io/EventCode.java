package renderer.io;

public enum EventCode {
    W(87, ListenerType.KEY), 
    A(65, ListenerType.KEY), 
    S(83, ListenerType.KEY), 
    D(68, ListenerType.KEY),
    E(69, ListenerType.KEY),
    Q(81, ListenerType.KEY),
    R(82, ListenerType.KEY),
    UP(38, ListenerType.KEY), 
    DOWN(40, ListenerType.KEY), 
    LEFT(37, ListenerType.KEY), 
    RIGHT(39, ListenerType.KEY),
    CTRL(17, ListenerType.KEY), 
    ESC(27, ListenerType.KEY), 
    SPACE(32, ListenerType.KEY), 
    SHIFT(16, ListenerType.KEY),
    LEFT_MOUSE(1, ListenerType.MOUSE), 
    RIGHT_MOUSE(3, ListenerType.MOUSE),
    MOUSE_MOVED(0, ListenerType.MOUSE);

    private final int value;
    private final ListenerType listenerType;

    EventCode(int value, ListenerType listenerType) {
        this.value = value;
        this.listenerType = listenerType;
    }

    public int getValue() {
        return value;
    }

    public ListenerType getType() {
        return listenerType;
    }

    public static EventCode fromValue(int value) {
        for (EventCode eventCode : EventCode.values()) {
            if (eventCode.value == value) {
                return eventCode;
            }
        }
        return null;
    }

    public enum ListenerType {
        KEY, 
        MOUSE, 
        WINDOW;
    }
    
    public enum EventType {
        KEY_PRESSED(ListenerType.KEY), 
        KEY_RELEASED(ListenerType.KEY),
                
        MOUSE_RELEASED(ListenerType.MOUSE),
        MOUSE_PRESSED(ListenerType.MOUSE),
        MOUSE_MOVED(ListenerType.MOUSE),
        
        WINDOW_CLOSED(ListenerType.WINDOW),
        WINDOW_FOCUSED(ListenerType.WINDOW);
        
        private final ListenerType listenerType;

        EventType(ListenerType listenerType) {
            this.listenerType = listenerType;
        }

        public ListenerType getListenerType() {
            return listenerType;
        }
    }
}


