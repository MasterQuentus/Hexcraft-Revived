package net.masterquentus.hexcraftmod.spells;

import java.util.Set;

public interface ISpellData {
    Set<String> getKnownSpells();
    boolean knowsSpell(String spellId);
    void learnSpell(String spellId);

    void setSelectedSpell(String id);

    String getSelectedSpell();
}