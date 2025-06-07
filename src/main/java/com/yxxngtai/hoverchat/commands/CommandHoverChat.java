package com.yxxngtai.hoverchat.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.tree.LiteralCommandNode;
import com.yxxngtai.hoverchat.HoverChatPlugin;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;

@SuppressWarnings("UnstableApiUsage")
public class CommandHoverChat {

    public static LiteralCommandNode<CommandSourceStack> create(HoverChatPlugin plugin) {
        return Commands.literal("hoverchat")
            .requires(stack -> stack.getSender().hasPermission("hoverchat.command"))
            .then(Commands.literal("reload")
                .executes(ctx -> {
                    plugin.reload();
                    ctx.getSource().getSender().sendRichMessage("<gold>Successfully reloaded HoverChat!");
                    return Command.SINGLE_SUCCESS;
                })
            )
            .build();
    }
}