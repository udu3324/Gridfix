package com.udu3324.gridfix.commands;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.udu3324.gridfix.utils.Grid;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.text.Text;

public class GridGuide {
    public static String description = "Show a customizable grid that follows the player and guides motion.";

    public static int help(FabricClientCommandSource source) {
        source.sendFeedback(Text.literal(description));
        return 1;
    }

    public static int toggle(FabricClientCommandSource source) {
        source.sendFeedback(Text.literal("Toggled GridGuide to " + !Grid.render + "."));

        Grid.render = !Grid.render;

        return 1;
    }

    public static int size(CommandContext<FabricClientCommandSource> context) {
        int input = IntegerArgumentType.getInteger(context, "size");

        context.getSource().sendFeedback(Text.literal("Set size of grid to " + input));

        Grid.size = input;
        return 1;
    }
}
