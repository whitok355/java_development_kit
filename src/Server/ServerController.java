package JavaDevelomentKitLesson2.Server;

import JavaDevelomentKitLesson2.CLient.ClientController;

import java.util.ArrayList;
import java.util.List;

public class ServerController {
    private boolean work;
    private final ServerView serverView;
    private final List<ClientController> clientList;
    private final Repository repository;


    public ServerController(ServerView serverView, Repository repository){
        this.serverView = serverView;
        this.repository = repository;
        clientList = new ArrayList<>();
    }


    public void start(){
        if (work){
            showOnWindow("Сервер уже запущен.");
        } else {
            work = true;
            showOnWindow("Сервер запущен.");
        }
    }



    public void stop(){
        if (!work){
            showOnWindow("Сервер уже остановлен.");
        } else {
            work = false;
            for (ClientController client: clientList){
                disconnectUser(client);
            }
            showOnWindow("Сервер остановлен.");
        }
    }

    /**
     * Метод connectUser предназначен для подключения пользователя к беседе в чате.
     * Если сервер работает, то пользователя подключают к беседе, выводя соответствующее сообщение.
     */
    public boolean connectUser(ClientController client){
        if (!work){
            return false;
        }
        clientList.add(client);
        showOnWindow(client.getName() + " подключился к беседе.");
        return true;
    }


    public void disconnectUser(ClientController client){
        clientList.remove(client);
        if (client != null){
            client.disconnectedFromServer();
            showOnWindow(client.getName() + " отключился от беседы.");
        }
    }


    public void sendMessage(String text){
        if (!work){
            return;
        }
        showOnWindow(text);
        answerAll(text);
        saveInHistory(text);
    }

    /**
     * Метод getHistory обращается к интерфейсу "Репозиторий" для вывода текста сообщений.
     * @return текст сообщений, сохраненных в файле.
     */
    public String getHistory(){
        return repository.readText().toString();
    }

    /**
     * Метод answerAll позволяет формировать ответ, доступный всем пользователям.
     * @param text сообщение сервера.
     */
    private void answerAll(String text){
        for (ClientController client: clientList){
            client.answerFromServer(text);
        }
    }


    private void showOnWindow(String text){
        serverView.showMessage(text + "\n");
    }


    private void saveInHistory(String text){
        repository.saveMessage(text);
    }
}
