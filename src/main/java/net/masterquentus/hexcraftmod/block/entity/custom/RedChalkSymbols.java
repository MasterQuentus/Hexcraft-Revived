package net.masterquentus.hexcraftmod.block.entity.custom;

import net.minecraft.util.StringRepresentable;

public enum RedChalkSymbols implements StringRepresentable {
    BLOODLINE_TOTEM("bloodline_totem"),
    FANG_SIGIL("fang_sigil"),
    SANGUINE_COIL("sanguine_coil"),
    SANGUINE_OATH("sanguine_oath"),
    SIRE_SIGIL ("sire_sigil"),
    MMORTAL_BOND ("immortal_bond"),
    SERPENT_PATH("serpent_path"),
    ELDER_SIGIL ("elder_sigil"),
    BLOOD_PACT("blood_pact"),
    HUNGER_GLYPH("hunger_glyph"),
    SACRIFICE_RUNE("sacrifice_rune"),
    THORNBIND("thornbind"),
    RITUAL_CHAIN("ritual_chain"),
    HEXMARK("hexmark"),
    NIGHTBRINGER("nightbringer");

    private final String name;

    RedChalkSymbols(String name) {
        this.name = name;
    }

    @Override
    public String getSerializedName() {
        return name;
    }
}