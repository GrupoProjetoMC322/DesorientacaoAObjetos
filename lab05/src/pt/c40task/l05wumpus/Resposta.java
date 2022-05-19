package pt.c40task.l05wumpus;

public class Resposta{
    private int score;
    private String mensagem;

    public Resposta(int score, String mensagem) {
        this.score = score;
        this.mensagem = mensagem;
    }

    public int getScore() {
        return this.score;
    }

    public String getMensagem() {
        return this.mensagem;
    }
}
