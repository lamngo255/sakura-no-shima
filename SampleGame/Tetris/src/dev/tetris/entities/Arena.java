package dev.tetris.entities;

import dev.tetris.Handler;
import dev.tetris.worlds.World;
import java.awt.Graphics;

public class Arena {

    private Handler handler;
    private int width, height;
    private int matrix[][];

    public Arena(Handler handler, int width, int height) {
	this.handler = handler;
	this.width = width;
	this.height = height;
	this.matrix = new int[height][width];
    }

    public void tick() {
	sweep();
    }

    public void render(Graphics g) {
	for (int row = 0; row < height; row++) {
	    for (int col = 0; col < width; col++) {
		if (matrix[row][col] != 0) {
		    g.setColor(Piece.getColor(matrix[row][col]));
		    g.fillRect(col * World.SCALE,
			    row * World.SCALE,
			    World.SCALE, World.SCALE);
		}

	    }
	}
    }

    public void clearRow(int index) {
	for (int row = index - 1; row > 0; --row) {
	    System.arraycopy(matrix[row], 0, matrix[row + 1], 0, width);
	}
    }

    public void sweep() {
	int rowCount = 0;
	outer:
	for (int row = 0; row < height; row++) {
	    for (int col = 0; col < width; col++) {
		if (matrix[row][col] == 0) {
		    continue outer;
		}
	    }
	    clearRow(row);
	    rowCount++;
	}
	handler.getWorld().addScore(rowCount * 10);
    }

    public void clearMap() {
	for (int row = 0; row < height; ++row) {
	    for (int col = 0; col < width; col++) {
		matrix[row][col] = 0;
	    }
	}
    }

    public int getWidth() {
	return width;
    }

    public void setWidth(int width) {
	this.width = width;
    }

    public int getHeight() {
	return height;
    }

    public void setHeight(int height) {
	this.height = height;
    }

    public int[][] getMatrix() {
	return matrix;
    }

    public void setMatrix(int[][] matrix) {
	this.matrix = matrix;
    }

}
