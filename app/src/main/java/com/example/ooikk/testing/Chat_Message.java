package com.example.ooikk.testing;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import java.util.Date;

public class Chat_Message extends AppCompatActivity {
    private String messageText;
    private String messageUser;
    private long messageTime;

    //constructor
    public Chat_Message(String messageText, String messageUser) {
        this.messageText = messageText;
        this.messageUser = messageUser;

        messageTime = new Date().getTime();
    }

    public Chat_Message(){
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public String getMessageUser() {
        return messageUser;
    }

    public void setMessageUser(String messageUser) {
        this.messageUser = messageUser;
    }

    public long getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(long messageTime) {
        this.messageTime = messageTime;
    }


    //when the back button is pressed
    public boolean onSupportNavigateUp(){
        backToMainActivity();
        return true;
    }

    private void backToMainActivity(){
        Intent intent = new Intent(Chat_Message.this, MainActivity.class);
        startActivity(intent);
        finish();
    }





}
