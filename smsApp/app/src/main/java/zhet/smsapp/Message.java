package zhet.smsapp;
import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable {
    String user;
    String message;
    Date timestamp;

    public Message(String user, String message) {
        this.user = user;
        this.message = message;
        this.timestamp = new Date();
    }

    public String getUser() {
        return user;
    }

    public String getMessage() {
        return message;
    }

    public Date getTimestamp() {
        return timestamp;
    }
}
