package me.amgelo563.conditionalpanels.actions;

import me.amgelo563.conditionalpanels.actions.request.PanelActionRequest;
import me.rockyhawk.commandpanels.api.CommandPanelsAPI;
import me.rockyhawk.commandpanels.api.Panel;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.Map;
import java.util.logging.Logger;

public class GiveCPItemAction extends AbstractCPPanelAction {
  public GiveCPItemAction(Logger logger, CommandPanelsAPI api) {
    super("cp_give_item", logger, api);
  }

  @Override
  public void execute(Player player, String argString) {
    PanelActionRequest request = this.parseRequest(argString);
    if (request == null) {
      return;
    }

    Panel panel = request.getPanel();
    ItemStack item = panel.getHotbarItem(player);
    Map<String, String> args = request.getArgs();
    PlayerInventory inventory = player.getInventory();

    if (args.containsKey("slot")) {
      String slotString = args.get("slot").toLowerCase();
      switch (slotString) {
        case "hand": {
          inventory.setItemInMainHand(item);
          break;
        }
        case "offhand": {
          inventory.setItemInOffHand(item);
          break;
        }
        default: {
          int slot;

          try {
            slot = Integer.parseInt(slotString);
          } catch (NumberFormatException e) {
            this.logError("Invalid arguments on " + this.name + " action: Invalid slot " + slotString + ". Must be HAND, OFF_HAND, or a number.");
            return;
          }

          inventory.setItem(slot, item);
          break;
        }
      }

      return;
    }

    inventory.addItem(item);
  }
}
