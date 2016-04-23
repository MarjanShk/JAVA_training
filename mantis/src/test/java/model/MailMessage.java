package model;

/**
 * Created by Admin on 23.04.2016.
 */
public class MailMessage {
    public String to;
    public String text;

    public MailMessage(String to,String text){

        this.to = to;
        this.text = text;
    }

    @Override
    public String toString() {
        return "MailMessage{" +
                "to='" + to + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
