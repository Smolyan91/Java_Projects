package Massager.client;

import Massager.server.ConsoleHelper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by smolyan on 23.03.16.
 */
public class BotClient extends Client {
    public class BotSocketThread extends SocketThread{

        @Override
        protected void clientMainLoop() throws IOException, ClassNotFoundException
        {
            sendTextMessage("Hi chat. Я бот. Понимаю команды: дата, день, месяц, год, время, час, минуты, секунды.");
            super.clientMainLoop();
        }

        @Override
        protected void processIncomingMessage(String message)
        {
            ConsoleHelper.writeMessage(message);
            String nameFromMessage = "";
            String textFromMessage;
            if (message.contains(": ")){
                nameFromMessage = message.substring(0,message.indexOf(": "));
                textFromMessage = message.substring(message.indexOf(": ")+ 2);
            }else {
                textFromMessage = message;
            }
            SimpleDateFormat dateFormat = null;


            if (textFromMessage.equalsIgnoreCase("дата")){
                dateFormat = new SimpleDateFormat("d.MM.YYYY");

            }else if (textFromMessage.equalsIgnoreCase("день")){
                dateFormat = new SimpleDateFormat("d");

            }else  if (textFromMessage.equalsIgnoreCase("месяц")){
                dateFormat = new SimpleDateFormat("MMMM");

            }else if (textFromMessage.equalsIgnoreCase("год")){
                dateFormat = new SimpleDateFormat("YYYY");

            }else if (textFromMessage.equalsIgnoreCase("время")){
                dateFormat = new SimpleDateFormat("H:mm:ss");
            }else if (textFromMessage.equalsIgnoreCase("час")){
                dateFormat = new SimpleDateFormat("H");
            }else if (textFromMessage.equalsIgnoreCase("минуты")){
                dateFormat = new SimpleDateFormat("m");
            }else if (textFromMessage.equalsIgnoreCase("секунды")){
                dateFormat = new SimpleDateFormat("s");
            }

            if (dateFormat != null){
                sendTextMessage("Информация для " + nameFromMessage + ": " + dateFormat.format(Calendar.getInstance().getTime()));
            }
        }
    }

    /**
     * Должен создавать и возвращать объект класса BotSocketThread
     * @return  new BotSocketThread();
     */
    @Override
    protected SocketThread getSocketThread()
    {
        return new BotSocketThread();
    }

    /**
     * Он должен всегда возвращать false. Мы не хотим, чтобы бот отправлял текст введенный в консоль
     * @return false
     */
    @Override
    protected boolean shouldSentTextFromConsole()
    {
        return false;
    }

    /**
     * Метод должен генерировать новое имя бота
     * @return
     */
    @Override
    protected String getUserName()
    {
        int numForNameRandom =(int) (Math.random() * 99);
        return "date_bot_" + numForNameRandom;
    }

    public static void main(String[] args)
    {
        BotClient botClient = new BotClient();
        botClient.run();
    }
}
