package renderer.display;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import renderer.objects.PhysicsObject;

public class Renderer extends Frame {
    private final Canvas gameScreen;
    private final Camera camera;
    private final List<PhysicsObject> physicsObjects;
    private final ScheduledExecutorService itterator;
    private int width = 800;
    private int height = 600;
    private static final int FRAME_DURATION = 1000 / 60;
    private boolean visible = true;
    private String title = "Animator";

    public Renderer(Canvas gameScreen) {
        super();

        this.camera = new Camera(10, 10, 10, 0, 0, 0, 60.0f, 800.0f / 600.0f, 0.1f, 1000.0f);
        
        this.itterator = Executors.newSingleThreadScheduledExecutor();

        this.physicsObjects = new ArrayList<>();

        this.gameScreen = gameScreen;

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                dispose();
                System.exit(0);
            }
        });

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
        camera.updateProjectionMatrix();
        gameScreen.update(g);
        drawAxies(g);
        physicsObjects.forEach(physicsObject -> physicsObject.paint(g, camera));
        g.dispose();
        bufferStrategy.show();
        Toolkit.getDefaultToolkit().sync();
    }

    public void drawAxies(Graphics g) {
        float[] origin = camera.convertTo2D(new float[] {0, 0, 0});
        float[] x = camera.convertTo2D(new float[] {10, 0, 0});
        float[] y = camera.convertTo2D(new float[] {0, 10, 0});
        float[] z = camera.convertTo2D(new float[] {0, 0, 10});

        g.setColor(Color.RED);
        g.drawLine((int) origin[0], (int) origin[1], (int) x[0], (int) x[1]);
        g.setColor(Color.BLUE);
        g.drawLine((int) origin[0], (int) origin[1], (int) y[0], (int) y[1]);
        g.setColor(Color.GREEN);
        g.drawLine((int) origin[0], (int) origin[1], (int) z[0], (int) z[1]);
    }

    public void register(PhysicsObject physicsObject) {
        physicsObjects.add(physicsObject);
    }

    public void start() {
        applyConfig();
        itterator.scheduleAtFixedRate(this::repaint, 0, FRAME_DURATION, java.util.concurrent.TimeUnit.MILLISECONDS);
    }
    
}
