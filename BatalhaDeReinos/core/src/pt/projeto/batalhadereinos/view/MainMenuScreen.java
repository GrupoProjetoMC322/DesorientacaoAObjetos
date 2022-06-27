package pt.projeto.batalhadereinos.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;

import pt.projeto.batalhadereinos.BatalhaDeReinos;
import pt.projeto.batalhadereinos.controller.IScreenMediator;

public class MainMenuScreen implements Screen{
    final BatalhaDeReinos game;
	private IScreenMediator screenMediator;
	OrthographicCamera camera;

	public MainMenuScreen(final BatalhaDeReinos game, IScreenMediator screenMediator) {
		this.game = game;

		this.screenMediator = screenMediator;

		System.out.println("Carregou MainMenuScreen");

		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1440, 1024);
	}

	@Override
	public void render(float delta) {
		ScreenUtils.clear(0, 0, 0.2f, 1);

		camera.update();

		Stage stage = new Stage();
		Gdx.input.setInputProcessor(stage);

		Texture myTexture = new Texture(Gdx.files.internal("botao.png"));

		TextureRegion myTextureRegion = new TextureRegion(myTexture);
		TextureRegionDrawable myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
		ImageButton myButton = new ImageButton(myTexRegionDrawable);
		myButton.setSize(100,100);
		myButton.moveBy(600,600);

		TextureRegion myTextureRegion2 = new TextureRegion(myTexture);
		TextureRegionDrawable myTexRegionDrawable2 = new TextureRegionDrawable(myTextureRegion2);
		ImageButton myButton2 = new ImageButton(myTexRegionDrawable2);
		myButton2.setSize(100,100);
		myButton2.moveBy(800,600);


		myButton.addListener(new InputListener() {
			@Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				System.out.println("Apertou o botão");
                screenMediator.changeScreen("NewDynamicGameScreen");
				Gdx.input.setInputProcessor(null);
				return true;
            }
		});

		myButton2.addListener(new InputListener() {
			@Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				System.out.println("Apertou o botão");
                screenMediator.changeScreen("NewTurnGameScreen");
				Gdx.input.setInputProcessor(null);
				return true;
            }
		});

        stage.addActor(myButton);
		stage.addActor(myButton2);


		game.batch.setProjectionMatrix(camera.combined);

		game.batch.begin();
		game.font.draw(game.batch, "Welcome to Drop!!! ", 100, 150);
		game.font.draw(game.batch, "Tap anywhere to begin!", 100, 100);
		myButton.draw(game.batch, 1);
		myButton2.draw(game.batch, 1);
		game.batch.end();
	}

	@Override
	public void pause(){}

	@Override
	public void show(){}

	@Override
	public void resume(){}

	@Override
	public void dispose(){}

	@Override
	public void hide(){}

	@Override
	public void resize(int width, int height){}
}
