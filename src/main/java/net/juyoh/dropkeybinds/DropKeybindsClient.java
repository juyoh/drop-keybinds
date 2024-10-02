package net.juyoh.dropkeybinds;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.network.packet.c2s.play.PlayerActionC2SPacket;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import org.lwjgl.glfw.GLFW;

import java.util.List;

public class DropKeybindsClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.

		KeyBinding DropStackkeyBinding;
		DropStackkeyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.dropkeybinds.stack", // The translation key of the keybinding's name
				InputUtil.Type.KEYSYM, // The type of the keybinding, KEYSYM for keyboard, MOUSE for mouse.
				GLFW.GLFW_KEY_V, // The keycode of the key
				"key.category.dropkeybinds" // The translation key of the keybinding's category.
		));
		KeyBinding DropHalfkeyBinding;
		DropHalfkeyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.dropkeybinds.half", // The translation key of the keybinding's name
				InputUtil.Type.KEYSYM, // The type of the keybinding, KEYSYM for keyboard, MOUSE for mouse.
				GLFW.GLFW_KEY_Y, // The keycode of the key
				"key.category.dropkeybinds" // The translation key of the keybinding's category.
		));
		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			ClientPlayerEntity client_player = client.player;
			while (DropStackkeyBinding.wasPressed()) {
				client_player.dropSelectedItem(true);
			}
			while (DropHalfkeyBinding.wasPressed()) {
				int count = client.player.getInventory().getMainHandStack().getCount() / 2;
				for (int i = 0; i < count; i++) {
					client_player.dropSelectedItem(false);
				}

			}
			



		});


	}


}
