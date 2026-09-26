package cn.zbx1425.worldcomment.platform.multiver;

import cn.zbx1425.worldcomment.mixin.HudAccessor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;

public class MinecraftClientShim {

    public static Screen guiGetScreen() {
        //? if >=26.3 {
        return Minecraft.getInstance().gui.screen();
        //? } else {
        /*return Minecraft.getInstance().screen;
        *///? }
    }

    public static void guiSetScreen(Screen screen) {
        //? if >=26.3 {
        Minecraft.getInstance().gui.setScreen(screen);
        //? } else {
        /*Minecraft.getInstance().setScreen(screen);
        *///? }
    }

    public static boolean guiHudGetIsHidden() {
        //? if >=26.3 {
        return Minecraft.getInstance().gui.hud.isHidden();
        //? } else {
        /*return Minecraft.getInstance().options.hideGui;
        *///? }
    }

    public static void guiHudSetIsHidden(boolean value) {
        //? if >=26.3 {
        ((HudAccessor)Minecraft.getInstance().gui.hud).setIsHidden(value);
        //? } else {
        /*Minecraft.getInstance().options.hideGui = value;
         *///? }
    }
}
