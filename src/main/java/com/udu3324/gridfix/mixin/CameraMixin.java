package com.udu3324.gridfix.mixin;

import com.udu3324.gridfix.Gridfix;
import com.udu3324.gridfix.commands.Current;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.Camera;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Camera.class)
public class CameraMixin {
    private static final MinecraftClient client = MinecraftClient.getInstance();

    @Inject(at = @At("HEAD"), method = "setRotation")
    private void setRotation(float yaw, float pitch, CallbackInfo ci) {
        if (client.cameraEntity == null) return;

        Current.yawStored = yaw;
        Current.pitchStored = pitch;

        //Gridfix.LOGGER.info("yaw {} | pitch {}", yaw, pitch);

        if (Gridfix.yaw != null && Gridfix.pitch != null) {
            client.cameraEntity.setAngles(Gridfix.yaw, Gridfix.pitch);
        } else if (Gridfix.yaw != null) {
            client.cameraEntity.setAngles(Gridfix.yaw, pitch);
        } else if (Gridfix.pitch != null) {
            client.cameraEntity.setAngles(yaw, Gridfix.pitch);
        }
    }
}
