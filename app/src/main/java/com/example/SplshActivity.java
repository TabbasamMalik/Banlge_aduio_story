package com.example;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.banlge_aduio_story.MainActivity;
import com.example.banlge_aduio_story.R;

public class SplshActivity extends AppCompatActivity {

   // private AVLoadingIndicatorView avLoadingIndicatorView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splsh);
        getSupportActionBar().hide();
        int SPLASH_DISPLAY_LENGTH = 1090;
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                try {
                    Intent mainIntent = new Intent(SplshActivity.this, MainActivity.class);
                    mainIntent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(mainIntent);
                    //avLoadingIndicatorView.smoothToHide();
                    SplshActivity.this.finish();
                    //overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }


            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}
