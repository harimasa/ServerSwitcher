package ru.harimasa.config;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import net.minecraft.util.math.MathHelper;

public class DraggableButton extends ButtonWidget {
    private boolean dragging;
    private double dragOffsetX;
    private double dragOffsetY;

    public DraggableButton(int x, int y, int width, int height, Text message, PressAction onPress) {
        super(x, y, width, height, message, onPress, DEFAULT_NARRATION_SUPPLIER);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (active && visible && isMouseOver(mouseX, mouseY)) {
            this.dragging = true;
            this.dragOffsetX = mouseX - this.getX();
            this.dragOffsetY = mouseY - this.getY();
            return true;
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        if (this.dragging) {
            int newX = (int) (mouseX - this.dragOffsetX);
            int newY = (int) (mouseY - this.dragOffsetY);
            this.setPosition(
                    MathHelper.clamp(newX, 0, MinecraftClient.getInstance().getWindow().getScaledWidth() - this.width),
                    MathHelper.clamp(newY, 0, MinecraftClient.getInstance().getWindow().getScaledHeight() - this.height)
            );
            return true;
        }
        return super.mouseDragged(mouseX, mouseY, button, deltaX, deltaY);
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        this.dragging = false;
        return super.mouseReleased(mouseX, mouseY, button);
    }
}