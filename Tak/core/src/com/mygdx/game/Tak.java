package com.mygdx.game;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Old.MainMenu;
import com.mygdx.game.Screens.PlayScreen;

public class Tak extends Game
{
	public OrthographicCamera camera;
	public SpriteBatch batch;

	//private MainMenu mainMenu;
	private PlayScreen playScreen;

	public Tak(){}

	@Override
	public void create()
	{
		camera = new OrthographicCamera();
		camera.setToOrtho(false, StaticVariables.V_WIDTH ,StaticVariables.V_HEIGHT);
		batch = new SpriteBatch();

		//mainMenu = new com.mygdx.game.Old.MainMenu(this);
		//setScreen(mainMenu);
		playScreen = new PlayScreen(this);
		setScreen(playScreen);
	}
}

