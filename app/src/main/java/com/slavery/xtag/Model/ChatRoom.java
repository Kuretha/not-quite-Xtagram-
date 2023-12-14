package com.slavery.xtag.Model;


import com.google.firebase.Timestamp;

import java.util.List;

public class ChatRoom {

    private String roomId, lastMessageSenderId, lastMessage;
    private Timestamp lastMessageTimeStamp;
    private List<String> userId;

    public ChatRoom() {
    }

    public ChatRoom(String roomId, String lastMessageSenderId, Timestamp lastMessageTimeStamp, List<String> userId) {
        this.roomId = roomId;
        this.lastMessageSenderId = lastMessageSenderId;
        this.lastMessageTimeStamp = lastMessageTimeStamp;
        this.userId = userId;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getLastMessageSenderId() {
        return lastMessageSenderId;
    }

    public void setLastMessageSenderId(String lastMessageSenderId) {
        this.lastMessageSenderId = lastMessageSenderId;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }


    public void setLastMessageTimeStamp(Timestamp lastMessageTimeStamp) {
        this.lastMessageTimeStamp = lastMessageTimeStamp;
    }

    public List<String> getUserId() {
        return userId;
    }

    public void setUserId(List<String> userId) {
        this.userId = userId;
    }

    public Timestamp getLastMessageTimestamp() {
        return lastMessageTimeStamp;
    }
}
