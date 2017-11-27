package com.kos.walkerinnight.NTTochk.functions;

//C++ TO JAVA CONVERTER TODO TASK: There is no preprocessor in Java:
///#pragma warning(default:4244)

///#endif


import com.kos.walkerinnight.NTTochk.vector3d;

//Класс функции для построения поверхности
public abstract class IFunctionXZ
{

	public abstract float Main(float x,float z); //Сама функция


	public abstract float DifferentX(float x,float z); //Её дифференциал по X

	public abstract float DifferentZ(float x,float z); //Её дифференциал по Z

	public float Different(float x,float z) //Её дифференциал
	{
		return 0.0f;
	}

	public vector3d Normal(float x, float z) //Функция нормали
	{

		float alpha = (float) -Math.atan(DifferentX(x, z));

		float beta = (float) -Math.atan(DifferentZ(x, z));
		return new vector3d((float) Math.sin(alpha),(float) Math.sin(beta),(float)(Math.cos(alpha) * Math.cos(beta))).Normalize();
	}

	public void SetCoefficient(int index,float value)
	{
	}

	public float GetCoefficient(int index)
	{
		return 0;
	}
}