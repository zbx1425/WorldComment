package cn.zbx1425.worldcomment.mixin;

//? if >=26.3
import net.minecraft.client.gui.Hud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import cn.zbx1425.worldcomment.platform.multiver.MinecraftClientShim;

//? if >=26.3
@Mixin(Hud.class)
//? if <26.3
//@Mixin(MinecraftClientShim.class)
public interface HudAccessor {
//? if >=26.3 {

    @Accessor
    void setIsHidden(boolean value);

//? }
}
