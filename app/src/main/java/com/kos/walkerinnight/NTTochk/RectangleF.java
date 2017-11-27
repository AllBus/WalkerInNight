package com.kos.walkerinnight.NTTochk;

public class RectangleF
{
	public float Left;
	public float Top;
	public float width;
	public float height;
	public RectangleF()
	{

	}
	public RectangleF(float left,float top,float Width,float Height)
	{
		Left = left;
		Top = top;
		width = Width;
		height = Height;
	}

	public final float Width()
	{
		return width;
	}
	public final float Height()
	{
		return height;
	}
  /*
	int Width(int newwidth)
	{
		Right=Left+newwidth;
		return newwidth;
	}
	int Height(int newheight)
	{
		Bottom=Top+newheight;
		return newheight;
	}  */

	public final float SetX(float newdata)
	{
		return Left = newdata;
	}
//C++ TO JAVA CONVERTER WARNING: 'const' methods are not available in Java:
//ORIGINAL LINE: __property int getx() const
	public final float getx()
	{
		return Left;
	}


	public final float SetY(float newdata)
	{
		return Top = newdata;
	}
//C++ TO JAVA CONVERTER WARNING: 'const' methods are not available in Java:
//ORIGINAL LINE: __property float gety() const
	public final float gety()
	{
		return Top;
	}

	public final float SetRight(float newdata)
	{
		return width = newdata - Left;
	}
//C++ TO JAVA CONVERTER WARNING: 'const' methods are not available in Java:
//ORIGINAL LINE: __property float getRight() const
	public final float getRight()
	{
		return Left + width;
	}


	public final float SetBottom(float newdata)
	{
		return height = newdata - Top;
	}
//C++ TO JAVA CONVERTER WARNING: 'const' methods are not available in Java:
//ORIGINAL LINE: __property float getBottom() const
	public final float getBottom()
	{
		return height + Top;
	}


	public final String toString()
	{
		return "";//Left.toString() + ", " + Top.toString() + ", " + Right.toString() + ", " + Bottom.toString();
	}

	public RectangleF divide(float value)
	{
		return new RectangleF((float)(Left / value), (float)(Top / value), (float)(getRight() / value), (float)(getBottom() / value));
	}
	public RectangleF multiply(float value)
	{
		return new RectangleF((float)(Left * value), (float)(Top * value), (float)(getRight() * value), (float)(getBottom() * value));
	}
}