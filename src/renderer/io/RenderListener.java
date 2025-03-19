package renderer.io;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import util.Vector;

public class RenderListener {
    private final MouseAdapter mouseListener = new MouseAdapter() {
        @Override
        public void mousePressed(MouseEvent e) {
            if (mouseEvents[e.getButton()] == null) {  
                mouseEvents[e.getButton()] = e;

                callBinding(EventCode.EventType.KEY_PRESSED, e.getButton());
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            mouseEvents[e.getButton()] = null;

            callBinding(EventCode.EventType.KEY_RELEASED, e.getButton());
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            mousePos.setX(e.getPoint().getX() - 400 + 8);
            mousePos.setY(e.getPoint().getY() - 300 + 31);

            callBinding(EventCode.EventType.MOUSE_MOVED);
        }
    };

    private final KeyAdapter keyListener = new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent e) {
            if (keyEvents[e.getKeyCode()] == null) {
                keyEvents[e.getKeyCode()] = e;
    
                callBinding(EventCode.EventType.KEY_PRESSED, e.getKeyCode());
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            keyEvents[e.getKeyCode()] = null;
            
            callBinding(EventCode.EventType.KEY_RELEASED, e.getKeyCode());

        }
    };

    private final WindowAdapter windowListener = new WindowAdapter() {
        @Override
        public void windowClosed(WindowEvent e) {
            callBinding(EventCode.EventType.WINDOW_CLOSED);
        }

        @Override
        public void windowGainedFocus(WindowEvent e) {
            callBinding(EventCode.EventType.WINDOW_FOCUSED);

        }
    };
    
    public MouseAdapter getMouseListener() {
        return mouseListener;
    }

    public KeyAdapter getKeyListener() {
        return keyListener;
    }

    public WindowAdapter getWindowListener() {
        return windowListener;
    }

    private final List<Binding> bindings = new ArrayList<>();

    private final MouseEvent[] mouseEvents = new MouseEvent[3];
    private final KeyEvent[] keyEvents = new KeyEvent[256];

    private final Vector mousePos = new Vector();

    public Vector getMousePos() {
        return mousePos;
    }

    public boolean isKeyPressed(int keyCode) {
        return keyEvents[keyCode] != null;
    }

    public boolean isKeyPressed(EventCode code) {
        return isKeyPressed(code.getValue());
    }

    public boolean isMousePressed(int button) {
        return mouseEvents[button] != null;
    }

    public boolean isMousePressed(EventCode code) {
        return isMousePressed(code.getValue());
    }

    public void addBinding(EventCode.EventType eventType, EventCode code, Runnable action) {
        bindings.add(new Binding(eventType, code, action));
    }

    public void addBinding(Binding binding) {
        bindings.add(binding);
    }

    private void callBinding(EventCode.EventType eventType, int code) {
        for (Binding binding : bindings) {
            if (binding.getEventType() == eventType && binding.getCode().getValue() == code) {
                binding.run();
            }
        }
    }

    private void callBinding(EventCode.EventType eventType) {
        for (Binding binding : bindings) {
            if (binding.getEventType() == eventType) {
                binding.run();
            }
        }
    }
}
