package com.kos.walkerinnight;


import android.content.Context;
import android.graphics.PixelFormat;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;

import com.kos.walkerinnight.NTTochk.meshs.TMeshWithoutNormal;

import java.util.List;

class MyGLSurfaceView extends GLSurfaceView  {

	private final MyGLRenderer mRenderer;

	private final float TOUCH_SCALE_FACTOR = 180.0f / 320;
	private float mPreviousX;
	private float mPreviousY;

	public MyGLSurfaceView(Context context, Game mBoard) {
		super(context);
		getHolder().setFormat(PixelFormat.TRANSLUCENT);


		// Create an OpenGL ES 2.0 context.
		setEGLContextClientVersion(2);

		// Set the Renderer for drawing on the GLSurfaceView
		mRenderer = new MyGLRenderer(this.getContext(),  mBoard);
		setRenderer(mRenderer);

		// Render the view only when there is a change in the drawing data
		setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
	}






	public void onPause() {
		mRenderer.stop();
		super.onPause();
	}

	public void onResume() {
		mRenderer.start();
		super.onResume();
	}

	@Override
	public boolean onTouchEvent(MotionEvent e) {
		// MotionEvent reports input details from the touch screen
		// and other input controls. In this case, you are only
		// interested in events where the touch position changed.

		float x = e.getX();
		float y = e.getY();

		switch (e.getAction()) {
			case MotionEvent.ACTION_DOWN:
				mRenderer.select((int) x, (int) y, ESelectType.Down);
				break;
			case MotionEvent.ACTION_UP:
				mRenderer.select((int) x, (int) y, ESelectType.Up);
				break;
			case MotionEvent.ACTION_MOVE:
				mRenderer.select((int) x, (int) y, ESelectType.Move);
				break;
		}

		mPreviousX = x;
		mPreviousY = y;
		return true;
	}


}
