package net.masterquentus.hexcraftmod.block.entity.custom;

import net.minecraft.util.StringRepresentable;

public enum BlackChalkSymbols implements StringRepresentable {
    HYBRID_MARK("hybrid_mark"),
    TRINITY_MARK("trinity_mark"),
    ANCESTOR_RUNE("ancestor_rune"),
    DARK_SPIRAL("dark_spiral"),
    NECROMANTIC_MARK("necromantic_mark"),
    CENTER_SIGIL("center_sigil"),
    MOON_SYMBOL("moon_symbol"),
    LOCKED_CIRCLE("locked_circle"),
    NIGHTBOUND_SEAL("nightbound_seal"),
    SHADE_RUNE("shade_rune"),
    BONE_CHAIN("bone_chain"),
    ABYSSAL_KNOT("abyssal_knot"),
    VOID_STAR("void_star"),
    BLOODLOCK_WARD("bloodlock_ward"),
    FANGED_RING("fanged_ring");

    private final String name;

    BlackChalkSymbols(String name) {
        this.name = name;
    }

    @Override
    public String getSerializedName() {
        return name;
    }
}