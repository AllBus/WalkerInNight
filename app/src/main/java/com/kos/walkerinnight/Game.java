package com.kos.walkerinnight;

import com.kos.walkerinnight.graphics.Brushes;
import com.kos.walkerinnight.graphics.Pen;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kos on 26.02.2017.
 */

public class Game {

	public interface IGameListener{
		void OnChangeState(int state);
	}

	public static final int STATE_NONE = 0;
	public static final int STATE_LOGO = 1;
	public static final int STATE_GAME = 2;
	public static final int STATE_MENU = 3;

	private ISnegowikBoardListener boardListener = null;
	private IGameListener gameListener =null;

	private long tekTime; //Текущее время игры
	private long startTime;
	public boolean FConstruct=false;

	private Pen[] pens;

	private int state=STATE_NONE;

	private List<TDrawObjects> objects=new ArrayList<>();



	public Game(){
		pens = new Pen[6];
		for (int i=0;i<6;i++){
			pens[i]=new Pen( Brushes.GRAY);
		}
		pens[0].Color = Brushes.GRAY;
		pens[1].Color = Brushes.GREEN;
		pens[2].Color = Brushes.RED;
		pens[3].Color = Brushes.BLUE;
		pens[4].Color = Brushes.WHITE;
		pens[5].Color = Brushes.MAGENTA;



	}

	public void setBoardListener(ISnegowikBoardListener listener) {
		this.boardListener = listener;


	}

	public void setGameListener(IGameListener gameListener){
		this.gameListener=gameListener;
	}


	public void newGame() {
		StartBtn_Click();

	}

	public void tick() {
		this.tekTime = boardListener.getTime();


		switch (state){
			case STATE_LOGO:
				logoTick();

				break;
			case STATE_MENU:
				menuTick();
				break;
			case STATE_GAME:
				gameTick();
				break;
		}
	}
	private static final long LOGO_LENGTH=5000;

	private void logoTick() {

		if (gameTime() >= LOGO_LENGTH) {
			setState(STATE_MENU);
			state = STATE_MENU;

		}
	}
	private void menuTick() {

	}
	private void gameTick() {

	}


	private void StartBtn_Click() {
		ResetGame();

		//timer1.Enabled = true;
		tekTime = startTime = boardListener.getTime();
		FConstruct=true;
		setState(STATE_GAME);
	}

	private void ResetGame() {
		//todo:
	}

	public void DisplayMouseDown(int pos) {

	}

	public void DisplayMouseUp(boolean b) {

	}

	public void DisplayMouseMove(int pos) {

	}

	public void draw(MyGLRenderer g) {

		switch (state){
			case STATE_LOGO: {

			}
				break;
			case STATE_MENU: {

			}
				break;
			case STATE_GAME:{

			}
				break;
		}

		g.ReadyObjects();


		for (TDrawObjects d : objects) {
			long t = tekTime - d.startTime;
			if (t<d.endTime){
				float time = t*1.0f / d.endTime;
				g.DrawObject(d, pens[d.player], time);
			}else{
				d.needDelete=true;
			}
		}

		for (int i= objects.size()-1;i>=0;i--) {
			TDrawObjects d=objects.get(i);
			if (d.needDelete){
				objects.remove(i);
			}
		}
	}

	private long gameTime() {
		return tekTime-startTime;
	}

	public void drawSelectMode(MyGLRenderer g) {

	}

	public void logoGame() {
		tekTime = startTime = boardListener.getTime();
		FConstruct=true;
		setState(STATE_LOGO);
		putLogoFlash();
	}

	private void putLogoFlash() {
		TDrawObjects flashShadow;
		flashShadow=new TDrawObjects();
		flashShadow.player=0;
		flashShadow.texIndex=1;
		flashShadow.movePos=new float[4];
		flashShadow.movePos[0]=-0.5f;
		flashShadow.movePos[1]=-0.5f;
		flashShadow.movePos[2]=1.2f;
		flashShadow.movePos[3]=1.2f;
		flashShadow.startTime=tekTime;
		flashShadow.endTime=LOGO_LENGTH;
		objects.add(flashShadow);
	}

	public void setState(int state) {
		this.state = state;
		if (gameListener!=null)
			gameListener.OnChangeState(state);
	}
}
