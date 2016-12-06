package com.mygdx.game;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Screens.MainMenu;
import com.mygdx.game.Screens.PlayScreen;

public class Tak extends Game
{
	public OrthographicCamera camera;
	public SpriteBatch batch;

	//private MainMenu mainMenu;
	private PlayScreen playScreen;
	private MainMenu mainMenu;

	private Tak obj;

	public Tak(){obj = this;}


	@Override
	public void create()
	{
		Music music = Gdx.audio.newMusic(Gdx.files.internal("Nova.wav"));
		music.play();

		camera = new OrthographicCamera();
		camera.setToOrtho(false, StaticVariables.V_WIDTH ,StaticVariables.V_HEIGHT);
		batch = new SpriteBatch();


		//mainMenu = new com.mygdx.game.Old.MainMenu(this);
		//setScreen(mainMenu);
		//playScreen = new PlayScreen(this);
		//setScreen(playScreen);
		obj = this;
		mainMenu = new MainMenu(obj);
		setScreen(mainMenu);
	}

	public Tak getTak()
	{
		return obj;
	}
}

