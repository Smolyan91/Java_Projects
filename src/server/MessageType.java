package server;

/**
 * Created by smolyan on 23.03.16.
 */

public enum MessageType {
    /**
     * Enum, который отвечает за тип сообщений пересылаемых между
     клиентом и сервером
     */
    /**
     * Fields:
     *
     * NAME_REQUEST – запрос имени
     * USER_NAME – имя пользователя
     * NAME_ACCEPTED – имя принято
     * TEXT – текстовое сообщение
     * USER_ADDED – пользователь добавлен
     * USER_REMOVED – пользователь удален
     */
    NAME_REQUEST,
    USER_NAME,
    NAME_ACCEPTED,
    TEXT,
    USER_ADDED,
    USER_REMOVED
}
