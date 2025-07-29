package net.masterquentus.hexcraftmod.spells;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import net.minecraftforge.common.util.INBTSerializable;

import java.util.HashSet;
import java.util.Set;

/**
 * Capability backend implementation for ISpellData.
 */
public class SpellData implements ISpellData, INBTSerializable<CompoundTag> {

    private final Set<String> knownSpells = new HashSet<>();
    private String selectedSpell = null;

    @Override
    public Set<String> getKnownSpells() {
        return knownSpells;
    }

    @Override
    public boolean knowsSpell(String spellId) {
        return knownSpells.contains(spellId);
    }

    @Override
    public void learnSpell(String spellId) {
        knownSpells.add(spellId);
        // Auto-select the first learned spell if none is selected
        if (selectedSpell == null) {
            selectedSpell = spellId;
        }
    }

    @Override
    public void setSelectedSpell(String id) {
        if (id == null || !knowsSpell(id)) return;
        selectedSpell = id;
    }

    @Override
    public String getSelectedSpell() {
        return selectedSpell;
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();

        // Save known spells
        ListTag list = new ListTag();
        for (String spell : knownSpells) {
            list.add(StringTag.valueOf(spell));
        }
        tag.put("KnownSpells", list);

        // Save selected spell
        tag.putString("SelectedSpell", selectedSpell == null ? "" : selectedSpell);

        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag tag) {
        knownSpells.clear();

        // Load known spells
        ListTag list = tag.getList("KnownSpells", Tag.TAG_STRING);
        for (Tag t : list) {
            knownSpells.add(t.getAsString());
        }

        // Load selected spell
        String stored = tag.getString("SelectedSpell");
        selectedSpell = stored.isEmpty() ? null : stored;
    }
}