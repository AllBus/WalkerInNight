package com.kos.walkerinnight.NTTochk.meshs;

import android.opengl.GLES20;

import com.kos.walkerinnight.NTTochk.TGLUtils;
import com.kos.walkerinnight.NTTochk.vector3d;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;



public class TMesh {
	 private FloatBuffer mVertexBuffer;
     private FloatBuffer mColorBuffer;
     private ShortBuffer mIndexBuffer;
     private int vertexCount;
     public int texture;
   //  private int colorCount;=vertexCount
     private int faceCount;
     //==============================
     private final String vertexShaderCode =
    	        // This matrix member variable provides a hook to manipulate
    	        // the coordinates of the objects that use this vertex shader
    	        "uniform mat4 uMVPMatrix;" +

    	        "attribute vec4 vPosition;" +
    	        "void main() {" +
    	        // the matrix must be included as a modifier of gl_Position
    	        "  gl_Position = vPosition * uMVPMatrix;" +
    	        "}";

    	    private final String fragmentShaderCode =
    	        "precision mediump float;" +
    	        "uniform vec4 vColor;" +
    	        "void main() {" +
    	        "  gl_FragColor = vColor;" +
    	        "}";
     private final int mProgram;
     private int mPositionHandle;
     private int mColorHandle;
     private int mMVPMatrixHandle;
     static final int COORDS_PER_VERTEX = 3;
     private final int vertexStride = COORDS_PER_VERTEX * 4;
     float color[] = { 0.42f, 0.709803922f, 0.898039216f, 1.0f };

     //==========================
     public TMesh()
     {
    	  // prepare shaders and OpenGL program
         int vertexShader = TGLUtils.loadShader(GLES20.GL_VERTEX_SHADER,
                                                    vertexShaderCode);
         int fragmentShader = TGLUtils.loadShader(GLES20.GL_FRAGMENT_SHADER,
                                                      fragmentShaderCode);

         mProgram = GLES20.glCreateProgram();             // create empty OpenGL Program
         GLES20.glAttachShader(mProgram, vertexShader);   // add the vertex shader to program
         GLES20.glAttachShader(mProgram, fragmentShader); // add the fragment shader to program
         GLES20.glLinkProgram(mProgram);                  // create OpenGL program executables
    	 
     }
     
     public void draw(float[] mvpMatrix) {
         // Add program to OpenGL environment
    	// Log.d("My","Pered risovaniem");
         GLES20.glUseProgram(mProgram);

         // get handle to vertex shader's vPosition member
         mPositionHandle = GLES20.glGetAttribLocation(mProgram, "vPosition");

         // Enable a handle to the triangle vertices
         GLES20.glEnableVertexAttribArray(mPositionHandle);

         // Prepare the triangle coordinate data
         GLES20.glVertexAttribPointer(mPositionHandle, COORDS_PER_VERTEX,
                                      GLES20.GL_FLOAT, false,
                                      vertexStride, mVertexBuffer);

        /* // get handle to fragment shader's vColor member
         mColorHandle = GLES20.glGetUniformLocation(mProgram, "vColor");

         // Set color for drawing the triangle
         GLES20.glUniform4fv(mColorHandle, 1, color, 0);

         // get handle to shape's transformation matrix
         mMVPMatrixHandle = GLES20.glGetUniformLocation(mProgram, "uMVPMatrix");
         MyGLRenderer.checkGlError("glGetUniformLocation");

         // Apply the projection and view transformation
         GLES20.glUniformMatrix4fv(mMVPMatrixHandle, 1, false, mvpMatrix, 0);
         MyGLRenderer.checkGlError("glUniformMatrix4fv");*/

        // Log.d("My","Pered risovaniem elementov "+Integer.toString(vertexCount)+" "+Integer.toString(faceCount));
         // Draw the square
         GLES20.glDrawElements(GLES20.GL_TRIANGLES, faceCount*3,
                               GLES20.GL_UNSIGNED_SHORT, mIndexBuffer);
         //Log.d("My","Posle risovaniem elementov");
         // Disable vertex array
         GLES20.glDisableVertexAttribArray(mPositionHandle);
     }
     public void draw(GL10 gl, float[] mMVPMatrix) {
    	 //Log.d("My","Pered risovaniem");
         gl.glEnable(GL10.GL_CULL_FACE);
         gl.glFrontFace(GL10.GL_CW);
         gl.glShadeModel(GL10.GL_SMOOTH);
         gl.glVertexPointer(3, GL10.GL_FLOAT, 0, mVertexBuffer);
         gl.glColorPointer(4, GL10.GL_FLOAT, 0, mColorBuffer);
        // Log.d("My","Pered risovaniem elementov "+Integer.toString(vertexCount)+" "+Integer.toString(faceCount));
         gl.glPushMatrix();
      
         gl.glDrawElements(GL10.GL_TRIANGLES, faceCount*3,
                 GL10.GL_UNSIGNED_SHORT, mIndexBuffer);
         gl.glPopMatrix();
        // gl.glDrawElements(GL10.GL_TRIANGLES, faceCount*3, GL10.GL_UNSIGNED_BYTE, mIndexBuffer);
        // Log.d("My","Posle risovaniem elementov");
     }
     
	public void ConstructPoints(int pointcount) {
		vertexCount=pointcount;
		 ByteBuffer vbb = ByteBuffer.allocateDirect(vertexCount*3*4);//xyz*sizeof(float)
            vbb.order(ByteOrder.nativeOrder());
            mVertexBuffer = vbb.asFloatBuffer();
           // mVertexBuffer.put(vertices);
            mVertexBuffer.position(0);

            ByteBuffer cbb = ByteBuffer.allocateDirect(vertexCount*4*4);//rgba*sizeof(float)
            cbb.order(ByteOrder.nativeOrder());
            mColorBuffer = cbb.asFloatBuffer();
            //mColorBuffer.put(colors);
            mColorBuffer.position(0);

      
		
	}
	public void ConstructFace(int facecount) {
		
		faceCount=facecount;
		 ByteBuffer dlb = ByteBuffer.allocateDirect(
			        // (# of coordinate values * 2 bytes per short)
				  faceCount*3 * 2);
		dlb.order(ByteOrder.nativeOrder());
		mIndexBuffer = dlb.asShortBuffer();
		
		/*ByteBuffer cbb = ByteBuffer.allocateDirect(faceCount*3*2);//xyz*sizeof(short int)
		cbb.order(ByteOrder.nativeOrder());
		mIndexBuffer =cbb.asShortBuffer();*/
	//	mIndexBuffer =ByteBuffer.allocateDirect(faceCount*3);
     //   mIndexBuffer.put(indices);
        mIndexBuffer.position(0);
	}
	
	public void SetVertex(int index, float xpos, float ypos, float zpos,
						  vector3d normal, float u, float v) {
		// TODO Auto-generated method stub
		mVertexBuffer.position(index*3);
		mVertexBuffer.put(index*3,xpos);
		mVertexBuffer.put(index*3+1,ypos);
		mVertexBuffer.put(index*3+2,zpos);
		
		mColorBuffer.position(index*4);
		mColorBuffer.put(1.0f);
		mColorBuffer.put(0.0f);
		mColorBuffer.put(1.0f);
		mColorBuffer.put(1.0f);
		 mVertexBuffer.position(0);
		 mColorBuffer.position(0);
	}
	
	public void SetFace(int index, int i, int j, int k) {
		//Log.d("My", "index"+Integer.toString(index)+": "+Short.toString((short)i)+" "+Short.toString((short)j)+" "+Short.toString((short)k));
		//mIndexBuffer.position(index*3);
		mIndexBuffer.put(index*3,(short)i);
		mIndexBuffer.put(index*3+1,(short)j);
		mIndexBuffer.put(index*3+2,(short)k);
		mIndexBuffer.position(0);
	}
	public void SetTexture(int texture) {
		this.texture=texture;
	}
	public void SetVertex(int index, double xpos, double ypos, double zpos,
			vector3d normal, double u, double v) {
		SetVertex(index,(float)xpos,(float)ypos,(float)zpos,normal,(float)u,(float) v);
		
	}

	public TMeshWithoutNormal toMeshWithoutNormal(){
		TMeshWithoutNormal m=new TMeshWithoutNormal();
		m.init(mVertexBuffer.array(),mColorBuffer.array(),mIndexBuffer.array());
		return m;
	}
}
