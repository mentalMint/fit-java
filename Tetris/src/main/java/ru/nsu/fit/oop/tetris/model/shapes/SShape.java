package ru.nsu.fit.oop.tetris.model.shapes;

import javafx.scene.paint.Color;
import ru.nsu.fit.oop.tetris.model.Block;

public class SShape extends Shape {
    public SShape(Color color, int x, int y) {
        super(color, x, y);
        blocks.add(new Block(color,-1,1));
        blocks.add(new Block(color, 0,1));
        blocks.add(new Block(color, 0,0));
        blocks.add(new Block(color, 1,0));
    }

    public SShape(SShape shape) {
        super(shape);
    }
}
