package com.example;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.banlge_aduio_story.R;
import com.github.ybq.android.spinkit.SpinKitView;

import java.io.IOException;

public class PlayMp3Activity2 extends AppCompatActivity  {

    ImageButton btnList,btnPlay,btnStop;
    private MediaPlayer mp;
    private TextView txtTotal,txtCurrent,txtTitle;
    private String MEDIA_PATH = "storage/extSdCard/Music/";
    private SeekBar soundSeek;
    private ImageView imgCover;
    int currentPosition = 0;
    int totalDuration = 0;
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
        //getSupportActionBar().hide();


        audioFile =getIntent().getStringExtra("URL");
        audioName = getIntent().getStringExtra("name");

        setTitle(audioName);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        audioName=audioName.replace("&#8211;","");
        btnList = (ImageButton)findViewById(R.id.imbList);
        btnPlay = (ImageButton) findViewById(R.id.imageButton3);
        btnStop = (ImageButton) findViewById(R.id.imageButton4);
        txtTitle = (TextView) findViewById(R.id.mediPlyerName);
        txtCurrent = (TextView) findViewById(R.id.textView2);
        txtTotal = (TextView) findViewById(R.id.textView);
        soundSeek = (SeekBar) findViewById(R.id.seekBar);
        spin_kit = (SpinKitView) findViewById(R.id.spin_kit);
        txtTitle.setText(audioName+"");
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
                    StartPlay();
                }

            }
        });


showProgressDialog();


    }

    private void showProgressDialog()
    {
        progressDialog=new ProgressDialog(PlayMp3Activity2.this);
        progressDialog.show();
    }

    private void hideProgressDialog()
    {
        progressDialog.dismiss();
    }


ProgressDialog progressDialog;
    @Override
    protected void onResume() {
        super.onResume();

     new Handler().postDelayed(new Runnable() {
         @Override
         public void run() {

             hideProgressDialog();

             StartPlay();

         }
     },2000);


    }

    private void StartPlay() {
        spin_kit.setVisibility(View.VISIBLE);
        mp = new MediaPlayer();
        playMedia(audioFile);
        setListeners();

    }

    private void stopPlayer() {
        try {
            mp.stop();
            mp.release();
            mp = null;

            Playing = false;
            mHandler.removeCallbacks(mRunnable);

            currentPosition = 0;
            totalDuration = 0;
            txtTitle.setText(audioName+"");
            txtTotal.setText(getFormatTime(0));
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
        if(mp!=null)
        mp.stop();
    }





    private void setListeners() {
        mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            public void onPrepared(final MediaPlayer mp) {
                mp.start();
                mRunnable.run();
            }
        });

        soundSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    mp.seekTo(progress);
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
            try{
               // setTitle(path);
                Uri myUri = Uri.parse(path); // initialize Uri here
                if(mp!=null) {
                    mp.stop();
                    mp.reset();
                    mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    mp.setDataSource(getApplicationContext(), myUri);
                    mp.prepare();
                    btnPlay.setImageResource(R.drawable.pause_selector);
                    value = 0;
                    Playing = true;
                }
            }catch(IOException e) {
                e.printStackTrace();
            }
        }
    }

    private Handler mHandler = new Handler();
    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            if(mp != null) {
                currentPosition = 0;
                totalDuration = mp.getDuration();
                currentPosition = mp.getCurrentPosition();
                soundSeek.setMax(totalDuration);
                soundSeek.setProgress(currentPosition);
                txtCurrent.setText(getFormatTime(currentPosition));
                txtTotal.setText(getFormatTime(totalDuration));
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
