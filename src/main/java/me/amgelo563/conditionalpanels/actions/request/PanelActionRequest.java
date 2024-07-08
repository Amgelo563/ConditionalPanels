package me.amgelo563.conditionalpanels.actions.request;

import me.rockyhawk.commandpanels.api.Panel;

import java.util.Map;

public class PanelActionRequest {
  protected final Panel panel;

  protected final Map<String, String> args;

  public PanelActionRequest(Panel panel, Map<String, String> args) {
    this.panel = panel;
    this.args = args;
  }

  public Panel getPanel() {
    return this.panel;
  }

  public Map<String, String> getArgs() {
    return this.args;
  }
}
