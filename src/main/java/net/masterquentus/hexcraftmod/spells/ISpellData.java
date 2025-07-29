package net.masterquentus.hexcraftmod.spells;

import java.util.Set;

/**
 * Interface representing a player's spell data, including known spells and the currently selected spell.
 */
public interface ISpellData {

    /**
     * Gets a set of all known spell IDs.
     *
     * @return a set containing the IDs of all known spells
     */
    Set<String> getKnownSpells();

    /**
     * Checks if the player knows a specific spell.
     *
     * @param spellId the ID of the spell
     * @return true if the spell is known, false otherwise
     */
    boolean knowsSpell(String spellId);

    /**
     * Adds a spell to the player's known spells.
     *
     * @param spellId the ID of the spell to learn
     */
    void learnSpell(String spellId);

    /**
     * Sets the currently selected spell.
     *
     * @param id the ID of the spell to select
     */
    void setSelectedSpell(String id);

    /**
     * Gets the ID of the currently selected spell.
     *
     * @return the selected spell ID
     */
    String getSelectedSpell();
}