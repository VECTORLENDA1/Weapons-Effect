package net.vector.weaponseffect.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.vector.weaponseffect.entity.CustomFallingBlockEntity;

public class CustomFallingBlockEntityRenderer extends EntityRenderer<CustomFallingBlockEntity> {
    public CustomFallingBlockEntityRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public void render(CustomFallingBlockEntity entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        BlockState blockState = entity.getBlockState();
        if (blockState == null) {
            return; // Se não houver bloco, não renderiza
        }

        poseStack.pushPose();

        // Posição da entidade
        Vec3 pos = entity.position();
        poseStack.translate(pos.x - 0.5, pos.y, pos.z - 0.5);

        // Renderiza o bloco usando o modelo baked
        Minecraft minecraft = Minecraft.getInstance();
        BakedModel model = minecraft.getBlockRenderer().getBlockModel(blockState);

        // Obtém o buffer de renderização
        VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.solid());

        // Renderiza o modelo do bloco com a textura padrão
        minecraft.getBlockRenderer().getModelRenderer().renderModel(poseStack.last(), vertexConsumer, blockState, model, 1.0F, 1.0F, 1.0F, packedLight, 0);

        poseStack.popPose();
        super.render(entity, entityYaw, partialTicks, poseStack, bufferSource, packedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(CustomFallingBlockEntity entity) {
        // O bloco usa a textura do próprio bloco, então retornamos a textura padrão do atlas
        return TextureAtlas.LOCATION_BLOCKS;
    }
}
