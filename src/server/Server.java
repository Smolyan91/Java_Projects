package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by smolyan on 23.03.16.
 */
public class Server {
    /**
     *  Основной класс сервера
     Сервер должен поддерживать
     множество соединений с разными клиентами одновременно.
     */
    private static class Handler extends Thread{
        /**
         * Здесь будет происходить обмен сообщениями с клиентом.
         */
        private Socket socket;

        public Handler(Socket socket)
        {
            this.socket = socket;
        }

        @Override
        public void run()
        {
            String name = null;
            try(Connection connection = new Connection(socket))
            {
                ConsoleHelper.writeMessage("Successful connect :" + connection.getRemoteSocketAddress());
                name = serverHandshake(connection);
                sendBroadcastMessage(new Message(MessageType.USER_ADDED,name));
                sendListOfUsers(connection, name);
                serverMainLoop(connection,name);
            }catch (Exception e){
                ConsoleHelper.writeMessage("Произошла ошибка при обмене данными с удаленным адресом");
            }
            if (name != null){
                connectionMap.remove(name);
                sendBroadcastMessage(new Message(MessageType.USER_REMOVED,name));
            }
            ConsoleHelper.writeMessage("Соединение с удаленным адресом закрыто");
        }
        /**
         * @param connection
         * @return connectName String
         * @throws IOException, ClassNotFoundException
         */
        private String serverHandshake(Connection connection) throws IOException,ClassNotFoundException{
            while (true){
                connection.send(new Message(MessageType.NAME_REQUEST));
                Message message = connection.receive();
                if (message.getType() == MessageType.USER_NAME){
                    if (message.getData() != null && !message.getData().isEmpty()){
                        if (!connectionMap.containsKey(message.getData())){
                            connectionMap.put(message.getData(), connection);
                            connection.send(new Message(MessageType.NAME_ACCEPTED));
                            return message.getData();
                        }
                    }
                }

            }
        }

        /**
         * Отправка клиенту (новому участнику) информации об
         остальных клиентах (участниках) чата. Для этого:
         **Добавить приватный метод void sendListOfUsers(Connection connection, String userName) throws
         IOException, где connection – соединение с участником, которому будем слать
         информацию, а userName – его имя. Метод должен:
         **Пройтись по connectionMap
         У каждого элемента из п.9.2 получить имя клиента, сформировать команду с типом
         USER_ADDED и полученным именем
         Отправить сформированную команду через connection
         Команду с типом USER_ADDED и именем равным userName отправлять не нужно,
         пользователь и так имеет информацию о себе
         * @param connection
         * @param userName
         * @throws IOException
         */
        private void sendListOfUsers(Connection connection, String userName) throws IOException{
            for (Map.Entry<String, Connection> pair : connectionMap.entrySet()){
                if (!userName.equals(pair.getKey())){
                    connection.send(new Message(MessageType.USER_ADDED, pair.getKey()));
                }
            }
        }

        /**
         * Он должен:
         ***** Принимать сообщение клиента
         Если принятое сообщение – это текст (тип TEXT), то формировать новое
         текстовое сообщение путем конкатенации: имени клиента, двоеточия, пробела и
         текста сообщения. Например, если мы получили сообщение с текстом "привет чат" от
         пользователя "Боб", то нужно сформировать сообщение "Боб: привет чат".
         Отправлять сформированное сообщение всем клиентам с помощью метода
         sendBroadcastMessage.
         Если принятое сообщение не является текстом, вывести сообщение об ошибке
         Организовать бесконечный цикл, внутрь которого перенести функционал
         пунктов 10.1-10.4.
         * @param connection
         * @param userName
         * @throws IOException
         * @throws ClassNotFoundException
         */
        private void serverMainLoop(Connection connection, String userName) throws IOException, ClassNotFoundException{
            while (true){
                Message message = connection.receive();
                if (message.getType() == MessageType.TEXT){
                    sendBroadcastMessage(new Message(MessageType.TEXT, userName + ": " + message.getData()));
                }else {
                    ConsoleHelper.writeMessage("This message is not TXT- Type !");
                }
            }
        }


    }

    /**
     * Name : Map<String, Connection> connectionMap
     * Key - имя клиента
     * Value - значением - соединение с ним.
     */
    private static Map<String, Connection> connectionMap = new ConcurrentHashMap<>();

    public static void main(String[] args)
    {
        ConsoleHelper.writeMessage("Введите порт сервера:");
        int socketPort = ConsoleHelper.readInt();
        try(ServerSocket serverSocket = new ServerSocket(socketPort))
        {
            ConsoleHelper.writeMessage("Сервер запущен");
            while (true){
                Socket socket = serverSocket.accept();
                Handler handler = new Handler(socket);
                handler.start();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    /**
     *Статический метод void sendBroadcastMessage(Message message), который должен
     отправлять сообщение  message по всем соединениям из connectionMap. Если при
     отправке сообщение произойдет исключение IOException, нужно отловить его и
     сообщить пользователю, что не смогли отправить сообщение.
     */
    public static void sendBroadcastMessage(Message message){
        try
        {
            for (Map.Entry<String,Connection> pair : connectionMap.entrySet()){
                pair.getValue().send(message);
            }
        }catch(IOException e){
            ConsoleHelper.writeMessage("Error send message =( ");

        }
    }
}
