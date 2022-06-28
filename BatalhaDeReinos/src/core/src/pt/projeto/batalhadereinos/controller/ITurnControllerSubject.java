package pt.projeto.batalhadereinos.controller;

import pt.projeto.batalhadereinos.model.IPlayerObserver;
import pt.projeto.batalhadereinos.model.ITroopObserver;

public interface ITurnControllerSubject {
    public void subscribeTroop(ITroopObserver troop);
    public void unsubscribeTroop(ITroopObserver troop);
    public void subscribePlayers(IPlayerObserver player1, IPlayerObserver player2);
    public void notifyTroops();
    public void notifyPlayers();
    public void passTurn();
    public int getTurn();
}
