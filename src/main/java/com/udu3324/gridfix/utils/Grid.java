package com.udu3324.gridfix.utils;

import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderContext;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.*;
import net.minecraft.client.util.BufferAllocator;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.ColorHelper;
import org.joml.Matrix4f;

public class Grid {
    private static final int DARK_CYAN = ColorHelper.getArgb(255, 0, 155, 155);
    private static final int YELLOW = ColorHelper.getArgb(255, 255, 255, 0);

    private static final MinecraftClient client = MinecraftClient.getInstance();
    private static final BufferAllocator allocator = new BufferAllocator(65536);
    private static final VertexConsumerProvider.Immediate vertexConsumers = VertexConsumerProvider.immediate(allocator);

    public static void render(WorldRenderContext context) {

        if (client.player == null || client.getCameraEntity() == null) return;

        if (client.world == null) return;

        // get relative player coordinates
        //Vec3d cam = context.gameRenderer().getCamera().getPos();

        int playerX = (int) Math.round(client.player.getX());
        int playerY = (int) Math.round(client.player.getY());
        int playerZ = (int) Math.round(client.player.getZ());

        // matrix positioning system
        MatrixStack matrices = context.matrixStack();
        assert matrices != null;
        matrices.push();

        Entity entity = client.getCameraEntity();
        float lowestY = (float) ((double) client.world.getBottomY() - playerY - 1);
        float highestY = (float) ((double) (client.world.getTopYInclusive() + 1) - playerY);

        ChunkPos chunkPos = entity.getChunkPos();
        //float h = (float)((double)chunkPos.getStartX() - playerX);
        //float i = (float)((double)chunkPos.getStartZ() - playerZ);
        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(RenderLayer.getDebugLineStrip(1.0));
        Matrix4f matrix4f = matrices.peek().getPositionMatrix();

        vertexConsumer.vertex(matrix4f, playerX, lowestY, playerZ).color(1.0F, 0.0F, 0.0F, 0.0F);

        vertexConsumer.vertex(matrix4f, playerX, highestY, playerZ).color(1.0F, 0.0F, 0.0F, 0.5F);

        System.out.println("Got (" + playerX + ", " + playerZ + ")");
        //int x;
        //int z;
        //for(x = -16; x <= 32; x += 16) { // Loop for X-axis positions
        //    for(z = -16; z <= 32; z += 16) { // Loop for Z-axis positions
        //        // Bottom vertex (Y = lowestY)
        //        vertexConsumer.vertex(matrix4f, h + (float)x, lowestY, i + (float)z).color(1.0F, 0.0F, 0.0F, 0.0F);
//
        //        // Top vertex (Y = highestY, which is g)
        //        vertexConsumer.vertex(matrix4f, h + (float)x, highestY, i + (float)z).color(1.0F, 0.0F, 0.0F, 0.5F);
        //    }
        //}

        //int j;
        //int k;
        //for(j = -16; j <= 32; j += 16) {
        //    for(k = -16; k <= 32; k += 16) {
        //        vertexConsumer.vertex(matrix4f, h + (float)j, lowestY, i + (float)k).color(1.0F, 0.0F, 0.0F, 0.0F);
        //        vertexConsumer.vertex(matrix4f, h + (float)j, lowestY, i + (float)k).color(1.0F, 0.0F, 0.0F, 0.5F);
        //        vertexConsumer.vertex(matrix4f, h + (float)j, highestY, i + (float)k).color(1.0F, 0.0F, 0.0F, 0.5F);
        //        vertexConsumer.vertex(matrix4f, h + (float)j, highestY, i + (float)k).color(1.0F, 0.0F, 0.0F, 0.0F);
        //    }
        //}

        //for(j = 2; j < 16; j += 2) {
        //    k = j % 4 == 0 ? DARK_CYAN : YELLOW;
        //    vertexConsumer.vertex(matrix4f, h + (float)j, lowestY, i).color(1.0F, 1.0F, 0.0F, 0.0F);
        //    vertexConsumer.vertex(matrix4f, h + (float)j, lowestY, i).color(k);
        //    vertexConsumer.vertex(matrix4f, h + (float)j, highestY, i).color(k);
        //    vertexConsumer.vertex(matrix4f, h + (float)j, highestY, i).color(1.0F, 1.0F, 0.0F, 0.0F);
        //    vertexConsumer.vertex(matrix4f, h + (float)j, lowestY, i + 16.0F).color(1.0F, 1.0F, 0.0F, 0.0F);
        //    vertexConsumer.vertex(matrix4f, h + (float)j, lowestY, i + 16.0F).color(k);
        //    vertexConsumer.vertex(matrix4f, h + (float)j, highestY, i + 16.0F).color(k);
        //    vertexConsumer.vertex(matrix4f, h + (float)j, highestY, i + 16.0F).color(1.0F, 1.0F, 0.0F, 0.0F);
        //}

        //for(j = 2; j < 16; j += 2) {
        //    k = j % 4 == 0 ? DARK_CYAN : YELLOW;
        //    vertexConsumer.vertex(matrix4f, h, lowestY, i + (float)j).color(1.0F, 1.0F, 0.0F, 0.0F);
        //    vertexConsumer.vertex(matrix4f, h, lowestY, i + (float)j).color(k);
        //    vertexConsumer.vertex(matrix4f, h, highestY, i + (float)j).color(k);
        //    vertexConsumer.vertex(matrix4f, h, highestY, i + (float)j).color(1.0F, 1.0F, 0.0F, 0.0F);
        //    vertexConsumer.vertex(matrix4f, h + 16.0F, lowestY, i + (float)j).color(1.0F, 1.0F, 0.0F, 0.0F);
        //    vertexConsumer.vertex(matrix4f, h + 16.0F, lowestY, i + (float)j).color(k);
        //    vertexConsumer.vertex(matrix4f, h + 16.0F, highestY, i + (float)j).color(k);
        //    vertexConsumer.vertex(matrix4f, h + 16.0F, highestY, i + (float)j).color(1.0F, 1.0F, 0.0F, 0.0F);
        //}

        //float l;
        //for(j = client.world.getBottomY(); j <= client.world.getTopYInclusive() + 1; j += 2) {
        //    l = (float)((double)j - cameraY);
        //    int m = j % 8 == 0 ? DARK_CYAN : YELLOW;
        //    vertexConsumer.vertex(matrix4f, h, l, i).color(1.0F, 1.0F, 0.0F, 0.0F);
        //    vertexConsumer.vertex(matrix4f, h, l, i).color(m);
        //    vertexConsumer.vertex(matrix4f, h, l, i + 16.0F).color(m);
        //    vertexConsumer.vertex(matrix4f, h + 16.0F, l, i + 16.0F).color(m);
        //    vertexConsumer.vertex(matrix4f, h + 16.0F, l, i).color(m);
        //    vertexConsumer.vertex(matrix4f, h, l, i).color(m);
        //    vertexConsumer.vertex(matrix4f, h, l, i).color(1.0F, 1.0F, 0.0F, 0.0F);
        //}

        //vertexConsumer = vertexConsumers.getBuffer(RenderLayer.getDebugLineStrip(2.0));

        //for(j = 0; j <= 16; j += 16) {
        //    for(k = 0; k <= 16; k += 16) {
        //        vertexConsumer.vertex(matrix4f, h + (float)j, lowestY, i + (float)k).color(0.25F, 0.25F, 1.0F, 0.0F);
        //        vertexConsumer.vertex(matrix4f, h + (float)j, lowestY, i + (float)k).color(0.25F, 0.25F, 1.0F, 1.0F);
        //        vertexConsumer.vertex(matrix4f, h + (float)j, highestY, i + (float)k).color(0.25F, 0.25F, 1.0F, 1.0F);
        //        vertexConsumer.vertex(matrix4f, h + (float)j, highestY, i + (float)k).color(0.25F, 0.25F, 1.0F, 0.0F);
        //    }
        //}

        //for(j = client.world.getBottomY(); j <= client.world.getTopYInclusive() + 1; j += 16) {
        //    l = (float)((double)j - cameraY);
        //    vertexConsumer.vertex(matrix4f, h, l, i).color(0.25F, 0.25F, 1.0F, 0.0F);
        //    vertexConsumer.vertex(matrix4f, h, l, i).color(0.25F, 0.25F, 1.0F, 1.0F);
        //    vertexConsumer.vertex(matrix4f, h, l, i + 16.0F).color(0.25F, 0.25F, 1.0F, 1.0F);
        //    vertexConsumer.vertex(matrix4f, h + 16.0F, l, i + 16.0F).color(0.25F, 0.25F, 1.0F, 1.0F);
        //    vertexConsumer.vertex(matrix4f, h + 16.0F, l, i).color(0.25F, 0.25F, 1.0F, 1.0F);
        //    vertexConsumer.vertex(matrix4f, h, l, i).color(0.25F, 0.25F, 1.0F, 1.0F);
        //    vertexConsumer.vertex(matrix4f, h, l, i).color(0.25F, 0.25F, 1.0F, 0.0F);
        //}
    }

    public static void create(int size) {

    }
}
