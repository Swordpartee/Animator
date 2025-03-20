package renderer.display;

import java.awt.Canvas;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import renderer.io.RenderListener;

import renderer.renderables.Renderable;

public class Renderer extends Frame {

    private final List<Renderable> renderables = new ArrayList<>();

    private final ScheduledExecutorService itterator = Executors.newSingleThreadScheduledExecutor();

    private final Runnable updateFunction;
    
    private final Camera camera;
    private final RenderListener listener;
    private Canvas canvas;

    private static final int FRAME_DURATION = 1000 / 60;

    public Renderer(Runnable updateFunction, Camera camera, RenderListener listener) {
        this.updateFunction = updateFunction;

        this.camera = camera;
        this.listener = listener;

        initScreen();
        
        addListener(listener);
    }

    public Renderer(Runnable updateFunction) {
        this(updateFunction, new Camera(), new RenderListener());
    }
    
    public Renderer() {
        this(() -> {}, new Camera(), new RenderListener());
    }

    private void initScreen() {
        canvas = new Canvas();

        add(canvas);
        pack();
        canvas.createBufferStrategy(3);

        setVisible(true);
        setFocusable(true);
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
    
    private void addListener(RenderListener listener) {
        addKeyListener(listener.getKeyListener());
        addMouseListener(listener.getMouseListener());
        addMouseMotionListener(listener.getMouseListener());
        addWindowListener(listener.getWindowListener());

        canvas.addKeyListener(listener.getKeyListener());
        canvas.addMouseListener(listener.getMouseListener());
        canvas.addMouseMotionListener(listener.getMouseListener());
        
    }
    
    public Renderable add(Renderable renderable) {
        renderables.add(renderable);
        return renderable;
    }
    

    public Camera getCamera() {
        return camera;
    }

    public RenderListener getListener() {
        return listener;
    }

    private RenderGraphic getRenderGraphic() {
        return new RenderGraphic(canvas.getBufferStrategy().getDrawGraphics(), camera);
    }

    @Override
    public void repaint() {
        // get graphics
        RenderGraphic graphics = getRenderGraphic();

        // update and render display
        try {updateFunction.run();} catch (Exception e) {throw e;}
        camera.update();
        renderables.forEach(r -> r.paint(graphics));
        graphics.render();

        // dispose graphics and show display
        graphics.getGraphics().dispose();
        canvas.getBufferStrategy().show();
        Toolkit.getDefaultToolkit().sync();
    }
    
    public void start() {
        canvas.requestFocus();
        itterator.scheduleAtFixedRate(() -> {try {repaint();} catch (Exception e) {throw e;}}, 0, FRAME_DURATION, java.util.concurrent.TimeUnit.MILLISECONDS);
    }
}
