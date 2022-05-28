package abilities;

public enum Ability {
    FIREBALL(1_000_000_000L);

    private long CD; // Cooldown in Nano seconds

    Ability(long CD) {
        this.CD = CD;
    }

    public long getCoolDown() {
        return this.CD;
    }
}
