package dev.tetris.entities;

import dev.tetris.Handler;
import dev.tetris.worlds.World;
import java.awt.Color;
import java.awt.Graphics;

public class Player {

    protected Handler handler;
    protected int x, y;
    protected int[][] matrix;
    private Arena arena;

    public Player(Handler handler, int x, int y) {
	this.handler = handler;
	this.x = x;
	this.y = y;
	this.matrix = Piece.createPiece('T');
    }

    public void tick() {
	if (handler.getGame().isGameOn()) {
	    playerDrop();
	    handler.getGame().setGameOn(false);
	}
    }

    public void render(Graphics g) {
	g.setColor(Color.red);
	for (int row = 0; row < matrix.length; row++) {
	    for (int col = 0; col < matrix[row].length; col++) {
		if (matrix[row][col] != 0) {
		    g.setColor(Piece.getColor(matrix[row][col]));
		    g.fillRect((x + col) * World.SCALE,
			    (y + row) * World.SCALE,
			    World.SCALE, World.SCALE);
		}
	    }
	}
    }

    public boolean isCollide() {
	for (int row = 0; row < matrix.length; row++) {
	    for (int col = 0; col < matrix[row].length; col++) {
		try {
		    if (matrix[row][col] != 0
			    && arena.getMatrix()[row + y][col + x] != 0) {
			return true;
		    }
		} catch (Exception e) {
		    return true;
		}
	    }
	}
	return false;
    }

    public void merge() {
	for (int row = 0; row < matrix.length; row++) {
	    for (int col = 0; col < matrix[row].length; col++) {
		if (matrix[row][col] != 0) {
		    arena.getMatrix()[row + y][col + x] = matrix[row][col];
		}
	    }
	}
    }

    public void playerDrop() {
	this.y++;
	if (isCollide()) {
	    this.y--;
	    merge();
	    playerReset();
	}
	handler.getGame().setTimer(0);
    }

    public void playerMove(int offset) {
	this.x += offset;
	if (isCollide()) {
	    this.x -= offset;
	}
    }

    private void rotate(int dir) {
	int temp;
	for (int row = 0; row < matrix.length; row++) {
	    for (int col = 0; col < row; col++) {
		temp = matrix[row][col];
		matrix[row][col] = matrix[col][row];
		matrix[col][row] = temp;
	    }
	}

	if (dir > 0) {
	    for (int row = 0; row < matrix.length / 2; row++) {
		for (int col = 0; col < matrix[row].length; ++col) {
		    temp = matrix[row][col];
		    matrix[row][col] = matrix[matrix.length - row - 1][col];
		    matrix[matrix.length - row - 1][col] = temp;
		}

	    }
	} else {
	    for (int row = 0; row < matrix.length; row++) {
		for (int col = 0; col < matrix[row].length / 2; col++) {
		    temp = matrix[row][col];
		    matrix[row][col] = matrix[row][matrix.length - col - 1];
		    matrix[row][matrix.length - col - 1] = temp;
		}
	    }
	}
    }

    public void playerRotate(int dir) {
	int posX = x;
	int offset = 1;
	rotate(offset);

	while (isCollide()) {
	    this.x += offset;
	    offset = -(offset + (offset > 0 ? 1 : -1));
	    if (offset > matrix.length) {
		rotate(-dir);
		this.x = posX;
		return;
	    }
	}
    }

    public void playerReset() {
	String pieces = "TJLOSZI";
	char selectedPiece = pieces.charAt((int) (Math.random() * pieces.length()));

	matrix = Piece.createPiece(selectedPiece);
	this.y = 0;
	this.x = (arena.getWidth() / 2) - (matrix.length / 2) - 1;

	if (isCollide()) {
	    arena.clearMap();
	    handler.getWorld().setScore(0);
	}
    }

    public int getX() {
	return x;
    }

    public void setX(int x) {
	this.x = x;
    }

    public int getY() {
	return y;
    }

    public void setY(int y) {
	this.y = y;
    }

    public int[][] getMatrix() {
	return matrix;
    }

    public void setMatrix(int[][] matrix) {
	this.matrix = matrix;
    }

    public Arena getArena() {
	return arena;
    }

    public void setArena(Arena arena) {
	this.arena = arena;
    }
}
