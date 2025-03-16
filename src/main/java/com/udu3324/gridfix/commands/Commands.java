package com.udu3324.gridfix.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.udu3324.gridfix.Gridfix;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.text.Text;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;

public class Commands {
    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher) {
        dispatcher.register(literal("gridfix")
                .executes(ctx -> help(ctx.getSource()))
                .then(literal("help").executes(ctx -> help(ctx.getSource())))
                .then(literal("test").executes(ctx -> test(ctx.getSource())))
                .then(literal("test2").executes(ctx -> test2(ctx.getSource())))
        );
    }

    public static int help(FabricClientCommandSource source) {
        source.sendFeedback(Text.literal("Gridfix is by udu3324."));

        return 1;
    }

    public static int test(FabricClientCommandSource source) {
        source.sendFeedback(Text.literal("doing the thing"));

        if (Gridfix.yaw == null) {
            Gridfix.yaw = 180;
            Gridfix.lockXMouse = true;
        } else {
            Gridfix.yaw = null;
            Gridfix.lockXMouse = false;
        }

        return 1;
    }

    public static int test2(FabricClientCommandSource source) {
        source.sendFeedback(Text.literal("doing the thing2"));

        if (Gridfix.yaw == null) {
            Gridfix.yaw = 180;
            Gridfix.pitch = 30;
            Gridfix.lockXMouse = true;
            Gridfix.lockYMouse = true;
        } else {
            Gridfix.yaw = null;
            Gridfix.pitch = null;
            Gridfix.lockXMouse = false;
            Gridfix.lockYMouse = false;
        }

        return 1;
    }

}
