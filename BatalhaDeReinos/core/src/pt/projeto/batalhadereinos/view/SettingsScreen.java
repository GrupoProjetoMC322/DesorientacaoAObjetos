package pt.projeto.batalhadereinos.view;

import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;

import pt.projeto.batalhadereinos.BatalhaDeReinos;

public class SettingsScreen extends Screen {
    private final SettingsScreen screen = this;

    private ImageButtonStyle btnTurnsOn, btnTurnsOff, btnContinuousOn, btnContinuousOff;
    private MyButton btnTurns, btnContinuous;

    private int[] settings = {1, 0};

    public SettingsScreen(BatalhaDeReinos game) {
        super(game);
    }

    public void show() {
        super.show();
        createTextures();
        setBackgroundImage("background/PlayBackground.jpg");

        createButton("buttons/btn2PlayersLocal.png", 440, 730.23f,
                    new IButtonCommand() {public void execute() {
                        System.out.println("Número de Jogadores selecionado: 2 (local)");
                    }});

        btnTurns = createButton(btnTurnsOn, 14, 383,
                                new IButtonCommand() {public void execute() {
                                    settings[1] = 0;
                                    btnTurns.setStyle(btnTurnsOn);
                                    btnContinuous.setStyle(btnContinuousOff);
                                }});

        btnContinuous = createButton(btnContinuousOff, 406, 383,
                                    new IButtonCommand() {public void execute() {
                                        settings[1] = 1;
                                        btnContinuous.setStyle(btnContinuousOn);
                                        btnTurns.setStyle(btnTurnsOff);
                                    }});

        createButton("buttons/btnVoltar.png", 68, 53,
                    new IButtonCommand() {public void execute() {
                        screenController.update(new MainMenuScreen(game));
                    }});

        createButton("buttons/btnComoJogar.png", 485, 40,
                    new IButtonCommand() {public void execute() {
                        screenController.update(new HowToPlayScreen(game, screen));
                    }});

        createButton("buttons/btnIniciar.png", 1134, 53,
                    new IButtonCommand() {public void execute() {
                        screenController.update(new GameScreen(game, settings));
                    }});
    }

    public void createTextures() {
        btnTurnsOn = MyButton.generateStyle("buttons/btnTurnos.png");
        btnTurnsOff = MyButton.generateStyle("buttons/btnTurnosDis.png");
        btnContinuousOn = MyButton.generateStyle("buttons/btnContinuo.png");
        btnContinuousOff = MyButton.generateStyle("buttons/btnContinuoDis.png");
    }
    
}
