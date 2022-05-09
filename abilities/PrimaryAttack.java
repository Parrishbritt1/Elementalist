package abilities;

import entities.Player;
import main.GamePanel;

public class PrimaryAttack {
    private Player player;

    private Ability currentAbility;

    private GamePanel gp;
    
    public PrimaryAttack(GamePanel gp, Player player, Ability currentAbility) {
        this.gp = gp;
        this.player = player;
        this.currentAbility = currentAbility;
    }

    public PrimaryAttack(GamePanel gp, Player player) {
        this(gp, player, null);
    }

    public Ability getCurrentAbility() {
        return this.currentAbility;
    }

    public void setCurrentAbility(Ability currentAbility) {
        this.currentAbility = currentAbility;
    }

    public Fireball triggerAbility() {
        switch(this.currentAbility) {
            case FIREBALL:
                return new Fireball(this.gp, player.screenX, player.screenY);

            default:
                return null;
        }
    }
}
