package net.masterquentus.hexcraftmod.spells;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class ModularSpell extends HexSpell {

    private final CoreComponent core;
    private final List<ModifierComponent> modifiers;
    private final List<AugmentComponent> augments;
    private final MagicSource magicSource;

    public ModularSpell(CoreComponent core,
                        List<ModifierComponent> modifiers,
                        List<AugmentComponent> augments,
                        MagicSource magicSource) {
        this.core = Objects.requireNonNull(core);
        this.modifiers = modifiers == null ? Collections.emptyList() : modifiers;
        this.augments = augments == null ? Collections.emptyList() : augments;
        this.magicSource = Objects.requireNonNull(magicSource);
    }

    public CoreComponent getCore() {
        return core;
    }

    public List<ModifierComponent> getModifiers() {
        return Collections.unmodifiableList(modifiers);
    }

    public List<AugmentComponent> getAugments() {
        return Collections.unmodifiableList(augments);
    }

    public MagicSource getMagicSource() {
        return magicSource;
    }

    @Override
    public String getId() {
        StringBuilder idBuilder = new StringBuilder(core.getId());
        for (ModifierComponent mod : modifiers) {
            idBuilder.append("_").append(mod.getId());
        }
        for (AugmentComponent aug : augments) {
            idBuilder.append("_").append(aug.getId());
        }
        idBuilder.append("_").append(magicSource.name().toLowerCase());
        return idBuilder.toString();
    }

    @Override
    public void cast(ServerLevel level, Player caster) {
        core.applyEffect(level, caster);

        for (ModifierComponent mod : modifiers) {
            mod.modify(level, caster, core);
        }

        for (AugmentComponent aug : augments) {
            aug.applyExtra(level, caster);
        }

        magicSource.applyMagicSourceEffects(level, caster, this);
    }
}