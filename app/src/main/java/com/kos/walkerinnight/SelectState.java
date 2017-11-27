package com.kos.walkerinnight;

/**
 * Created by Kos on 18.02.2017.
 */

public class SelectState {
	public boolean isSelect;

	private ESelectType lastSelectType;
	public int x;
	public int y;
	public boolean changepix;//флаг того что надо выбрать объект
	public int height = 10;
	public int width = 10;

	public SelectState(){
		init();
	}
	/**
	 * Вызов изменения
	 *
	 * @param x    координата экрана x
	 * @param y    координата экрана y
	 */
	public void select(int x, int y, ESelectType selectType) {
		if (isSelect) {
			this.x = x;
			this.y = y;
			changepix = true;
			isSelect = false;
			lastSelectType = selectType;

		} else if (lastSelectType == ESelectType.Move && lastSelectType != selectType) {
			this.x = x;
			this.y = y;
			changepix = true;
			isSelect = false;
			lastSelectType = selectType;
		}
	}

	public void init() {
		x=0;
		y=0;
		lastSelectType = ESelectType.None;
		isSelect = true;
	}

	public ESelectType getSelectType() {
		return lastSelectType;
	}
}

