package pt.projeto.batalhadereinos.view;

import org.w3c.dom.Text;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;

import pt.projeto.batalhadereinos.BatalhaDeReinos;
import pt.projeto.batalhadereinos.controller.GameFacade;
import pt.projeto.batalhadereinos.controller.IScreenMediator;
import pt.projeto.batalhadereinos.model.Board;
import pt.projeto.batalhadereinos.model.Troop;

public class GameScreen implements Screen{

    final BatalhaDeReinos game;
	OrthographicCamera camera;

    Texture BackgroundImage;
    Texture SoldierButtonImage;
    Texture ArcherButtonImage;
    Texture KnightButtonImage;
    Texture RogueButtonImage;
    Texture BarrierButtonImage;
    Texture MageButtonImage;
    Texture CoinImage;

    Board board;

    Troop mago;
    Troop arqueiro;
    Troop cavaleiro;

    //int firstTime = 0;
    Float time = 0f;

    IScreenMediator screenMediator;
    GameFacade gameFacade;

    public GameScreen(final BatalhaDeReinos game, GameFacade gameFacade, IScreenMediator screenMediator){
        this.game = game;

        this.gameFacade = gameFacade;
        this.screenMediator = screenMediator;

        board = new Board(); 

        gameFacade.setBoard(board);

        System.out.println("Carregou GameScreen");

        camera = new OrthographicCamera();
		camera.setToOrtho(false, 1440, 1024);
        

        BackgroundImage = new Texture(Gdx.files.internal("Background.png"));
        SoldierButtonImage = new Texture(Gdx.files.internal("SoldierButton.png"));
        ArcherButtonImage = new Texture(Gdx.files.internal("ArcherButton.png"));
        KnightButtonImage = new Texture(Gdx.files.internal("KnightButton.png"));
        RogueButtonImage = new Texture(Gdx.files.internal("RogueButton.png"));
        BarrierButtonImage = new Texture(Gdx.files.internal("BarrierButton.png"));
        MageButtonImage = new Texture(Gdx.files.internal("MageButton.png"));
        CoinImage = new Texture(Gdx.files.internal("Coin.png"));


        gameFacade.selectTroop("Rogue", 2);
        gameFacade.dynamicPlaceTroop(0, 9, 2);
    }

    @Override
	public void render(float delta){
        ScreenUtils.clear(135f / 255f, 195f / 255f, 107f / 255f, 1);
        //ScreenUtils.clear(0.257f, 0.761f, 0.417f, 1)

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
        game.batch.draw(CoinImage, 106, 344);
        game.batch.draw(CoinImage, 1331, 344);

        time += Gdx.graphics.getDeltaTime();
        
        
        game.font.draw(game.batch,  "Tempo: " +String.format("%.2f", time) , 1121, 761);

        game.font.draw(game.batch, "Turno: "+gameFacade.getTurn(), 214, 761);
        game.font.draw(game.batch, gameFacade.getPlayerName(1), 38, 702+16);
        game.font.draw(game.batch, gameFacade.getPlayerName(2), 1260, 702+16);
        game.font.draw(game.batch, gameFacade.getCastleHealth(1) + "/ 30", 57, 395+16);
        game.font.draw(game.batch, gameFacade.getCastleHealth(2) + "/ 30", 1280, 395+16);
        game.font.draw(game.batch, gameFacade.getPlayerCoins(1) + "", 75, 344+16);
        game.font.draw(game.batch, gameFacade.getPlayerCoins(2) + "", 1296, 344+16);

        game.font.draw(game.batch, "Selected: " + gameFacade.getSelectedTroop(1), 38, 60);
        game.font.draw(game.batch, "Selected: " + gameFacade.getSelectedTroop(2), 1304, 60);
        


        if(gameFacade.getGameMode().equals("time"))
        {
            gameFacade.passTurn();

            if(Gdx.input.isKeyJustPressed(Keys.A)){
                gameFacade.selectTroop("Archer", 1);
            }
            if(Gdx.input.isKeyJustPressed(Keys.NUM_1)){
                gameFacade.dynamicPlaceTroop(0, 0,1);
            }

        } 
        else 
        {
            if(Gdx.input.isKeyJustPressed(Keys.N)){  // Mover para dentro do 1 dps
                gameFacade.passTurn();
            }
            if(gameFacade.getCurrentPlayer() == 1){
                if(Gdx.input.isKeyJustPressed(Keys.A)){
                    gameFacade.selectTroop("Archer", gameFacade.getCurrentPlayer());
                }
                if(Gdx.input.isKeyJustPressed(Keys.NUM_1) && !gameFacade.getSelectedTroop(1).equals("None")){
                    gameFacade.turnPlaceTroop(0, 0);
                }
            }
            
        }
        
        gameFacade.renderBoard(game.batch);
        

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
