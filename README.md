# 📖 ConditionalPanels

A [ConditionalEvents](https://modrinth.com/plugin/conditionalevents) addon that adds integration with [CommandPanels](https://commandpanels.net/)

## 📦 Installation

> [!WARNING]
> While the plugin should work from 1.8 to 1.21, it requires **Java 17**.
> It's not advised to use ancient software like Spigot 1.8.8 or PaperSpigot 1.8.8. They can have issues with modern Java versions.
> If you **really** need 1.8, consider using a maintained fork that fully supports modern Java.

To install:
* Download the latest version from the [Releases page](https://github.com/Amgelo563/ConditionalPanels/releases/latest)
* Drop the plugin jar in your plugins folder. Make sure you have ConditionalEvents `4.47.1`+ and CommandPanels installed.
* Enjoy!

## 🚀 Actions

Actions are used in the format:
```
action_name: mainArgument [extraArgument= value] [otherArgument= otherValue]
```

### `cp_open`
Opens a CommandPanels panel to the player.

#### Usage:
```
cp_open: panel_name [position=Top/Bottom/Middle] [placeholders= placeholder1: value1; placeholder2: value2; ...]
```

#### Examples:
```yaml
actions:
    default:
      # Opens the "menu" panel
      - "cp_open: menu"

      # Opens the "menu" panel in the Middle position
      - "cp_open: menu [position=Middle]"
        
      # Opens the "menu" panel with the placeholders "example" and "example2"
      - "cp_open: menu [placeholders= example: Placeholder Value; example2: Other placeholder]"

      # Opens the "menu" panel with the placeholders "example" and "example2", in the Middle position
      - "cp_open: menu [placeholders= example: Placeholder Value; example2: Other placeholder] [position=Middle]"
```

### `cp_give_item`
Gives or sets a CommandPanels item to the player.

#### Usage:
```
cp_give_item: panel_name [slot=Hand/Offhand/1/2/3/...]
```

If a slot isn't provided, the item will be given (filling an empty slot).

#### Examples:
```yaml
actions:
    default:
      # Gives the "menu" panel's item
      - "cp_give_item: menu"
        
      # Sets the player's item in their hand to the "menu" panel's item
      - "cp_give_item: menu [slot=Hand]"
        
      # Sets the player's item in their offhand to the "menu" panel's item
      - "cp_give_item: menu [slot=Offhand]"
        
      # Sets the player's item in their slot 1 to the "menu" panel's item
      - "cp_give_item: menu [slot=1]" 
```
