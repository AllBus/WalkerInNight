package com.kos.walkerinnight.shaders;

import com.kos.walkerinnight.NTTochk.Shader;
import com.kos.walkerinnight.graphics.Pen;

/**
 * Created by Kos on 18.02.2017.
 */

public class FlashShader extends Shader {

	private int aColor;
	private int aMVP;
	private int aTime;
	private int aMovePos;


	public FlashShader(String vertexShaderCode, String fragmentShaderCode) {
		super(vertexShaderCode, fragmentShaderCode);
	}

	@Override
	public void ready(){
		super.ready();
		aColor = getAttrHandle("vColor");
		aMVP = getAttrHandle("uMVPMatrix");
		aTime = getAttrHandle("time");
		aMovePos = getAttrHandle("vMovePos");
	}

	public void setup(float[] mHexMatrix, Pen pen, float time,float[] movePos) {
		setMatrix(aMVP, mHexMatrix);
		setColor(aColor, pen.Color.c, 0);
		SetFloat(aTime, time);
		setColor(aMovePos, movePos, 0);
	}

	public void setup(float[] mHexMatrix, float[] color, float time,float[] movePos) {
		setMatrix(aMVP, mHexMatrix);
		setColor(aColor, color, 0);
		SetFloat(aTime, time);
		setColor(aMovePos, movePos, 0);
	}
}
