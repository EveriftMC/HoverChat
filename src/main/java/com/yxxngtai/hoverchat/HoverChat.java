package com.yxxngtai.hoverchat;

import com.yxxngtai.hoverchat.listeners.ChatHoverListener;
import org.bukkit.plugin.java.JavaPlugin;

public class HoverChat extends JavaPlugin {
   public void onEnable() {
      this.getLogger().info("onEnable is called!");
      this.saveDefaultConfig();
      this.getServer().getPluginManager().registerEvents(new ChatHoverListener(this), this);
   }

   public void onDisable() {
      this.getLogger().info("onDisable is called!");
   }
}
