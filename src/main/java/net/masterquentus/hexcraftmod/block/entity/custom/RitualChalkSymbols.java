package net.masterquentus.hexcraftmod.block.entity.custom;

import net.minecraft.util.StringRepresentable;

public enum RitualChalkSymbols implements StringRepresentable {
    BINDING_CIRCLE("binding_circle"),
    ANCESTRAL_FLAME("ancestral_flame"),
    TETHERED_SOUL("tethered_soul"),
    VEIN_STAR("vein_star"),
    TRIQUETRA("triquetra"),
    PROTECTION_WARD("protection_ward"),
    BLOODLINE_TOTEM("bloodline_totem"),
    CENTER_SIGIL("center_sigil"),
    HYBRID_MARK("hybrid_mark"),
    DARK_SPIRAL("dark_spiral"),
    MOON_SYMBOL("moon_symbol"),
    QUARTZ_RUNE("quartz_rune"),
    SERPENT_PATH("serpent_path");

    private final String name;

    RitualChalkSymbols(String name) {
        this.name = name;
    }

    @Override
    public String getSerializedName() {
        return name;
    }
}