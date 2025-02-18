package renderer.display;

import java.awt.Canvas;
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

import renderer.renderables.Renderable;

public class Renderer extends Frame {

    private final List<Renderable> renderables = new ArrayList<>();
    private final ScheduledExecutorService itterator = Executors.newSingleThreadScheduledExecutor();

    private final Canvas canvas;
    private final Camera camera;

    private static final int FRAME_DURATION = 1000 / 60;

    public Renderer(Runnable updateFunction, Camera camera) {
        this.camera = camera;

        canvas = new Canvas() {
            @Override
            public void paint(Graphics g) {
                updateFunction.run();
            }
        };
        
        add(canvas);
        pack();
        canvas.createBufferStrategy(3);

        setVisible(true);
        setSize(800, 600);
        setTitle("Renderer");

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                dispose();
                System.exit(0);
            }
        });

    }

    public void registerRenderable(Renderable renderable) {
        renderables.add(renderable);
    }

    @Override
    public void repaint() {
        BufferStrategy buffer = canvas.getBufferStrategy();
        RenderGraphic g = new RenderGraphic(buffer.getDrawGraphics());
        camera.update();
        renderables.forEach(r -> r.paint(g, camera));
        g.getGraphics().dispose();
        buffer.show();
        Toolkit.getDefaultToolkit().sync();
    }
    
    public void start() {
        itterator.scheduleAtFixedRate(() -> repaint(), 0, FRAME_DURATION, java.util.concurrent.TimeUnit.MILLISECONDS);
    }
}
