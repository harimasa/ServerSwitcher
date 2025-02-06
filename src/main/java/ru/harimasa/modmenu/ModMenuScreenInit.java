package ru.harimasa.modmenu;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import ru.harimasa.config.ButtonPositionScreen;

public class ModMenuScreenInit implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return ButtonPositionScreen::new;
    }
}