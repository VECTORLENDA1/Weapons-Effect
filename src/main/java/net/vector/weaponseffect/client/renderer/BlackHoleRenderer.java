package net.vector.weaponseffect.client.renderer;

import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.vector.weaponseffect.entity.BlackHoleEntity;

public class BlackHoleRenderer extends EntityRenderer<BlackHoleEntity> {
    public BlackHoleRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public ResourceLocation getTextureLocation(BlackHoleEntity entity) {
        return  ResourceLocation.fromNamespaceAndPath("weaponseffect", "textures/entity/black_hole.png");
    }
}
