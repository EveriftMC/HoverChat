package com.yxxngtai.hoverchat.utils;

import me.clip.placeholderapi.PlaceholderAPI;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.tag.Tag;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.entity.Player;
import org.jspecify.annotations.NullMarked;

@NullMarked
public class HoverChatUtils {

    /**
     * Creates a tag resolver for formatting minutes into readable time
     *
     * @param minutes the number of minutes
     * @return the tag resolver
     */
    public static TagResolver playtimeResolver(int minutes) {
        int hours = minutes / 60;
        int days = hours / 24;
        StringBuilder formattedTime = new StringBuilder();
        if (days > 0) {
            formattedTime.append(days).append("d ");
            int remainingHours = hours - days * 24;
            formattedTime.append(remainingHours).append("h");
        } else {
            int remainingMinutes = minutes % 60;
            if (hours > 0) {
                formattedTime.append(hours).append("h ");
            }

            formattedTime.append(remainingMinutes).append("m");
        }

        return TagResolver.resolver("playtime", Tag.inserting(
            Component.text(formattedTime.toString())
        ));
    }

    /**
     * Creates a tag resolver capable of resolving PlaceholderAPI tags for a given player.
     *
     * @param player the player
     * @return the tag resolver
     * @author mbaxter
     */
    public static TagResolver papiTag(final Player player) {
        return TagResolver.resolver("papi", (argumentQueue, context) -> {
            // Get the string placeholder that they want to use.
            final String papiPlaceholder = argumentQueue.popOr("papi tag requires an argument").value();

            // Then get PAPI to parse the placeholder for the given player.
            final String parsedPlaceholder = PlaceholderAPI.setPlaceholders(player, '%' + papiPlaceholder + '%');

            // We need to turn this ugly legacy string into a nice component.
            final Component componentPlaceholder = LegacyComponentSerializer.legacySection().deserialize(parsedPlaceholder);

            // Finally, return the tag instance to insert the placeholder!
            return Tag.selfClosingInserting(componentPlaceholder);
        });
    }
}
