package com.kos.walkerinnight.shaders;


import com.kos.walkerinnight.NTTochk.Shader;
import com.kos.walkerinnight.graphics.Pen;

/**
 * Created by Kos on 18.02.2017.
 */

public class BackgroundShader extends Shader {

	private int vvcolor;
	private int vvmat;
	private int vvnas;

	public BackgroundShader(String vertexShaderCode, String fragmentShaderCode) {
		super(vertexShaderCode, fragmentShaderCode);
	}

	@Override
	public void ready() {
		super.ready();
		vvnas = getAttrHandle("nas");
		vvcolor = getAttrHandle("vColor");
		vvmat = getAttrHandle("uMVPMatrix");
	}

	public void setup(float[] mHexMatrix, Pen pen, float nas) {
		setColor(vvcolor, pen.Color.c, 0);
		setMatrix(vvmat, mHexMatrix);
		SetFloat(vvnas, nas);
	}

	public void setup(float[] mHexMatrix, float[] color, float nas) {
		setColor(vvcolor, color, 0);
		setMatrix(vvmat, mHexMatrix);
		SetFloat(vvnas, nas);
	}
}
