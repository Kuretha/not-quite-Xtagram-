package com.example.nottwitter;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.fragment.app.FragmentManager;
import androidx.appcompat.app.AppCompatActivity;

public class home extends AppCompatActivity {
    ImageButton buttonHome, buttonSearch, settingButton, buttonMessage, buttonProfile;
    ImageView profile; // For the profile picture, default image is in XML

    Dialog logoutBox;
    Button logOut, logOutCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        buttonProfile = findViewById(R.id.profile);
        buttonMessage = findViewById(R.id.buttonMessage);
        buttonHome = findViewById(R.id.buttonHome);
        buttonSearch = findViewById(R.id.btnSearch);
        settingButton = findViewById(R.id.settingButton);

        // Log out function
        logoutBox = new Dialog(home.this);
        logoutBox.setContentView(R.layout.logout_box);
        logoutBox.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        logoutBox.getWindow().setBackgroundDrawable(getDrawable(R.drawable.logout_box));
        logoutBox.setCancelable(false);

        logOut = logoutBox.findViewById(R.id.logout);
        logOutCancel = logoutBox.findViewById(R.id.cancelLogOut);

        // Adjust the log out method

        settingButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                logoutBox.show();
            }
        });

        logOut.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                logoutBox.dismiss();
            }
        });

        logOutCancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                logoutBox.dismiss();
            }
        });

        // Show Search fragment
        buttonSearch.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View view) {
                // Change the button image to thicc when pressed
                buttonHome.setImageResource(R.drawable.home);
                buttonMessage.setImageResource(R.drawable.mail);
                buttonSearch.setImageResource(R.drawable.searchpressed);
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView, Search.class, null)
                        .setReorderingAllowed(true)
                        .commit();
            }
        });

        // Show Tweet fragment (timeline for showing tweet, please refer to Tweet.java)
        buttonHome.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View view) {
                // Change the button image to thicc when pressed
                buttonHome.setImageResource(R.drawable.homepressed);
                buttonMessage.setImageResource(R.drawable.mail);
                buttonSearch.setImageResource(R.drawable.search);
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView, Tweet.class, null)
                        .setReorderingAllowed(true)
                        .commit();
            }
        });

        // Show messages fragment
        buttonMessage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View view) {
                // Change the button image to thicc when pressed
                buttonHome.setImageResource(R.drawable.home);
                buttonMessage.setImageResource(R.drawable.mailpressed);
                buttonSearch.setImageResource(R.drawable.search);
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView, Messaging.class, null)
                        .setReorderingAllowed(true)
                        .commit();
            }
        });


        buttonProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(home.this, Profile.class);
                startActivity(intent);
            }
        });
    }
}
