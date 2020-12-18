package Fxml;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class LobbyView extends Application {
    private byte[][] maze = new byte[10][8];
    private int max;
    private String title;

    public LobbyView(Lobby lobby) {
        this.maze = lobby.getMaze();
        title = lobby.getName();
    }

    @Override
    public void start(Stage stage) {
        initUI(stage);
    }

    private void initUI(Stage stage) {
        Canvas canvas = new Canvas(270, 240);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        Pane root = new Pane();
        drawLines(gc);
        drawTanks(root);
        root.getChildren().add(canvas);
        Scene scene = new Scene(root, 260, 200, Color.WHITESMOKE);
        stage.setResizable(false);
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }

    private void drawLines(GraphicsContext gc) {
        gc.beginPath();
        int cursorX = 0;
        int cursorY = 0;
        initArray();
        for (int i = 0; i < 10; i++) {
            for (int j = 7; j >= 0; j--) {
                if (maze[i][j] == 1) {
                    gc.moveTo(cursorX, cursorY);
                    gc.lineTo(cursorX + 30, cursorY);
                    cursorY = cursorY + 30;
                }
                if (maze[i][j] == 3) {
                    gc.moveTo(cursorX, cursorY);
                    gc.lineTo(cursorX, cursorY - 30);
                    gc.moveTo(cursorX, cursorY);
                    gc.lineTo(cursorX + 30, cursorY);
                    cursorY = cursorY + 30;
                }
                if (maze[i][j] == 2) {
                    gc.moveTo(cursorX, cursorY);
                    gc.lineTo(cursorX, cursorY - 30);
                    cursorY = cursorY + 30;
                }
                if (maze[i][j] == 0) {
                    cursorY = cursorY + 30;
                }
                if (j == 0) {
                    cursorY = 0;
                    cursorX = cursorX + 30;
                }
            }
        }
        gc.stroke();
    }

    private void drawTanks(Pane root) {
        for (int i = 0; i < max; i++) {
            Rectangle t = new Rectangle();
            if (i == 0) {
                t = new Rectangle(10, 10, 20, 20);
                t.setFill(Color.RED);
            }
            if (i == 1) {
                t = new Rectangle(240, 10, 20, 20);
                t.setFill(Color.BLUE);
            }
            if (i == 2) {
                t = new Rectangle(240, 180, 20, 20);
                t.setFill(Color.YELLOW);
            }
            if (i == 3) {
                t = new Rectangle(10, 180, 20, 20);
                t.setFill(Color.GREEN);
            }
            root.getChildren().add(t);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
