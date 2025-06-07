package com.yxxngtai.hoverchat;

import com.google.common.base.Preconditions;
import com.mojang.brigadier.tree.LiteralCommandNode;
import com.yxxngtai.hoverchat.commands.CommandHoverChat;
import com.yxxngtai.hoverchat.listeners.ChatHoverListener;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import net.luckperms.api.LuckPerms;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

@SuppressWarnings("UnstableApiUsage")
@NullMarked
public class HoverChatPlugin extends JavaPlugin {

    private @Nullable String chatFormat;
    private @Nullable String hoverFormat;

    @Override
    public void onLoad() {
        LiteralCommandNode<CommandSourceStack> command = CommandHoverChat.create(this);
        this.getLifecycleManager().registerEventHandler(LifecycleEvents.COMMANDS.newHandler(
            event -> event.registrar().register(command, "Admin command for the HoverChat plugin")
        ));
    }

    @Override
    public void onEnable() {
        reload();
        this.getServer().getPluginManager().registerEvents(new ChatHoverListener(this), this);
    }

    public void reload() {
        this.saveDefaultConfig();
        this.reloadConfig();

        chatFormat = this.getConfig().getString("chat-format");
        hoverFormat = this.getConfig().getString("hover-format");
    }

    public String getChatFormat() {
        Preconditions.checkState(chatFormat != null, "chat-format is not set.");
        return chatFormat;
    }

    public String getHoverFormat() {
        Preconditions.checkState(hoverFormat != null, "hover-format is not set.");
        return hoverFormat;
    }

    public static boolean hasLuckPerms() {
        try {
            Class.forName("net.luckperms.api.LuckPerms");
            return true;
        } catch (ClassNotFoundException noLuckPermsFound) {
            return false;
        }
    }

    public static LuckPerms getLuckPerms() {
        RegisteredServiceProvider<LuckPerms> provider = Bukkit.getServicesManager().getRegistration(LuckPerms.class);
        if (provider != null) {
            return provider.getProvider();
        }

        throw new IllegalStateException("LuckPerms is not loaded!");
    }
}