package net.sirsarcasm.createmm.overheat;

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

public class JetpackOverheatProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
    public static Capability<JetpackOverheat> JETPACK_OVERHEAT = CapabilityManager.get(new CapabilityToken<JetpackOverheat>() { });

    private JetpackOverheat heat = null;
    private final LazyOptional<JetpackOverheat> optional = LazyOptional.of(this::createJetpackOverheat);

    private JetpackOverheat createJetpackOverheat() {
        if(this.heat == null) {
            this.heat = new JetpackOverheat();
        }

        return this.heat;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction direction) {
        if(cap == JETPACK_OVERHEAT) {
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
