package com.udu3324.gridfix.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.udu3324.gridfix.Gridfix;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.text.Text;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.argument;
import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;

public class Commands {
    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher) {
        dispatcher.register(literal("gridfix")
                .executes(ctx -> help(ctx.getSource()))
                .then(literal("help").executes(ctx -> help(ctx.getSource())))
                .then(literal("unlock_all").executes(ctx -> reset(ctx.getSource())))
                .then(literal("yaw")
                        .executes(ctx -> Yaw.help(ctx.getSource()))
                        .then(argument("angle", StringArgumentType.greedyString()).executes(Yaw::angle))
                        .then(literal("n").executes(ctx -> Yaw.set(ctx.getSource(), 180)))
                        .then(literal("ne").executes(ctx -> Yaw.set(ctx.getSource(), -135)))
                        .then(literal("e").executes(ctx -> Yaw.set(ctx.getSource(), -90)))
                        .then(literal("se").executes(ctx -> Yaw.set(ctx.getSource(), -45)))
                        .then(literal("s").executes(ctx -> Yaw.set(ctx.getSource(), 0)))
                        .then(literal("sw").executes(ctx -> Yaw.set(ctx.getSource(), 45)))
                        .then(literal("w").executes(ctx -> Yaw.set(ctx.getSource(), 90)))
                        .then(literal("nw").executes(ctx -> Yaw.set(ctx.getSource(), 135)))
                )
                .then(literal("pitch")
                        .executes(ctx -> Pitch.help(ctx.getSource()))
                        .then(argument("angle", StringArgumentType.greedyString()).executes(Pitch::angle))
                )
                .then(literal("current_angle").executes(ctx -> Current.angle(ctx.getSource())))
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
