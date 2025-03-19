package com.udu3324.gridfix.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.udu3324.gridfix.Gridfix;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.text.*;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.argument;
import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;

public class Commands {
    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher) {
        dispatcher.register(literal("gridfix")
                .executes(ctx -> help(ctx.getSource()))
                .then(literal("help").executes(ctx -> help(ctx.getSource())))
                .then(literal("unlock_yaw").executes(ctx -> Yaw.reset(ctx.getSource())))
                .then(literal("unlock_pitch").executes(ctx -> Pitch.reset(ctx.getSource())))
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
        source.sendFeedback(Text.literal("Gridfix v" + Gridfix.version.toString() + " - udu3324").styled(style -> style
                .withColor(TextColor.fromRgb(0x86c2a2))
                .withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, Text.literal("Gridfix is by udu3324. \nClick here to look at the repository!")))
                .withClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://github.com/udu3324/gridfix"))
                .withBold(true)
        ));

        source.sendFeedback(Text.literal("> unlock_yaw").styled(style -> style
                .withColor(TextColor.fromRgb(0xd5f5e4))
                .withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, Text.literal("Reset yaw lock.")))
                .withClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/gridfix unlock_yaw"))
        ));

        source.sendFeedback(Text.literal("> unlock_pitch").styled(style -> style
                .withColor(TextColor.fromRgb(0xd5f5e4))
                .withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, Text.literal("Reset pitch lock.")))
                .withClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/gridfix unlock_pitch"))
        ));

        source.sendFeedback(Text.literal("> unlock_all").styled(style -> style
                .withColor(TextColor.fromRgb(0xd5f5e4))
                .withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, Text.literal("Reset all gridfix locks currently enabled.")))
                .withClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/gridfix unlock_all"))
        ));

        source.sendFeedback(Text.literal("> current_angle").styled(style -> style
                .withColor(TextColor.fromRgb(0xd5f5e4))
                .withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, Text.literal(Current.description)))
                .withClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/gridfix current_angle"))
        ));

        source.sendFeedback(Text.literal("> yaw").styled(style -> style
                .withColor(TextColor.fromRgb(0xd5f5e4))
                .withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, Text.literal(Yaw.description)))
                .withClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/gridfix yaw "))
        ));

        source.sendFeedback(Text.literal("> pitch").styled(style -> style
                .withColor(TextColor.fromRgb(0xd5f5e4))
                .withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, Text.literal(Pitch.description)))
                .withClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/gridfix pitch "))
        ));

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
