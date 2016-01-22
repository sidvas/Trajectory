package com.example.trajectory;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
public class WalkingActivity extends Activity implements SensorEventListener  {
	
	public SensorManager mSensor;
	//public SensorEventListener mSensorListener;
	private int count=0;
	long currTime, prevTime;
	double  Gx=0.0, Gy=0.0, Gz=0.0;
	int counter=0;
	private TextView mainText;
	private EditText aText;
	double distance=0.0;
	double alpha=0,initial=0.0,diff=0.0;
	int type=0,height=0;
	boolean flag=false;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_walking);
		findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View v) {
	              stop();
	            }
	          });
		Bundle extras = getIntent().getExtras();
		type=extras.getInt("type");
		height=Integer.valueOf(extras.getString("height"));
		String x="";
		if(type==1)
			x="Male";
		else if(type==2)
			x="Female";
		Toast.makeText(getApplicationContext(), x+ " of height " + String.valueOf(height)+ " cm", Toast.LENGTH_SHORT).show();
		mainText = (TextView)findViewById(R.id.textView1);
		aText = (EditText)findViewById(R.id.angle);
		currTime=System.currentTimeMillis();
		prevTime=System.currentTimeMillis();
		mSensor=(SensorManager) this.getSystemService(Context.SENSOR_SERVICE);
		//mSensorListener=new SensorEventListener();
		mSensor.registerListener(this, mSensor.getDefaultSensor(Sensor.TYPE_ORIENTATION), mSensor.SENSOR_DELAY_FASTEST);
		mSensor.registerListener(this, mSensor.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR), mSensor.SENSOR_DELAY_FASTEST);

		
	}
			@Override
			public void onSensorChanged(SensorEvent event) {
				// TODO Auto-generated method stub
				if(event.sensor.getType()==Sensor.TYPE_STEP_DETECTOR)
				{				
					counter++;
					if(type==1)
						alpha=0.415;
					if(type==2)
						alpha=0.413;
					distance=counter*alpha*height;
					mainText.setText(" " + String.format("%.3f", distance/1000)  + " metres");			
				}
				if(event.sensor.getType()==Sensor.TYPE_ORIENTATION)
				{
					if(flag==false)
					{
						initial=event.values[0];
						flag=true;
					}
					else
						diff=event.values[0]-initial;
					aText.setText("Rotation from start: " + String.format("%.3f", diff) + " degrees");
				}
				
			}

			@Override
			public void onAccuracyChanged(Sensor sensor, int accuracy) {
				// TODO Auto-generated method stub
		
			}
			private void stop(){
				mSensor.unregisterListener(this);
				Intent intent = new Intent(WalkingActivity.this, MainActivity.class);
				startActivity(intent);
				
			}
			
}
