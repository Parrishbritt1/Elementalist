package abilities;

import main.GamePanel;

public class PrimaryAttack {

    private Ability currentAbility;

    private GamePanel gp;
    
    public PrimaryAttack(GamePanel gp, Ability currentAbility) {
        this.gp = gp;
        this.currentAbility = currentAbility;
    }

    public PrimaryAttack(GamePanel gp) {
        this(gp, null);
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
                return new Fireball(this.gp);

            default:
                return null;
        }
    }
}
