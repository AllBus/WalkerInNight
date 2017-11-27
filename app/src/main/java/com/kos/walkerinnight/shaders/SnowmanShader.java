package com.kos.walkerinnight.shaders;


import com.kos.walkerinnight.NTTochk.Shader;
import com.kos.walkerinnight.graphics.Pen;

/**
 * Created by Kos on 18.02.2017.
 */

public class SnowmanShader extends Shader {

	private int vvsnowmancolor;
	private int vvsnowmanmat;
	private int vvsnowmanFrame;
	private int vvsnowmanType;
	private int vvsnowmanWidth;
	private int vvsnowmanHeight;

	public SnowmanShader(String vertexShaderCode, String fragmentShaderCode) {
		super(vertexShaderCode, fragmentShaderCode);
	}

	@Override
	public void ready(){
		super.ready();
		vvsnowmancolor = getAttrHandle("vColor");
		vvsnowmanmat = getAttrHandle("uMVPMatrix");
		vvsnowmanFrame = getAttrHandle("frame");
		vvsnowmanType = getAttrHandle("type");
		vvsnowmanWidth = getAttrHandle("texx");
		vvsnowmanHeight = getAttrHandle("texy");
	}

	public void setup(float[] mHexMatrix, float snowframewidth, float snowframeheight, Pen pen, int frameIndex, int typeIndex) {
		setMatrix(vvsnowmanmat, mHexMatrix);
		setColor(vvsnowmancolor, pen.Color.c, 0);
		SetFloat(vvsnowmanWidth, snowframewidth);
		SetFloat(vvsnowmanHeight, snowframeheight);
		SetFloat(vvsnowmanFrame, frameIndex);
		SetFloat(vvsnowmanType, typeIndex);
	}
}
