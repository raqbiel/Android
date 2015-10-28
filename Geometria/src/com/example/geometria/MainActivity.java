package com.example.geometria;

import android.os.Bundle;

import com.example.geometria.PoleProstokataActivity;
import com.example.geometria.MainActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener{

	
private	TextView t1, t2;
	Context context;


	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initControl();
		  // t1 = (TextView)findViewById(R.id.textView1);
		  // t2 = (TextView)findViewById(R.id.textView2);
	}
	private void initControl(){
		
		t1 = (TextView)findViewById(R.id.textView1);
		t2 = (TextView)findViewById(R.id.textView2);
		t1.setOnClickListener(this);
		t2.setOnClickListener(this);
		
		
	}
	
	
            public void onClick(View v) {
                switch(v.getId()){
                    case R.id.textView1:
                    	context = getApplicationContext();
        				Intent intent = new Intent(context, PoleKwadratuActivity.class);
                        startActivity(intent);
                    break;
                    case R.id.textView2:
                    	//context = getApplicationContext();
        				Intent intent2 = new Intent(this, PoleProstokataActivity.class);
                        startActivity(intent2);
                    break;
                }

            }
        };

        //button3.setOnClickListener(onClickListener);   
	
		/*t1 = (TextView) findViewById(R.id.textView1);
		OnClickListener myhandler1 = new OnClickListener() {
			
			@Override
			public void onClick(View v) {								
				//t1.setText("AŁA!!!");
				context = getApplicationContext();
				Intent intent = new Intent(context, PoleKwadratuActivity.class);
                startActivity(intent);
			}
		};
		t1.setOnClickListener(myhandler1);
		
		t2 = (TextView) findViewById(R.id.textView2);
		OnClickListener myhandler2 = new OnClickListener() {
				
			public void onClick(View v) {								
				//Intent intent = new Intent(MainActivity.this, PoleProstokata.class);
				context = getApplicationContext();
				Intent intent = new Intent(context, PoleProstokata.class);
                startActivity(intent);

			}
	};
	t2.setOnClickListener(myhandler2);
}*/



