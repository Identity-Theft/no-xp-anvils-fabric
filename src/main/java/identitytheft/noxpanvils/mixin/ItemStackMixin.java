package identitytheft.noxpanvils.mixin;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {
    @Shadow public abstract NbtCompound getOrCreateNbt();

    @Inject(method = "getRepairCost", at = @At("HEAD"), cancellable = true)
    public void noxpcost$getRepairCost(CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(0);
    }

    @Inject(method = "setRepairCost", at = @At("HEAD"), cancellable = true)
    public void noxpcost$setRepairCost(int repairCost, CallbackInfo ci) {
        getOrCreateNbt().putInt("RepairCost", 0);
        ci.cancel();
    }
}
