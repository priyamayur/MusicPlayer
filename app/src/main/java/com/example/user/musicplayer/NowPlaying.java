package com.example.user.musicplayer;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import org.parceler.Parcels;

import java.io.Serializable;

public class
NowPlaying extends AppCompatActivity {

    static int length = 0;
    static int audioId = -1, play_position = 0;
    static int PauseVis = View.GONE, PlayVis = View.VISIBLE;
    Button pauseButton, playButton, prevButton, nextButton;

    private MediaPlayer mMediaPlayer;
    static MediaPlayer mp;
    static PlayList play;

    static int flag = 0;

    private AudioManager mAudioManager;
    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                    focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {

                if (mAudioManager.isMusicActive() == true) {
                    flag = 1;
                    mMediaPlayer.pause();
                    mMediaPlayer.seekTo(length);
                } else
                    flag = 0;

            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {

                {

                    if (flag == 1)
                        mMediaPlayer.start();
                }
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {

                releaseMediaPlayer();
            }
        }
    };

    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {

            releaseMediaPlayer();
        }
    };


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_now_playing);
        final String gone = "GONE";
        String VISIBLE = "VISIBLE";

        play_position = (int) getIntent().getIntExtra("song_position", 0);
        String frag = (String) getIntent().getStringExtra("song_fragment");
        if (frag.equalsIgnoreCase("english")) {
            play = (PlayList) EnglishFragment.playlist.get(play_position);

        } else if (frag.equalsIgnoreCase("bengali"))
            play = (PlayList) BengaliFragment.playlist.get(play_position);
        else
            play = (PlayList) HindiFragment.playlist.get(play_position);

        mAudioManager = (AudioManager) this.getSystemService(Context.AUDIO_SERVICE);
        pauseButton = (Button) findViewById(R.id.pause_button);
        playButton = (Button) findViewById(R.id.play_button);
        prevButton = (Button) findViewById(R.id.prev_button);
        prevButton.setVisibility(View.VISIBLE);
        nextButton = (Button) findViewById(R.id.next_button);
        nextButton.setVisibility(View.VISIBLE);

        if (play.getAudioResourceId() == audioId) {


            playButton.setVisibility(PlayVis);
            pauseButton.setVisibility(PauseVis);
            mMediaPlayer = mp;

        } else {
            length = 0;
        }

        prevButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {


                String frag = (String) getIntent().getStringExtra("song_fragment");

                if (play_position != 0) {
                    if (frag.equalsIgnoreCase("english")) {
                        playButton.setVisibility(View.VISIBLE);
                        pauseButton.setVisibility(View.GONE);
                        mp.pause();
                        length = 0;
                        play_position--;
                        play = (PlayList) EnglishFragment.playlist.get(play_position);
                    } else if (frag.equalsIgnoreCase("bengali")) {
                        playButton.setVisibility(View.VISIBLE);
                        pauseButton.setVisibility(View.GONE);
                        mp.pause();
                        length = 0;
                        play_position--;
                        play = (PlayList) BengaliFragment.playlist.get(play_position);
                    } else {
                        playButton.setVisibility(View.VISIBLE);
                        pauseButton.setVisibility(View.GONE);
                        mp.pause();
                        length = 0;
                        play_position--;
                        play = (PlayList) HindiFragment.playlist.get(play_position);
                    }
                } else {
                    prevButton.setVisibility(View.GONE);
                }

            }
        });


        nextButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {


                String frag = (String) getIntent().getStringExtra("song_fragment");

                if (frag.equalsIgnoreCase("english")) {
                    if (play_position != (EnglishFragment.playlist.size() - 1)) {

                        playButton.setVisibility(View.VISIBLE);
                        pauseButton.setVisibility(View.GONE);
                        mp.pause();
                        length = 0;
                        play_position++;
                        play = (PlayList) EnglishFragment.playlist.get(play_position);

                    } else
                        nextButton.setVisibility(View.GONE);
                } else if (frag.equalsIgnoreCase("bengali")) {
                    if (play_position != (BengaliFragment.playlist.size() - 1)) {
                        playButton.setVisibility(View.VISIBLE);
                        pauseButton.setVisibility(View.GONE);
                        mp.pause();
                        length = 0;
                        play_position++;
                        play = (PlayList) BengaliFragment.playlist.get(play_position);
                    } else
                        nextButton.setVisibility(View.GONE);
                } else {
                    if (play_position != (HindiFragment.playlist.size() - 1)) {
                        playButton.setVisibility(View.VISIBLE);
                        pauseButton.setVisibility(View.GONE);
                        mp.pause();
                        length = 0;
                        play_position++;
                        play = (PlayList) HindiFragment.playlist.get(play_position);
                    } else
                        nextButton.setVisibility(View.GONE);

                }

            }
        });

        playButton.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {

                playButton.setVisibility(View.GONE);
                pauseButton.setVisibility(View.VISIBLE);

                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                        AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
//
                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {

                    mMediaPlayer = MediaPlayer.create(NowPlaying.this, play.getAudioResourceId());

                    audioId = play.getAudioResourceId();

                    mMediaPlayer.seekTo(length);
                    mMediaPlayer.start();
                    mp = mMediaPlayer;

                    PlayVis = playButton.getVisibility();
                    PauseVis = pauseButton.getVisibility();

                    mMediaPlayer.setOnCompletionListener(mCompletionListener);


                }

            }
        });


        pauseButton.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers category is clicked on.


            @Override
            public void onClick(View view) {


                playButton.setVisibility(View.VISIBLE);
                pauseButton.setVisibility(View.GONE);

                mp.pause();
                length = mMediaPlayer.getCurrentPosition();
                audioId = play.getAudioResourceId();
                PlayVis = playButton.getVisibility();
                PauseVis = pauseButton.getVisibility();

                mMediaPlayer.setOnCompletionListener(mCompletionListener);

            }


            // Setup a listener on the media player, so that we can stop and release the
            // media player once the sound has finished playing.
        });


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("playVis", playButton.getVisibility());
        outState.putInt("pauseVis", pauseButton.getVisibility());

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        PlayVis = savedInstanceState.getInt("playVis");
        PauseVis = savedInstanceState.getInt("pauseVis");

    }


    public void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {

            mMediaPlayer.release();

            mMediaPlayer = null;

            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
            playButton.setVisibility(View.VISIBLE);
            pauseButton.setVisibility(View.GONE);


        }


    }

}
