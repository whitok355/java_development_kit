package clientGUI;

import db.DB;
import serverGUI.ServerGUI;
import service.CurrentDate;
import service.ReaderService;
import service.WriterService;
import service.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ClientGUI {
    private ClientWindow clientWindow = new ClientWindow();
    private ServerGUI ser;
    private DB db = new DB();
    private User currentUser;
    private final static String pathLog = "./log.txt";
    private final static String pathHistory = "./chat.txt";
    private ReaderService readerService = new ReaderService();
    public ClientGUI(ServerGUI ser) {
        this.ser = ser;
    }
    public void renderWindow(){
        clientWindow.generateWindow();
    }
    private void autorizationClientGUI(String nick, String pass){
        User findUser = db.findUser(nick, pass);
        if(findUser == null){
            clientWindow.writer("Пользователь с ником "+ nick + " не найден в системе", pathLog);
        } else {
            if(findUser.getActive()){
                if(currentUser == null){
                    clientWindow.writer("Пользователь " + nick + " уже авторизован в другом окне", pathLog);
                } else{
                    if (findUser.getNickname().equals(currentUser.getNickname())) {
                        clientWindow.writer("Пользователь " + nick + " уже авторизован", pathLog);
                    }
                }
            } else {
                if(DB.checkUser(findUser.getNickname(), findUser.getPassword())){
                    currentUser = findUser;
                    currentUser.setActive(true);
                    clientWindow.writer("Пользователь с ником "+ nick +" успешно авторизован", pathLog);
                    clientWindow.writer(nick +" присоеденился к беседе", pathHistory);
                    clientWindow.removePanel();
                    clientWindow.writeIntoClientGUIWindow(nick + " присоеденился к беседе");
                    clientWindow.generateBottomPanel();
                    clientWindow.writeIntoClientGUIWindow(readerService.getChatHistory());
                } else {
                    clientWindow.writer("Неверный пароль для пользователя "+ nick, pathLog);
                }
            }
        }
    }
    private class ClientWindow extends JFrame {
        //region
        private static final int WINDOW_WEIGHT = 500, WINDOW_HEIGHT = 500;
        private JButton BTN_SEND = new JButton("send");
        private JButton BTN_LOGIN = new JButton("login");
        private JPanel topJpanel = new JPanel(new GridLayout(2,1));
        private JPanel panBottom = new JPanel(new GridLayout(1,2));
        private CurrentDate currentDate = new CurrentDate();
        private JTextArea textArea = new JTextArea();
        private JPanel panMsg = new JPanel();
        private JTextField USER_LOGIN;
        private JTextField USER_PASS;
        private JTextArea USER_INPUT;
        //endregion"Пользователь с ником"+ nick +" успешно авторизован"
        public void generateWindow(){
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setTitle("Client chat: the user is not logged in");
            setResizable(false);
            setSize(WINDOW_WEIGHT, WINDOW_HEIGHT);

            topJpanel.add(new JTextField());
            topJpanel.add(new JTextField());
            topJpanel.add(new Label());

            USER_LOGIN = new JTextField();
            USER_PASS = new JTextField();

            topJpanel.add(USER_LOGIN);
            topJpanel.add(USER_PASS);
            topJpanel.add(BTN_LOGIN);
            topJpanel.setBorder(new EmptyBorder(5, 0, 0, 0));

            BTN_LOGIN.addActionListener(e-> autorizationClientGUI(USER_LOGIN.getText(), USER_PASS.getText()));
            add(topJpanel, BorderLayout.NORTH);
            add(panBottom, BorderLayout.SOUTH);
            setVisible(true);
        }
        private void generateBottomPanel(){
            setTitle("Client chat: the user "+ currentUser.getNickname() +" is logged in. Hello");
            USER_INPUT = new JTextArea();
            panBottom.add(USER_INPUT);
            panBottom.add(BTN_SEND);
            BTN_SEND.addActionListener(e-> sendMsg(USER_INPUT.getText()));
            add(panBottom, BorderLayout.SOUTH);
            revalidate();
        }
        private void sendMsg(String msg){
            if(!msg.equals("")){
                String messageIntoWrite = currentDate.getCurrentDateAndTime() + " " + currentUser.getNickname() + " " + msg;
                writer(messageIntoWrite, pathLog);
                writeIntoClientGUIWindow(messageIntoWrite);
            }
        }
        private void writer(String msg, String path){
            WriterService.wirteIntoFile( currentDate.getCurrentDateAndTime() + msg, path);

            if(path.equals(pathLog)){
                ser.writeIntoWindowServerGUI(msg);
            }
        }
        private void writeIntoClientGUIWindow(String msg){
            textArea.append(msg);
            textArea.append("\n");
            panMsg.add(textArea);
            add(panMsg, BorderLayout.WEST);
            revalidate();
        }
        private void removePanel(){
            remove(topJpanel);
        }
    }
}
