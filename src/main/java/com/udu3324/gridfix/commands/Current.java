package com.udu3324.gridfix.commands;

import com.udu3324.gridfix.Gridfix;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.text.Text;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Current {
    public static final String description = "Lock your viewport to the current angle being held.";

    public static Float yawStored;
    public static Float pitchStored;

    public static int angle(FabricClientCommandSource source) {
        source.sendFeedback(Text.literal("Locking viewport to (" + round(yawStored, 2) + " / " + round(pitchStored, 2) + ")"));

        Gridfix.yaw = yawStored;
        Gridfix.pitch = pitchStored;

        Gridfix.lockXMouse = true;
        Gridfix.lockYMouse = true;

        return 1;
    }

    //ty https://stackoverflow.com/a/8911683
    public static BigDecimal round(float d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Float.toString(d));
        bd = bd.setScale(decimalPlace, RoundingMode.HALF_UP);
        return bd;
    }
}
