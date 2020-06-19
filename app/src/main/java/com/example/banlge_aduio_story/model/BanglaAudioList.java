
package com.example.banlge_aduio_story.model;

import java.util.List;

public class BanglaAudioList {


    private Integer id;

    private String date;

    private String dateGmt;

    private Guid guid;

    private String modified;

    private String modifiedGmt;

    private String slug;

    private String status;

    private String type;

    private String link;

    private Title title;

    private Integer featuredMedia;

    private String template;

    private List<Integer> audioCat = null;

    private List<Object> audioSeries = null;

    private List<Integer> audioWriter = null;

    private AudioInfo audio_info;

    private String yoastHead;

    private Links links;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDateGmt() {
        return dateGmt;
    }

    public void setDateGmt(String dateGmt) {
        this.dateGmt = dateGmt;
    }

    public Guid getGuid() {
        return guid;
    }

    public void setGuid(Guid guid) {
        this.guid = guid;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    public String getModifiedGmt() {
        return modifiedGmt;
    }

    public void setModifiedGmt(String modifiedGmt) {
        this.modifiedGmt = modifiedGmt;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public Integer getFeaturedMedia() {
        return featuredMedia;
    }

    public void setFeaturedMedia(Integer featuredMedia) {
        this.featuredMedia = featuredMedia;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public List<Integer> getAudioCat() {
        return audioCat;
    }

    public void setAudioCat(List<Integer> audioCat) {
        this.audioCat = audioCat;
    }

    public List<Object> getAudioSeries() {
        return audioSeries;
    }

    public void setAudioSeries(List<Object> audioSeries) {
        this.audioSeries = audioSeries;
    }

    public List<Integer> getAudioWriter() {
        return audioWriter;
    }

    public void setAudioWriter(List<Integer> audioWriter) {
        this.audioWriter = audioWriter;
    }

    public AudioInfo getaudio_info() {
        return audio_info;
    }

    public void setaudio_info(AudioInfo audio_info) {
        this.audio_info = audio_info;
    }

    public String getYoastHead() {
        return yoastHead;
    }

    public void setYoastHead(String yoastHead) {
        this.yoastHead = yoastHead;
    }

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

}
