package com.example.nottwitter;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

// For changing the font colors
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set orientation to portrait and prevent landscape lol too lazy to adjust
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        TextView welcomeText;

        TextView textView = findViewById(R.id.welcometext);

        // Changing PU text color to red and blue
        // Have to be run in order to see result
        String fullText = "See what's happening in President University right now.";
        SpannableString spannableString = new SpannableString(fullText);

        // Set "President" text to red color
        int presidentStartIndex = fullText.indexOf("President");
        int presidentEndIndex = presidentStartIndex + "President".length();
        spannableString.setSpan(new ForegroundColorSpan(Color.RED), presidentStartIndex, presidentEndIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        // Set "University" text to blue color
        int universityStartIndex = fullText.indexOf("University");
        int universityEndIndex = universityStartIndex + "University".length();
        spannableString.setSpan(new ForegroundColorSpan(Color.BLUE), universityStartIndex, universityEndIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        textView.setText(spannableString);

        //

        Button goToSecondActivityButton = findViewById(R.id.logingoogle);
        goToSecondActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, login.class);
                startActivity(intent);
            }
        });

        Button registerButton = findViewById(R.id.registerbutton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, register.class);
                startActivity(intent);
            }
        });
    }
}
