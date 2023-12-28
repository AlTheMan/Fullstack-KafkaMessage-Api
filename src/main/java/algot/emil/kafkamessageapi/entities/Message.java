package algot.emil.kafkamessageapi.entities;

import jakarta.persistence.Entity;

import java.time.LocalDate;

@Entity
public class Message extends BaseEntity{
    private Long senderId;
    private Long receiverId;
    private String message;
    private LocalDate time;

    public Message(Long senderId, Long receiverId, String message, LocalDate time) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.message = message;
        this.time = time;
    }

    public Message() {
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public Long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDate getTime() {
        return time;
    }

    public void setTime(LocalDate time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Message{" +
                "senderId=" + senderId +
                ", receiverId=" + receiverId +
                ", message='" + message + '\'' +
                ", time=" + time +
                '}';
    }
}
