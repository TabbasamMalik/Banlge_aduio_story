package com.example.banlge_aduio_story;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.banlge_aduio_story.adapter.Popular_Program_Adapter;

import com.example.banlge_aduio_story.model.AduioWriter;
import com.example.banlge_aduio_story.utils.Getaudio;
import com.example.banlge_aduio_story.utils.RecyclerViewClickListener;
import com.example.banlge_aduio_story.utils.RetrofitClientInstance;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PopularProgramActivity extends AppCompatActivity implements RecyclerViewClickListener {

    List<AduioWriter> aduioWriterList;
    RecyclerView recyclerview;
    String category;
    ProgressDialog progress;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_witer);
       // getSupportActionBar().hide();

loadad();
        category = getIntent().getStringExtra("Category");

        setTitle(category);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        progress = new ProgressDialog(this);
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        progress.show();

        getViews();
        try {

            if(category.equals("Popular Writer"))
            {
                Call<List<AduioWriter>> call = null;
                Getaudio service = RetrofitClientInstance.getRetrofitInstance().create(Getaudio.class);

                call = service.getAudioWriter();
                call.enqueue(new Callback<List<AduioWriter>>() {
                    @Override
                    public void onResponse(Call<List<AduioWriter>> call, Response<List<AduioWriter>> response) {

                        aduioWriterList = response.body();
                        getAduioWriterList(aduioWriterList,category);
                        progress.dismiss();

                    }

                    @Override
                    public void onFailure(Call<List<AduioWriter>> call, Throwable t) {
                        String str = "";
                        progress.dismiss();
                        Toast.makeText(PopularProgramActivity.this, "Internet Connection is not stable", Toast.LENGTH_SHORT).show();
                    }
                });
            }
            else if(category.equals("Popular Series")) {
                Call<List<AduioWriter>> call = null;
                Getaudio service = RetrofitClientInstance.getRetrofitInstance().create(Getaudio.class);

                call = service.getPopulrSeries();
                call.enqueue(new Callback<List<AduioWriter>>() {
                    @Override
                    public void onResponse(Call<List<AduioWriter>> call, Response<List<AduioWriter>> response) {

                        aduioWriterList = response.body();
                        getAduioWriterList(aduioWriterList,category);
                        progress.dismiss();

                    }

                    @Override
                    public void onFailure(Call<List<AduioWriter>> call, Throwable t) {
                        String str = "";
                        progress.dismiss();
                        Toast.makeText(PopularProgramActivity.this, "Internet Connection is not stable", Toast.LENGTH_SHORT).show();
                    }
                });
            }
            else if(category.equals("Popular Program")) {
                Call<List<AduioWriter>> call = null;
                Getaudio service = RetrofitClientInstance.getRetrofitInstance().create(Getaudio.class);

                call = service.getPopularProgram();
                call.enqueue(new Callback<List<AduioWriter>>() {
                    @Override
                    public void onResponse(Call<List<AduioWriter>> call, Response<List<AduioWriter>> response) {

                        aduioWriterList = response.body();
                        getAduioWriterList(aduioWriterList,category);
                        progress.dismiss();

                    }

                    @Override
                    public void onFailure(Call<List<AduioWriter>> call, Throwable t) {
                        String str = "";
                        progress.dismiss();
                        Toast.makeText(PopularProgramActivity.this, "Internet Connection is not stable", Toast.LENGTH_SHORT).show();
                    }
                });
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
            progress.dismiss();
            Toast.makeText(PopularProgramActivity.this, "Internet Connection is not stable", Toast.LENGTH_SHORT).show();
        }

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
        mAdView = findViewById(R.id.adViewTop);
//        mAdView.setAdSize(AdSize.BANNER);
//        mAdView.setAdUnitId("ca-app-pub-3940256099942544/6300978111");
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
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void getViews() {
        recyclerview = findViewById(R.id.recyclerview);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
    }

    private void getAduioWriterList(List<AduioWriter> aduioWriterList,String type) {
        Popular_Program_Adapter audio_Adapter = new Popular_Program_Adapter(this,aduioWriterList,type,
                this);
        recyclerview.setAdapter(audio_Adapter);
    }

    @Override
    public void recyclerViewListClicked(View v, int position) {
        int count = aduioWriterList.get(position).getCount();
        if(count==0)
        {

        }
        else {
            Intent intent = new Intent(this, Radio_MirchiActivity.class);
            String url = aduioWriterList.get(position).getLink();
            String name = aduioWriterList.get(position).getName();
            int id = aduioWriterList.get(position).getId();

            //plyMp3(url);
            intent.putExtra("Category", category);
            intent.putExtra("id", id + "");
            startActivity(intent);
        }
    }

}
