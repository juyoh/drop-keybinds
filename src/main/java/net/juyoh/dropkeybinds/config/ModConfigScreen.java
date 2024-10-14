package net.juyoh.dropkeybinds.config;

import net.juyoh.dropkeybinds.DropKeybinds;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.SliderWidget;
import net.minecraft.client.gui.widget.TextWidget;
import net.minecraft.text.Text;
import net.minecraft.util.math.MathHelper;

public class ModConfigScreen extends Screen {
    public ModConfigScreen() {
        super(Text.of("Drop Keybinds Config"));
    }

    @Override
    public void init() {
        int window_width = MinecraftClient.getInstance().getWindow().getScaledWidth();
        int window_height = MinecraftClient.getInstance().getWindow().getScaledHeight();

        TextWidget mod_name_text = new TextWidget((window_width / 4), 32, (int) (window_width / 2), window_height / 10, Text.translatable("gui.dropkeybinds.config_title"), MinecraftClient.getInstance().textRenderer);
        this.addDrawableChild(mod_name_text);
        addSlider("Keybind 1 Drop Amount", ModConfig.getInstance().amount_1, 1);
        addSlider("Keybind 2 Drop Amount", ModConfig.getInstance().amount_2,2);
        addSlider("Keybind 3 Drop Amount", ModConfig.getInstance().amount_3, 3);
        addSlider("Keybind 4 Drop Amount", ModConfig.getInstance().amount_4, 4);
        addSlider("Keybind 5 Drop Amount", ModConfig.getInstance().amount_5, 5);


    }
    private void addSlider(String name, int initialValue, int index) {
        int window_width = MinecraftClient.getInstance().getWindow().getScaledWidth();
        SliderWidget slider = new SliderWidget(window_width / 8, (index * 30) + 64, (int) (window_width * 0.75), 16, Text.of(name + ": " + initialValue + " items."), initialValue) {

            @Override
            protected void updateMessage() {
                this.setMessage(Text.literal(name + ": " + (int) Math.floor(this.value * 64) + " items."));
            }
            @Override
            protected void applyValue() {
                if (index == 1) {
                    ModConfig.getInstance().amount_1 = (int) Math.floor(this.value * 64); //this.value() gives us a double between 0 and 1, multiply it by 64 to get desired range.
                }
                if (index == 2) {
                    ModConfig.getInstance().amount_2 = (int) Math.floor(this.value * 64);
                }
                if (index == 3) {
                    ModConfig.getInstance().amount_3 = (int) Math.floor(this.value * 64);
                }
                if (index == 4) {
                    ModConfig.getInstance().amount_4 = (int) Math.floor(this.value * 64);
                }
                if (index == 5) {
                    ModConfig.getInstance().amount_5 = (int) Math.floor(this.value * 64);
                }
            }
        };

        this.addDrawableChild(slider);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        this.renderBackground(context, mouseX, mouseY, delta);
        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public void close() {
        super.close();
        ModConfig.saveConfig();
    }

    @Override
    public boolean shouldPause() {
        return Boolean.FALSE;
    }
}