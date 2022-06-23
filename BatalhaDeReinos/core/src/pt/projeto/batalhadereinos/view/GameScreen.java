package pt.projeto.batalhadereinos.view;

import org.w3c.dom.Text;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;

import pt.projeto.batalhadereinos.BatalhaDeReinos;
import pt.projeto.batalhadereinos.controller.PlayerController;
import pt.projeto.batalhadereinos.controller.TurnController;
import pt.projeto.batalhadereinos.model.Archer;
import pt.projeto.batalhadereinos.model.Board;
import pt.projeto.batalhadereinos.model.Mage;
import pt.projeto.batalhadereinos.model.Troop;

public class GameScreen implements Screen{
    final BatalhaDeReinos game;
    Board board;
	OrthographicCamera camera;
    Texture BackgroundImage;
    Texture SoldierButtonImage;
    Texture ArcherButtonImage;
    Texture KnightButtonImage;
    Texture RogueButtonImage;
    Texture BarrierButtonImage;
    Texture MageButtonImage;
    Troop mago;
    Troop arqueiro;
    Troop arqueiro2;

    TurnController turnController;
    PlayerController playerController;

    public GameScreen(final BatalhaDeReinos game){
        this.game = game;

        System.out.println("Carregou GameScreen");

        camera = new OrthographicCamera();
		camera.setToOrtho(false, 1440, 1024);

        board = new Board(); 
       
        turnController = new TurnController();
        playerController = new PlayerController(board);

        mago = new Mage(board,"Mage",0,0,1);
        arqueiro = new Archer(board, "Mage", 0, 9, 2);
        arqueiro2 = new Archer(board, "Mage", 1, 9, 2);
        board.addTroop(mago, 0, 0);
        turnController.subscribeTroop(mago);
        board.addTroop(arqueiro, 0, 9);
        turnController.subscribeTroop(arqueiro);
        board.addTroop(arqueiro2, 1, 9);
        turnController.subscribeTroop(arqueiro2);
    }

    @Override
	public void render(float delta){
        ScreenUtils.clear(135f / 255f, 195f / 255f, 107f / 255f, 1);
        //ScreenUtils.clear(0.257f, 0.761f, 0.417f, 1);
        BackgroundImage = new Texture(Gdx.files.internal("Background.png"));
        SoldierButtonImage = new Texture(Gdx.files.internal("SoldierButton.png"));
        ArcherButtonImage = new Texture(Gdx.files.internal("ArcherButton.png"));
        KnightButtonImage = new Texture(Gdx.files.internal("KnightButton.png"));
        RogueButtonImage = new Texture(Gdx.files.internal("RogueButton.png"));
        BarrierButtonImage = new Texture(Gdx.files.internal("BarrierButton.png"));
        MageButtonImage = new Texture(Gdx.files.internal("MageButton.png"));

        camera.update();

        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();

        game.batch.draw(BackgroundImage,0,0);
		game.batch.draw(SoldierButtonImage, 257, 95);
        game.batch.draw(ArcherButtonImage, 414, 95);
        game.batch.draw(KnightButtonImage, 571, 95);
        game.batch.draw(RogueButtonImage, 728, 95);
        game.batch.draw(BarrierButtonImage, 885, 95);
        game.batch.draw(MageButtonImage, 1042, 95);

        board.draw(game.batch);

        game.font.draw(game.batch, "Turno: "+turnController.getTurn(), 214, 761);
        game.font.draw(game.batch, playerController.getPlayerName(1), 38, 702+16);
        game.font.draw(game.batch, playerController.getPlayerName(2), 1260, 702+16);
        game.font.draw(game.batch, playerController.getCastleHealth(1) + "/ 30", 57, 395+16);
        game.font.draw(game.batch, playerController.getCastleHealth(2) + "/ 30", 1280, 395+16);

        if(Gdx.input.isKeyJustPressed(Keys.A)){
            turnController.passTurn();
        }       

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
