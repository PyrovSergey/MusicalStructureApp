package com.test.musicalstructureapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity {

    public static final String CURRENT_SONG = "currentSong";
    public static final String IS_SHUFFLE_ON = "isShuffleOn";
    public static final String IS_REPEAT_ON = "isRepeatOn";
    public static final String IS_PLAY_ON = "isPlayOn";
    @BindView(R.id.text_name_of_album)
    TextView textNameOfAlbum;
    @BindView(R.id.text_name_song)
    TextView textNameSong;
    @BindView(R.id.button_play)
    ImageButton buttonPlay;
    @BindView(R.id.button_pause)
    ImageButton buttonPause;
    @BindView(R.id.button_forward)
    ImageButton buttonForward;
    @BindView(R.id.button_rewind)
    ImageButton buttonRewind;
    @BindView(R.id.button_list)
    ImageButton buttonList;
    @BindView(R.id.button_shuffle_off)
    ImageButton buttonShuffleOff;
    @BindView(R.id.button_shuffle_on)
    ImageButton buttonShuffleOn;
    @BindView(R.id.button_repeat_off)
    ImageButton buttonRepeatOff;
    @BindView(R.id.button_repeat_on)
    ImageButton buttonRepeatOn;

    public static List<Song> songs;

    public static int currentSong = 0;

    public static boolean fromTheList;

    public static boolean isPlayON;

    private boolean isShuffleON;

    private boolean isRepeatON;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        songs = SongFactory.getSongFactory();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (fromTheList) {
            play();
            fromTheList = false;
            repeatOff();
            shuffleOff();
        }
        setSong(currentSong);
    }

    @OnClick({R.id.text_name_of_album, R.id.text_name_song, R.id.button_play, R.id.button_pause, R.id.button_forward, R.id.button_rewind, R.id.button_list, R.id.button_shuffle_off, R.id.button_shuffle_on, R.id.button_repeat_off, R.id.button_repeat_on})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button_play:
                play();
                break;
            case R.id.button_pause:
                pause();
                break;
            case R.id.button_forward:
                forward();
                break;
            case R.id.button_rewind:
                rewind();
                break;
            case R.id.button_list:
                goToList();
                break;
            case R.id.button_shuffle_off:
                shuffleOn();
                break;
            case R.id.button_shuffle_on:
                shuffleOff();
                break;
            case R.id.button_repeat_off:
                repeatOn();
                break;
            case R.id.button_repeat_on:
                repeatOff();
                break;
        }
    }

    private void repeatOff() {
        buttonRepeatOff.setVisibility(View.VISIBLE);
        buttonRepeatOn.setVisibility(View.INVISIBLE);
        isRepeatON = false;
    }

    private void repeatOn() {
        buttonRepeatOff.setVisibility(View.INVISIBLE);
        buttonRepeatOn.setVisibility(View.VISIBLE);
        isRepeatON = true;
    }

    private void shuffleOff() {
        buttonShuffleOff.setVisibility(View.VISIBLE);
        buttonShuffleOn.setVisibility(View.INVISIBLE);
        isShuffleON = false;
    }

    private void shuffleOn() {
        buttonShuffleOff.setVisibility(View.INVISIBLE);
        buttonShuffleOn.setVisibility(View.VISIBLE);
        isShuffleON = true;
    }

    private void goToList() {
        Intent intent = new Intent(this, SongsActivity.class);
        startActivity(intent);
    }

    private void rewind() {
        repeatOff();
        currentSong--;
        if (currentSong < 0) {
            currentSong = 0;
        }
        setSong(currentSong);
    }

    private void forward() {
        repeatOff();
        if (getButtonShuffleState()) {
            int random = currentSong;
            random = rnd(songs.size());
            currentSong = random;
            setSong(currentSong);
        } else {
            currentSong++;
            if (currentSong > songs.size() - 1) {
                currentSong--;
            }
            setSong(currentSong);
        }
    }

    private void pause() {
        buttonPlay.setVisibility(View.VISIBLE);
        buttonPause.setVisibility(View.INVISIBLE);
        isPlayON = false;
    }

    private void play() {
        buttonPlay.setVisibility(View.INVISIBLE);
        buttonPause.setVisibility(View.VISIBLE);
        isPlayON = true;
    }

    public void setSong(int id) {
        Song song = songs.get(id);
        textNameOfAlbum.setText(song.getAlbum());
        textNameSong.setText(song.getTitle());
    }

    public boolean getButtonShuffleState() {
        if (buttonShuffleOn.getVisibility() == View.VISIBLE) {
            return true;
        }
        return false;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(CURRENT_SONG, currentSong);
        outState.putBoolean(IS_SHUFFLE_ON, isShuffleON);
        outState.putBoolean(IS_REPEAT_ON, isRepeatON);
        outState.putBoolean(IS_PLAY_ON, isPlayON);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        currentSong = savedInstanceState.getInt(CURRENT_SONG);
        isShuffleON = savedInstanceState.getBoolean(IS_SHUFFLE_ON);
        if (isShuffleON) {
            buttonShuffleOn.setVisibility(View.VISIBLE);
            buttonShuffleOff.setVisibility(View.INVISIBLE);
        } else {
            buttonShuffleOn.setVisibility(View.INVISIBLE);
            buttonShuffleOff.setVisibility(View.VISIBLE);
        }
        isRepeatON = savedInstanceState.getBoolean(IS_REPEAT_ON);
        if (isRepeatON) {
            buttonRepeatOn.setVisibility(View.VISIBLE);
            buttonRepeatOff.setVisibility(View.INVISIBLE);
        } else {
            buttonRepeatOn.setVisibility(View.INVISIBLE);
            buttonRepeatOff.setVisibility(View.VISIBLE);
        }
        isPlayON = savedInstanceState.getBoolean(IS_PLAY_ON);
        if (isPlayON) {
            buttonPlay.setVisibility(View.INVISIBLE);
            buttonPause.setVisibility(View.VISIBLE);
        } else {
            buttonPlay.setVisibility(View.VISIBLE);
            buttonPause.setVisibility(View.INVISIBLE);
        }
    }

    public static int rnd(int max) {
        return (int) (Math.random() * max);
    }
}
