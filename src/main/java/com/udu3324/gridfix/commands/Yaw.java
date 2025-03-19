package com.udu3324.gridfix.commands;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.udu3324.gridfix.Gridfix;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.text.Text;

public class Yaw {
    public static final String description = "Lock your viewport at a yaw (north, south, 83 degrees, etc.) while having full control in the y-axis.";

    public static int help(FabricClientCommandSource source) {
        source.sendFeedback(Text.literal(description));
        return 1;
    }

    public static int reset(FabricClientCommandSource source) {
        source.sendFeedback(Text.literal("Unlocking yaw angle."));

        Gridfix.yaw = null;
        Gridfix.lockXMouse = false;

        return 1;
    }

    public static int set(FabricClientCommandSource source, Integer yaw) {
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

        Gridfix.yaw = Float.valueOf(yaw);
        Gridfix.lockXMouse = true;

        return 1;
    }

    public static int angle(CommandContext<FabricClientCommandSource> context) {
        String input = StringArgumentType.getString(context, "angle");
        float parse;

        // check if it's a valid int
        try {
            parse = Float.parseFloat(input);
        } catch (NumberFormatException e) {
            context.getSource().sendFeedback(Text.literal("Could not lock view to \"" + input + "\""));
            return -1;
        }

        context.getSource().sendFeedback(Text.literal("Locking view to " + input + " degrees."));

        Gridfix.yaw = parse;
        Gridfix.lockXMouse = true;

        return 1;
    }
}
