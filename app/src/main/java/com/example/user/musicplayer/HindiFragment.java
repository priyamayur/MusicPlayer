package com.example.user.musicplayer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class HindiFragment extends Fragment {

    static ArrayList<PlayList> playlist = new ArrayList<PlayList>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.play_list, container, false);

        playlist.add(new PlayList(R.string.HindiSong1, R.string.HindiSongDur1, R.raw.azaadiyan));
        playlist.add(new PlayList(R.string.HindiSong2, R.string.HindiSongDur2, R.raw.bezubaan));
        playlist.add(new PlayList(R.string.HindiSong3, R.string.HindiSongDur3, R.raw.der_lagi_lekin));
        playlist.add(new PlayList(R.string.HindiSong4, R.string.HindiSongDur4, R.raw.dil_chahta_hai));
        playlist.add(new PlayList(R.string.HindiSong5, R.string.HindiSongDur5, R.raw.in_dino));
        playlist.add(new PlayList(R.string.HindiSong6, R.string.HindiSongDur6, R.raw.jo_bhi_main));
        playlist.add(new PlayList(R.string.HindiSong7, R.string.HindiSongDur7, R.raw.kaari_kaari));
        playlist.add(new PlayList(R.string.HindiSong8, R.string.HindiSongDur8, R.raw.kar_salaam));
        playlist.add(new PlayList(R.string.HindiSong9, R.string.HindiSongDur9, R.raw.pataka_guddi));
        playlist.add(new PlayList(R.string.HindiSong10, R.string.HindiSongDur10, R.raw.ud_da_punjab));


        // Create an {@link playlistBAdapter}, whose data source is a list of {@link playlistB}s. The
        // adapter knows how to create list items for each item in the list.
        PlayListAdapter adapter = new PlayListAdapter(getActivity(), playlist);

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // playlistB_list.xml layout file.
        ListView listView = (ListView) rootView.findViewById(R.id.list);

        // Make the {@link ListView} use the {@link playlistBAdapter} we created above, so that the
        // {@link ListView} will display list items for each {@link playlistB} in the list.
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Release the media player if it currently exists because we are about to
                // Create a new intent to open the {@link to QuizActivity
                Intent startIntent = new Intent(getActivity(), NowPlaying.class);

                // startIntent.putExtra("current_song", Parcels.wrap(playlistB.get(position)) );
                startIntent.putExtra("song_position", position);
                startIntent.putExtra("song_fragment", getResources().getString(R.string.hindi));

                // Start the new activity
                startActivity(startIntent);

            }
        });
        return rootView;
    }


}