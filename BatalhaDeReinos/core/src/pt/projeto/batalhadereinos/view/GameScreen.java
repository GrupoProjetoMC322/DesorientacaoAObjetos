package pt.projeto.batalhadereinos.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Texture;

import pt.projeto.batalhadereinos.BatalhaDeReinos;
import pt.projeto.batalhadereinos.controller.IGameFacade;
import pt.projeto.batalhadereinos.controller.IScreenMediator;
import pt.projeto.batalhadereinos.model.Board;
import pt.projeto.batalhadereinos.model.Buff;
import pt.projeto.batalhadereinos.model.Troop;

public class GameScreen extends Screen {

    private IGameFacade gameFacade;

    private PauseScreen pauseScreen;
    private EndScreen endScreen;

    private Board board;

    private Texture mistLeftImage;
    private Texture mistRightImage;
    private Texture fireRectangle;

    private Texture fieldGameBackground;
    private Texture volcanoGameBackground;
    private Texture snowGameBackground;
    private Texture mistGameBackground;

    private Texture fieldPauseBackground;
    private Texture volcanoPauseBackground;
    private Texture snowPauseBackground;
    private Texture mistPauseBackground;

    

    private MyLabel life1, life2, coins1, coins2, turnCounter, timer;

    float time = 0;

    public GameScreen(final BatalhaDeReinos game, IGameFacade gameFacade, IScreenMediator gameScreenMediator, PauseScreen pauseScreen, EndScreen endScreen) {
        super(game, gameScreenMediator);
        this.gameFacade = gameFacade;
        this.pauseScreen = pauseScreen;
        this.endScreen = endScreen;
     
        generateTextures();

        board = new Board();
        gameFacade.setBoard(board);

        createWidgets();
    }

    public void generateTextures(){
        this.mistLeftImage = new Texture(Gdx.files.internal("icons/MistLeft.png"));
        this.mistRightImage = new Texture(Gdx.files.internal("icons/MistRight.png"));
        this.fireRectangle = new Texture(Gdx.files.internal("icons/FireRectangle.png"));

        this.fieldGameBackground = new Texture(Gdx.files.internal("background/FieldGameBackground.jpg"));
        this.volcanoGameBackground = new Texture(Gdx.files.internal("background/VolcanoGameBackground.jpg"));
        this.snowGameBackground = new Texture(Gdx.files.internal("background/SnowGameBackground.jpg"));
        this.mistGameBackground = new Texture(Gdx.files.internal("background/MistGameBackground.jpg"));

        this.fieldPauseBackground = new Texture(Gdx.files.internal("background/FieldPauseBackground.jpg"));
        this.volcanoPauseBackground = new Texture(Gdx.files.internal("background/VolcanoPauseBackground.jpg"));
        this.snowPauseBackground = new Texture(Gdx.files.internal("background/SnowPauseBackground.jpg"));
        this.mistPauseBackground = new Texture(Gdx.files.internal("background/MistPauseBackground.jpg"));
    }

    public String formatTime() {
        String formattedTime;

        int seconds = (int) time;

        int minutes = seconds/60;

        int restSeconds = seconds - minutes*60;

        if(restSeconds < 10){
            formattedTime = minutes + ":0" + restSeconds;
        } else {
            formattedTime = minutes + ":" + restSeconds;
        }
        

        return formattedTime;
    }

    public void show() {
        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(stage);
        multiplexer.addProcessor(new InputAdapter() {
            @Override
            public boolean keyDown(int keyCode) {
                switch (keyCode) {
                    case Input.Keys.NUM_1:
                        gameFacade.selectTroop("Soldier", 1);
                        break;
                    case Input.Keys.NUM_2:
                        gameFacade.selectTroop("Archer", 1);
                        break;
                    case Input.Keys.NUM_3:
                        gameFacade.selectTroop("Knight", 1);
                        break;
                    case Input.Keys.NUM_4:
                        gameFacade.selectTroop("Rogue", 1);
                        break;
                    case Input.Keys.NUM_5:
                        gameFacade.selectTroop("Barrier", 1);
                        break;
                    case Input.Keys.NUM_6:
                        gameFacade.selectTroop("Mage", 1);
                        break;
                    case Input.Keys.A:
                        if (gameFacade.getGameMode().equals("turn") && gameFacade.getTurn() % 2 == 0) {
                            gameFacade.turnPlaceTroop(0, 0);
                        } else {
                            gameFacade.dynamicPlaceTroop(0, 0, 1);
                        }
                        break;
                    case Input.Keys.B:
                        if (gameFacade.getGameMode().equals("turn") && gameFacade.getTurn() % 2 == 0) {
                            gameFacade.turnPlaceTroop(0, 0);
                            gameFacade.turnPlaceTroop(1, 0);
                        } else {
                            gameFacade.dynamicPlaceTroop(1, 0, 1);
                        }
                        break;
                    case Input.Keys.C:
                        if (gameFacade.getGameMode().equals("turn") && gameFacade.getTurn() % 2 == 0) {
                            gameFacade.turnPlaceTroop(2, 0);
                        } else {
                            gameFacade.dynamicPlaceTroop(2, 0, 1);
                        }
                        break;
                    case Input.Keys.D:
                        if (gameFacade.getGameMode().equals("turn") && gameFacade.getTurn() % 2 == 0) {
                            gameFacade.turnPlaceTroop(3, 0);
                        } else {
                            gameFacade.dynamicPlaceTroop(3, 0, 1);
                        }
                        break;
                    case Input.Keys.P:
                        gameFacade.passTurn();
                        gameFacade.tryGenerateBuff();
                        gameFacade.tryChangeMap();
                        break;
                    case Input.Keys.ESCAPE:
                        gameScreenMediator.changeScreen("Pause");
                        break;
                }
                return true;
            }
        });
        Gdx.input.setInputProcessor(multiplexer);

        setBackgroundImage(fieldGameBackground);     
    }

    public void createWidgets() {
        createButton("buttons/btnPause.png", 32, 926,
                    new IButtonCommand() {public void execute() {
                        gameScreenMediator.changeScreen("Pause");
                    }});
        
        createButton("buttons/btnSoldier.png", 257, 136,
                    new IButtonCommand() {public void execute() {
                        gameFacade.selectTroop("Soldier", 2);
                    }});
    
        createButton("buttons/btnArcher.png", 414, 136,
                    new IButtonCommand() {public void execute() {
                        gameFacade.selectTroop("Archer", 2);
                    }});

        createButton("buttons/btnKnight.png", 571, 136,
                    new IButtonCommand() {public void execute() {
                        gameFacade.selectTroop("Knight", 2);
                    }});

        createButton("buttons/btnRogue.png", 728, 136,
                    new IButtonCommand() {public void execute() {
                        gameFacade.selectTroop("Rogue", 2);
                    }});

        createButton("buttons/btnBarrier.png", 885, 136,
                    new IButtonCommand() {public void execute() {
                        gameFacade.selectTroop("Barrier", 2);
                    }});

        createButton("buttons/btnMage.png", 1042, 136,
                    new IButtonCommand() {public void execute() {
                        gameFacade.selectTroop("Mage", 2);
                    }});

        createButton("buttons/btnPlot.png", 1120, 644,
                    new IButtonCommand() {public void execute() {
                        if (gameFacade.getGameMode().equals("turn") && gameFacade.getTurn() % 2 != 0) {
                            gameFacade.turnPlaceTroop(0, 9);
                        } else {
                            gameFacade.dynamicPlaceTroop(0, 9, 2);
                        }
                    }});

        createButton("buttons/btnPlot.png", 1120, 544,
                    new IButtonCommand() {public void execute() {
                        if (gameFacade.getGameMode().equals("turn") && gameFacade.getTurn() % 2 != 0) {
                            gameFacade.turnPlaceTroop(1, 9);
                        } else {
                            gameFacade.dynamicPlaceTroop(1, 9, 2);
                        }
                    }});

        createButton("buttons/btnPlot.png", 1120, 444,
                    new IButtonCommand() {public void execute() {
                        if (gameFacade.getGameMode().equals("turn") && gameFacade.getTurn() % 2 != 0) {
                            gameFacade.turnPlaceTroop(2, 9);
                        } else {
                            gameFacade.dynamicPlaceTroop(2, 9, 2);
                        }
                    }});

        createButton("buttons/btnPlot.png", 1120, 344,
                    new IButtonCommand() {public void execute() {
                        if (gameFacade.getGameMode().equals("turn") && gameFacade.getTurn() % 2 != 0) {
                            gameFacade.turnPlaceTroop(3, 9);
                        } else {
                            gameFacade.dynamicPlaceTroop(3, 9, 2);
                        }
                    }});

        if (gameFacade.getGameMode().equals("turn")) {
            createButton("buttons/btnSkip.png", 1213, 168,
                        new IButtonCommand() {public void execute() {
                            gameFacade.passTurn();
                            gameFacade.tryGenerateBuff();
                            gameFacade.tryChangeMap();
                        }});
            turnCounter = createLabel("Turno 1", MyLabel.BLACK, 214, 745, 42);
        }
        
        timer = createLabel("00:01", MyLabel.BLACK, 1111, 745, 42);
        life1 = createLabel("30/30", MyLabel.BLACK, 53, 398, 34);
        life2 = createLabel("30/30", MyLabel.BLACK, 1272, 398, 34);
        coins1 = createLabel("5", MyLabel.YELLOW, 115, 344, 34);
        coins2 = createLabel("5", MyLabel.YELLOW, 1338, 344, 34);
    }

    public void drawBoardFromModel() {
        for(int i = 0; i<4;i++){
            for(int j = 0;j<10;j++){
                Troop troop = gameFacade.getTroopFromSquare(i,j);
                Buff buff = gameFacade.getBuffFromSquare(i,j);
                boolean fire = gameFacade.getFireFromSquare(i,j);

                int graphicRowTroop = j*100+220;
                int graphicColumnTroop = 644-i*100;

                int graphicRowBuff = j*100+234;
                int graphicColumnBuff= 658-i*100;

                if(fire){
                    batch.draw(fireRectangle, graphicRowTroop, graphicColumnTroop);
                }

                if(troop != null){
                    if(!troop.getBuff().equals("None")){
            
                        switch (troop.getBuff()){
                            case "Health":
                                batch.setColor(1f,0f,0f,1f);
                                break;
                            case "Attack":
                                batch.setColor(0f,0f,1f,1f);
                                break;
                            case "Mixed":
                                batch.setColor(1f,0f,1f,1f);
                                break;
                            default:
                                break;     
                        }

                        batch.draw(troop.getGraphic(), graphicRowTroop, graphicColumnTroop);

                        batch.setColor(1,1,1,1);
            
                    } else {
                        batch.draw(troop.getGraphic(), graphicRowTroop, graphicColumnTroop);
                    }
                }

                if(buff != null){
                    batch.draw(buff.getGraphic(), graphicRowBuff, graphicColumnBuff);
                }
                
            }
        }
    }

    public void defineBackgroundImages() {
        switch (gameFacade.getMap()) {
            case "Field":
                this.setBackgroundImage(fieldGameBackground);
                pauseScreen.setBackgroundImage(fieldPauseBackground);
                break;
            case "Volcano":
                this.setBackgroundImage(volcanoGameBackground);
                pauseScreen.setBackgroundImage(volcanoPauseBackground);
                break;
            case "Snow":
                this.setBackgroundImage(snowGameBackground);
                pauseScreen.setBackgroundImage(snowPauseBackground);
                break;
            case "Mist":
                this.setBackgroundImage(mistGameBackground);
                pauseScreen.setBackgroundImage(mistPauseBackground);
                break;
        }
    }

 	public void render(float delta) {
        super.render(delta);

        time += Gdx.graphics.getDeltaTime();
        timer.update(formatTime());

        defineBackgroundImages();

        if (gameFacade.getGameMode().equals("turn"))
            turnCounter.update("Turno: " + gameFacade.getTurn());
        else {
            int turn = gameFacade.getTurn();

            gameFacade.passTurn();

            if (gameFacade.getTurn() != turn) {
                gameFacade.tryGenerateBuff();
                gameFacade.tryChangeMap();
            }

        }

        life1.update(gameFacade.getCastleHealth(1) + "/30");
        coins1.update(gameFacade.getPlayerCoins(1));
        life2.update(gameFacade.getCastleHealth(2) + "/30");
        coins2.update(gameFacade.getPlayerCoins(2));

        batch.begin();
        drawBoardFromModel();
        if(gameFacade.getMap().equals("Mist")){
            batch.draw(mistLeftImage,284,330);
            batch.draw(mistRightImage,795,327);
        }
        batch.end();

        if (gameFacade.checkEndGame()) {
            endScreen.setTempoDeJogo(formatTime());
            endScreen.setVencedor(gameFacade.getVencedor());
            gameScreenMediator.changeScreen("EndScreen");
        }
    }

}
