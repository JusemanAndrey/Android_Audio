package com.audio.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class AudioActivity extends Activity implements OnClickListener {
  private static final String TAG = "AudioTag";
  Button forward_but;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);

    forward_but = (Button) findViewById(R.id.forward);
    forward_but.setOnClickListener(this);
  }
  @Override
  public void onDestroy(){
	  super.onDestroy();
//	  startService(new Intent(this, MyService.class));
  }
  @Override
  public void onStop(){
	  super.onStop();
//	  startService(new Intent(this, MyService.class));
  }
  public void onClick(View src) {
    switch (src.getId()) {
    case R.id.forward:
      Log.d(TAG, "onClick: starting srvice");
      startActivity(new Intent(AudioActivity.this, PlayActivity.class));
//      startService(new Intent(this, MyService.class));
      break;
    }
  }
}