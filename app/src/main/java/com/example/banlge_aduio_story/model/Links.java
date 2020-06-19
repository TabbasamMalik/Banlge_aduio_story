
package com.example.banlge_aduio_story.model;

import java.util.List;

public class Links {


    private List<Self> self = null;

    private List<Collection> collection = null;

    private List<About> about = null;

    private List<WpAttachment> wpAttachment = null;

    private List<WpTerm> wpTerm = null;

    private List<Cury> curies = null;

    public List<Self> getSelf() {
        return self;
    }

    public void setSelf(List<Self> self) {
        this.self = self;
    }

    public List<Collection> getCollection() {
        return collection;
    }

    public void setCollection(List<Collection> collection) {
        this.collection = collection;
    }

    public List<About> getAbout() {
        return about;
    }

    public void setAbout(List<About> about) {
        this.about = about;
    }

    public List<WpAttachment> getWpAttachment() {
        return wpAttachment;
    }

    public void setWpAttachment(List<WpAttachment> wpAttachment) {
        this.wpAttachment = wpAttachment;
    }

    public List<WpTerm> getWpTerm() {
        return wpTerm;
    }

    public void setWpTerm(List<WpTerm> wpTerm) {
        this.wpTerm = wpTerm;
    }

    public List<Cury> getCuries() {
        return curies;
    }

    public void setCuries(List<Cury> curies) {
        this.curies = curies;
    }

}
