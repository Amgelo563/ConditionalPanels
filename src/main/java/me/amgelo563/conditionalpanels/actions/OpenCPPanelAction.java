package me.amgelo563.conditionalpanels.actions;

import me.amgelo563.conditionalpanels.actions.request.PanelActionRequest;
import me.rockyhawk.commandpanels.api.CommandPanelsAPI;
import me.rockyhawk.commandpanels.api.Panel;
import me.rockyhawk.commandpanels.openpanelsmanager.PanelPosition;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.logging.Logger;

public class OpenCPPanelAction extends AbstractCPPanelAction {
  public OpenCPPanelAction(Logger logger, CommandPanelsAPI api) {
    super("cp_open", logger, api);
  }

  @Override
  public void execute(Player player, String argString) {
    PanelActionRequest request = this.parseRequest(argString);
    if (request == null) {
      return;
    }

    Panel panel = request.getPanel();
    Map<String, String> args = request.getArgs();

    PanelPosition panelPosition = PanelPosition.Top;
    if (args.containsKey("position")) {
      String position = args.get("position");
      try {
        panelPosition = PanelPosition.valueOf(position);
      } catch (IllegalArgumentException e) {
        this.logError("Invalid arguments on " + this.name + " action: Invalid panel position " + position + ".");
        return;
      }
    }

    if (args.containsKey("placeholders")) {
      String allPlaceholders = args.get("placeholders");
      this.parseAndAddPlaceholders(allPlaceholders, panel);
    }

    panel.open(player, panelPosition);
  }

  protected void parseAndAddPlaceholders(String input, Panel panel) {
    String[] pairs = input.split(";");

    for (String pair : pairs) {
      String[] keyValue = pair.split(":");
      if (keyValue.length == 2) {
        String key = keyValue[0].trim();
        String value = keyValue[1].trim();
        panel.placeholders.addPlaceholder(key, value);
      } else {
        this.logError("Invalid arguments on " + this.name + " action: Invalid pair " + pair + " for placeholders " + input + ".");
      }
    }
  }
}
