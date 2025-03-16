package com.udu3324.gridfix.mixin;

import com.udu3324.gridfix.Gridfix;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.Mouse;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Mouse.class)
public class MouseMixin {
    @Shadow
    @Final
    private MinecraftClient client;

    @Shadow private double cursorDeltaX;
    @Shadow private double cursorDeltaY;

    @Inject(method = "updateMouse", at = @At("HEAD"), cancellable = true)
    private void updateMouse(double timeDelta, CallbackInfo ci) {
        if (Gridfix.lockXMouse) {
            cursorDeltaX = 0.0;
        }

        if (Gridfix.lockYMouse) {
            cursorDeltaY = 0.0;
        }
    }
}
