package server;

import java.io.Closeable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * Created by smolyan on 23.03.16.
 */
public class Connection implements Closeable {
    /**
     *  Класс соединения между клиентом и сервером
     *  Класс Connection будет выполнять роль обвертки над классом
     *  java.net.Socket, которая должна будет уметь сериализовать и десериализовать объекты
     *  типа Message в сокет. Методы этого класса должны быть готовы к вызову из разных
     *  потоков.
     */
    //Fields

    private final Socket socket ;
    private final ObjectOutputStream out;
    private final ObjectInputStream in  ;

    public Connection(Socket socket)throws IOException
    {
        this.socket = socket;
        this.out = new ObjectOutputStream(socket.getOutputStream());
        this.in = new ObjectInputStream(socket.getInputStream());
    }
    public void send(Message message) throws IOException{
        synchronized (out){
            out.writeObject(message);
            out.flush();
        }
    }

    /**
     *
     * @return message Message
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public Message receive() throws IOException, ClassNotFoundException{
        Message message;
        synchronized (in){
            message = (Message)in.readObject();
            return message;
        }

    }

    public SocketAddress getRemoteSocketAddress(){
        return socket.getRemoteSocketAddress();
    }
    @Override
    public void close() throws IOException
    {
        out.close();
        in.close();
        socket.close();
    }
}
