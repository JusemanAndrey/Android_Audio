package com.audio.main;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service {
	private static final String TAG = "MyService";
	MediaPlayer player;
	int position;
	
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
	
	@Override
	public void onCreate() {		
		player = MediaPlayer.create(this, R.raw.music);
		player.setLooping(false); // Set looping
	}
	public int onStartCommand (Intent intent, int flags, int startId){
	    position = intent.getIntExtra("position", 0);
	    Toast.makeText(getApplicationContext(), ""+position, Toast.LENGTH_LONG).show();
	    return START_STICKY;
	}
	@Override
	public void onDestroy() {
		player.stop();
	}
	
	@Override
	public void onStart(Intent intent, int startid) {
		player.start();
	}
}
