package me.pog5.leashmod.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import me.pog5.leashmod.LeashImpl;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(PlayerEntity.class)
public abstract class MixinPlayerEntity {
    @ModifyReturnValue(method = "interact", at = @At("RETURN"))
    private ActionResult leashplayers$onInteract(ActionResult original, @Local(argsOnly = true) Entity entity, @Local(argsOnly = true) Hand hand) {
        if (original != ActionResult.PASS) return original;
        if ((Object) this instanceof ServerPlayerEntity player && entity instanceof LeashImpl impl) {
            return impl.leashplayers$interact(player, hand);
        }
        return original;
    }
}
