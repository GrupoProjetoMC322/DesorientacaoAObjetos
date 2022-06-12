package pt.projeto.batalhadereinos.view;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;

import pt.projeto.batalhadereinos.BatalhaDeReinos;

public class GameScreen implements Screen{
    final BatalhaDeReinos game;

	OrthographicCamera camera;
    
    public GameScreen(final BatalhaDeReinos game){
        this.game = game;

        camera = new OrthographicCamera();
		camera.setToOrtho(false, 1440, 1024);
    }

    @Override
	public void render(float delta){
        ScreenUtils.clear(135f / 255f, 195f / 255f, 107f / 255f, 1);
        //ScreenUtils.clear(0.257f, 0.761f, 0.417f, 1);

        camera.update();

        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.font.draw(game.batch, "Welcome to Drop!!! ", 100, 150);
		game.font.draw(game.batch, "Tap anywhere to begin!", 100, 100);
		game.batch.end();
    }

    @Override
	public void hide(){}

    @Override
	public void pause(){}

    @Override
	public void show(){}

    @Override
	public void dispose(){}

    @Override
	public void resume(){}

    @Override
	public void resize(int width, int height){}
}
