package com.audio.main;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class PlayActivity extends Activity {
	private static final String TAG = "AudioTag";
//	http://198.57.247.237/~ibrahim/quranmp3/001 AL FATIHA_0.mp3
	
	String preUrl = "http://198.57.247.237/~ibrahim/quranmp3/";
	int len = Constant.total_url.length;
	public int pos = -1;
	public String mp, file_name;
	
	ProgressBar pb;
	TextView text;
    Dialog dialog;
    float downloadedSize = 0;
    float totalSize = 0;
    float per = 0;
    TextView cur_val, cur_val1;
    
    SharedPreferences sharedpreferences;
	public static final String MyPREFERENCES = "myPref";
    public static final String ISDOWNLOADED = "download";
    public static final String ISALLDOWNLOADED = "alldownload";
    
    boolean isAllDownloaded = false; 
    int isDownloaded = 0;
    
    CustomListAdapter listAdapter;
    ArrayList<NewsItem> results = new ArrayList<NewsItem>();
    NewsItem newsData;
    TypedArray profile_pics, profile_pics2;
    
	Button info_but, arrow_but;
	ListView list_view;	
	TextView txt_view, txt_view1;	
	LinearLayout music_layout, layout_text;
	
	Button info_play_but;
	Button info_pause_but;
	TextView second;
	
	Button play_but, pause_but, stop_but;
	Button x_but, d_but; 
	private SeekBar seekBar;
//	TextView duration;
	private double timeElapsed = 0, finalTime = 0;
	public Handler durationHandler = new Handler();
	public MediaPlayer mediaPlayer;
	
	String PATH, fileName;
	File file, outputFile;
	
	mp3loadAsyncTask mTask;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_play);
		
		sharedpreferences = getSharedPreferences(MyPREFERENCES, 0);
		isDownloaded = sharedpreferences.getInt(ISDOWNLOADED, 0);
		
		PATH = Environment.getExternalStorageDirectory() + "/download/";
        file = new File(PATH);
        
		
		info_but = (Button)findViewById(R.id.info_but);
		arrow_but = (Button)findViewById(R.id.arrow_but);
		list_view = (ListView)findViewById(R.id.list_view);
		profile_pics = getResources().obtainTypedArray(R.array.status_icon);
//		profile_pics2 = getResources().obtainTypedArray(R.array.status_icon2);
		
		x_but = (Button)findViewById(R.id.x_sign);
		d_but = (Button)findViewById(R.id.d_sign);
//		duration = (TextView)findViewById(R.id.duration);
		music_layout = (LinearLayout)findViewById(R.id.music_layout);
		layout_text = (LinearLayout)findViewById(R.id.layout_text);
		txt_view = (TextView)findViewById(R.id.txt_view);
		txt_view1 = (TextView)findViewById(R.id.txt_view1);
		seekBar = (SeekBar)findViewById(R.id.seekBar);
		
		
//		seekBar.setProgressDrawable(getResources().getDrawable(R.drawable.progressbar));
		
//		play_but.setOnClickListener(new OnClickListener(){
//			@Override
//			public void onClick(View v) {
//				as
//			}
//		});
		init();
		itemShow();
		allDownload();
		
	}
	private void init() {
		play_but = (Button)findViewById(R.id.media_play);
		pause_but = (Button)findViewById(R.id.media_pause);
		stop_but = (Button)findViewById(R.id.media_stop);
		play_but.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				play();
			}			
		});
		pause_but.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				pause();
			}			
		});
		stop_but.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				stop();
			}			
		});
	}
	private void allDownload() {
		arrow_but = (Button)findViewById(R.id.arrow_but);
		arrow_but.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				AlertDialog.Builder allDialog = new AlertDialog.Builder(PlayActivity.this);
				String msg = "Do you want to download the \n                     All Sura?";
				allDialog.setCancelable(false);
				allDialog.setMessage(msg);
				allDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						
						for(int i=0; i < len; i++){
							pos = i;
							mp = preUrl+Constant.total_url[i];
							file_name = Constant.total_url[i];
							String i_title = Constant.i_title[i];
							String e_title = Constant.e_title[i];
							showProgress(e_title, i_title);
							
							String PATH = Environment.getExternalStorageDirectory() + "/download/";
				            File file = new File(PATH);
							File outputFile = new File(file, file_name);
							if(outputFile.exists()) {
								return;
							} else {
								Log.e("length", ""+file_name);
								new allMp3loadAsyncTask().execute("");
							}
							
						}
					}
				});
				allDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});
				allDialog.create();
				allDialog.show();
				
//				new mp3AllloadAsyncTask().execute("");
			}
			
		});
	}
	public void itemShow() {
		int arr_len = Constant.e_title.length;
		
		for(int i=0; i<arr_len; i++){
			newsData = new NewsItem();
			String i_title_val = Constant.i_title[i];
			String e_title_val = Constant.e_title[i];
			
			String num_val = String.valueOf(i+1);
			newsData.setNumber(num_val);
			newsData.setSecond("");
			newsData.setITitle(i_title_val);
			newsData.setETitle(e_title_val);	
			fileName = Constant.total_url[i];
	        outputFile = new File(file, fileName);
			if(outputFile.exists()) {
				newsData.setImageId(R.drawable.number_but1);
			} else {
				newsData.setImageId(R.drawable.number_but2);
			}
//			newsData.setSecondView(sec);
			
			results.add(newsData);
		}		
		
		listAdapter = new CustomListAdapter(this, results);
		list_view.setAdapter(listAdapter);
		list_view.setOnItemClickListener(new OnItemClickListener(){
			@Override
			public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
				
//				view.setSelected(true);
//				list_view.setChoiceMode(ListView.CHOICE_MODE_SINGLE);				
				
				Object o = list_view.getItemAtPosition(position);
				newsData = (NewsItem) o;
				String msg = "      ئایا حه ز ده که ی ئه م سوره ته دابه زێنی؟\n     Do you want to download the \n                           Sura?";
				//=======================================================================
				list_view.post(new Runnable() {
				    @Override
				    public void run() {
				    	list_view.smoothScrollToPosition(position);
				    }
				});
				//=======================================================================
				outputFile = new File(file, Constant.total_url[position]);
				
		        if(outputFile.exists()) {
		        	
					music_layout.setVisibility(View.VISIBLE);
					layout_text.setVisibility(View.GONE);
//					NewsItem preData = (NewsItem)list_view.getItemAtPosition(pos);
			        if(mediaPlayer == null) {
			        	
			        	mediaPlayer = MediaPlayer.create(PlayActivity.this, Uri.parse(outputFile.toString()));
			        				        	
			        	finalTime = mediaPlayer.getDuration();
			        	seekBar = (SeekBar)findViewById(R.id.seekBar);
						seekBar.setMax((int) finalTime);
						seekBar.setClickable(false);
			        	
						mediaPlayer.start();
						
			        	play_but.setVisibility(View.GONE);
			    		pause_but.setVisibility(View.VISIBLE);
			    		
//			    		second = (TextView)view.findViewById(R.id.second);
			    		timeElapsed = mediaPlayer.getCurrentPosition();
			    		seekBar.setProgress((int) timeElapsed);
			    		durationHandler.postDelayed(updateSeekBarTime, 100);
			    		
			    		newsData.setVisible(false);
			        	newsData.setPauseVisible(true);
			        	newsData.setBgImageId(R.color.pressed_color);
			        	results.set(position, newsData);
			        	
			        	pos = position;
			        } else {
			        	if(position == pos){
			        		if(isPlaying()) {		        		
			        			mediaPlayer.pause();
			        			
			        			play_but.setVisibility(View.VISIBLE);
					    		pause_but.setVisibility(View.GONE);
					    		
					    		timeElapsed = mediaPlayer.getCurrentPosition();
					    		seekBar.setProgress((int) timeElapsed);
					    		durationHandler.postDelayed(updateSeekBarTime, 100);
					    		
					    		newsData.setVisible(true);
					        	newsData.setPauseVisible(false);
					        	newsData.setBgImageId(0);
					        	results.set(position, newsData);
					    		
			        		} else {
			        			
			        			mediaPlayer.start();
			        			
			        			play_but.setVisibility(View.GONE);
					    		pause_but.setVisibility(View.VISIBLE);
					    		
					    		timeElapsed = mediaPlayer.getCurrentPosition();
					    		seekBar.setProgress((int) timeElapsed);
					    		durationHandler.postDelayed(updateSeekBarTime, 100);
					    		
					    		newsData.setVisible(false);
					        	newsData.setPauseVisible(true);
					        	newsData.setBgImageId(R.color.pressed_color);
					        	results.set(position, newsData);
			        		}
			        	} else {
			        		outputFile = null;
			        		mediaPlayer.stop();			        		
			        		mediaPlayer = null;
			        		
			        		outputFile = new File(file, Constant.total_url[position]);
			        		mediaPlayer = MediaPlayer.create(PlayActivity.this, Uri.parse(outputFile.toString()));
		        			mediaPlayer.start();
		        			
		        			play_but.setVisibility(View.GONE);
				    		pause_but.setVisibility(View.VISIBLE);
				    		
				    		NewsItem preData = (NewsItem)list_view.getItemAtPosition(pos);
				    		preData.setVisible(false);
				    		preData.setPauseVisible(false);
				    		preData.setBgImageId(0);				    		
				    		preData.setSecond("");
				    		results.set(pos, preData);
					        
					        newsData.setVisible(false);
				        	newsData.setPauseVisible(true);
				        	newsData.setBgImageId(R.color.pressed_color);
				        	results.set(position, newsData);
				        	
				    		timeElapsed = mediaPlayer.getCurrentPosition();
				    		seekBar.setProgress((int) timeElapsed);
				    		durationHandler.postDelayed(updateSeekBarTime, 100);
		        			
		        			pos = position;
		        			int org_val = position;
			        	}
			        }
			        listAdapter = new CustomListAdapter(PlayActivity.this, results);
			        list_view.setAdapter(listAdapter);
		        } else {
		        	
		        	sharedpreferences.edit().remove(ISDOWNLOADED).commit();
			        
			        pos = position;
					dialogShow(msg, position);
		        }

			}

		});
	}
	public void dialogShow(String msg, final int position) {
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
		alertDialog.setMessage(msg);
		alertDialog.setPositiveButton("Yes به ڵێ", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				mp = preUrl+Constant.total_url[position];
				file_name = Constant.total_url[position];
				String i_title = Constant.i_title[position];
				String e_title = Constant.e_title[position];
				pos = position;
				showProgress(e_title, i_title);
				(mTask = new mp3loadAsyncTask()).execute("");
				
			}
		});
		alertDialog.setNegativeButton("No نه خێر", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		alertDialog.create();
		alertDialog.show();
	}
	class allMp3loadAsyncTask extends AsyncTask<String, Void, String> {
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			text.setText(file_name);
		}
		@Override
		protected String doInBackground(String... params) {
			try {	
					
					URL url = new URL(mp);
		            HttpURLConnection con = (HttpURLConnection) url.openConnection();
		            con.setRequestMethod("GET");
		            con.setDoOutput(true);
		            con.connect();
		
		            String PATH = Environment.getExternalStorageDirectory() + "/download/";
		            File file = new File(PATH);
		            file.mkdirs();
		
		             String fileName = file_name;
		
		            File outputFile = new File(file, fileName);
		            FileOutputStream fos = new FileOutputStream(outputFile);
		
		            InputStream is = con.getInputStream();
		            
		            totalSize = con.getContentLength();
		            runOnUiThread(new Runnable() {
					    public void run() {
					    	pb.setMax((int)totalSize);
					    }			    
					});
		            
					byte[] buffer = new byte[1024];
		    		float bufferLength = 0;
		
		    		while ( (bufferLength = is.read(buffer)) > 0 ) {
		    			fos.write(buffer, 0, (int)bufferLength);
		    			downloadedSize += bufferLength;
		    			// update the progressbar //
		    			runOnUiThread(new Runnable() {
		    			    public void run() {
		    			    	pb.setProgress((int)downloadedSize);
		    			    	per = ((float)downloadedSize/totalSize) * 100;
		    			    	String d = String.format("%.2f", downloadedSize);
		    			    	String s = String.format("%.2f", totalSize);
		    			    	// (" + (int)per + "%)
		    			    	cur_val.setText("Downloading....دابه زاندن");
		    			    	cur_val1.setText(d+"M/"+s+"M");
		    			    }
		    			});
		    		}
		    		Log.e("dddddddd", "ddd");
		    		pb.setProgress(0);
		    		downloadedSize = 0;
		    		//close the output stream when complete //
		    		fos.flush();
		    		fos.close();
//		    		is.close();
		    		runOnUiThread(new Runnable() {
					    public void run() {
					    	dialog.dismiss();
//							    	 pb.dismiss(); // if you want close it..
					    }
					});  				
				
			} catch (final MalformedURLException e) {
	    		showError("Error : MalformedURLException " + e);  		
	    		e.printStackTrace();
	    	} catch (final IOException e) {
	    		showError("Error : IOException " + e);  		
	    		e.printStackTrace();
	    	}
	    	catch (final Exception e) {
	    		showError("Error : Please check your internet connection " + e);
	    	}    	
			return "done";
		}
		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			dialog.dismiss();	
			if(result.equals("done")) {
				if((int)per == 100) {
					for(int i=0; i<len; i++){
						
						fileName = Constant.total_url[i];
				        outputFile = new File(file, fileName);
				        
						if(outputFile.exists()) {
	
							NewsItem temp = results.get(i);
							temp.setImageId(R.drawable.number_but1);
							
							results.set(i, temp);
			
							listAdapter = new CustomListAdapter(PlayActivity.this, results);
							list_view.setAdapter(listAdapter);
						}
					}
				}
				return;
//				startService(new Intent(PlayActivity.this, MyService.class));
			} else {
				if(isConnectedInternet()){
					Toast.makeText(getApplicationContext(), "Please try Again", Toast.LENGTH_LONG).show();
				} else {
					Toast.makeText(getApplicationContext(), "Internet Connection Failed", Toast.LENGTH_LONG).show();
				}
			}
		}
	}
	class mp3loadAsyncTask extends AsyncTask<String, Void, String> {
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			dialog.setCancelable(false);
		}
		@Override
		protected String doInBackground(String... params) {
			try {	
					
					URL url = new URL(mp);
		            HttpURLConnection con = (HttpURLConnection) url.openConnection();
		            con.setRequestMethod("GET");
		            con.setDoOutput(true);
		            con.connect();
		
		            String PATH = Environment.getExternalStorageDirectory() + "/download/";
		            File file = new File(PATH);
		            file.mkdirs();
		
		             String fileName = file_name;
		
		            File outputFile = new File(file, fileName);
		            FileOutputStream fos = new FileOutputStream(outputFile);
		
		            InputStream is = con.getInputStream();
		            
		            final float totalSize = con.getContentLength();
		            runOnUiThread(new Runnable() {
					    public void run() {
					    	pb.setMax((int)totalSize);
					    }			    
					});
		            
					byte[] buffer = new byte[1024];
		    		float bufferLength = 0;
		
		    		while ( (bufferLength = is.read(buffer)) > 0 ) {
		    			fos.write(buffer, 0, (int)bufferLength);
		    			downloadedSize += bufferLength;
		    			// update the progressbar //
		    			runOnUiThread(new Runnable() {
		    			    public void run() {
		    			    	pb.setProgress((int)downloadedSize);
		    			    	float per = ((float)downloadedSize/totalSize) * 100;
		    			    	DecimalFormat df = new DecimalFormat("#.##");
		    			    	String d = df.format(downloadedSize/1024/1024);
		    			    	String s = df.format(totalSize/1024/1024);
		    			    	Log.e("=======", ""+(float)(downloadedSize/1024/1024)+"/"+(float)(totalSize/1024/1024));
		    			    	// (" + (int)per + "%)
		    			    	cur_val.setText("Downloading....دابه زاندن");
		    			    	cur_val1.setText(d+"M/"+s+"M");
		    			    }
		    			});
		    		}
		    		Log.e("dddddddd", "ddd");
		    		pb.setProgress(0);
		    		downloadedSize = 0;
		    		//close the output stream when complete //
		    		fos.flush();
		    		fos.close();
//		    		is.close();
		    		runOnUiThread(new Runnable() {
					    public void run() {
					    	dialog.dismiss();
//							    	 pb.dismiss(); // if you want close it..
					    }
					});  				
				
			} catch (final MalformedURLException e) {
	    		showError("Error : MalformedURLException " + e);  		
	    		e.printStackTrace();
	    	} catch (final IOException e) {
	    		showError("Error : IOException " + e);  		
	    		e.printStackTrace();
	    	}
	    	catch (final Exception e) {
	    		showError("Error : Please check your internet connection " + e);
	    	}    	
			return "done";
		}
		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			dialog.dismiss();	
			if(result.equals("done")) {
				int val = pos;
				sharedpreferences.edit().putInt(ISDOWNLOADED, val).commit();
				
//				newsData = new NewsItem();
				fileName = Constant.total_url[pos];
		        outputFile = new File(file, fileName);
				if(outputFile.exists()) {
					
					NewsItem temp = results.get(val);
					temp.setImageId(R.drawable.number_but1);					
					results.set(val, temp);	
					
					
					music_layout.setVisibility(View.VISIBLE);
					layout_text.setVisibility(View.GONE);
					
//					NewsItem preData = (NewsItem)list_view.getItemAtPosition(pos);
			        if(mediaPlayer == null) {
			        	
			        	mediaPlayer = MediaPlayer.create(PlayActivity.this, Uri.parse(outputFile.toString()));			        	
			        	
			        	finalTime = mediaPlayer.getDuration();
			        	seekBar = (SeekBar)findViewById(R.id.seekBar);
						seekBar.setMax((int) finalTime);
						seekBar.setClickable(false);
			        	
						mediaPlayer.start();
						
			        	play_but.setVisibility(View.GONE);
			    		pause_but.setVisibility(View.VISIBLE);
			    		
//			    		second = (TextView)view.findViewById(R.id.second);
			    		timeElapsed = mediaPlayer.getCurrentPosition();
			    		seekBar.setProgress((int) timeElapsed);
			    		durationHandler.postDelayed(updateSeekBarTime, 100);
			    		
			    		newsData.setVisible(false);
			        	newsData.setPauseVisible(true);
			        	newsData.setBgImageId(R.color.pressed_color);
			        	results.set(val, newsData);
			        }
			        listAdapter = new CustomListAdapter(PlayActivity.this, results);
					list_view.setAdapter(listAdapter);
					
					list_view.post(new Runnable() {
					    @Override
					    public void run() {
					    	list_view.smoothScrollToPosition(pos);
					    }
					});
				}
			} else {
				sharedpreferences.edit().putInt(ISDOWNLOADED, 0).commit();
			}
		}
	}
//	void fileDownload() {
//		try {
//    		URL url = new URL(dwnload_file_path);
//    		HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
//    		urlConnection.setRequestMethod("GET");
//    		urlConnection.setDoOutput(true);
//    		//connect
//    		urlConnection.connect();
//
//    		//set the path where we want to save the file    		
//    		File SDCardRoot = Environment.getExternalStorageDirectory(); 
//    		//create a new file, to save the downloaded file 
//    		File file = new File(SDCardRoot,"downloaded_file.png");
// 
//    		FileOutputStream fileOutput = new FileOutputStream(file);
//
//    		//Stream used for reading the data from the internet
//    		InputStream inputStream = urlConnection.getInputStream();
//
//    		//this is the total size of the file which we are downloading
//    		totalSize = urlConnection.getContentLength();
//
//    		runOnUiThread(new Runnable() {
//			    public void run() {
//			    	pb.setMax(totalSize);
//			    }			    
//			});
//    		
//    		//create a buffer...
//    		byte[] buffer = new byte[1024];
//    		int bufferLength = 0;
//
//    		while ( (bufferLength = inputStream.read(buffer)) > 0 ) {
//    			fileOutput.write(buffer, 0, bufferLength);
//    			downloadedSize += bufferLength;
//    			// update the progressbar //
//    			runOnUiThread(new Runnable() {
//    			    public void run() {
//    			    	pb.setProgress(downloadedSize);
//    			    	float per = ((float)downloadedSize/totalSize) * 100;
//    			    	cur_val.setText("Downloaded " + downloadedSize + "KB / " + totalSize + "KB (" + (int)per + "%)" );
//    			    }
//    			});
//    		}
//    		//close the output stream when complete //
//    		fileOutput.close();
//    		runOnUiThread(new Runnable() {
//			    public void run() {
//			    	dialog.dismiss();
////			    	 pb.dismiss(); // if you want close it..
//			    }
//			});    		
//    	
//    	} catch (final MalformedURLException e) {
//    		showError("Error : MalformedURLException " + e);  		
//    		e.printStackTrace();
//    	} catch (final IOException e) {
//    		showError("Error : IOException " + e);  		
//    		e.printStackTrace();
//    	}
//    	catch (final Exception e) {
//    		showError("Error : Please check your internet connection " + e);
//    	}    	
//	}
//	class downloadAsyncTask extends AsyncTask<Void, Void, String> {
//		
//		protected void onPreExecute() {
//		}
//		@Override
//		protected String doInBackground(Void... params) {
//			try {
//				URL url = new URL(dwnload_file_path);
//				HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
//				urlConnection.setRequestMethod("GET");
//	    		urlConnection.setDoOutput(true);
//	    		//connect
//	    		urlConnection.connect();
//	
//	    		//set the path where we want to save the file    		
//	    		File SDCardRoot = Environment.getExternalStorageDirectory(); 
//	    		//create a new file, to save the downloaded file 
//	    		File file = new File(SDCardRoot,"downloaded_file.png");
//	 
//	    		FileOutputStream fileOutput = new FileOutputStream(file);
//	
//	    		//Stream used for reading the data from the internet
//	    		InputStream inputStream = urlConnection.getInputStream();
//	
//	    		//this is the total size of the file which we are downloading
//	    		final int totalSize = urlConnection.getContentLength();
//	
//	    		runOnUiThread(new Runnable() {
//				    public void run() {
//				    	pb.setMax(totalSize);
//				    }			    
//				});
//	    		
//	    		//create a buffer...
//	    		byte[] buffer = new byte[1024];
//	    		int bufferLength = 0;
//	
//	    		while ( (bufferLength = inputStream.read(buffer)) > 0 ) {
//	    			fileOutput.write(buffer, 0, bufferLength);
//	    			downloadedSize += bufferLength;
//	    			// update the progressbar //
//	    			runOnUiThread(new Runnable() {
//	    			    public void run() {
//	    			    	pb.setProgress(downloadedSize);
//	    			    	float per = ((float)downloadedSize/totalSize) * 100;
//	    			    	cur_val.setText("Downloaded " + downloadedSize + "KB / " + totalSize + "KB (" + (int)per + "%)" );
//	    			    }
//	    			});
//	    		}
//	    		//close the output stream when complete //
//	    		fileOutput.close();
//	    		runOnUiThread(new Runnable() {
//				    public void run() {
//				    	// pb.dismiss(); // if you want close it..
//				    }
//				});    		
//	    	
//	    	} catch (final MalformedURLException e) {
//	    		showError("Error : MalformedURLException " + e);  		
//	    		e.printStackTrace();
//	    	} catch (final IOException e) {
//	    		showError("Error : IOException " + e);  		
//	    		e.printStackTrace();
//	    	}
//	    	catch (final Exception e) {
//	    		showError("Error : Please check your internet connection " + e);
//	    	}
////			try {
////
////				URL url = new URL(dwnload_file_path);
////				HttpURLConnection c = (HttpURLConnection) url.openConnection();
////				c.setRequestMethod("GET");
////				c.setDoOutput(true);
////				c.connect();
////				String[] path = url.getPath().split("/");
////				String mp3 = path[path.length-1];
////				int lengthOfFile = c.getContentLength();
////
////				String PATH = Environment.getExternalStorageDirectory()+ "/DownLoad/" ;
////				File file = new File(PATH);
////				file.mkdirs();
////
////				String fileName = mp3;
////
////				File outputFile = new File(file , fileName);
////				FileOutputStream fos = new FileOutputStream(outputFile);
////
////				InputStream is = c.getInputStream();
////
////				byte[] buffer = new byte[1024];
////				int len1 = 0;
////				while ((len1 = is.read(buffer)) != -1) {
////					fos.write(buffer, 0, len1);
////				}
////				fos.close();
////				is.close();
////
////			} catch (IOException e) {
////				e.printStackTrace();
////			}
//				return "done";
//			}
//			protected void onPostExecute(String result) {
//			}
//			
//	}
	void showError(final String err){
    	runOnUiThread(new Runnable() {
		    public void run() {
		    	Toast.makeText(PlayActivity.this, err, Toast.LENGTH_LONG).show();
		    }
		});
    }
	public void showProgress(String e_title, String i_title){
    	dialog = new Dialog(PlayActivity.this);
    	dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
    	dialog.setContentView(R.layout.myprogressdialog);
    	dialog.setTitle("Download Progress");    	
    	
    	TextView text = (TextView) dialog.findViewById(R.id.tv1);
    	text.setText(e_title+" "+i_title);
    	cur_val = (TextView) dialog.findViewById(R.id.cur_pg_tv);
    	cur_val1 = (TextView) dialog.findViewById(R.id.cur_pg_tv1);
    	
    	cur_val.setText("Download....دابه زاندن");
    	dialog.setCancelable(false);
    	dialog.show();
    	
    	pb = (ProgressBar)dialog.findViewById(R.id.progress_bar);
    	pb.setProgress(0);
    	pb.setProgressDrawable(getResources().getDrawable(R.drawable.green_progress));  
    	
    	Button stop_but = (Button)dialog.findViewById(R.id.ok_but);
    	stop_but.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				dialog.dismiss();
				mTask.cancel(true);
				String PATH = Environment.getExternalStorageDirectory() + "/download/";
	            File file = new File(PATH);
	            String file_name = Constant.total_url[pos];
				File outputFile = new File(file, file_name);
				if(outputFile.exists()) {
					outputFile.delete();
					pb.setProgress(0);
				}
			}
    		
    	});
    	
//    	LinearLayout bottom_bar = (LinearLayout)findViewById(R.id.layout_bottom);
//    	bottom_bar.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				cur_val.setText("Cancel Downloading");
//				pb.setProgress(0);
//				dialog.dismiss();
//				
//			}
//    		
//    	});
    }
//	public void allShowProgress(){
//		dialog = new Dialog(PlayActivity.this);
//    	dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//    	dialog.setContentView(R.layout.myprogressdialog);
//    	dialog.setTitle("Download Progress");
//
//    	text = (TextView) dialog.findViewById(R.id.tv1);
//    	
////    	for(int i=0; i < len; i++){
////    		mp = Constant.total_url[i];
////    		text.setText(mp);
////    	}
//    	
//    	cur_val = (TextView) dialog.findViewById(R.id.cur_pg_tv);
//    	cur_val.setText("Starting download...");
//    	dialog.setCancelable(false);
//    	dialog.show();
//    	
//    	pb = (ProgressBar)dialog.findViewById(R.id.progress_bar);
//    	pb.setProgress(0);
//    	pb.setProgressDrawable(getResources().getDrawable(R.drawable.green_progress));  
//	}
	public void play() {
		play_but.setVisibility(View.GONE);
		pause_but.setVisibility(View.VISIBLE);
	
		info_play_but = (Button)findViewById(R.id.play_but_info);
		info_pause_but = (Button)findViewById(R.id.media_pause_info);
		second = (TextView)findViewById(R.id.second);
//		info_play_but.setVisibility(View.GONE);
//		info_pause_but.setVisibility(View.VISIBLE);
		
		mediaPlayer.start();
		timeElapsed = mediaPlayer.getCurrentPosition();
		seekBar.setProgress((int) timeElapsed);
		durationHandler.postDelayed(updateSeekBarTime, 100);
		
		newsData.setVisible(false);
		newsData.setPauseVisible(true);
		results.set(pos, newsData);
		listAdapter = new CustomListAdapter(PlayActivity.this, results);
        list_view.setAdapter(listAdapter);
        //=============================================================================
        list_view.post(new Runnable() {
		    @Override
		    public void run() {
		    	list_view.smoothScrollToPosition(pos);
		    }
		});
        //=============================================================================
	}
	public boolean isPlaying(){
		AudioManager mAudioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
		if(mAudioManager.isMusicActive()) return true;
		else return false;
	}
	public void pause() {
		play_but.setVisibility(View.VISIBLE);
		pause_but.setVisibility(View.GONE);
		
		info_play_but = (Button)findViewById(R.id.play_but_info);
		info_pause_but = (Button)findViewById(R.id.media_pause_info);
//		second = (TextView)findViewById(R.id.second);
//		info_play_but.setVisibility(View.VISIBLE);
//		info_pause_but.setVisibility(View.GONE);
		
		newsData.setVisible(true);
		newsData.setPauseVisible(false);
		results.set(pos, newsData);
		listAdapter = new CustomListAdapter(PlayActivity.this, results);
        list_view.setAdapter(listAdapter);
        //===========================================================================
        list_view.post(new Runnable() {
		    @Override
		    public void run() {
		    	list_view.smoothScrollToPosition(pos);
		    }
		});
		//=============================================================================
		mediaPlayer.pause();
	}
	public void stop(){
		music_layout.setVisibility(View.GONE);
		layout_text.setVisibility(View.VISIBLE);
	
		info_play_but = (Button)findViewById(R.id.play_but_info);
		info_pause_but = (Button)findViewById(R.id.media_pause_info);
//		second = (TextView)findViewById(R.id.second);
//		info_play_but.setVisibility(View.GONE);
//		info_pause_but.setVisibility(View.GONE);
//		second.setText("");
		durationHandler.removeCallbacks(updateSeekBarTime);

		newsData.setVisible(false);
		newsData.setPauseVisible(false);
		newsData.setSecond("");
		newsData.setBgImageId(0);
		results.set(pos, newsData);
		listAdapter = new CustomListAdapter(PlayActivity.this, results);		
        list_view.setAdapter(listAdapter);
		
		mediaPlayer.stop();
		mediaPlayer = null;
	}
	public Runnable updateSeekBarTime = new Runnable() {
		public void run() {
			//get current position
			timeElapsed = mediaPlayer.getCurrentPosition();
			//set seekbar progress
			seekBar.setProgress((int) timeElapsed);
			//set time remaing
			double timeRemaining = finalTime - timeElapsed;
			
			
//			newsData.setSecond(String.format("%02d:%02d:%02d", hours, minutes, seconds));
	        newsData.setSecond(formatTime((long)timeRemaining));

			results.set(pos, newsData);
			listAdapter = new CustomListAdapter(PlayActivity.this, results);
			list_view.setAdapter(listAdapter);
			durationHandler.postDelayed(updateSeekBarTime, 100);
			
		}
	};
	

	public void infoShow(View view) {
		Intent i = new Intent(PlayActivity.this, InfoActivity.class);
		startActivity(i);
	}
	@Override
	public void onDestroy(){
		super.onDestroy();
	}
	@Override
	public void onStop(){
		super.onStop();
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == R.id.action_stop) {
			dialog.setCancelable(true);
			dialog.dismiss();
			mTask.cancel(true);
		}		
		if(item.getItemId() == R.id.action_cancel){
			
		}
		return super.onOptionsItemSelected(item);
	}
	public boolean isConnectedInternet() {
	    ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo netInfo = cm.getActiveNetworkInfo();
	    return netInfo != null && netInfo.isConnectedOrConnecting();
	}
	private String formatTime(long millis) {
        String output = "00:00:00";
        long seconds = millis / 1000;
        long minutes = seconds / 60;
        long hours = minutes / 60;

        seconds = seconds % 60;
        minutes = minutes % 60;
        hours = hours % 60;

        String secondsD = String.valueOf(seconds);
        String minutesD = String.valueOf(minutes);
        String hoursD = String.valueOf(hours);

        if (seconds < 10)
              secondsD = "0" + seconds;
        if (minutes < 10)
              minutesD = "0" + minutes;
        if (hours < 10)
              hoursD = "0" + hours;

        output = hoursD + " : " + minutesD + " : " + secondsD;
        return output;
  }
	@Override
	public boolean onTouchEvent(MotionEvent event) {
	    int action = event.getActionMasked();
	 
	    switch (action) {	 
	        case MotionEvent.ACTION_DOWN:
	        	Toast.makeText(getApplicationContext(), "aaaaaaaaaaaaa", Toast.LENGTH_LONG).show();
//	        	mTask.cancel(true);
//	        	dialog.dismiss();
//	        	dialog.cancel();	        	
	    }	    
	    return super.onTouchEvent(event);
	}
//	@Override
//	public boolean dispatchTouchEvent(MotionEvent ev) {
//	    Rect dialogBounds = new Rect();
//	    getWindow().getDecorView().getHitRect(dialogBounds);
//
//	    if (!dialogBounds.contains((int) ev.getX(), (int) ev.getY())) {
//	        // Tapped outside so we finish the activity
//	        this.finish();
//	    }
//	    return super.dispatchTouchEvent(ev);
//	}
	
}
