package felipehernandez.com.appsocketio;

import com.stfalcon.chatkit.commons.models.IMessage;

import java.util.Date;

public class Message implements IMessage {

    private String id;
    private String text;
    private Date createdAt;
    private Author author;


    public Message(String id, String text, Date createdAt, Author author) {
        this.id = id;
        this.text = text;
        this.createdAt = createdAt;
        this.author = author;
    }


    public Message(String id, String text, Date createdAt) {
        this.id = id;
        this.text = text;
        this.createdAt = createdAt;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public Author getUser() {
        return author;
    }

    @Override
    public Date getCreatedAt() {
        return createdAt;
    }
}