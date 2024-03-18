import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ServerGUI {
    private List<User> users = new ArrayList<>();
    private int port;
    private String connect_url;
    private static Boolean flag = false;

    public static void setFlag() {
        flag = !flag;
    }
    public ServerGUI(int port, String connect_url){
        this.port = port;
        this.connect_url = connect_url;
    }
    public void start(){
        ServerWindow serverWindow = new ServerWindow();
        serverWindow.generateWindow();
    }
    private class ServerWindow extends JFrame {
        private int WINDOW_WEIGHT = 500, WINDOW_HEIGHT = 500;
        JButton BTN_START = new JButton("start");
        JButton BTN_STOP = new JButton("stop");
        public void generateWindow(){
            JPanel pannBottom = new JPanel(new GridLayout(1,2));
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setSize(WINDOW_HEIGHT, WINDOW_WEIGHT);
            setResizable(false);
            setLocationRelativeTo(null);
            setTitle("Server window");
            pannBottom.add(BTN_START);
            pannBottom.add(BTN_STOP);
            BTN_START.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(ServerGUI.flag){
                        System.out.println(1);
                        renderMsg("Сервер уже запущен");
                    } else{
                        System.out.println(2);
                        renderMsg("Сервер запущен");
                        ServerGUI.setFlag();
                    }
                }
            });
            BTN_STOP.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(!ServerGUI.flag) {
                        renderMsg("Сервер уже отключен");
                    }
                    else{
                        renderMsg("Сервер остановлен");
                        ServerGUI.setFlag();
                    }

                }
            });
            add(pannBottom, BorderLayout.SOUTH);
            setVisible(true);
        }
        private void renderMsg(String msg){
            JPanel panel = new JPanel();
            JLabel label = new JLabel(msg);
            panel.add(label);
            add(panel);
        }
    }

}
