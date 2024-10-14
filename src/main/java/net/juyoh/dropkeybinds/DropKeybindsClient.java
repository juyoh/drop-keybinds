package net.juyoh.dropkeybinds;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.screen.v1.ScreenEvents;
import net.fabricmc.fabric.api.client.screen.v1.Screens;
import net.juyoh.dropkeybinds.config.ModConfig;
import net.juyoh.dropkeybinds.config.ModConfigScreen;
import net.minecraft.client.MinecraftClient;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screen.ButtonTextures;
import net.minecraft.client.gui.screen.option.KeybindsScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TexturedButtonWidget;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.network.packet.c2s.play.PlayerActionC2SPacket;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import org.lwjgl.glfw.GLFW;

import java.util.List;

public class DropKeybindsClient implements ClientModInitializer {


	public void drop_items(int count, ClientPlayerEntity clientPlayer){
		if (count >= 64) {
			clientPlayer.dropSelectedItem(true);
		} else {
			for (int i = 0; i < count; i++) {
				clientPlayer.dropSelectedItem(false);
			}
		}

	}

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
		KeyBinding OpenConfigkeyBinding;
		OpenConfigkeyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.dropkeybinds.config", // The translation key of the keybinding's name
				InputUtil.Type.KEYSYM, // The type of the keybinding, KEYSYM for keyboard, MOUSE for mouse.
				GLFW.GLFW_KEY_L, // The keycode of the key
				"key.category.dropkeybinds" // The translation key of the keybinding's category.
		));

		KeyBinding Amount1keyBinding;
		Amount1keyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.dropkeybinds.amount1", // The translation key of the keybinding's name
				InputUtil.Type.KEYSYM, // The type of the keybinding, KEYSYM for keyboard, MOUSE for mouse.
				InputUtil.UNKNOWN_KEY.getCode(),
				"key.category.dropkeybinds" // The translation key of the keybinding's category.
		));
		KeyBinding Amount2keyBinding;
		Amount2keyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.dropkeybinds.amount2", // The translation key of the keybinding's name
				InputUtil.Type.KEYSYM, // The type of the keybinding, KEYSYM for keyboard, MOUSE for mouse.
				InputUtil.UNKNOWN_KEY.getCode(),
				"key.category.dropkeybinds" // The translation key of the keybinding's category.
		));
		KeyBinding Amount3keyBinding;
		Amount3keyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.dropkeybinds.amount3", // The translation key of the keybinding's name
				InputUtil.Type.KEYSYM, // The type of the keybinding, KEYSYM for keyboard, MOUSE for mouse.
				InputUtil.UNKNOWN_KEY.getCode(),
				"key.category.dropkeybinds" // The translation key of the keybinding's category.
		));
		KeyBinding Amount4keyBinding;
		Amount4keyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.dropkeybinds.amount4", // The translation key of the keybinding's name
				InputUtil.Type.KEYSYM, // The type of the keybinding, KEYSYM for keyboard, MOUSE for mouse.
				InputUtil.UNKNOWN_KEY.getCode(),
				"key.category.dropkeybinds" // The translation key of the keybinding's category.
		));
		KeyBinding Amount5keyBinding;
		Amount5keyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.dropkeybinds.amount5", // The translation key of the keybinding's name
				InputUtil.Type.KEYSYM, // The type of the keybinding, KEYSYM for keyboard, MOUSE for mouse.
				InputUtil.UNKNOWN_KEY.getCode(),
				"key.category.dropkeybinds" // The translation key of the keybinding's category.
		));

		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			ClientPlayerEntity client_player = client.player;
			if (DropStackkeyBinding.wasPressed()) {
				client_player.dropSelectedItem(true);
			}

			if (Amount1keyBinding.wasPressed()){
				drop_items(ModConfig.amount_1, client_player);
			}
			if (Amount2keyBinding.wasPressed()){
				drop_items(ModConfig.amount_2, client_player);
			}
			if (Amount3keyBinding.wasPressed()){
				drop_items(ModConfig.amount_3, client_player);
			}
			if (Amount4keyBinding.wasPressed()){
				drop_items(ModConfig.amount_4, client_player);
			}
			if (Amount5keyBinding.wasPressed()){
				drop_items(ModConfig.amount_5, client_player);
			}


			if (DropHalfkeyBinding.wasPressed()) {
				int count = client.player.getInventory().getMainHandStack().getCount() / 2;
				drop_items(count, client_player);
			}
			if(OpenConfigkeyBinding.wasPressed()){
				client.setScreen(new ModConfigScreen());
			}
		});
		ScreenEvents.AFTER_INIT.register(((client, screen, scaledWidth, scaledHeight) -> {
			if (screen instanceof KeybindsScreen) {
				Screens.getButtons(screen).add(
						ButtonWidget.builder(Text.translatable("gui.dropkeybinds.openconfigbutton"), press -> {
                            MinecraftClient.getInstance().setScreen(new ModConfigScreen()); //Add a button to the Keybinding menu
                        }).dimensions(16, 4, 160, 24).build()
				);
			}
		}));



	}


}
