package com.example.user.musicplayer;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class PlayListAdapter extends ArrayAdapter<PlayList> {

    public PlayListAdapter(Context context, ArrayList<PlayList> words) {
        super(context, 0, words);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.playlist_item, parent, false);
        }


        PlayList currentWord = getItem(position);

        TextView miwokTextView = (TextView) listItemView.findViewById(R.id.song_text_view);

        miwokTextView.setText(currentWord.getSongNameID());

        TextView defaultTextView = (TextView) listItemView.findViewById(R.id.time_text_view);

        defaultTextView.setText(currentWord.getSongDurationID());

        return listItemView;
    }
}
