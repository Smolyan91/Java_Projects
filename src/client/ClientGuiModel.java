package client;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by smolyan on 23.03.16.
 */

public class ClientGuiModel {
    private final Set<String> allUserNames = new HashSet<>();
    private String newMessage;

    public Set<String> getAllUserNames()
    {
        return Collections.unmodifiableSet(allUserNames);
    }

    public String getNewMessage()
    {
        return newMessage;
    }

    public void setNewMessage(String newMessage)
    {
        this.newMessage = newMessage;
    }

    /**
     * Должен добавлять имя участника во множество, хранящее всех участников
     * @param newUserName
     */
    public void addUser(String newUserName){
        allUserNames.add(newUserName);
    }

    /**
     * Будет удалять имя участника из множества
     * @param userName
     */
    public void deleteUser(String userName){
        allUserNames.remove(userName);
    }
}
