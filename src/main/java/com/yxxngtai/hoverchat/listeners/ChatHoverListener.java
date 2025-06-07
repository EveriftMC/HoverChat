package com.yxxngtai.hoverchat.listeners;

import com.yxxngtai.hoverchat.HoverChat;
import com.yxxngtai.hoverchat.utils.TimeFormatter;
import me.clip.placeholderapi.PlaceholderAPI;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.HoverEvent.Action;
import net.md_5.bungee.api.chat.hover.content.Content;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatHoverListener implements Listener {
   HoverChat plugin;

   public ChatHoverListener(HoverChat plugin) {
      this.plugin = plugin;
   }

   @EventHandler
   public void onChatTextHover(AsyncPlayerChatEvent event) {
      Player player = event.getPlayer();
      TextComponent msg = new TextComponent(
         ChatColor.translateAlternateColorCodes('&', PlaceholderAPI.setPlaceholders(player, "%vault_prefix%%player_name% &f")) + event.getMessage()
      );
      String hoverText = this.plugin.getConfig().getString("hover-text");
      if (hoverText != null) {
         int playTimeMinutes = Integer.parseInt(PlaceholderAPI.setPlaceholders(player, "%player_minutes_lived%"));
         String playTime = TimeFormatter.formatMinutes(playTimeMinutes);
         hoverText = hoverText.replaceAll("_formatted_playtime_", playTime);
         msg.setHoverEvent(
            new HoverEvent(
               Action.SHOW_TEXT, new Content[]{new Text(ChatColor.translateAlternateColorCodes('&', PlaceholderAPI.setPlaceholders(player, hoverText)))}
            )
         );
      }

      Bukkit.spigot().broadcast(msg);
      event.setCancelled(true);
   }
}
