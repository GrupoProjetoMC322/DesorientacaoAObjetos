package pt.projeto.batalhadereinos.view;

import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;

import pt.projeto.batalhadereinos.BatalhaDeReinos;
import pt.projeto.batalhadereinos.controller.IScreenMediator;

public class SettingsScreen extends Screen {

    private ImageButtonStyle btnTurnsOn, btnTurnsOff, btnContinuousOn, btnContinuousOff, btnInsaneOn, btnInsaneOff;
    private MyButton btnTurns, btnContinuous, btnInsane;

    private int[] settings = {1, 0};

    public SettingsScreen(BatalhaDeReinos game, IScreenMediator gameScreenMediator) {
        super(game, gameScreenMediator);
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
                                    btnInsane.setStyle(btnInsaneOff);
                                }});

        btnContinuous = createButton(btnContinuousOff, 406, 383,
                                    new IButtonCommand() {public void execute() {
                                        settings[1] = 1;
                                        btnTurns.setStyle(btnTurnsOff);
                                        btnContinuous.setStyle(btnContinuousOn);
                                        btnInsane.setStyle(btnInsaneOff);
                                    }});

        btnInsane = createButton(btnInsaneOff, 767, 383,
                                new IButtonCommand() {public void execute() {
                                    settings[1] = 2;
                                    btnTurns.setStyle(btnTurnsOff);
                                    btnContinuous.setStyle(btnContinuousOff);
                                    btnInsane.setStyle(btnInsaneOn);
                                }});

        createButton("buttons/btnVoltar.png", 68, 53,
                    new IButtonCommand() {public void execute() {
                        gameScreenMediator.changeScreen("MainMenu");
                    }});

        createButton("buttons/btnComoJogar.png", 485, 40,
                    new IButtonCommand() {public void execute() {
                        gameScreenMediator.changeScreen("HowToPlay");
                    }});

        createButton("buttons/btnIniciar.png", 1134, 53,
                    new IButtonCommand() {public void execute() {
                        if(settings[1] == 0){
                            gameScreenMediator.changeScreen("NewTurnGameScreen");
                        } else if(settings[1] == 1){
                            gameScreenMediator.changeScreen("NewDynamicGameScreen");
                        } else if (settings[1] == 2) {
                            gameScreenMediator.changeScreen("NewInsaneDynamicGameScreen");
                        }
                    }});
    }

    public void createTextures() {
        btnTurnsOn = MyButton.generateStyle("buttons/btnTurnos.png");
        btnTurnsOff = MyButton.generateStyle("buttons/btnTurnosDis.png");
        btnContinuousOn = MyButton.generateStyle("buttons/btnContinuo.png");
        btnContinuousOff = MyButton.generateStyle("buttons/btnContinuoDis.png");
        btnInsaneOn = MyButton.generateStyle("buttons/btnInsano.png");
        btnInsaneOff = MyButton.generateStyle("buttons/btnInsanoDis.png");
    }
    
}
