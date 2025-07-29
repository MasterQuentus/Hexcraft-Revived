package net.masterquentus.hexcraftmod.block.entity.custom;

import net.minecraft.util.StringRepresentable;

public enum WhiteChalkSymbols implements StringRepresentable {
    VITALITY_SIGIL("vitality_sigil"),
    SUN_SYMBOL("sun_symbol"),
    VEIN_STAR("vein_star"),
    DAYLIGHT_SEAL("daylight_seal"),
    PROTECTION_WARD("protection_ward"),
    NEXUS_SEAL("nexus_seal"),
    TETHERED_SOUL("tethered_soul"),
    QUARTZ_RUNE("quartz_rune"),
    BINDING_CIRCLE("binding_circle"),
    SACRED_THREAD("sacred_thread"),
    ILLUMINATION_SIGIL("illumination_sigil"),
    SANCTUM_MARK("sanctum_mark"),
    PURITY_STAR("purity_star"),
    GUARDIAN_WARD("guardian_ward"),
    ETHEREAL_CHAIN("ethereal_chain");

    private final String name;

    WhiteChalkSymbols(String name) {
        this.name = name;
    }

    @Override
    public String getSerializedName() {
        return name;
    }
}