package dev.game.freaking.main;

import dev.game.modules.ModuleFreaking;

public class FreakingHandler {
    private ModuleFreaking moduleFreaking;

    public FreakingHandler(ModuleFreaking moduleFreaking) {
        this.moduleFreaking = moduleFreaking;
    }

    public ModuleFreaking getGame() {
        return moduleFreaking;
    }

    public void setGame(ModuleFreaking freakingGame) {
        this.moduleFreaking = freakingGame;
    }
}
