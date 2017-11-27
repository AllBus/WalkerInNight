package com.kos.walkerinnight.NTTochk.functions;

public class TFunctionXZPolynom extends IFunctionXZ
{
	public static int CX=10;
	public static int CZ=10;

	public float[] coefficientx = new float[CX];

	public float[] coefficientz = new float[CZ];

	public TFunctionXZPolynom()
	{
		for (int i = 0;i < CX;i++)
		{
			coefficientx[i] = 1.0f;
		}
		for (int i = 0;i < CZ;i++)
		{
			coefficientz[i] = 1.0f;
		}		
	}
	public void dispose()
	{
	}

	@Override
	public float Main(float x,float z) //Сама функция
	{

		float ret = 0;

		float xpow = 1;
		for (int i = 0;i < CX;i++)
		{
			ret += xpow * coefficientx[i];
			xpow *= x;
		}
		xpow = 1;
		for (int i = 0;i < CZ;i++)
		{
			ret += xpow * coefficientz[i];
			xpow *= z;
		}
		return ret;
	}


	@Override
	public float DifferentX(float x,float z) //Её дифференциал по X
	{

		float ret = 0;

		float xpow = 1;
		for (int i = 1;i < CX;i++)
		{
			ret += xpow * coefficientx[i] * (i);
			xpow *= x;
		}
		return ret;
	}

	@Override
	public float DifferentZ(float x,float z) //Её дифференциал по Z
	{

		float ret = 0;

		float xpow = 1;
		for (int i = 1;i < CZ;i++)
		{
			ret += xpow * coefficientz[i] * (i);
			xpow *= z;
		}
		return ret;
	}
}