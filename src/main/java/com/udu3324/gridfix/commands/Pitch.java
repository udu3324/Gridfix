package com.udu3324.gridfix.commands;

import com.mojang.brigadier.arguments.FloatArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.udu3324.gridfix.Gridfix;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.text.Text;

public class Pitch {
    public static final String description = "Lock your viewport at a pitch (83 degrees, etc.) while having full control in the x-axis.";

    public static int help(FabricClientCommandSource source) {
        source.sendFeedback(Text.literal(description));
        return 1;
    }

    public static int reset(FabricClientCommandSource source) {
        source.sendFeedback(Text.literal("Unlocking pitch angle."));

        Gridfix.pitch = null;
        Gridfix.lockYMouse = false;

        return 1;
    }

    public static int angle(CommandContext<FabricClientCommandSource> context) {
        float input = FloatArgumentType.getFloat(context, "angle");

        context.getSource().sendFeedback(Text.literal("Locking view to " + input + " degrees."));

        Gridfix.pitch = input;
        Gridfix.lockYMouse = true;

        return 1;
    }
}
