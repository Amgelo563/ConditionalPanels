package me.amgelo563.conditionalpanels.actions;

import ce.ajneb97.api.ConditionalEventsAction;
import me.amgelo563.conditionalpanels.actions.request.PanelActionRequest;
import me.rockyhawk.commandpanels.api.CommandPanelsAPI;
import me.rockyhawk.commandpanels.api.Panel;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class AbstractCPPanelAction extends ConditionalEventsAction {
  protected final Logger logger;

  protected final CommandPanelsAPI api;

  public AbstractCPPanelAction(String name, Logger logger, CommandPanelsAPI api) {
    super(name);
    this.logger = logger;
    this.api = api;
  }

  protected void logError(String error) {
    logger.log(Level.SEVERE, error);
  }

  protected PanelActionRequest parseRequest(String args) {
    String[] entries = args.split(" ");
    Map<String, String> map = new HashMap<>();

    String panelName = entries[0];

    if (entries.length > 1) {
      for (String entry : entries) {
        String[] keyValue = entry.split("=");
        if (keyValue.length == 2) {
          map.put(keyValue[0], keyValue[1]);
        }
      }
    }

    if (panelName == null) {
      logError("Invalid arguments on " + this.name + " action: Couldn't find panel name.");
      return null;
    }

    Panel panel = this.api.getPanel(panelName);

    if (panel == null) {
      logError("Invalid arguments on " + this.name + " action: Couldn't find panel with name " + panelName + ".");
      return null;
    }

    return new PanelActionRequest(panel, map);
  }
}
