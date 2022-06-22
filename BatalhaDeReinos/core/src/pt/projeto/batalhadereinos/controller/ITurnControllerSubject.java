package pt.projeto.batalhadereinos.controller;

import pt.projeto.batalhadereinos.model.ITroopObserver;

public interface ITurnControllerSubject {
    public void subscribeTroop(ITroopObserver troop);
    public void unsubscribeTroop(ITroopObserver troop);
    public void notifyTroops();
}
