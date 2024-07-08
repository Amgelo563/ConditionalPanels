package me.amgelo563.conditionalpanels;

import ce.ajneb97.api.ConditionalEventsAPI;
import me.amgelo563.conditionalpanels.actions.GiveCPItemAction;
import me.amgelo563.conditionalpanels.actions.OpenCPPanelAction;
import me.rockyhawk.commandpanels.CommandPanels;
import me.rockyhawk.commandpanels.api.CommandPanelsAPI;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class ConditionalPanels extends JavaPlugin {
    @Override
    public void onEnable() {
        Logger logger = getLogger();
        CommandPanelsAPI api = CommandPanels.getAPI();

        OpenCPPanelAction openCPPanelAction = new OpenCPPanelAction(logger, api);
        GiveCPItemAction giveCPItemAction = new GiveCPItemAction(logger, api);

        ConditionalEventsAPI.registerApiActions(this, openCPPanelAction, giveCPItemAction);
    }
}
