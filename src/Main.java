package JavaDevelomentKitLesson2;

import JavaDevelomentKitLesson2.Server.ServerGUI;
import JavaDevelomentKitLesson2.CLient.ClientGUI;
public class Main {
    public static void main(String[] args) {
        ServerGUI serverGUI = new ServerGUI();
        new ClientGUI(serverGUI);
        new ClientGUI(serverGUI);
    }
}

