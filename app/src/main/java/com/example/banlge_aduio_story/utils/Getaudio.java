package com.example.banlge_aduio_story.utils;

import com.example.banlge_aduio_story.model.AduioWriter;
import com.example.banlge_aduio_story.model.BanglaAudioList;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface Getaudio {


    @GET
    Call<List<BanglaAudioList>> getAudioList(@Url String url);


    //writer,progrm,series plyList



    @GET("wp-json/wp/v2/audio_cat?s976_per_page=500")
    Call<List<BanglaAudioList>> getAudioStory();

    @GET("wp-json/wp/v2/audio_writer?s976_per_page=500")
    Call<List<AduioWriter>> getAudioWriter();

    @GET("wp-json/wp/v2/audio_series?s976_per_page=500")
    Call<List<AduioWriter>> getPopulrSeries();

    @GET("wp-json/wp/v2/audio_cat?s976_per_page=500")
    Call<List<AduioWriter>> getPopularProgram();

}
