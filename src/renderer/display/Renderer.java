package renderer.display;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import renderer.input.Listener;
import renderer.renderables.Renderable;

public class Renderer extends Frame {
    private final Screen gameScreen;
    private final Camera camera;
    private final Listener listener;
    private final List<Renderable> objects;
    private final ScheduledExecutorService itterator;
    private int width = 800;
    private int height = 600;
    private static final int FRAME_DURATION = 1000 / 60;
    private boolean visible = true;
    private String title = "Animator";

    public Renderer(Runnable update, Camera camera, Listener listener) {
        super();

        this.camera = camera;

        this.listener = listener;

        this.itterator = Executors.newSingleThreadScheduledExecutor();

        this.objects = new ArrayList<>();

        this.gameScreen = new Screen(update);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                dispose();
                System.exit(0);
            }
        });

        addKeyListener(listener);

        add(gameScreen);
        pack();
        gameScreen.createBufferStrategy(3);

    }

    public Listener getListener() {
        return listener;
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
        RenderGraphic g = new RenderGraphic(bufferStrategy.getDrawGraphics());
        camera.updateProjectionMatrix();
        gameScreen.update(g.getGraphic());
        drawAxies(g);
        objects.forEach(object -> object.paint(g, camera));
        g.getGraphic().dispose();
        bufferStrategy.show();
        Toolkit.getDefaultToolkit().sync();
    }

    public void drawAxies(RenderGraphic g) {
        float[] origin = { 0, 0, 0 };
        float[] x = { 5, 0, 0 };
        float[] y = { 0, 5, 0 };
        float[] z = { 0, 0, 5 };

        g.setColor(Color.RED);
        g.drawLine(origin, x, camera);
        g.setColor(Color.GREEN);
        g.drawLine(origin, y, camera);
        g.setColor(Color.BLUE);
        g.drawLine(origin, z, camera);
    }

    public void register(Renderable Object) {
        objects.add(Object);
    }

    public void start() {
        applyConfig();
        itterator.scheduleAtFixedRate(this::repaint, 0, FRAME_DURATION, java.util.concurrent.TimeUnit.MILLISECONDS);
    }

}
