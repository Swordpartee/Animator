package display;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Display extends Frame {
    private final AnimationScreen gameScreen;
    private int width = 800;
    private int height = 600;
    private boolean visible = false;
    private String title = "Animator";

    public Display() {
        super();
        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                dispose();
                System.exit(0);
            }
        });

        this.gameScreen = new AnimationScreen();
        add(gameScreen);
        pack();
        
    }

    public void config(Boolean visible, Integer width, Integer height, String title) {
        this.visible = visible != null ? visible : this.visible;
        this.width = width != null ? width : this.width;
        this.height = height != null ? height : this.height;
        this.title = title != null ? title : this.title;
    }

    public void applyConfig() {
        setSize(width, height);
        setTitle(title);
        setVisible(visible);
        gameScreen.setSize(width, height);
        gameScreen.setVisible(visible);
    }

    @Override
    public void repaint() {
        gameScreen.repaint();
    }
}
