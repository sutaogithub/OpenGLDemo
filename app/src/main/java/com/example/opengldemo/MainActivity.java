package com.example.opengldemo;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

import com.example.opengldemo.AirHockeyRenender;

public class MainActivity extends Activity {

	private GLSurfaceView glSurfaceView;
	private boolean renderSet=false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		glSurfaceView=new GLSurfaceView(this);
		glSurfaceView.setEGLContextClientVersion(2);
		glSurfaceView.setRenderer(new AirHockeyRenender(this));
		renderSet=true;
		setContentView(glSurfaceView);
	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		if(renderSet)
			glSurfaceView.onPause();
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if(renderSet)
			glSurfaceView.onResume();
	}
}
