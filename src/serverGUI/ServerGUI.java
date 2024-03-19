package serverGUI;

import clientGUI.ClientGUI;
import service.CurrentDate;
import service.WriterService;
import javax.swing.*;
import java.awt.*;

public class ServerGUI {
    //region
    private static Boolean isActive = false;
    ServerWindow serverWindow;
    public static Boolean getIsActive() {
        return isActive;
    }
    public static void setIsActive(Boolean value) {
        isActive = value;
    }
    //endregion
    public void start() {
        serverWindow = new ServerWindow();
        serverWindow.generateWindow();
    }
    private void createClientGUI(){
        ClientGUI firstUser = new ClientGUI(this);
        firstUser.renderWindow();
        ClientGUI secondUser = new ClientGUI(this);
        secondUser.renderWindow();
    }
    public void writeIntoWindowServerGUI(String msg){
        serverWindow.writeIntoWindow(msg);
    }
    private class ServerWindow extends JFrame {
        //region
        private static final int WINDOW_WEIGHT = 500, WINDOW_HEIGHT = 500;
        private static final JButton BTN_START = new JButton("start");
        private static final JButton BTN_STOP = new JButton("stop");
        private static final JPanel panBottom = new JPanel(new GridLayout(1,2));
        private static final CurrentDate currentDate = new CurrentDate();
        private static final WriterService logWriter = new WriterService();
        private static final JPanel panMsg = new JPanel(new GridLayout());
        private static final JTextArea textArea = new JTextArea();
        private final static String pathLog = "./log.txt";
        String msg;
        //endregion
        public void generateWindow(){
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setSize(WINDOW_HEIGHT, WINDOW_WEIGHT);
            setResizable(false);
            setLocationRelativeTo(null);
            setTitle("Server window");
            panBottom.add(BTN_START);
            panBottom.add(BTN_STOP);

            BTN_START.addActionListener(e-> startBtnAction());
            BTN_STOP.addActionListener(e-> stopBtnAaction());

            add(panBottom, BorderLayout.SOUTH);
            setVisible(true);
        }
        private void startBtnAction(){
            if(ServerGUI.getIsActive()){
                msg = currentDate.getCurrentDateAndTime() + "Сервер уже запущен";
                logWriter.wirteIntoFile(msg, pathLog);
            } else{
                msg = currentDate.getCurrentDateAndTime() + "Сервер запущен";
                logWriter.wirteIntoFile(msg, pathLog);
                ServerGUI.setIsActive(true);
                createClientGUI();
            }
            writeIntoWindow(msg);
        }
        private void stopBtnAaction(){
            String msg;
            if(!ServerGUI.getIsActive()) {
                msg = currentDate.getCurrentDateAndTime() + "Сервер уже отключен";
                logWriter.wirteIntoFile(msg, pathLog);
            }
            else{
                msg = currentDate.getCurrentDateAndTime() + "Сервер остановлен";
                logWriter.wirteIntoFile(msg, pathLog);
                ServerGUI.setIsActive(false);
            }
            writeIntoWindow(msg);
        }
        private void writeIntoWindow(String msg){
            textArea.append(msg);
            textArea.append("\n");
            panMsg.add(textArea);
            add(panMsg);
            revalidate();
        }
    }
}
