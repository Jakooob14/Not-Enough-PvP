package io.github.jakooob14.nep.config;

import io.github.jakooob14.nep.NEPMod;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
@Config(name = NEPMod.MOD_ID)
public class NEPConfig implements ConfigData {
    @ConfigEntry.Gui.Tooltip
    public boolean overwritePosition = false;
    @ConfigEntry.Gui.Tooltip
    public int xPosition = 0;
    @ConfigEntry.Gui.Tooltip
    public String position = "middle";
}