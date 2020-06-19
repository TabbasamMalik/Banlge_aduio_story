package com.example.banlge_aduio_story;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.PlayMp3Activity2;
import com.github.ybq.android.spinkit.SpinKitView;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

public class PlayMp3Activity extends AppCompatActivity  {

    ImageButton btnList,btnPlay,btnStop;
    private SimpleExoPlayer simpleExoPlayer;
    private TextView txtTotal,txtCurrent,txtTitle;
    private String MEDIA_PATH = "storage/extSdCard/Music/";
    private SeekBar soundSeek;
    private ImageView imgCover;
    long currentPosition = 0;
    long totalDuration = 0;
    int value  =0;
    boolean Playing = false;
    String audioFile = "";
    String audioName="";
    int SONG_NUMBER = 0;
    int CURRENT_NUMBER= 0;
    private SpinKitView spin_kit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_mp3);
       // getSupportActionBar().hide();

        audioFile =getIntent().getStringExtra("URL");
        audioName = getIntent().getStringExtra("name");
        audioName=audioName.replace("&#8211;","");

        setTitle(audioName);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        btnList = (ImageButton)findViewById(R.id.imbList);
        btnPlay = (ImageButton) findViewById(R.id.imageButton3);
        btnStop = (ImageButton) findViewById(R.id.imageButton4);
        txtTitle = (TextView) findViewById(R.id.mediPlyerName);
        txtCurrent = (TextView) findViewById(R.id.textView2);
        txtTotal = (TextView) findViewById(R.id.textView);
        txtTotal.setText("");
        soundSeek = (SeekBar) findViewById(R.id.seekBar);
        spin_kit = (SpinKitView) findViewById(R.id.spin_kit);
        txtTitle.setText(audioName+"");
        showProgressDialog();
        plymp3();
        setListeners();
        //(audioName);
        //mp = new MediaPlayer();
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopPlayer();


                // imgCover.setImageResource(R.drawable.default_cover); //any default cover
                // imgCover.setAdjustViewBounds(true);
            }
        });

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Playing)
                {
                    stopPlayer();
                }
                else {
                   startPlayer();
                }
            }
        });


    }
    private void showProgressDialog()
    {
        progressDialog=new ProgressDialog(PlayMp3Activity.this);
        progressDialog.show();
    }

    private void hideProgressDialog()
    {
        progressDialog.dismiss();
    }


    ProgressDialog progressDialog;
    private void stopPlayer() {
        try {
            simpleExoPlayer.stop();
            simpleExoPlayer.release();
            simpleExoPlayer = null;

            Playing = false;
            mHandler.removeCallbacks(mRunnable);

            currentPosition = 0;
            totalDuration = 0;
            txtTitle.setText(audioName+"");

            txtCurrent.setText(getFormatTime(0));
            soundSeek.setProgress(0);
            btnPlay.setImageResource(R.drawable.play_selector);
            spin_kit.setVisibility(View.INVISIBLE);
        }
        catch (Exception e)
        {
            //mp.stop();
            //mp.release();
        }
    }

    private void startPlayer() {

        if(Playing)
        {
            simpleExoPlayer.stop();
            simpleExoPlayer.release();
        }
        plymp3();
        setListeners();
    }

    public void plymp3()
    {

       // showProgressDialog();
        txtTotal.setText("");
        DefaultRenderersFactory renderersFactory = new DefaultRenderersFactory(
                this,
                null,
                DefaultRenderersFactory.EXTENSION_RENDERER_MODE_OFF
        );
        TrackSelector trackSelector = new DefaultTrackSelector();
        simpleExoPlayer = ExoPlayerFactory.newSimpleInstance(
                renderersFactory,
                trackSelector
        );
        String userAgent = Util.getUserAgent(this, "Play Audio");
        ExtractorMediaSource mediaSource = new ExtractorMediaSource(
                Uri.parse(audioFile), // file audio ada di folder assets
                new DefaultDataSourceFactory(this, userAgent),
                new DefaultExtractorsFactory(),
                null,
                null
        );
        simpleExoPlayer.prepare(mediaSource);
        simpleExoPlayer.setPlayWhenReady(true);
        btnPlay.setImageResource(R.drawable.pause_selector);
        value = 0;
        Playing = true;
        spin_kit.setVisibility(View.VISIBLE);
        hideProgressDialog();
       // simpleExoPlayer.stop();


    }
    @Override
    public void  onStart()
    {
        super.onStart();
       // playMedia(audioFile);//
        //setListeners();
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        if(simpleExoPlayer!=null) {
            simpleExoPlayer.stop();
            simpleExoPlayer.release();
        }
    }





    private void setListeners() {
        mRunnable.run();

        soundSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    simpleExoPlayer.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }


    public void playMedia(String path){

        if(path != null){
            // setTitle(path);
            Uri myUri = Uri.parse(path); // initialize Uri here
            if(simpleExoPlayer!=null) {
                plymp3();
                btnPlay.setImageResource(R.drawable.pause_selector);
                value = 0;
                Playing = true;
            }
        }
    }

    private Handler mHandler = new Handler();
    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            if(simpleExoPlayer != null) {
                currentPosition = 0;
                totalDuration = simpleExoPlayer.getDuration();
                currentPosition = simpleExoPlayer.getCurrentPosition();
                soundSeek.setMax((int) totalDuration);
                soundSeek.setProgress((int) currentPosition);
                txtCurrent.setText(getFormatTime(currentPosition));
                String totalDuration2 = totalDuration+"";
                if(totalDuration2.contains("-9223"))
                {
                    txtTotal.setText("00:00");
                }
                else {
                    txtTotal.setText(getFormatTime(totalDuration));
                }

            }
            mHandler.postDelayed(this, 10);


        }
    };

    private String getFormatTime(long millis) {
        StringBuffer buf = new StringBuffer();

        int hours = (int) (millis / (1000 * 60 * 60));
        int minutes = (int) ((millis % (1000 * 60 * 60)) / (1000 * 60));
        int seconds = (int) (((millis % (1000 * 60 * 60)) % (1000 * 60)) / 1000);

        buf     .append(String.format("%02d", hours))
                .append(":")
                .append(String.format("%02d", minutes))
                .append(":")
                .append(String.format("%02d", seconds));

        return buf.toString();
    }



    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;

    }
}
