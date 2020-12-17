import Fxml.ListOfStatusLobbies;
import Fxml.Test;
import Servers.GameServer;
import Servers.MenuServer;
import javafx.application.Application;
import javafx.stage.Stage;

import java.applet.Applet;

public class Main {
    public static void main(String[] args) {
        try {
            MenuServer.init();
            GameServer.init();


            Thread menuServer = new Thread(MenuServer.menuServer);
            menuServer.start();

            Thread gameServer = new Thread(GameServer.gameServer);
            gameServer.start();
            ListOfStatusLobbies.main(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
