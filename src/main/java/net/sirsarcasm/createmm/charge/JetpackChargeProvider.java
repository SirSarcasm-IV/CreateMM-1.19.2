package net.sirsarcasm.createmm.charge;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class JetpackChargeProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
    public static Capability<JetpackCharge> JETPACK_CHARGE = CapabilityManager.get(new CapabilityToken<JetpackCharge>() { });

    private JetpackCharge charge = null;
    private final LazyOptional<JetpackCharge> optional = LazyOptional.of(this::createJetpackOverheat);

    private JetpackCharge createJetpackOverheat() {
        if(this.charge == null) {
            this.charge = new JetpackCharge();
        }

        return this.charge;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction direction) {
        if(cap == JETPACK_CHARGE) {
            return optional.cast();
        }

        return null;
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        createJetpackOverheat().saveNBTData(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createJetpackOverheat().loadNBTData(nbt);
    }
}
