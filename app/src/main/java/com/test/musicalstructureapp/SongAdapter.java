package com.test.musicalstructureapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.UUID;

import static com.test.musicalstructureapp.MainActivity.currentSong;
import static com.test.musicalstructureapp.MainActivity.isPlayON;

public class SongAdapter extends ArrayAdapter<Song> {

    public SongAdapter(@NonNull Context context, @NonNull List<Song> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listView = convertView;

        if (listView == null) {
            listView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        Song song = getItem(position);

        TextView textViewSongTitle = (TextView) listView.findViewById(R.id.list_text_title_song);
        textViewSongTitle.setText(song.getTitle());

        TextView textViewAlbumTitle = (TextView) listView.findViewById(R.id.list_text_title_album);
        textViewAlbumTitle.setText(song.getAlbum());

        TextView textViewId = (TextView) listView.findViewById(R.id.list_number_id);
        textViewId.setText(String.valueOf(song.getId() + 1));

        ImageView imageViewPlay = (ImageView) listView.findViewById(R.id.list_image_play);
        ImageView imageViewPause = (ImageView) listView.findViewById(R.id.list_image_pause);

        if (currentSong == position & isPlayON == true) {
            imageViewPlay.setVisibility(View.INVISIBLE);
            imageViewPause.setVisibility(View.VISIBLE);
        } else {
            imageViewPlay.setVisibility(View.VISIBLE);
            imageViewPause.setVisibility(View.INVISIBLE);
        }

        return listView;
    }
}
