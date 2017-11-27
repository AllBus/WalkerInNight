package com.kos.walkerinnight.graphics;

public class Pen {

	public Pen(int color, float width) {
		Color=new TColor(color);
		Width=width;
		Brush=Color;
	}

	public Pen(TColor color) {
		Color=new TColor(color);
		Width=1;
		Brush=Color;
	}

	public TColor Color;
	public float Width;
	public TColor Brush;

}
