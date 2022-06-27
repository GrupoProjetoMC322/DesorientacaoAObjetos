package pt.projeto.batalhadereinos.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Texture;

import pt.projeto.batalhadereinos.BatalhaDeReinos;
import pt.projeto.batalhadereinos.controller.GameFacade;
import pt.projeto.batalhadereinos.controller.IScreenMediator;
import pt.projeto.batalhadereinos.model.Board;
import pt.projeto.batalhadereinos.model.Troop;

public class GameScreen extends Screen {
    private static final int[] STANDARD_SETTINGS = {1, 0};
    private final GameScreen gameScreen = this;

    private Texture redSoldier, redArcher, redKnight, redRogue, redBarrier, redMage,
                blueSoldier, blueArcher, blueKnight, blueRogue, blueBarrier, blueMage;
    private MyLabel life1, life2, coins1, coins2, turnCounter, timer;

    private int[] settings;

    public GameScreen(final BatalhaDeReinos game) {
        this(game, STANDARD_SETTINGS);
    }
    
    public GameScreen(final BatalhaDeReinos game, int[] settings){
        super(game);
        this.settings = settings;
    }

    public void show() {
        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(stage);
        multiplexer.addProcessor(new InputAdapter() {
            @Override
            public boolean keyDown(int keyCode) {
                switch (keyCode) {
                    case Input.Keys.NUM_1:
                        System.out.println("Jogador 1: Soldado selecionado!");
                        break;
                    case Input.Keys.NUM_2:
                        System.out.println("Jogador 1: Arqueiro selecionado!");
                        break;
                    case Input.Keys.NUM_3:
                        System.out.println("Jogador 1: Cavaleiro selecionado!");
                        break;
                    case Input.Keys.NUM_4:
                        System.out.println("Jogador 1: Ladino selecionado!");
                        break;
                    case Input.Keys.NUM_5:
                        System.out.println("Jogador 1: Barreira selecionada!");
                        break;
                    case Input.Keys.NUM_6:
                        System.out.println("Jogador 1: Mago selecionado!");
                        break;
                    case Input.Keys.A:
                        System.out.println("Jogador 1: Tropa treinada na linha 1!");
                        break;
                    case Input.Keys.B:
                        System.out.println("Jogador 1: Tropa treinada na linha 2!");
                        break;
                    case Input.Keys.C:
                        System.out.println("Jogador 1: Tropa treinada na linha 3!");
                        break;
                    case Input.Keys.D:
                        System.out.println("Jogador 1: Tropa treinada na linha 4!");
                        break;
                    case Input.Keys.P:
                        System.out.println("Jogador 1: Turno pulado!");
                        break;
                    case Input.Keys.ESCAPE:
                        gameScreenMediator.changeScreen("Pause");
                        break;
                }
                return true;
            }
        });
        Gdx.input.setInputProcessor(multiplexer);

        setBackgroundImage("background/GameBackground.jpg");
        createWidgets();       
    }

    public void generateTextures() {
        redSoldier = new Texture(Gdx.files.internal("icons/redSoldier.png"));
        redArcher = new Texture(Gdx.files.internal("icons/redArcher.png"));
        redKnight = new Texture(Gdx.files.internal("icons/redKnight.png"));
        redRogue = new Texture(Gdx.files.internal("icons/redRogue.png"));
        redBarrier = new Texture(Gdx.files.internal("icons/redBarrier.png"));
        redMage = new Texture(Gdx.files.internal("icons/redMage.png"));
        blueSoldier = new Texture(Gdx.files.internal("icons/blueSoldier.png"));
        blueArcher = new Texture(Gdx.files.internal("icons/blueArcher.png"));
        blueKnight = new Texture(Gdx.files.internal("icons/blueKnight.png"));
        blueRogue = new Texture(Gdx.files.internal("icons/blueRogue.png"));
        blueBarrier = new Texture(Gdx.files.internal("icons/blueBarrier.png"));
        blueMage = new Texture(Gdx.files.internal("icons/blueMage.png"));
    }

    public void createWidgets() {
        createButton("buttons/btnPause.png", 32, 926,
                    new IButtonCommand() {public void execute() {
                        gameScreenMediator.changeScreen("Pause");
                    }});
        
        createButton("buttons/btnSoldier.png", 257, 136,
                    new IButtonCommand() {public void execute() {
                        System.out.println("Jogador 2: Soldado selecionado!");
                    }});
    
        createButton("buttons/btnArcher.png", 414, 136,
                    new IButtonCommand() {public void execute() {
                        System.out.println("Jogador 2: Arqueiro selecionado!");
                    }});

        createButton("buttons/btnKnight.png", 571, 136,
                    new IButtonCommand() {public void execute() {
                        System.out.println("Jogador 2: Cavaleiro selecionado!");
                    }});

        createButton("buttons/btnRogue.png", 728, 136,
                    new IButtonCommand() {public void execute() {
                        System.out.println("Jogador 2: Ladino selecionado!");
                    }});

        createButton("buttons/btnBarrier.png", 885, 136,
                    new IButtonCommand() {public void execute() {
                        System.out.println("Jogador 2: Barreira selecionada!");
                    }});

        createButton("buttons/btnMage.png", 1042, 136,
                    new IButtonCommand() {public void execute() {
                        System.out.println("Jogador 2: Mago selecionado!");
                    }});

        createButton("buttons/btnPlot.png", 1120, 644,
                    new IButtonCommand() {public void execute() {
                        System.out.println("Jogador 2: Tropa treinada na linha 1!");
                    }});

        createButton("buttons/btnPlot.png", 1120, 544,
                    new IButtonCommand() {public void execute() {
                        System.out.println("Jogador 2: Tropa treinada na linha 2!");
                    }});

        createButton("buttons/btnPlot.png", 1120, 444,
                    new IButtonCommand() {public void execute() {
                        System.out.println("Jogador 2: Tropa treinada na linha 3!");
                    }});

        createButton("buttons/btnPlot.png", 1120, 344,
                    new IButtonCommand() {public void execute() {
                        System.out.println("Jogador 2: Tropa treinada na linha 4!");
                    }});

        if (settings[1] == 0) {
            createButton("buttons/btnSkip.png", 1213, 168,
                        new IButtonCommand() {public void execute() {
                            System.out.println("Jogador 2: Turno pulado!");
                        }});
            turnCounter = addLabel("Turno 1", MyLabel.BLACK, 214, 745, 42);
        }
        
        timer = addLabel("00:01", MyLabel.BLACK, 1111, 745, 42);
        life1 = addLabel("30/30", MyLabel.BLACK, 53, 398, 34);
        life2 = addLabel("30/30", MyLabel.BLACK, 1272, 398, 34);
        coins1 = addLabel("5", MyLabel.YELLOW, 115, 344, 34);
        coins2 = addLabel("5", MyLabel.YELLOW, 1338, 344, 34);
    }

	public void render(float delta){
        super.render(delta);
    }

}
