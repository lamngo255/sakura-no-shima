package dev.tetris.entities;

import java.awt.Color;

public class Piece {

    public static Color[] colors = new Color[]{
	null,
	new Color(Integer.parseInt("FF0D72", 16)),
	new Color(Integer.parseInt("0DC2FF", 16)),
	new Color(Integer.parseInt("0DFF72", 16)),
	new Color(Integer.parseInt("F538FF", 16)),
	new Color(Integer.parseInt("FF8E0D", 16)),
	new Color(Integer.parseInt("FFE138", 16)),
	new Color(Integer.parseInt("3877FF", 16)),};

    public static int[][] createPiece(char type) {
	int matrix[][];

	switch (type) {
	    case 'T':
		matrix = new int[][]{
		    {0, 0, 0},
		    {1, 1, 1},
		    {0, 1, 0}};
		return matrix;
	    case 'I':
		matrix = new int[][]{
		    {0, 2, 0, 0},
		    {0, 2, 0, 0},
		    {0, 2, 0, 0},
		    {0, 2, 0, 0}};
		return matrix;
	    case 'L':
		matrix = new int[][]{
		    {0, 3, 0},
		    {0, 3, 0},
		    {0, 3, 3}};
		return matrix;
	    case 'J':
		matrix = new int[][]{
		    {0, 4, 0},
		    {0, 4, 0},
		    {4, 4, 0}};
		return matrix;
	    case 'O':
		matrix = new int[][]{
		    {5, 5},
		    {5, 5}};
		return matrix;
	    case 'Z':
		matrix = new int[][]{
		    {6, 6, 0},
		    {0, 6, 6},
		    {0, 0, 0}};
		return matrix;
	    case 'S':
		matrix = new int[][]{
		    {0, 7, 7},
		    {7, 7, 0},
		    {0, 0, 0}};
		return matrix;
	}
	return null;
    }

    public static Color getColor(int id) {
	return colors[id];
    }
}
