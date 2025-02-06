package display;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;

public class Display extends Frame {
    private final AnimationScreen gameScreen;
    private final Camera camera;
    private int width = 800;
    private int height = 600;
    private boolean visible = false;
    private String title = "Animator";

    public Display() {
        super();

        this.camera = new Camera(10, 10, 10, 0, 0, 0, 60.0f, 800.0f / 600.0f, 0.1f, 1000.0f);
        
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
        gameScreen.createBufferStrategy(3);
        
    }

    public Camera getCamera() {
        return camera;
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
        BufferStrategy bufferStrategy = gameScreen.getBufferStrategy();
        Graphics g = bufferStrategy.getDrawGraphics();
        gameScreen.update(g);
        g.dispose();
        bufferStrategy.show();
        Toolkit.getDefaultToolkit().sync();
    }
}
