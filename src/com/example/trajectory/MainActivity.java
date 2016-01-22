package com.example.trajectory;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


public class MainActivity extends Activity {
	
	private EditText height;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		height = (EditText)findViewById(R.id.hei);
		   findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View v) {
	 
	              Intent intent = new Intent(MainActivity.this, WalkingActivity.class);
	              if(!String.valueOf(height.getText()).equals(""))
	            	  intent.putExtra("height", String.valueOf(height.getText()));
	              else
	            	  intent.putExtra("height", String.valueOf(182));
	              intent.putExtra("type", 1);
	              startActivity(intent);
	            }
	          });
		   findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View v) {
	 
	              Intent intent = new Intent(MainActivity.this, WalkingActivity.class);
	              if(!String.valueOf(height.getText()).equals(""))
	            	  intent.putExtra("height", String.valueOf(height.getText()));
	              else
	            	  intent.putExtra("height", String.valueOf(182));
	              intent.putExtra("type", 2);
	              startActivity(intent);
	            }
	          });
		
	}
}