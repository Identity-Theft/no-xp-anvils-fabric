package identitytheft.norepaircost.mixin;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.EnchantedBookItem;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.AnvilScreenHandler;
import net.minecraft.screen.Property;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AnvilScreenHandler.class)
public class AnvilScreenHandlerMixin {
	@Shadow @Final private Property levelCost;

	@Inject(at = @At("HEAD"), method = "canTakeOutput", cancellable = true)
	private void norepaircost$canTakeOutput(PlayerEntity player, boolean present, CallbackInfoReturnable<Boolean> cir) {
		var screen = ((AnvilScreenHandler) (Object)this).getSlot(1).getStack();

		if (!(screen.getItem() instanceof EnchantedBookItem))
			cir.setReturnValue(true);
	}

	@Inject(at = @At("HEAD"), method = "onTakeOutput")
	private void norepaircost$onTakeOutput(PlayerEntity player, ItemStack stack, CallbackInfo ci) {
		var screen = ((AnvilScreenHandler) (Object)this).getSlot(1).getStack();

		if (!(screen.getItem() instanceof EnchantedBookItem))
			levelCost.set(0);
	}

	@Inject(at = @At("TAIL"), method = "updateResult")
	private void norepaircost$updateResult(CallbackInfo ci) {
		var screen = ((AnvilScreenHandler) (Object)this).getSlot(1).getStack();

		if (!(screen.getItem() instanceof EnchantedBookItem))
			levelCost.set(0);
	}
}