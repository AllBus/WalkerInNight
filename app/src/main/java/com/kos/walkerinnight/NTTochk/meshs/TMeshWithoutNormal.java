package com.kos.walkerinnight.NTTochk.meshs;

import com.kos.walkerinnight.NTTochk.Shader;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;



public class TMeshWithoutNormal {
	public FloatBuffer texBuffer;
	public FloatBuffer vertexBuffer;
	public ShortBuffer drawListBuffer;
	public int orderLength = 0;

	static final int POINT_PER_FACE = 3;
	static final int COORDS_PER_VERTEX = 3;
	private static final int vertexStride = COORDS_PER_VERTEX * 4;

	public void useVertex(Shader shader) {
		shader.linkVertex(COORDS_PER_VERTEX, vertexStride, vertexBuffer, "vPosition");
	}

	public void useTex(Shader shader) {
		shader.linkTexVertex(2, 2 * 4, texBuffer, "aTexCoord");
	}
	public void useTexVertex(Shader shader) {
		useVertex(shader);
		useTex(shader);
	}


	public void draw(Shader shader) {
		shader.draw(orderLength, drawListBuffer);
	}

	public TMeshWithoutNormal() {

	}

	public void init(float[] coords, float[] texCoords, short[] drawOrder) {
		ByteBuffer bb = ByteBuffer.allocateDirect(
				// (# of coordinate values * 4 bytes per float)
				coords.length * 4);
		bb.order(ByteOrder.nativeOrder());
		vertexBuffer = bb.asFloatBuffer();
		vertexBuffer.put(coords);
		vertexBuffer.position(0);

		// initialize byte buffer for the draw list
		ByteBuffer dlb = ByteBuffer.allocateDirect(
				// (# of coordinate values * 2 bytes per short)
				drawOrder.length * 2);
		dlb.order(ByteOrder.nativeOrder());
		drawListBuffer = dlb.asShortBuffer();
		drawListBuffer.put(drawOrder);
		drawListBuffer.position(0);

		ByteBuffer bbt = ByteBuffer.allocateDirect(
				// (# of coordinate values * 4 bytes per float)
				texCoords.length * 4);
		bbt.order(ByteOrder.nativeOrder());
		texBuffer = bbt.asFloatBuffer();
		texBuffer.put(texCoords);
		texBuffer.position(0);
		orderLength = drawOrder.length;
	}

	public void init(int length, int orderLength) {
		ByteBuffer bb = ByteBuffer.allocateDirect(length * COORDS_PER_VERTEX * 4);
				// (# of coordinate values * 4 bytes per float)

		bb.order(ByteOrder.nativeOrder());
		vertexBuffer = bb.asFloatBuffer();

		// initialize byte buffer for the draw list
		ByteBuffer dlb = ByteBuffer.allocateDirect(orderLength * POINT_PER_FACE*2);
				// (# of coordinate values * 2 bytes per short)

		dlb.order(ByteOrder.nativeOrder());
		drawListBuffer = dlb.asShortBuffer();

		ByteBuffer bbt = ByteBuffer.allocateDirect(length * 2 * 4);
				// (# of coordinate values * 4 bytes per float)

		bbt.order(ByteOrder.nativeOrder());
		texBuffer = bbt.asFloatBuffer();

		vertexBuffer.position(0);
		drawListBuffer.position(0);
		texBuffer.position(0);

		this.orderLength = orderLength*POINT_PER_FACE;
	}

	public void GridOrdered(int fButtonCount, int fDlinaOrdered,
							int fDlinaButton) {
		short drawOrder[] = new short[fButtonCount * fDlinaOrdered];

		for (int i = 0; i < fButtonCount; i++) {
			drawOrder[i * fDlinaOrdered + 0] = (short) (i * fDlinaButton + 0);
			drawOrder[i * fDlinaOrdered + 1] = (short) (i * fDlinaButton + 1);
			drawOrder[i * fDlinaOrdered + 2] = (short) (i * fDlinaButton + 2);

			drawOrder[i * fDlinaOrdered + 3] = (short) (i * fDlinaButton + 3);
			drawOrder[i * fDlinaOrdered + 4] = (short) (i * fDlinaButton + 2);
			drawOrder[i * fDlinaOrdered + 5] = (short) (i * fDlinaButton + 1);
		}
		drawListBuffer.put(drawOrder);
		drawListBuffer.position(0);
	}

	public void setVertex(int index, float xpos, float ypos, float zpos,
						  float u, float v) {
		vertexBuffer.position(index*3);
		vertexBuffer.put(xpos);
		vertexBuffer.put(ypos);
		vertexBuffer.put(zpos);
		texBuffer.position(index*2);
		texBuffer.put(u);
		texBuffer.put(v);
	}

	public void setVertex(int index, double xpos, double ypos, double zpos,
						  double u, double v) {
		vertexBuffer.position(index*3);
		vertexBuffer.put((float) xpos);
		vertexBuffer.put((float) ypos);
		vertexBuffer.put((float) zpos);
		texBuffer.position(index*2);
		texBuffer.put((float) u);
		texBuffer.put((float) v);
	}


	public void setFace(int index, int i, int j, int k) {
		drawListBuffer.position(index*3);
		drawListBuffer.put((short)i);
		drawListBuffer.put((short)j);
		drawListBuffer.put((short)k);

	}

	public void completeBuffer(){
		vertexBuffer.position(0);
		texBuffer.position(0);
		drawListBuffer.position(0);
	}




}