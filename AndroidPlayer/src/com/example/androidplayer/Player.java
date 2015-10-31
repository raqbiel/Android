package com.example.androidplayer;

import java.util.concurrent.TimeUnit;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class Player extends Activity {

	
	private ImageButton btnPlay, btnPause;
	private MediaPlayer mp;
	private double czasUplywajacy = 0, czasFinal = 0;
	private int forwardTime = 2000, backwardTime = 2000;
	private Handler durationHandler = new Handler();
	private SeekBar seekbar;
	private TextView nazwaPiosenki, czas;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_player);
		btnPlay = (ImageButton) findViewById(R.id.imageButton1);
		btnPause = (ImageButton) findViewById(R.id.imageButton2);
		btnPlay.setVisibility(View.VISIBLE);
        btnPlay.setClickable(true);
        btnPause.setVisibility(View.INVISIBLE);
        btnPause.setClickable(false);
	
		initializeViews();
	}
	
	
	public void initializeViews(){
		nazwaPiosenki = (TextView)findViewById(R.id.textView1);
		mp = MediaPlayer.create(this,R.raw.wychylybymy);
		czasFinal = mp.getDuration();
		czas = (TextView)findViewById(R.id.textView2);
		seekbar = (SeekBar)findViewById(R.id.seekBar1);
		nazwaPiosenki.setText("Piosenka");
		
		seekbar.setMax((int) czasFinal);
		seekbar.setClickable(false);
	}
	
	public void play(View v){
       btnPlay.setVisibility(View.INVISIBLE);
       btnPause.setVisibility(View.VISIBLE);
       btnPlay.setClickable(false);
       btnPause.setClickable(true);
		mp.start();
		czasUplywajacy = mp.getCurrentPosition();
		seekbar.setProgress((int) czasUplywajacy);
		durationHandler.postDelayed(updateSeekBarTime, 100); 

       // btnPause.setClickable(true);
       // btnPlay.setClickable(false);
		/* if (mp.isPlaying()) {
             mp.pause();
             btnPlay.setBackgroundResource(R.drawable.btnplay);
         } 
*/
            

     }
		
	
	private Runnable updateSeekBarTime = new Runnable(){
		public void run(){
			czasUplywajacy = mp.getCurrentPosition();
			seekbar.setProgress((int) czasUplywajacy);
			double pozostalyCzas = czasFinal - czasUplywajacy;
			czas.setText(String.format("%d min, %d sec",TimeUnit.MILLISECONDS.toMinutes((long) pozostalyCzas), TimeUnit.MILLISECONDS.toSeconds((long) pozostalyCzas) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) pozostalyCzas))));
			durationHandler.postDelayed(this, 100);
		}
	};
	
	public void pause(View v){
		btnPause.setVisibility(View.INVISIBLE);
		btnPlay.setVisibility(View.VISIBLE);
        btnPlay.setClickable(true);
        btnPause.setClickable(false);
		mp.pause();
       //  btnPlay.setBackgroundResource(R.drawable.btnplayst);
     
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.player, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
