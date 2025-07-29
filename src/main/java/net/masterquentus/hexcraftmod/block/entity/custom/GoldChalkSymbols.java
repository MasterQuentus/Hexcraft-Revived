package net.masterquentus.hexcraftmod.block.entity.custom;

import net.minecraft.util.StringRepresentable;

public enum GoldChalkSymbols implements StringRepresentable {
    SUN_RING("sun_ring"),
    CELESTIAL_WARD("celestial_ward"),
    ANCESTRAL_SIGIL("ancestral_sigil"),
    DIVINE_KNOT("divine_knot"),
    COVEN_STAR("coven_star"),
    SOLAR_VOW ("solar_vow"),
    SANCTUARY_GLYPH("sanctuary_glyph"),
    AEGIS_MARK("aegis_mark"),
    HARMONY_RUNE("harmony_rune"),
    LAW_OF_THREE("law_of_three"),
    ETERNAL_OATH("eternal_oath"),
    JUDGMENT_SEAL("judgment_seal"),
    ORDERED_SPIRAL("ordered_spiral"),
    SACRED_PILLAR("sacred_pillar"),
    PACTBOUND_SIGIL("pactbound_sigil");

    private final String name;

    GoldChalkSymbols(String name) {
        this.name = name;
    }

    @Override
    public String getSerializedName() {
        return name;
    }
}