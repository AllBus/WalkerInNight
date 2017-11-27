package com.kos.walkerinnight.NTTochk;

public class TBound
{
	public int Top;
	public int Right;
	public int Left; //Лишняя добавлена для проверки
	public TBound()
	{
	}
	public TBound(int top,int right)
	{
		Top = top;
		Right = right;
		Left = 0;
	}

}