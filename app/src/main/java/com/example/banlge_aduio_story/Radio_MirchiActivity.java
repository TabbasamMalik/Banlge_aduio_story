package com.example.banlge_aduio_story;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;


import com.example.banlge_aduio_story.adapter.Audio_Adapter;
import com.example.banlge_aduio_story.adapter.Audio__Radio_Mirchi_Adapter;
import com.example.banlge_aduio_story.model.BanglaAudioList;
import com.example.banlge_aduio_story.utils.Getaudio;
import com.example.banlge_aduio_story.utils.RecyclerViewClickListener;
import com.example.banlge_aduio_story.utils.RetrofitClientInstance;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Radio_MirchiActivity extends AppCompatActivity implements RecyclerViewClickListener, View.OnClickListener {

    RecyclerView recyclerview;
    List<BanglaAudioList> list;
    MediaPlayer mediaPlayer;
    ProgressDialog progress;
    Button btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btn10,btn11;
    RelativeLayout loadMore;
    String ctegory;
    String id;
    private com.google.android.gms.ads.AdView mAdView;
    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio__mirchi);
        ctegory =getIntent().getStringExtra("Category");




        mInterstitialAd = new InterstitialAd(this);

        // set the ad unit ID
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_full_screen));

        AdRequest adRequest = new AdRequest.Builder().build();

        // Load ads into Interstitial Ads
        mInterstitialAd.loadAd(adRequest);

        mInterstitialAd.setAdListener(new AdListener() {
            public void onAdLoaded() {
                showInterstitial();
            }
        });


        loadad();
        setTitle(ctegory);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

         id = getIntent().getStringExtra("id");
        progress = new ProgressDialog(this);
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        progress.show();

        getViews();
//        getSupportActionBar().hide();

        try {
            if(ctegory.equals("All Audio Story"))
            {
                loadMore.setVisibility(View.VISIBLE);
                getList(id,1+"");
            }
            else if(ctegory.equals("Popular Series"))
            {
                Getaudio service = RetrofitClientInstance.getRetrofitInstance().create(Getaudio.class);
                Call<List<BanglaAudioList>> call=null;
                call = service.getAudioList("wp-json/wp/v2/audio-story?audio_series="+id+"+&per_page=100&page=1");

                call.enqueue(new Callback<List<BanglaAudioList>>() {
                    @Override
                    public void onResponse(Call<List<BanglaAudioList>> call, Response<List<BanglaAudioList>> response) {
                        progress.dismiss();
                        list= response.body();
                        getNameandaudioList(list);

                    }

                    @Override
                    public void onFailure(Call<List<BanglaAudioList>> call, Throwable t) {
                        String str = "";
                        progress.dismiss();
                        Toast.makeText(Radio_MirchiActivity.this,"Internet Connection is not stable",Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
            }
            else if(ctegory.equals("Popular Program"))
            {
                ///https://onlinebanglaradio.com/wp-json/wp/v2/audio-story?audio_cat=451&per_page=100&page=1

                Getaudio service = RetrofitClientInstance.getRetrofitInstance().create(Getaudio.class);
                Call<List<BanglaAudioList>> call=null;
                call = service.getAudioList("wp-json/wp/v2/audio-story?audio_cat="+id+"+&per_page=100&page=1");

                call.enqueue(new Callback<List<BanglaAudioList>>() {
                    @Override
                    public void onResponse(Call<List<BanglaAudioList>> call, Response<List<BanglaAudioList>> response) {
                        progress.dismiss();
                        list= response.body();
                        getNameandaudioList(list);

                    }

                    @Override
                    public void onFailure(Call<List<BanglaAudioList>> call, Throwable t) {
                        String str = "";
                        progress.dismiss();
                        Toast.makeText(Radio_MirchiActivity.this,"Internet Connection is not stable",Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
            }
            else if(ctegory.equals("Popular Writer"))
            {
                Getaudio service = RetrofitClientInstance.getRetrofitInstance().create(Getaudio.class);
                Call<List<BanglaAudioList>> call=null;
                //https://onlinebanglaradio.com/wp-json/wp/v2/audio-story?audio_series=481&per_page=100&page=1
                call = service.getAudioList("wp-json/wp/v2/audio-story?audio_writer="+id+"+&per_page=100&page=1");

                call.enqueue(new Callback<List<BanglaAudioList>>() {
                    @Override
                    public void onResponse(Call<List<BanglaAudioList>> call, Response<List<BanglaAudioList>> response) {
                        progress.dismiss();
                        list= response.body();
                        getNameandaudioList(list);

                    }

                    @Override
                    public void onFailure(Call<List<BanglaAudioList>> call, Throwable t) {
                        String str = "";
                        progress.dismiss();
                        Toast.makeText(Radio_MirchiActivity.this,"Internet Connection is not stable",Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
            }
            else {
                if(ctegory.equals("Sunday Suspense - Radio Mirchi")||(ctegory.equals("Kuasha - ABC Radio")))
                {
                    loadMore.setVisibility(View.VISIBLE);
                    btn5.setVisibility(View.GONE);
                    btn6.setVisibility(View.GONE);
                    btn7.setVisibility(View.GONE);
                    btn8.setVisibility(View.GONE);
                    btn9.setVisibility(View.GONE);
                    btn10.setVisibility(View.GONE);
                    btn11.setVisibility(View.GONE);
                    getList(id,1+"");
                }
                else if(ctegory.equals("Midnight Horror Station"))
                {
                    loadMore.setVisibility(View.VISIBLE);
                    btn1.setVisibility(View.VISIBLE);
                    btn2.setVisibility(View.VISIBLE);
                    btn3.setVisibility(View.GONE);
                    btn4.setVisibility(View.GONE);
                    btn5.setVisibility(View.GONE);
                    btn6.setVisibility(View.GONE);
                    btn7.setVisibility(View.GONE);
                    btn8.setVisibility(View.GONE);
                    btn9.setVisibility(View.GONE);
                    btn10.setVisibility(View.GONE);
                    btn11.setVisibility(View.GONE);

                    getList(id,1+"");
                }
                else {
                    loadMore.setVisibility(View.GONE);
                    getList(id,1+"");
                }

            }
        }
        catch (Exception e)
        {
            Toast.makeText(this,"Internet Connection is not stable",Toast.LENGTH_SHORT).show();
            e.printStackTrace();
            progress.dismiss();
            finish();
        }
    }



    private void showInterstitial() {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }
    }

    public void getList(String id , String page)
    {
        Getaudio service = RetrofitClientInstance.getRetrofitInstance().create(Getaudio.class);
        Call<List<BanglaAudioList>> call = null;
        if(ctegory.equals("All Audio Story"))
        {
            call = service.getAudioList("wp-json/wp/v2/audio-story?per_page=100&page="+ page +"");
        }
        else {
            call = service.getAudioList("wp-json/wp/v2/audio-story?audio_cat=" + id + "&per_page=100&page=" + page + "");
        }
        call.enqueue(new Callback<List<BanglaAudioList>>() {
            @Override
            public void onResponse(Call<List<BanglaAudioList>> call, Response<List<BanglaAudioList>> response) {
                progress.dismiss();
                list = response.body();
                getNameandaudioList(list);

            }

            @Override
            public void onFailure(Call<List<BanglaAudioList>> call, Throwable t) {
                String str = "";
                progress.dismiss();
                Toast.makeText(Radio_MirchiActivity.this, "Internet Connection is not stable", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
    public void getStories(String id , String page){

            Getaudio service = RetrofitClientInstance.getRetrofitInstance().create(Getaudio.class);
            Call<List<BanglaAudioList>> call = null;

            call = service.getAudioList("wp-json/wp/v2/audio-story?per_page=100&page="+ page +"");

            call.enqueue(new Callback<List<BanglaAudioList>>() {
                @Override
                public void onResponse(Call<List<BanglaAudioList>> call, Response<List<BanglaAudioList>> response) {
                    progress.dismiss();
                    list = response.body();
                    getNameandaudioList(list);

                }

                @Override
                public void onFailure(Call<List<BanglaAudioList>> call, Throwable t) {
                    String str = "";
                    progress.dismiss();
                    Toast.makeText(Radio_MirchiActivity.this, "Internet Connection is not stable", Toast.LENGTH_SHORT).show();
                    finish();
                }
            });
    }
    private void getViews() {
        recyclerview = findViewById(R.id.recyclerview);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        btn1 = findViewById(R.id.one);
        btn2= findViewById(R.id.two);
        btn3=findViewById(R.id.three);
        btn4 = findViewById(R.id.four);
        btn5 = findViewById(R.id.five);
        btn6 =  findViewById(R.id.six);
        btn7 = findViewById(R.id.seven);
        btn8 = findViewById(R.id.eight);
        btn9 =  findViewById(R.id.nine);
        btn10= findViewById(R.id.ten);
        btn11 = findViewById(R.id.eleven);
        loadMore = findViewById(R.id.loadMore);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btn10.setOnClickListener(this);
        btn11.setOnClickListener(this);


    }

    private void getNameandaudioList(List<BanglaAudioList> list) {

        int mode  = list.size()%100;

        Audio__Radio_Mirchi_Adapter audio_Adapter = new Audio__Radio_Mirchi_Adapter(this,list,this);
        recyclerview.setAdapter(audio_Adapter);
    }

    @Override
    public void recyclerViewListClicked(View v, int position) {
        Intent intent = new Intent(this, PlayMp3Activity.class);
        String url = list.get(position).getaudio_info().get_audio_audio_stream_url().get(0);
        String name = list.get(position).getTitle().getRendered().toString();
        intent.putExtra("URL",url);
        intent.putExtra("name",name);
        startActivity(intent);


    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        finish();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.one) {
            list.clear();
            getList(id, btn1.getText().toString());
            progress.show();
        } else if (v.getId() == R.id.two) {
            list.clear();
            getList(id, btn2.getText().toString());
            progress.show();
        } else if (v.getId() == R.id.three) {
            list.clear();
            getList(id, btn3.getText().toString());
            progress.show();
        } else if (v.getId() == R.id.four)
        {
            list.clear();
            getList(id, btn4.getText().toString());
            progress.show();
        }
        else if (v.getId() == R.id.five)
        {
            list.clear();
            getList(id, btn5.getText().toString());
            progress.show();
        }
        else if (v.getId() == R.id.six)
        {
            list.clear();
            getList(id, btn6.getText().toString());
            progress.show();
        }
        else if (v.getId() == R.id.seven)
        {
            list.clear();
            getList(id, btn7.getText().toString());
            progress.show();
        }
        else if (v.getId() == R.id.eight)
        {
            list.clear();
            getList(id, btn8.getText().toString());
            progress.show();
        }
        else if (v.getId() == R.id.nine)
        {
            list.clear();
            getList(id, btn9.getText().toString());
            progress.show();
        }
        else if (v.getId() == R.id.ten)
        {
            list.clear();
            getList(id, btn10.getText().toString());
            progress.show();
        }
        else if (v.getId() == R.id.eleven)
        {
            list.clear();
            getList(id, btn11.getText().toString());
            progress.show();
        }
    }

    public void loadad()
    {

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mAdView = findViewById(R.id.adViewTop);
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
}
