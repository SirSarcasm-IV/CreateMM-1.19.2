package net.sirsarcasm.createmm.charge;

import net.minecraft.nbt.CompoundTag;

public class JetpackCharge {
    private int charge;
    private final int MIN_CHARGE = 0;
    private final int MAX_CHARGE = 1200;

    public int getCharge() {
        return charge;
    }

    public void addCharge(int add) {
        this.charge = Math.min(charge + add, MAX_CHARGE);
    }

    public void subCharge(int sub) {
        this.charge = Math.max(charge - sub, MIN_CHARGE);
    }

    public void copyFrom(JetpackCharge source) {
        this.charge = source.charge;
    }

    public void saveNBTData(CompoundTag nbt) {
        nbt.putInt("heat", charge);
    }

    public void loadNBTData(CompoundTag nbt) {
        charge = nbt.getInt("heat");
    }
}
