package io.github.jakooob14.nep;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

@Environment(EnvType.CLIENT)
public class NEPModClient implements ClientModInitializer {
    private static KeyBinding keyBinding;
    public static boolean missingArmorEnabled = true;
    @Override
    public void onInitializeClient() {
        keyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.nep.toggleArmor",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_G,
                "key.category.nep.main"
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (keyBinding.wasPressed()) {
                missingArmorEnabled = !missingArmorEnabled;
                assert client.player != null;
                client.player.sendMessage(missingArmorEnabled ? Text.translatable("text.missingArmorToggleMessage.enabled") : Text.translatable("text.missingArmorToggleMessage.disabled"));
            }
        });
    }
}
