package com.techstomach.hospitalconnect;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        final ImageView imageView = (ImageView) findViewById(R.id.imageDisplay);
        imageView.setBackgroundResource(R.drawable.background2);
    }
}
