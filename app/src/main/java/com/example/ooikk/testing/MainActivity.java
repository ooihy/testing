package com.example.ooikk.testing;

//This is the main page of the app
//Users can access the chat or logout

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Check if user is logged in
        // If user is not logged in, direct user to login page
        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            Intent intent = new Intent(MainActivity.this, loginActivity.class);
            startActivity(intent);
            finish(); //prevent activities from stacking up: once prev activity no needed, finish()
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) { //specify the options menu for an activity
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    //after inflating the menu with the items you might want
    // to add some action when they are selected:
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                userLogout();
                return true;
            case R.id.Chat: //added chat option to be available on menu
                userChat();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // Signout User and return user to LoginActivity
    private void userLogout() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(MainActivity.this, loginActivity.class);
        startActivity(intent);
        finish();
    }

    //guide user to chat room -> Not sure if this works
    private void userChat() {
        FirebaseAuth.getInstance().signOut();
        Intent secondIntent = new Intent(MainActivity.this, ChatActivity.class);
        startActivity(secondIntent);
        finish();
    }

}
