package game;

import java.util.Arrays;

public class GameState {

    private final Cell[] cells;
    private final String currentPlayer;
    private final String winner;

    private GameState(Cell[] cells, String currentPlayer, String winner) {
        this.cells = cells;
        this.currentPlayer = currentPlayer;
        this.winner = winner;
    }

    public static GameState forGame(Game game) {
        Cell[] cells = getCells(game);
        String currentPlayer = getCurrentPlayer(game);
        String winner = getWinner(game);
        return new GameState(cells, currentPlayer, winner);
    }

    public Cell[] getCells() {
        return this.cells;
    }

    public String getCurrentPlayer() {
        return this.currentPlayer;
    }

    public String getWinner() {
        return this.winner;
    }

    @Override
    public String toString() {
        return """
                {
                    "cells": %s,
                    "currentPlayer": "%s",
                    "winner": %s
                }
                """.formatted(Arrays.toString(this.cells), this.currentPlayer,
                this.winner != null ? "\"" + this.winner + "\"" : null);
    }

    private static Cell[] getCells(Game game) {
        Cell cells[] = new Cell[9];
        Board board = game.getBoard();
        for (int x = 0; x <= 2; x++) {
            for (int y = 0; y <= 2; y++) {
                String text = "";
                boolean playable = false;
                Player player = board.getCell(x, y);
                if (player == Player.PLAYER0)
                    text = "X";
                else if (player == Player.PLAYER1)
                    text = "O";
                else if (player == null) {
                    playable = true;
                }
                cells[3 * y + x] = new Cell(x, y, text, playable);
            }
        }
        return cells;
    }

    private static String getCurrentPlayer(Game game) {
        Player currentPlayer = game.getPlayer();
        if (currentPlayer == Player.PLAYER0) {
            return "Player0";
        } else if (currentPlayer == Player.PLAYER1) {
            return "Player1";
        } else {
            return "";
        }
    }

    private static String getWinner(Game game) {
        Player winner = game.getWinner();
        if (winner == Player.PLAYER0) {
            return "Player0";
        } else if (winner == Player.PLAYER1) {
            return "Player1";
        } else {
            return null; // No winner yet
        }
    }
}

class Cell {
    private final int x;
    private final int y;
    private final String text;
    private final boolean playable;

    Cell(int x, int y, String text, boolean playable) {
        this.x = x;
        this.y = y;
        this.text = text;
        this.playable = playable;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getText() {
        return this.text;
    }

    public boolean isPlayable() {
        return this.playable;
    }

    @Override
    public String toString() {
        return """
                {
                    "text": "%s",
                    "playable": %b,
                    "x": %d,
                    "y": %d
                }
                """.formatted(this.text, this.playable, this.x, this.y);
    }
}