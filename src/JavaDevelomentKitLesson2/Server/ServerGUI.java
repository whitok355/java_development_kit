package JavaDevelomentKitLesson2.Server;

import javax.swing.*;
import java.awt.*;

public class ServerGUI extends JFrame implements ServerView{
    public static final int WIDTH = 400;
    public static final int HEIGHT = 300;

    JButton btnStart, btnStop;
    JTextArea log;
    private ServerController server;

    /**
     * ServerWindow - метод запуска процесса формирования окна "Chat server".
     **/
    public ServerGUI() {
        setting();
        createPanel();
        setVisible(true);
    }


    private void setting() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setTitle("Chat server");
        setLocationRelativeTo(null);
        server = new ServerController(this, new LogFile());
    }

    /**
     * Метод getConnection возвращает экземпляр класса ServerController.
     * @return экземпляр класса Server
     */
    public ServerController getConnection() {
        return server;
    }

    private void createPanel() {
        log = new JTextArea();
        add(log);
        add(createButtons(), BorderLayout.SOUTH);
    }


    private Component createButtons() {
        JPanel panel = new JPanel(new GridLayout(1, 2));
        btnStart = new JButton("Start");
        btnStop = new JButton("Stop");
        btnStart.addActionListener(e -> server.start());
        btnStop.addActionListener(e -> server.stop());
        panel.add(btnStart);
        panel.add(btnStop);
        return panel;
    }

    /**
     * Метод showMessage добавляет переданный текст в конец текстовой области.
     * @param msg - крайнее сообщение в чате.
     */
    @Override
    public void showMessage(String msg) {
        log.append(msg);
    }
}
