import engine.Engine;
import java.awt.Color;

import renderer.display.Camera;
import renderer.display.Renderer;
import renderer.input.Listener;
import renderer.renderables.Cube;
import renderer.tools.Factory;

public class App {

    public static void main(String[] args) throws Exception {
        Cube cube = Factory.createCube();

        cube.setPos(0, 2, 1);

        Engine.register(cube);

        Engine.start();

    }
}
