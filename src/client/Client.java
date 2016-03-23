package client;

import server.Connection;
import server.ConsoleHelper;
import server.Message;
import server.MessageType;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by smolyan on 23.03.16.
 */
public class Client {
    protected Connection connection;

    public static void main(String[] args)
    {
        Client client = new Client();
        Client client1 = new Client();
        client.run();
        client1.run();
    }
    /**
     * Метод void run(). Он должен создавать вспомогательный поток
     SocketThread, ожидать пока тот установит соединение с сервером, а после этого
     в цикле считывать сообщения с консоли и отправлять их серверу. Условием выхода
     из цикла будет отключение клиента или ввод пользователем команды 'exit'.
     Для информирования главного потока, что соединение установлено во
     вспомогательным потоке, используй методы wait и notify объекта класса Client.
     Реализация метода run:
     Создавать новый сокетный поток с помощью метода getSocketThread.
     Помечать созданный поток как daemon, это нужно для того, чтобы при выходе
     из программы вспомогательный поток прервался автоматически.
     Запустить вспомогательный поток.
     Заставить текущий поток ожидать, пока он не получит нотификацию из другого
     потока. Используется  wait и синхронизация на уровне объекта. Если во
     время ожидания возникнет исключение, сообщаем об этом пользователю и выходим
     из программы.
     После того, как поток дождался нотификации, проверяем значение
     clientConnected. Если оно true – выведи "Соединение установлено. Для выхода
     наберите команду 'exit'.". Если оно false – выведи "Произошла ошибка во время
     работы клиента.".
     Считываем сообщения с консоли пока клиент подключен. Если будет введена
     команда 'exit', то выйдем из цикла.
     После каждого считывания, если метод shouldSentTextFromConsole()
     возвращает true, отправляем считанный текст с помощью метода  sendTextMessage().
     */
    public void run(){
        SocketThread socketThread = getSocketThread();
        socketThread.setDaemon(true);
        socketThread.start();
        try
        {
            synchronized (this){
                this.wait();
            }
        }catch (InterruptedException e){
            ConsoleHelper.writeMessage("Error in programs. Class \"Client.run\"");
            return;
        }
        if (clientConnected){
            ConsoleHelper.writeMessage("Соединение установлено. Для выхода наберите команду 'exit'.");
            while (clientConnected){
                String line ;
                if (!(line = ConsoleHelper.readString()).equals("exit")){
                    if (shouldSentTextFromConsole()){
                        sendTextMessage(line);
                    }
                }else return;
            }

        }else {
            ConsoleHelper.writeMessage("Произошла ошибка во время работы клиента.");
        }
    }
    /**
     * @param clientConnected
     * В дальнейшем оно будет устанавливаться в true, если клиент
    подсоединен к серверу или в false в противном случае.
     */
    private volatile boolean clientConnected = false;

    /**
     * Должен запросить ввод адреса сервера у
     пользователя и вернуть введенное значение
     * @return address String
     */
    protected String getServerAddress(){
        ConsoleHelper.writeMessage("Input your address: ");
        return ConsoleHelper.readString();
    }

    /**
     * Должен запрашивать ввод порта сервера и возвращать его
     * @return port int
     */
    protected int getServerPort(){
        ConsoleHelper.writeMessage("Input server port: ");
        return ConsoleHelper.readInt();
    }

    /**
     * Должен запрашивать и возвращать имя пользователя
     * @return name String
     */
    protected String getUserName(){
        ConsoleHelper.writeMessage("Input user name: ");
        return ConsoleHelper.readString();
    }

    /**
     * В данной реализации клиента всегда должен возвращать true (мы всегда отправляем текст введенный в консоль).
     Этот метод может быть переопределен, если мы будем писать какой-нибудь другой клиент, унаследованный от нашего,
     который не должен отправлять введенный в консоль текст.
     * @return true boolean
     */
    protected boolean shouldSentTextFromConsole(){
        return true;
    }

    /**
     * Должен создавать и возвращать новый объект класса SocketThread
     * @return new SocketThread();
     */
    protected SocketThread getSocketThread(){
        return new SocketThread();
    }

    /**
     * Создает новое текстовое сообщение, используя переданный текст и отправляет его серверу
     через соединение connection. Если во время отправки произошло исключение IOException,
     то необходимо вывести информацию об этом пользователю и присвоить false полю clientConnected
     * @param text
     */
    protected void sendTextMessage(String text){
        try
        {
            connection.send(new Message(MessageType.TEXT,text));
        }catch (IOException e){
            ConsoleHelper.writeMessage("Error sending message.");
            clientConnected = false;
        }
    }
    public class SocketThread extends Thread{
        /**
         * Он будет отвечать за поток, устанавливающий сокетное соединение и
         читающий сообщения сервера.
         */

        /**
         * Должен выводить текст message в консоль
         * @param message
         */
        protected void processIncomingMessage(String message){
            ConsoleHelper.writeMessage(message);
        }

        /**
         * Должен выводить в консоль информацию о том, что участник с именем userName присоединился к чату
         * @param userName
         */
        protected void informAboutAddingNewUser(String userName) {
            ConsoleHelper.writeMessage("Client: "+ userName + "add in chat/ Welcome, nigger!");
        }

        /** Должен выводить в консоль, что участник с именем userName покинул чат
         * @param userName
         */
        protected void informAboutDeletingNewUser(String userName) {
            ConsoleHelper.writeMessage("Client : " + userName + "came out of chat =(");
        }

        /**
         *  Этот метод должен:
         Устанавливать значение поля clientConnected класса Client в соответствии с
         переданным параметром.
         Оповещать (пробуждать ожидающий) основной поток класса Client. Подсказка:
         используй синхронизацию на уровне объекта внешнего класса и метод notify. Для
         класса SocketThread внешним классом является класс Client.
         * @param clientConnected
         */
        protected void notifyConnectionStatusChanged(boolean clientConnected){
            Client.this.clientConnected = clientConnected;
            synchronized (Client.this){
                Client.this.notify();
            }
        }

        /**
         * Метод будет представлять клиента серверу
         *
         * @throws IOException
         * @throws ClassNotFoundException
         */
        protected void clientHandshake() throws IOException,ClassNotFoundException{
            while (true){
                Message messageFromServer = connection.receive();
                switch (messageFromServer.getType()){
                    case NAME_REQUEST:
                    {
                        String userName = getUserName();
                        connection.send(new Message(MessageType.USER_NAME, userName));
                        break;
                    }
                    case NAME_ACCEPTED:
                    {
                        notifyConnectionStatusChanged(true);
                        return;
                    }
                    default: throw new IOException("Unexpected MessageType");
                }
            }
        }

        /**
         * Этот метод будет реализовывать главный цикл обработки сообщений сервера
         * @throws IOException
         * @throws ClassNotFoundException
         */
        protected void clientMainLoop() throws IOException,ClassNotFoundException{
            while (true){
                Message messageFromServer = connection.receive();
                switch (messageFromServer.getType()){
                    case TEXT:          processIncomingMessage(messageFromServer.getData());    break;

                    case USER_ADDED:    informAboutAddingNewUser(messageFromServer.getData());  break;

                    case USER_REMOVED:  informAboutDeletingNewUser(messageFromServer.getData());break;

                    default:            throw new IOException("Unexpected MessageType");
                }
            }
        }

        public void run(){
            String addressHost = getServerAddress();
            int port = getServerPort();
            try
            {
                Socket socket = new Socket(addressHost, port);
                Client.this.connection = new Connection(socket);
                clientHandshake();
                clientMainLoop();
            }catch (IOException e){
                notifyConnectionStatusChanged(false);
            }catch (ClassNotFoundException e){
                notifyConnectionStatusChanged(false);
            }
        }
    }
}
