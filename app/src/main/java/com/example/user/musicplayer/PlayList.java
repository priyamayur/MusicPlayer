package com.example.user.musicplayer;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;


public class PlayList {

    private int mAudioResourceId;

    private int mSongNameID;


    private int mSongDurationID;

    public PlayList(int songNameID, int songDurationID, int audioResourceId) {

        mSongNameID = songNameID;
        mSongDurationID = songDurationID;
        mAudioResourceId = audioResourceId;

    }


    public int getSongNameID() {
        return mSongNameID;
    }


    public int getSongDurationID() {
        return mSongDurationID;
    }

    public int getAudioResourceId() {
        return mAudioResourceId;
    }


}
