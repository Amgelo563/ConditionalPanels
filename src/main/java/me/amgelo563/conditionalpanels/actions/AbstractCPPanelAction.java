package me.amgelo563.conditionalpanels.actions;

import ce.ajneb97.api.ConditionalEventsAction;
import me.amgelo563.conditionalpanels.actions.request.PanelActionRequest;
import me.rockyhawk.commandpanels.api.CommandPanelsAPI;
import me.rockyhawk.commandpanels.api.Panel;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class AbstractCPPanelAction extends ConditionalEventsAction {
  protected static final Pattern PATTERN = Pattern.compile("\\[(.*?)\\]");

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

    if (entries.length == 0) {
      logError("Invalid arguments on " + this.name + " action: Couldn't find panel name.");
      return null;
    }

    String panelName = entries[0];
    Panel panel = this.api.getPanel(panelName);

    if (panel == null) {
      logError("Invalid arguments on " + this.name + " action: Couldn't find panel with name " + panelName + ".");
      return null;
    }

    Map<String, String> map = entries.length > 1 ? parseStringToMap(args) : new HashMap<>();

    return new PanelActionRequest(panel.copy(), map);
  }

  protected HashMap<String, String> parseStringToMap(String input) {
    HashMap<String, String> map = new HashMap<>();
    Matcher matcher = PATTERN.matcher(input);

    while (matcher.find()) {
      String[] keyValue = matcher.group(1).split("=", 2);
      if (keyValue.length == 2) {
        map.put(keyValue[0].trim(), keyValue[1].trim());
      }
    }

    return map;
  }
}
