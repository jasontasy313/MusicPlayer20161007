package com.example.kemie.musicplayer20161007;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {
    private LinkedList<Song> songList;
    ImageButton imgBtnPlay,imgBtnStop,imgBtnPrev,imgBtnNext;
    ListView musicList;
    SeekBar seekBar;
    TextView resultText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
        getMusics();
    }

    private void initialize(){
        imgBtnPlay = (ImageButton) findViewById(R.id.imgBtnPlay);
        imgBtnStop = (ImageButton) findViewById(R.id.imgBtnStop);
        imgBtnPrev = (ImageButton) findViewById(R.id.imgBtnPrev);
        imgBtnNext = (ImageButton) findViewById(R.id.imgBtnNext);
        musicList = (ListView) findViewById(R.id.musicList);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        resultText = (TextView) findViewById(R.id.resultText);
    }
    private void getMusics(){
        songList = new LinkedList<Song>();
        ContentResolver contentResolver = getContentResolver();
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Cursor cursor = contentResolver.query(uri,null,null,null,null);
        if (cursor == null){
            Log.d("=======>","查詢錯誤");
        }else if (!cursor.moveToFirst()){
            Log.d("=======>","沒有媒體檔");
        }else{
            int titleColumn = cursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
            int idColumn = cursor.getColumnIndex(MediaStore.Audio.Media._ID);
            int albumColumn = cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM);
            do {
                long thisId = cursor.getLong(idColumn);
                String thisTitle = cursor.getString(titleColumn);
                String thisAlbum = cursor.getString(albumColumn);
                Log.d("=======>","id: " + thisId + ", title: " + thisTitle + ", album: " + thisAlbum);
                songList.add(new Song(thisId,thisTitle,thisAlbum));
            }while(cursor.moveToNext());
            SongAdapter adapter = new SongAdapter(MainActivity.this, songList);
            musicList.setAdapter(adapter);
            resultText.setText("共有 "+ songList.size() + " 首歌曲");
        }

    }
}
