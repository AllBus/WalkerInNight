package com.kos.walkerinnight.shaders;


import com.kos.walkerinnight.NTTochk.Shader;
import com.kos.walkerinnight.graphics.Pen;

/**
 * Created by Kos on 18.02.2017.
 */

public class DomikShader extends Shader {

	private int vvdomiknas;
	private int vvdomikcolor;
	private int vvdomikmat;

	public DomikShader(String vertexShaderCode, String fragmentShaderCode) {
		super(vertexShaderCode, fragmentShaderCode);
	}

	@Override
	public void ready() {
		super.ready();
		vvdomiknas = getAttrHandle("nas");
		vvdomikcolor = getAttrHandle("vColor");
		vvdomikmat = getAttrHandle("uMVPMatrix");
	}

	public void setup(float[] mHexMatrix, Pen pen, float nas) {
		setColor(vvdomikcolor, pen.Color.c, 0);
		setMatrix(vvdomikmat, mHexMatrix);
		SetFloat(vvdomiknas, nas);
	}
}
