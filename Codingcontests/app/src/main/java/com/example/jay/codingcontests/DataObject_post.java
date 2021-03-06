package com.example.jay.codingcontests;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

/**
 * Created by Jai on 08-Nov-15.
 */

public class DataObject_post {

    public static final int NO_IMAGE = 0;
    public static final int WITH_IMAGE = 1;

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getContestid() {
        return contestid;
    }

    public void setContestid(String contestid) {
        this.contestid = contestid;
    }

    public String getResource_id() {
        return resource_id;
    }

    public void setResource_id(String resource_id) {
        this.resource_id = resource_id;
    }

    public String getResource_name() {
        return resource_name;
    }

    public void setResource_name(String resource_name) {
        this.resource_name = resource_name;
    }

    public int getType() {
       mType=NO_IMAGE;
        return mType;
    }
    public void setType(int type) {
        this.mType = type;
    }

    private int mType;
    private String duration;
    private String end;
    private String start;
    private String event;
    private String href;
    private String contestid;
    private String resource_id;
    private String resource_name;

    DataObject_post(String duration, String end, String start, String event, String href, String contestid, String resource_id, String resource_name) {
        this.duration = duration;
        this.end = end;
        this.start = start;
        this.event = event;
        this.href = href;
        this.contestid = contestid;
        this.resource_id =resource_id ;
        this.resource_name = resource_name;

    }
}