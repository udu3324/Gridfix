package com.udu3324.gridfix.commands;

import com.udu3324.gridfix.Gridfix;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.text.Text;

public class Cardinal {
    public static final String description = "Lock your viewport to a cardinal direction (north, south, etc.) while having full control in the y-axis.";

    public static int help(FabricClientCommandSource source) {
        source.sendFeedback(Text.literal(description));
        return 1;
    }

    public static int yaw(FabricClientCommandSource source, Integer yaw) {
        String direction = switch (yaw) {
            case 180 -> "North";
            case -135 -> "Northeast";
            case -90 -> "East";
            case -45 -> "Southeast";
            case 0 -> "South";
            case 45 -> "Southwest";
            case 90 -> "West";
            case 135 -> "Northwest";
            default -> String.valueOf(yaw);
        };

        source.sendFeedback(Text.literal("Locking view to " + direction + "."));

        Gridfix.yaw = yaw;
        Gridfix.lockXMouse = true;

        return 1;
    }
}
