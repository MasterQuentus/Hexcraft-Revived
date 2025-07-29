package net.masterquentus.hexcraftmod.spells;

/**
 * Interface representing siphoner capability data for a player.
 */
public interface ISiphonerData {

    /**
     * Checks if the player currently has the siphoner ability.
     *
     * @return true if the player is a siphoner, false otherwise
     */
    boolean isSiphoner();

    /**
     * Sets whether the player is a siphoner.
     *
     * @param value true to make the player a siphoner, false otherwise
     */
    void setSiphoner(boolean value);

    /**
     * Gets the current amount of magic charge the player has.
     *
     * @return the magic charge value
     */
    int getMagicCharge();

    /**
     * Recharges the player's magic charge by the given amount.
     *
     * @param amount the amount to add to the magic charge
     */
    void recharge(int amount);

    /**
     * Drains the player's magic charge by the given amount.
     *
     * @param amount the amount to subtract from the magic charge
     */
    void drainCharge(int amount);
}