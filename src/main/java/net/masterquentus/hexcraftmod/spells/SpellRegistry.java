package net.masterquentus.hexcraftmod.spells;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class SpellRegistry {

    private static final Map<String, HexSpell> SPELLS = new HashMap<>();

    public static void registerSpell(HexSpell spell) {
        SPELLS.put(spell.getId(), spell);
    }

    public static HexSpell getSpell(String id) {
        return SPELLS.get(id);
    }

    public static Collection<HexSpell> getAllSpells() {
        return SPELLS.values();
    }
}