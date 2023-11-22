package SpaceInvaders.source_code.Controller;

import SpaceInvaders.source_code.Game;
import com.googlecode.lanterna.input.KeyStroke;

import java.io.IOException;

public abstract class Controller<T> {
    private final T model;

    public Controller(T model) {
        this.model = model;
    }

    public T getModel() {
        return model;
    }

    public abstract void step(Game game, KeyStroke key, long time) throws IOException;
}
