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
                .then(literal("reset").executes(ctx -> reset(ctx.getSource())))
                .then(literal("cardinal")
                        .executes(ctx -> Cardinal.help(ctx.getSource()))
                        .then(literal("n").executes(ctx -> Cardinal.yaw(ctx.getSource(), 180)))
                        .then(literal("ne").executes(ctx -> Cardinal.yaw(ctx.getSource(), -135)))
                        .then(literal("e").executes(ctx -> Cardinal.yaw(ctx.getSource(), -90)))
                        .then(literal("se").executes(ctx -> Cardinal.yaw(ctx.getSource(), -45)))
                        .then(literal("s").executes(ctx -> Cardinal.yaw(ctx.getSource(), 0)))
                        .then(literal("sw").executes(ctx -> Cardinal.yaw(ctx.getSource(), 45)))
                        .then(literal("w").executes(ctx -> Cardinal.yaw(ctx.getSource(), 90)))
                        .then(literal("nw").executes(ctx -> Cardinal.yaw(ctx.getSource(), 135)))
                )
        );
    }

    public static int help(FabricClientCommandSource source) {
        source.sendFeedback(Text.literal("Gridfix is by udu3324!"));
        //todo

        return 1;
    }

    public static int reset(FabricClientCommandSource source) {
        source.sendFeedback(Text.literal("Resetting all Gridfix locks."));

        Gridfix.yaw = null;
        Gridfix.pitch = null;

        Gridfix.lockXMouse = false;
        Gridfix.lockYMouse = false;

        return 1;
    }
}
