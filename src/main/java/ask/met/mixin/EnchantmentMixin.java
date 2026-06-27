package ask.met.mixin;

import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.npc.VillagerTrades.EnchantBookForEmeralds;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(EnchantBookForEmeralds.class)
public abstract class EnchantmentMixin {

	/* Minecraft runs getOffer to... get the trade offer. When doing this, it can use a random method to choose the
	enchantment's level. This mixin just tells that method to give the maximum level tradeable by default. */

	@Redirect(
			// the method this function is called in
			method = "getOffer",
			// tell method to shut up and return this instead.
			at = @At(
					value = "INVOKE",
					target = "Lnet/minecraft/util/Mth;nextInt(Lnet/minecraft/util/RandomSource;II)I"
			)
	)
	private int nextInt(RandomSource randomSource, int min, int max) {
		return max;
	}
}