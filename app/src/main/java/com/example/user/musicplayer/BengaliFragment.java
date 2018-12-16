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

public class BengaliFragment extends Fragment {

    // Create a list of playlistB
    static ArrayList<PlayList> playlist = new ArrayList<PlayList>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.play_list, container, false);

        playlist.add(new PlayList(R.string.BengSong1, R.string.BengSongDur1, R.raw.majhi_re));
        playlist.add(new PlayList(R.string.BengSong2, R.string.BengSongDur2, R.raw.amar_mawte));
        playlist.add(new PlayList(R.string.BengSong3, R.string.BengSongDur3, R.raw.bhindeshi_tara));
        playlist.add(new PlayList(R.string.BengSong4, R.string.BengSongDur4, R.raw.phiriye_dewar_gaan));
        playlist.add(new PlayList(R.string.BengSong5, R.string.BengSongDur5, R.raw.beche_thakar_gaan));
        playlist.add(new PlayList(R.string.BengSong6, R.string.BengSongDur6, R.raw.shesh_bela));
        playlist.add(new PlayList(R.string.BengSong7, R.string.BengSongDur7, R.raw.tumi_jake_bhalobasho));
        playlist.add(new PlayList(R.string.BengSong8, R.string.BengSongDur8, R.raw.the_bong_connection));
        playlist.add(new PlayList(R.string.BengSong9, R.string.BengSongDur9, R.raw.shudu_tumi_ele_na));
        playlist.add(new PlayList(R.string.BengSong10, R.string.BengSongDur10, R.raw.onno_keu_thakbe_kachakachi));


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
                startIntent.putExtra("song_fragment", getResources().getString(R.string.beng));

                // Start the new activity
                startActivity(startIntent);

            }
        });

        return rootView;
    }


}