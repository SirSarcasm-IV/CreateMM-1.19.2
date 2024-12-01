package net.sirsarcasm.createmm.overheat;

import net.minecraft.nbt.CompoundTag;

public class JetpackOverheat {
    private int heat;
    private final int MIN_HEAT = 0;
    private final int MAX_HEAT = 100;

    public int getHeat() {
        return heat;
    }

    public void addHeat(int add) {
        this.heat = Math.min(heat + add, MAX_HEAT);
    }

    public void subHeat(int sub) {
        this.heat = Math.max(heat + sub, MIN_HEAT);
    }

    public void copyFrom(JetpackOverheat source) {
        this.heat = source.heat;
    }

    public void saveNBTData(CompoundTag nbt) {
        nbt.putInt("heat", heat);
    }

    public void loadNBTData(CompoundTag nbt) {
        heat = nbt.getInt("heat");
    }
}
