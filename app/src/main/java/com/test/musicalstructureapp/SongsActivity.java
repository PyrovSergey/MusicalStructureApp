package com.test.musicalstructureapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.test.musicalstructureapp.MainActivity.currentSong;
import static com.test.musicalstructureapp.MainActivity.fromTheList;
import static com.test.musicalstructureapp.MainActivity.songs;

public class SongsActivity extends AppCompatActivity {

    @BindView(R.id.list)
    ListView songListView;
    private SongAdapter songAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_songs);
        ButterKnife.bind(this);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        songListView = (ListView) findViewById(R.id.list);
        songAdapter = new SongAdapter(this, songs);

        songListView.setAdapter(songAdapter);

        songListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Song selectedSong = songAdapter.getItem(i);
                View itemView = view;
                ImageView imageViewPlay = (ImageView) itemView.findViewById(R.id.list_image_play);
                ImageView imageViewPause = (ImageView) itemView.findViewById(R.id.list_image_pause);
                imageViewPlay.setVisibility(View.INVISIBLE);
                imageViewPause.setVisibility(View.VISIBLE);
                currentSong = i;
                songAdapter.notifyDataSetChanged();
                fromTheList = true;
                finish();
            }
        });
    }
}
