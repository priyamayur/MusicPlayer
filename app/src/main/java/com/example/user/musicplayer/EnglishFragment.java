package com.example.user.musicplayer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import org.parceler.Parcels;

import java.io.Serializable;
import java.util.ArrayList;

public class EnglishFragment extends Fragment {

    // Create a list of playlist
    static ArrayList<PlayList> playlist = new ArrayList<PlayList>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //   return inflater.inflate(R.layout.fragment_english, container, false);

        View rootView = inflater.inflate(R.layout.play_list, container, false);
        // Create a list of playlist

        playlist.add(new PlayList(R.string.EngSong1, R.string.EngSongDur1, R.raw.seven_years));
        playlist.add(new PlayList(R.string.EngSong2, R.string.EngSongDur2, R.raw.keeping_your_head_up));
        playlist.add(new PlayList(R.string.EngSong3, R.string.EngSongDur3, R.raw.caught_out_in_the_rain));
        playlist.add(new PlayList(R.string.EngSong4, R.string.EngSongDur4, R.raw.dancing_on_my_own));
        playlist.add(new PlayList(R.string.EngSong5, R.string.EngSongDur5, R.raw.chop_suey));
        playlist.add(new PlayList(R.string.EngSong6, R.string.EngSongDur6, R.raw.you_spin_my_head));
        playlist.add(new PlayList(R.string.EngSong7, R.string.EngSongDur7, R.raw.gotten));
        playlist.add(new PlayList(R.string.EngSong8, R.string.EngSongDur8, R.raw.ink));
        playlist.add(new PlayList(R.string.EngSong9, R.string.EngSongDur9, R.raw.born_to_die));
        playlist.add(new PlayList(R.string.EngSong10, R.string.EngSongDur10, R.raw.young_and_beautiful));

        // Create an {@link PlayListAdapter}, whose data source is a list of {@link PlayList}s. The
        // adapter knows how to create list items for each item in the list.
        PlayListAdapter adapter = new PlayListAdapter(getActivity(), playlist);

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // PlayList_list.xml layout file.
        ListView listView = (ListView) rootView.findViewById(R.id.list);

        // Make the {@link ListView} use the {@link PlayListAdapter} we created above, so that the
        // {@link ListView} will display list items for each {@link PlayList} in the list.
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Release the media player if it currently exists because we are about to
                // Create a new intent to open the {@link to QuizActivity
                Intent startIntent = new Intent(getActivity(), NowPlaying.class);

                // startIntent.putExtra("current_song", Parcels.wrap(playlist.get(position)) );
                startIntent.putExtra("song_position", position);
                startIntent.putExtra("song_fragment", getResources().getString(R.string.english));

                // Start the new activity
                startActivity(startIntent);

            }
        });
        return rootView;
    }


}