package ru.harimasa.config;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.CheckboxWidget;
import net.minecraft.text.Text;

public class ButtonPositionScreen extends Screen {
    private DraggableButton draggableButton;
    private CheckboxWidget showButtonCheckbox;
    private final Screen parent;

    public ButtonPositionScreen(Screen parent) {
        super(Text.of("Button Configuration"));
        this.parent = parent;
    }

    @Override
    protected void init() {
        // Draggable button
        int defaultX = MinecraftClient.getInstance().getWindow().getScaledWidth() - this.width / 2 - 100;
        int defaultY = MinecraftClient.getInstance().getWindow().getScaledHeight() - this.height / 4 + 120;
        draggableButton = new DraggableButton(
                ButtonPositionConfig.getX(defaultX),
                ButtonPositionConfig.getY(defaultY),
                204, 20,
                Text.of("Server Switcher (Drag Me)"),
                button -> {}
        );
        this.addDrawableChild(draggableButton);

        // Checkbox
        showButtonCheckbox = CheckboxWidget.builder(Text.of("Show Configure Button in Pause Menu"), textRenderer)
                .pos(this.width / 2 - 100, this.height - 80)
                .tooltip(Tooltip.of(Text.of("Hides or displays the button to enter config from the ESC menu")))
                .checked(ButtonPositionConfig.shouldShowConfigureButton())
                .build();
        this.addDrawableChild(showButtonCheckbox);

        // Save button
        this.addDrawableChild(ButtonWidget.builder(Text.of("Save"), button -> {
            ButtonPositionConfig.save(
                    draggableButton.getX(),
                    draggableButton.getY(),
                    showButtonCheckbox.isChecked()
            );
            MinecraftClient.getInstance().setScreen(parent);
        }).dimensions(this.width / 2 - 100, this.height - 40, 200, 20).build());
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        this.renderBackground(context, mouseX, mouseY, delta);
        super.render(context, mouseX, mouseY, delta);
        context.drawCenteredTextWithShadow(textRenderer, "Drag the button to desired position", this.width / 2, 20, 0xFFFFFF);
    }
}