package com.test.musicalstructureapp;


import java.util.ArrayList;
import java.util.List;

public final class SongFactory {

    private static SongFactory songFactory;

    private static List<Song> songs;

    private SongFactory() {
    }

    public static List<Song> getSongFactory() {
        if (songFactory == null) {
            songFactory = new SongFactory();
        }
        int id = 0;
        songs = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 12; j++) {
                songs.add(new Song(id, "Title № " + (j + 1), "Album № " + (i + 1)));
                id++;
            }
        }
        return songs;
    }

    public static Song getSong(int id) {
        for (int i = 0; i < 15; i++) {
            Song song = songs.get(i);
            if (song.getId() == id) {
                return song;
            }
        }
        return null;
    }
}
