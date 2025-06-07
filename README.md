# HoverChat

A basic chat plugin for Everift with hover capabilities and Vault/LuckPerms hooks!

## Configuration
The default `config.yml` looks like this:

```yml
chat-format: <hover_format><prefix_style><papi:player_name></prefix_style> <dark_gray>»</dark_gray> <message>

hover-format: |
  <prefix:' <b><papi:player_name>'> <gray>[<light_purple><papi:player_ping></light_purple>]</gray>
  <gray>▰▰▰▰▰▰▰▰▰▰▰▰▰</gray>
  <gold>☆ <white><prefix/></gold>
  <green>$ <white>Balance</white> <papi:vault_eco_balance></green>
  <blue>⌚ <white>Playtime</white> <playtime></blue>
  <light_purple>⚔ <white>Kills</white> <papi:statistic_player_kills></light_purple>
  <red>☠ <white>Deaths</white> <papi:statistic_deaths>
  <gray>▰▰▰▰▰▰▰▰▰▰▰▰▰</gray>
```

## Custom tags
There are a few custom tags to use:
- `<hover_format>`: Styles the content to show the hover text defined in `hover-format`
- `<prefix:<optional_content>`: Inserts the player's LuckPerms prefix. The style of the prefix only bleeds over
  to the content
- `<prefix_style>`: Style some content in the same style which would bleed over from the player's prefix
- `<papi:<arg>>`: The same as `%<arg>%`
- `<message>`: Inserts the player's message