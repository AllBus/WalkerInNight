package com.kos.walkerinnight;

import android.os.Handler;
import android.os.Looper;
import android.provider.Telephony;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity implements Game.IGameListener {


	private MyGLSurfaceView mGLView;
	private Game mBoard;
	private ListView mainMenuList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ViewGroup container= (ViewGroup) findViewById(R.id.container);

		mainMenuList= (ListView) findViewById(R.id.mainMenuList);
		ArrayAdapter<String> adapter= new ArrayAdapter<>(this,
				R.layout.item_main_menu,android.R.id.text1,
				getResources().getStringArray(R.array.mainMenu)
		);
		mainMenuList.setAdapter(adapter);



		mBoard=new Game();
		mGLView = new MyGLSurfaceView(this, mBoard);
		container.addView(mGLView);

		mBoard.setGameListener(this);
		mBoard.logoGame();


		mainMenuList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				switch (position) {
					case 0://start
						mBoard.newGame();
						break;
					case 1://level
						mBoard.logoGame();
						break;
					case 2://settings
						break;
					case 3://exit
						finish();
						break;
				}
			}
		});
	}

	@Override
	protected void onResume() {
		super.onResume();
		mGLView.onResume();
	}

	@Override
	protected void onPause() {
		super.onPause();
		mGLView.onPause();
	}

	@Override
	public void OnChangeState(final int state) {

		new Handler(Looper.getMainLooper()).post(new Runnable() {
			@Override
			public void run() {
				findViewById(R.id.logo).setVisibility(state==Game.STATE_LOGO? View.VISIBLE: View.GONE);
				mainMenuList.setVisibility(state==Game.STATE_MENU? View.VISIBLE: View.GONE);
			}
		});


		switch (state) {
			case Game.STATE_LOGO:

				break;
			case Game.STATE_MENU:

				break;
			case Game.STATE_GAME:

				break;
		}
	}
}
