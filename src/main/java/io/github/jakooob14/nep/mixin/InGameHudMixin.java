package io.github.jakooob14.nep.mixin;

import io.github.jakooob14.nep.NEPModClient;
import io.github.jakooob14.nep.config.NEPConfig;
import me.shedaniel.autoconfig.AutoConfig;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(InGameHud.class)
public class InGameHudMixin {
    @Shadow @Final private ItemRenderer itemRenderer;
    NEPConfig config = AutoConfig.getConfigHolder(NEPConfig.class).getConfig();

    // Default positions
    int helmetY = -16;
    int chestplateY = -16;
    int leggingsY = -16;
    int bootsY = -16;

        @Inject(at = @At("TAIL"), method = "render")
        private void render (MatrixStack matrices, float tickDelta, CallbackInfo info){
            MinecraftClient client = MinecraftClient.getInstance();
            int posX = 6;
            int offsetX = 20;
            String configPosition = config.position;
            String position;

            if (!config.overwritePosition) {
                // Check if config position is any of the available positions
                if (configPosition.equalsIgnoreCase("left") || configPosition.equalsIgnoreCase("middle") || configPosition.equalsIgnoreCase("right"))
                    position = configPosition;
                else position = "middle";

                // Set X position to predefined positions from config
                if (position.equalsIgnoreCase("left")) posX = 30;
                else if (position.equalsIgnoreCase("middle")) posX = (client.getWindow().getScaledWidth() - 38) / 2;
                else if (position.equalsIgnoreCase("right")) posX = client.getWindow().getScaledWidth() - 64;
            } else posX = config.xPosition;

            // Check if player is not null
            if (client.player != null) {
                // Check if armor slots are empty
                if (client.player.getInventory().armor.get(3).getItem().equals(Items.AIR) && NEPModClient.missingArmorEnabled) {
                    if (helmetY < 7) {
                        helmetY += 2;
                    }
                    this.itemRenderer.renderInGuiWithOverrides(new ItemStack(Items.NETHERITE_HELMET), posX - offsetX, helmetY);
                    this.itemRenderer.renderInGuiWithOverrides(new ItemStack(Items.BARRIER), posX - offsetX, helmetY);
                } else {
                    if (helmetY > -17) {
                        helmetY -= 2;
                    }
                    if (helmetY > -17) {
                        this.itemRenderer.renderInGuiWithOverrides(new ItemStack(Items.NETHERITE_HELMET), posX - offsetX, helmetY);
                        this.itemRenderer.renderInGuiWithOverrides(new ItemStack(Items.BARRIER), posX - offsetX, helmetY);
                    }
                }
                if (client.player.getInventory().armor.get(2).getItem().equals(Items.AIR) && NEPModClient.missingArmorEnabled) {
                    if (chestplateY < 7) {
                        chestplateY += 2;
                    }
                    this.itemRenderer.renderInGuiWithOverrides(new ItemStack(Items.NETHERITE_CHESTPLATE), posX, chestplateY);
                    this.itemRenderer.renderInGuiWithOverrides(new ItemStack(Items.BARRIER), posX, chestplateY);
                } else {
                    if (chestplateY > -17) {
                        chestplateY -= 2;
                    }
                    if (chestplateY > -17) {
                        this.itemRenderer.renderInGuiWithOverrides(new ItemStack(Items.NETHERITE_CHESTPLATE), posX, chestplateY);
                        this.itemRenderer.renderInGuiWithOverrides(new ItemStack(Items.BARRIER), posX, chestplateY);
                    }
                }
                if (client.player.getInventory().armor.get(1).getItem().equals(Items.AIR) && NEPModClient.missingArmorEnabled) {
                    if (leggingsY < 7) {
                        leggingsY += 2;
                    }
                    this.itemRenderer.renderInGuiWithOverrides(new ItemStack(Items.NETHERITE_LEGGINGS), posX + offsetX, leggingsY);
                    this.itemRenderer.renderInGuiWithOverrides(new ItemStack(Items.BARRIER), posX + offsetX, leggingsY);
                } else {
                    if (leggingsY > -17) {
                        leggingsY -= 2;
                    }
                    if (leggingsY > -17) {
                        this.itemRenderer.renderInGuiWithOverrides(new ItemStack(Items.NETHERITE_LEGGINGS), posX + offsetX, leggingsY);
                        this.itemRenderer.renderInGuiWithOverrides(new ItemStack(Items.BARRIER), posX + offsetX, leggingsY);
                    }
                }
                if (client.player.getInventory().armor.get(0).getItem().equals(Items.AIR) && NEPModClient.missingArmorEnabled) {
                    if (bootsY < 7) {
                        bootsY += 2;
                    }
                    this.itemRenderer.renderInGuiWithOverrides(new ItemStack(Items.NETHERITE_BOOTS), posX + (offsetX * 2), bootsY);
                    this.itemRenderer.renderInGuiWithOverrides(new ItemStack(Items.BARRIER), posX + (offsetX * 2), bootsY);
                } else {
                    if (bootsY > -17) {
                        bootsY -= 2;
                    }
                    if (bootsY > -17) {
                        this.itemRenderer.renderInGuiWithOverrides(new ItemStack(Items.NETHERITE_BOOTS), posX + (offsetX * 2), bootsY);
                        this.itemRenderer.renderInGuiWithOverrides(new ItemStack(Items.BARRIER), posX + (offsetX * 2), bootsY);
                    }
                }
            }
        }
    }
