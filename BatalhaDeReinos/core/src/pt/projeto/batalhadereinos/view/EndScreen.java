package pt.projeto.batalhadereinos.view;

import com.badlogic.gdx.Gdx;

import pt.projeto.batalhadereinos.BatalhaDeReinos;
import pt.projeto.batalhadereinos.controller.IScreenMediator;

public class EndScreen extends Screen {

    private String vencedor;
    private String tempoDeJogo;

    public EndScreen(BatalhaDeReinos game, IScreenMediator gameScreenMediator) {
        super(game, gameScreenMediator);
    }

    public void setVencedor(String vencedor){
        this.vencedor = vencedor;
    }

    public void setTempoDeJogo(String tempoDeJogo) {
        this.tempoDeJogo = tempoDeJogo;
    }

    public void show() {
        super.show();

        setBackgroundImage("background/EndBackground.jpg");

        createLabel(vencedor, MyLabel.BLACK, 512, 529, 80);
        createLabel(tempoDeJogo, MyLabel.BLACK, 722, 338, 80);

        createButton(MyButton.generateStyle("buttons/btnJogarNovamente.png"), 46, 43,
                    new IButtonCommand() {public void execute() {
                        gameScreenMediator.changeScreen("Settings");
                    }});
        
        createButton(MyButton.generateStyle("buttons/btnSair.png"), 869, 31,
                    new IButtonCommand() {public void execute() {
                        Gdx.app.exit();
                    }});
    }
    
}
