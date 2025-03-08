package net.vector.weaponseffect.client;

import com.mojang.blaze3d.vertex.PoseStack;
import mezz.jei.api.gui.drawable.IDrawable;
import net.minecraft.client.gui.GuiGraphics;

public class ScaledDrawable implements IDrawable {
    private final IDrawable original;
    private final float scale;

    public ScaledDrawable(IDrawable original, float scale) {
        this.original = original;
        this.scale = scale;
    }

    @Override
    public int getWidth() {
        return (int) (original.getWidth() * scale);
    }

    @Override
    public int getHeight() {
        return (int) (original.getHeight() * scale);
    }

    @Override
    public void draw(GuiGraphics guiGraphics, int xOffset, int yOffset) {
        PoseStack poseStack = guiGraphics.pose();
        poseStack.pushPose();
        poseStack.scale(scale, scale, scale);
        original.draw(guiGraphics, (int) (xOffset / scale), (int) (yOffset / scale));
        poseStack.popPose();
    }
}
