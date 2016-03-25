package Massager.server;

import java.io.Serializable;

/**
 * Created by smolyan on 23.03.16.
 */

public class Message implements Serializable {
    /**
     * Класс, отвечающий за пересылаемые сообщения
     */

    //Fields

    private final MessageType type;
    private final String data;
    //Constructors

    public Message(MessageType type)
    {
        this.type = type;
        data = null;
    }

    public Message(MessageType type, String data)
    {
        this.type = type;
        this.data = data;
    }
    //Methods

    public MessageType getType()
    {
        return type;
    }

    public String getData()
    {
        return data;
    }
}
