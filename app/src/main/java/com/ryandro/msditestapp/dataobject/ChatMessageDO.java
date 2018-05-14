package com.ryandro.msditestapp.dataobject;

/**
 * Created by WIN 1O on 15-03-2018.
 */

public class ChatMessageDO {
    private String messageTime = "";
    private String messageBy = "";
    private String message = "";

    public String getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(String messageTime) {
        this.messageTime = messageTime;
    }

    public String getMessageBy() {
        return messageBy;
    }

    public void setMessageBy(String messageBy) {
        this.messageBy = messageBy;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ChatMessageDO(String messageTime, String messageBy, String message) {
        this.messageTime = messageTime;
        this.messageBy = messageBy;
        this.message = message;
    }
}
