package com.audio.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;

public class InfoActivity extends Activity {
	private static final String TAG = "AudioTag";
	  ImageButton back_but;

	  @Override
	  public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.main_info);
	    back_but = (ImageButton)findViewById(R.id.back_but);
	    back_but.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				Intent i = new Intent(InfoActivity.this, PlayActivity.class);
				startActivity(i);
			}	    	
	    });
	  }
	  @Override
	  public void onDestroy(){
		  super.onDestroy();
//		  startService(new Intent(this, MyService.class));
	  }
	  @Override
	  public void onStop(){
		  super.onStop();
//		  startService(new Intent(this, MyService.class));
	  }
	  
}
