package com.example.banlge_aduio_story;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.banlge_aduio_story.adapter.Audio_Adapter;
import com.example.banlge_aduio_story.model.AudioInfo;
import com.example.banlge_aduio_story.model.Audioname;
import com.example.banlge_aduio_story.model.BanglaAudioList;
import com.example.banlge_aduio_story.model.Title;
import com.example.banlge_aduio_story.utils.Getaudio;
import com.example.banlge_aduio_story.utils.RetrofitClientInstance;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private AdView mAdView;
    private AdView mAdView2;

   // Button feedback;

    RecyclerView recyclerview;
    private ArrayList<Audioname> itemList= new ArrayList<>();
    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




    getViews();
        loadad();
        loadad2();

        setTitle("Home Categories");
       // getSupportActionBar().hide();
//        feedback.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Uri uri = Uri.parse("https://onlinebanglaradio.com/widget-feedback/"); // missing 'http://' will cause crashed
//                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//                startActivity(intent);
//            }
//        });

        itemList.add(new Audioname("Sunday Suspense - Radio Mirchi",R.drawable.ic_music,166));
        itemList.add(new Audioname("Kuasha - ABC Radio",R.drawable.ic_music,477));
        itemList.add(new Audioname("Thursday Night Saga - Radio Today",R.drawable.ic_music,463));
        itemList.add(new Audioname("Midnight Horror Station",R.drawable.ic_music,458));
        itemList.add(new Audioname("Rahasya Romancho - Friends FM",R.drawable.ic_music,476));
        itemList.add(new Audioname("Gobhir Rater Gopon Kotha - Fever FM",R.drawable.ic_music,475));
        itemList.add(new Audioname("All Audio Story",R.drawable.ic_play_all_audio,0));
        itemList.add(new Audioname("Popular Series",R.drawable.ic_play_populr,0));
        itemList.add(new Audioname("Popular Program",R.drawable.ic_music,0));
        itemList.add(new Audioname("Popular Writer",R.drawable.ic_populr_writer,0));
        itemList.add(new Audioname("Feedback",R.drawable.ic_feedback_black_24dp,0));

        Audio_Adapter audio_Adapter = new Audio_Adapter(this,itemList);
        recyclerview.setAdapter(audio_Adapter);
    }

        private void getViews() {
        recyclerview = findViewById(R.id.recyclerview);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
            //feedback = findViewById(R.id.feedback);
    }




    public void loadad()
    {
//        AdView adView = new AdView(this);
//        adView.setAdSize(AdSize.BANNER);
//        adView.setAdUnitId("ca-app-pub-3940256099942544/6300978111");
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
               // Toast.makeText(MainActivity.this,"lod",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
               // Toast.makeText(MainActivity.this,"f",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdOpened() {
               // Toast.makeText(MainActivity.this,"open",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdClicked() {
               // Toast.makeText(MainActivity.this,"clicked",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdLeftApplication() {
               // Toast.makeText(MainActivity.this,"left",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdClosed() {
               // Toast.makeText(MainActivity.this,"close",Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void loadad2()
    {
        AdView adView = new AdView(this);
//        adView.setAdSize(AdSize.BANNER);
//        adView.setAdUnitId("ca-app-pub-3940256099942544/6300978111");
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mAdView2 = findViewById(R.id.adViewTop);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView2.loadAd(adRequest);

        mAdView2.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
               // Toast.makeText(MainActivity.this,"load",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
               // Toast.makeText(MainActivity.this,"failed to load add",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdOpened() {
               // Toast.makeText(MainActivity.this,"open",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdClicked() {
               // Toast.makeText(MainActivity.this,"clicked",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdLeftApplication() {
               // Toast.makeText(MainActivity.this,"left",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdClosed() {
               // Toast.makeText(MainActivity.this,"close",Toast.LENGTH_SHORT).show();
            }
        });
    }

}
