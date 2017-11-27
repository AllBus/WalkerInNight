/*
 * Copyright (C) 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.kos.walkerinnight;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.os.SystemClock;
import android.util.Log;


import com.kos.walkerinnight.NTTochk.PointF;
import com.kos.walkerinnight.NTTochk.TGLUtils;
import com.kos.walkerinnight.NTTochk.TTexture;
import com.kos.walkerinnight.NTTochk.meshs.MeshConstructor;
import com.kos.walkerinnight.NTTochk.meshs.TArrayGLButtons;
import com.kos.walkerinnight.NTTochk.meshs.THexagon;
import com.kos.walkerinnight.NTTochk.meshs.TMeshRectangle;
import com.kos.walkerinnight.NTTochk.meshs.TMeshWithoutNormal;
import com.kos.walkerinnight.graphics.Pen;
import com.kos.walkerinnight.graphics.TColor;
import com.kos.walkerinnight.shaders.BackgroundShader;
import com.kos.walkerinnight.shaders.DomikShader;
import com.kos.walkerinnight.shaders.FlashShader;
import com.kos.walkerinnight.shaders.SnowmanShader;
import com.kos.walkerinnight.shaders.TargetShader;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class MyGLRenderer implements GLSurfaceView.Renderer, ISnegowikBoardListener {

	private static final String TAG = "MyGLRenderer";

	public Game mBoard;
	private TTexture mTex;
	private TTexture mTowerTex;
	private TTexture mTargetTex;
	private TTexture mSnowTex;
	private TTexture mSnowmansTex;
	private THexagon mHexagon;

	private TMeshWithoutNormal mAreaMesh;
	private TMeshWithoutNormal mFlashMesh;

	private TMeshRectangle mTarget;
	private TMeshRectangle mBackground;
	private TMeshRectangle mSnowman;


	// Declare as volatile because we are updating it from another thread
	private volatile float mmAngle = 0;
	private BackgroundShader mHexShader;//Шейдер клетки
	private BackgroundShader mBasicShader;//Шейдер выбора
	private SnowmanShader mSnowmanShader;//Шейдер снеговиков
	private DomikShader mDomikShader;//Шейдер снеговиков
	private TargetShader mTargetShader;//Шейдер цели
	private TargetShader mSelectShader;//Шейдер выбранного домика
	private TargetShader mExampleShader;
	private FlashShader mFlashShader;


	private volatile boolean bRotateAnimate = false;
	private float[] manRotationMatrix = new float[16];

	private float snowframewidth = 1.0f / 30.0f;
	private float snowframeheight = 60.0f / (1024.0f);


	public Context context;
	private float[] selectColor = {1.0f, 1.0f, 0.0f, 0.8f};
	private float[] backColor = {1.0f, 1.0f, 1.0f, 1.0f};

	private final float[] mMVPMatrix = new float[16];
	private final float[] mProjMatrix = new float[16];
	private final float[] mVMatrix = new float[16];

	private final float[] mHexMatrix = new float[16];
	private final float[] mHex2Matrix = new float[16];
	private float[] tekColor = {0, 0, 0, 1.0f}; //Цвет которым рисуем объекты для выбора

	//цвета которыми рисуем клетки
	public TArrayGLButtons mButtons;
	private float radius = 0.3f;
	private long lastTime=0;


	private ByteBuffer PixelBuffer;

	private SelectState selectState=new SelectState();


	public MyGLRenderer(Context context, Game mBoard2) {
		this.context = context;

		mBoard = mBoard2;
		mBoard.setBoardListener(this);
		PixelBuffer = ByteBuffer.allocateDirect(4);
		PixelBuffer.order(ByteOrder.nativeOrder());
		lastTime = SystemClock.uptimeMillis();



	}



	@Override
	public void onSurfaceChanged(GL10 unused, int width, int height) {
		// Adjust the viewport based on geometry changes,
		// such as screen rotation
		GLES20.glViewport(0, 0, width, height);

		selectState.width = width;
		selectState.height = height;

		float left = -1.0f;
		float right = 1.0f;
		float bottom = -1.0f;
		float top = 1.0f;
		float ratio;

		if (width > height) {
			ratio = (float) width / height;
			left *= ratio;
			right *= ratio;
		} else {
			ratio = (float) height / width;
			bottom *= ratio;
			top *= ratio;
		}

		// this projection matrix is applied to object coordinates
		// in the onDrawFrame() method
		Matrix.orthoM(mProjMatrix, 0, left,right,bottom,top, 1, 7);

		mFlashMesh=new TMeshRectangle(left,top,right,bottom,-1,1,1,-1);
		ReadyObjects();
	}

	@Override
	public void onSurfaceCreated(GL10 unused, EGLConfig config) {

		// Set the background frame color
		GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

		initTextures();
		initMeshs();

		initShaders();
	 /*   int mFramebufferWidth=width;
        int mFramebufferHeight=height;
        mTargetTexture = createTargetTexture( mFramebufferWidth, mFramebufferHeight);
        mFramebuffer = createFrameBuffer(mFramebufferWidth, mFramebufferHeight, mTargetTexture);*/
		mBoard.setBoardListener(this);
		mmAngle = 0;
		constructButtons();

		selectState.init();


	}

	private void initTextures() {
		mTex = new TTexture(context, R.drawable.bumpy_bricks_public_domain);
		mTowerTex = new TTexture(context, R.drawable.all);
		mTargetTex = new TTexture(context, R.drawable.target);
		mSnowTex = new TTexture(context, R.drawable.snow256);
		mSnowmansTex = new TTexture(context, R.drawable.snowmans);
	}

	private void initShaders() {
		String vertexShaderCode ; //TGLUtils.LoadTextShaderFromFile(this.context, R.raw.basic_vertext);//"shaders/light.slv");
		String fragmentShaderCode ;//TGLUtils.LoadTextShaderFromFile(this.context, R.raw.basicf);//"shaders/light.slf");

		vertexShaderCode = TGLUtils.LoadTextShaderFromFile(this.context, R.raw.basic_vertex);//  "basic_vertext_vertext.glsl");
		fragmentShaderCode = TGLUtils.LoadTextShaderFromFile(this.context, R.raw.basicf);//,"basic_vertext.slf");
		mBasicShader = new BackgroundShader(
				vertexShaderCode, fragmentShaderCode);
		vertexShaderCode = TGLUtils.LoadTextShaderFromFile(this.context, R.raw.basictexvertex);//, "basic_vertext.glsltext.slv");
		fragmentShaderCode = TGLUtils.LoadTextShaderFromFile(this.context, R.raw.per_pixel_fragment_shader_tex_and_light);//,"basic_vertext.slf");
		mHexShader = new BackgroundShader(vertexShaderCode, fragmentShaderCode);
		mDomikShader = new DomikShader(vertexShaderCode, fragmentShaderCode);

		vertexShaderCode = TGLUtils.LoadTextShaderFromFile(this.context, R.raw.basictexvertex);//, "basic_vertext_vertext.glsl");
		fragmentShaderCode = TGLUtils.LoadTextShaderFromFile(this.context, R.raw.feagment_snowmans);//,"basic_vertext.slf");
		mSnowmanShader = new SnowmanShader(vertexShaderCode, fragmentShaderCode);

		mTargetShader = new TargetShader(
				TGLUtils.LoadTextShaderFromFile(this.context, R.raw.target_vertex),
				TGLUtils.LoadTextShaderFromFile(this.context, R.raw.target_fragment));

		mSelectShader = new TargetShader(
				TGLUtils.LoadTextShaderFromFile(this.context, R.raw.select_vertex),
				TGLUtils.LoadTextShaderFromFile(this.context, R.raw.select_fragment));

		mExampleShader = new TargetShader(
				TGLUtils.LoadTextShaderFromFile(this.context, R.raw.snowman_vertex),
				TGLUtils.LoadTextShaderFromFile(this.context, R.raw.snowman_fragment));

		mFlashShader = new FlashShader(
				TGLUtils.LoadTextShaderFromFile(this.context, R.raw.flash_vertex),
				TGLUtils.LoadTextShaderFromFile(this.context, R.raw.flash_fragment));


		mHexShader.ready();
		mDomikShader.ready();
		mSnowmanShader.ready();
		mBasicShader.ready();
		mTargetShader.ready();
		mSelectShader.ready();
		mExampleShader.ready();
		mFlashShader.ready();
	}

	private void initMeshs() {
		mHexagon = new THexagon(radius);
		mAreaMesh = MeshConstructor.circle(radius - 0.03f, 12);
		mTarget = MeshConstructor.simpleRect(0.4f);
		mBackground =
			new TMeshRectangle(-3f, 3f, 3f, -3f, 0, 0, 10, 10);

		float sr = 0.2f;
		mSnowman = new TMeshRectangle(-sr * 1.5f * 34 / 60, sr, sr * 1.5f * 34 / 60, -sr, 0, 0, snowframewidth, snowframeheight);

	}


	private void constructButtons() {
		mButtons = new TArrayGLButtons(2);
		mButtons.setTexSize(4, 4, 0.1f, 0.1f);
		mButtons.AddButton(-1, 0, -1.5f, 0.2f, 1);
		mButtons.AddButton(-1, 0.4f, -1.5f, 0.6f, 2);
		mButtons.Ready();
	}


	@Override
	public void onDrawFrame(GL10 unused) {

    /*	GLES20.glBindFramebuffer(GLES20.GL_FRAMEBUFFER, mFramebuffer);
    	this.RenderSelectMode();
    	GLES20.glBindFramebuffer(GLES20.GL_FRAMEBUFFER, 0);
*/
		long time = SystemClock.uptimeMillis();


		lastTime = time;
		// Draw background color
		GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);

		// Set the camera position (View matrix)
		Matrix.setLookAtM(mVMatrix, 0, 0, 0, -3, 0f, 0f, 1f, 0f, 1.0f, 0.0f);

		// Calculate the projection and view transformation
		Matrix.multiplyMM(mMVPMatrix, 0, mProjMatrix, 0, mVMatrix, 0);

		Matrix.setRotateM(manRotationMatrix, 0, mmAngle, 0, 0, -1.0f);

		// Combine the rotation matrix with the projection and camera view
		Matrix.multiplyMM(mMVPMatrix, 0, mMVPMatrix, 0,manRotationMatrix , 0);

		if (selectState.changepix)// Рисование для выбора объектов
		{
			// GLES20.glBindFramebuffer(GLES20.GL_FRAMEBUFFER, mFramebuffer);
			DrawPoleSelectMode();
			DrawButtonsSelectMode();
			PixelBuffer.position(0);
			GLES20.glReadPixels(selectState.x, selectState.height - selectState.y, 1, 1, GLES20.GL_RGBA,
					GLES20.GL_UNSIGNED_BYTE, PixelBuffer);
			OnSelect();//Изменение выбранного объекта
			selectState.changepix = false;
			GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);
			// GLES20.glBindFramebuffer(GLES20.GL_FRAMEBUFFER, 0);
		}
		// Draw square
		//   mSquare.draw(mMVPMatrix);
		GLES20.glEnable(GLES20.GL_BLEND);
		//	GLES20.glEnable(GLES20.GL_DEPTH_TEST);
		GLES20.glBlendFunc(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA);
		DrawPole();
	//	DrawButtons();
		GLES20.glDisable(GLES20.GL_BLEND);
		// Create a rotation for the triangle
//      
//        float angle = 0.090f * ((int) time);
		//    Matrix.setRotateM(mRotationMatrix, 0, mAngle, 0, 0, -1.0f);

		// Combine the rotation matrix with the projection and camera view
		//  Matrix.multiplyMM(mMVPMatrix, 0, mRotationMatrix, 0, mMVPMatrix, 0);

		// Draw triangle
		// mTriangle.draw(mMVPMatrix);


		// mMesh.draw(unused,mMVPMatrix);

	}

	private void DrawButtons() {
		DrawButtonsSelectMode();

	}


	private void DrawButtonsSelectMode() {
		mBasicShader.useProgram();
		mButtons.useVertex(mBasicShader);
		//float[] mHexMatrix=new float[16];
		//float[] mHex2Matrix=new float[16];


		int cou = mButtons.Count();
		tekColor[0] = 0;
		tekColor[1] = 0.0f;
		tekColor[2] = 1.0f;
		tekColor[3] = 1.0f;

		for (int i = 0; i < cou; i++) {

			tekColor[0] = ((i)) / 128.0f;

			Matrix.setIdentityM(mHex2Matrix, 0);
			Matrix.translateM(mHex2Matrix,0, 0.2f, 0, -0.1f);
			Matrix.multiplyMM(mHexMatrix, 0, mMVPMatrix, 0, mHex2Matrix, 0);

			mBasicShader.setup(mHexMatrix,tekColor,1.0f);

			mButtons.draw(mBasicShader, i);
		}//end for


		mBasicShader.unlinkVertex();
	}


	/**
	 * Изменение выбора объекта в зависимости от цвета
	 */
	private void OnSelect() {

		int s = PixelBuffer.get() / 2 + PixelBuffer.get() / 2 * 100;
		int r = PixelBuffer.get();
		PixelBuffer.position(0);
//Log.d("Kos","Select "+s+" : "+r);
		if (r < -0) {
			if (s == 0) {
				mBoard.newGame();
				//		Log.d("Kos","New Game");
			}
		} else {
			//Log.d("Kos","Select Domik" +lastSelectType);
			switch (selectState.getSelectType()) {
				case Down:
					mBoard.DisplayMouseDown(s - 1);
					break;
				case Up:
					mBoard.DisplayMouseUp(false);
					break;
				case Move:
					mBoard.DisplayMouseMove(s - 1);
					break;
				default:
					break;
			}
		}


		//	Log.d("Kos","Select "+width+" "+height+" ; "+x+" "+y+" - "+s);
	/*	for (int i=0;i<50;i++)
		{
			Log.d("Kos","s "+selectcolor.get(i*i));
		}*/
		selectState.isSelect = true;

	}

	/**
	 * Рисование игровой доски с шашками
	 */
	private void DrawPole() {
		if (!mBoard.FConstruct)
			return;
		mBoard.tick();

		DrawBackground();
		mBoard.draw(this);

	}

	private void DrawBackground() {

		mHexShader.useProgram();
		mHexShader.linkTexture(mSnowTex, GLES20.GL_TEXTURE2, 2, "u_Texture");
		mBackground.useTexVertex(mHexShader);

		Matrix.setIdentityM(mHex2Matrix, 0);
		Matrix.translateM(mHex2Matrix,0,0, 0, 1f);
		///	Matrix.scaleM(mHex2Matrix, 0, 0.002f, 0.002f, 0.002f);
		Matrix.multiplyMM(mHexMatrix, 0,mMVPMatrix , 0, mHex2Matrix, 0);

		mHexShader.setup(mHexMatrix, backColor,1.0f);

		//mBasicShader.setColor("vColor",mBoard.colors,offset);
		//	mBasicShader.setMatrix("uMVPMatrix",mHexMatrix);
		mBackground.draw(mHexShader);

	}





	/**
	 * Utility method for debugging OpenGL calls. Provide the name of the call
	 * just after making it:
	 * <p>
	 * <pre>
	 * mColorHandle = GLES20.glGetUniformLocation(mProgram, "vColor");
	 * MyGLRenderer.checkGlError("glGetUniformLocation");</pre>
	 * <p>
	 * If the operation is not successful, the check throws an error.
	 *
	 * @param glOperation - Name of the OpenGL call to check.
	 */
	public static void checkGlError(String glOperation) {
		int error;
		while ((error = GLES20.glGetError()) != GLES20.GL_NO_ERROR) {
			Log.e(TAG, glOperation + ": glError " + error);
			throw new RuntimeException(glOperation + ": glError " + error);
		}
	}

	/**
	 * Выполняется при выключении экрана
	 */
	public void stop() {


	}

	/**
	 * Выполняется при вкдючении экрана
	*/
	public void start() {


	}


	/**
	 * Вызов изменение пикселя
	 *
	 * @param x    координата экрана x
	 * @param y    координата экрана y
	 */
	public void select(int x, int y, ESelectType selectType) {
		selectState.select(x,y,selectType);

	}

	/**
	 * Рисование игровой доски для выбора
	 */
	private void DrawPoleSelectMode() {
		if (mBoard.FConstruct == false)
			return;
		mBoard.drawSelectMode(this);

	}


	@Override
	public void OnChangePlayer() {


	}


	@Override
	public long getTime() {

		return SystemClock.uptimeMillis();
	}


	public void DrawString(String text, TColor brush, float posX, float posY) {
		// TODO Auto-generated method stub

	}


	public void DrawString(String text, TColor brush, PointF position) {
		DrawString(text, brush, position.X, position.Y);
	}


	public void DrawLine(Pen pen, PointF start, PointF end) {
		// TODO Auto-generated method stub

	}


	public void DrawSnowman(int frameIndex, int typeIndex, PointF position, Pen pen) {
		mSnowmanShader.useProgram();

		computeMatrix(mHexMatrix,position);

		mSnowman.useTexVertex(mSnowmanShader);
		mSnowmanShader.setup(mHexMatrix,snowframewidth,snowframeheight,pen,frameIndex,typeIndex);

		mSnowman.draw(mSnowmanShader);
	}

	private void computeMatrix(float[] outMatrix, PointF position) {
		Matrix.setIdentityM(mHex2Matrix, 0);
		Matrix.translateM(mHex2Matrix,0, position.X /200 - 1f, position.Y / 200 - 1f, position.Y / 2000 - 1f);

		Matrix.multiplyMM(outMatrix, 0,mMVPMatrix , 0, mHex2Matrix, 0);
	}


	public void ReadyWay() {
		// TODO Auto-generated method stub

	}



	public void ReadyString() {
		// TODO Auto-generated method stub

	}



	public void ReadySelect(TMeshWithoutNormal mesh) {
		mBasicShader.useProgram();
		mesh.useTexVertex(mBasicShader);
;
	}

	public void ReadySelectDomik() {
		ReadySelect(mHexagon);

	}


	public void SetSelectColor(int index, int indexB, int indexC) {
		tekColor[0] = ((index)) / 128.0f;
		tekColor[1] = ((indexB)) / 128.0f;
		tekColor[2] = ((indexC)) / 128.0f;

	}


	public void ReadyArea() {
		mSelectShader.useProgram();
		//mBasicShader.linkTexture(mTex, GLES20.GL_TEXTURE0,"u_Texture");
		mAreaMesh.useTexVertex(mSelectShader);

	}



	public void ReadyTarget() {
		mTargetShader.useProgram();
		mTargetShader.linkTexture(mTargetTex, GLES20.GL_TEXTURE0, 0, "u_Texture");

		mTarget.useTexVertex(mTargetShader);

	}


	public void ReadyObjects() {
		mDomikShader.useProgram();
		mDomikShader.linkTexture(mTowerTex, GLES20.GL_TEXTURE5, 5, "u_Texture");
		mSnowmanShader.useProgram();
		mSnowmanShader.linkTexture(mSnowmansTex, GLES20.GL_TEXTURE4, 4, "u_Texture");
		//mBasicShader.linkTexture(mTex, GLES20.GL_TEXTURE0,"u_Texture");
		//	mSnowman.useVertex(mSnowmanShader);
		//	mSnowman.useTex(mSnowmanShader);
	}


	public void DrawObject(TDrawObjects d, Pen pen,float time) {

		if (d.texIndex==1) {//flash
			drawFlash(d,pen,time);
			return;
		}
		if (d.texIndex == 5)//Domik
		{
			//todo:draw

		} else//Snowman
		{
			DrawSnowman(d.frameIndex, d.typeIndex, d.position, pen);
		}

	}

	private void drawFlash(TDrawObjects d, Pen pen,float time) {



		mFlashShader.useProgram();

		mFlashMesh.useTexVertex(mFlashShader);
		mFlashShader.setup(mMVPMatrix,pen,time,d.movePos);

		mFlashMesh.draw(mFlashShader);
	}

}
