package ru.harimasa.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.GameMenuScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ru.harimasa.config.ButtonPositionConfig;
import ru.harimasa.config.ButtonPositionScreen;

@Mixin(GameMenuScreen.class)
public abstract class GameMenuScreenMixin extends Screen {
	@Shadow public abstract void tick();

	protected GameMenuScreenMixin(Text title) {
		super(title);
	}

	@Inject(method = "init", at = @At("TAIL"))
	private void onInit(CallbackInfo ci) {
		ButtonPositionConfig.load();
		if (ButtonPositionConfig.shouldShowConfigureButton()) {
			this.addDrawableChild(ButtonWidget.builder(Text.of("Configure Button"), btn -> MinecraftClient.getInstance().setScreen(new ButtonPositionScreen(this))).dimensions(4, 4, 100, 20).build());
		}
		int defaultX = this.width / 2 - 102;
		int defaultY = this.height / 4 + 120 + 24;
		this.addDrawableChild(ButtonWidget.builder(Text.of("Server Switcher"), button -> MinecraftClient.getInstance().setScreen(new MultiplayerScreen(this))).dimensions(
				ButtonPositionConfig.getX(defaultX),
				ButtonPositionConfig.getY(defaultY),
				204, 20
		).build());
	}
}