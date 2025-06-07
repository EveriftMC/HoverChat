package com.yxxngtai.hoverchat.listeners;

import com.yxxngtai.hoverchat.HoverChatPlugin;
import com.yxxngtai.hoverchat.utils.HoverChatUtils;
import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.jspecify.annotations.NullMarked;

@NullMarked
public class ChatHoverListener implements Listener {

    private final HoverChatPlugin plugin;

    public ChatHoverListener(HoverChatPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onChatTextHover(AsyncChatEvent event) {
        Player player = event.getPlayer();
        int playTimeMinutes = player.getStatistic(Statistic.PLAY_ONE_MINUTE) / 20 / 60;

        MiniMessage miniMessage = MiniMessage.builder()
            .editTags(adder -> {
                adder.resolver(HoverChatUtils.playtimeResolver(playTimeMinutes));
                adder.resolver(HoverChatUtils.papiTag(player));
            })
            .build();
        TagResolver hoverResolver = Placeholder.styling("hover_format", miniMessage.deserialize(plugin.getHoverFormat()).asHoverEvent());

        event.renderer((source, sourceDisplayName, message, viewer) -> miniMessage.deserialize(plugin.getChatFormat(), hoverResolver));
    }
}