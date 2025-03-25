package com.udu3324.gridfix.utils;

import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderContext;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.*;
import net.minecraft.client.util.BufferAllocator;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.ColorHelper;
import net.minecraft.util.math.Vec3d;
import org.joml.Matrix4f;

public class Grid {
    // Reference: @Environment(EnvType.CLIENT) net.minecraft.client.render.debug.ChunkBorderDebugRenderer

    public static int size = 2;
    public static boolean render = false;

    private static final int TRANSPARENT = ColorHelper.getArgb(0, 0, 0, 0);
    private static final int OLIVE = ColorHelper.getArgb(255, 220, 210, 16);
    private static final int TURQUOISE = ColorHelper.getArgb(255, 61, 185, 243);
    private static final int SKY = ColorHelper.getArgb(255, 181, 227, 255);
    private static final int WHITE = ColorHelper.getArgb(255, 255, 255, 255);

    private static final MinecraftClient client = MinecraftClient.getInstance();
    private static final BufferAllocator allocator = new BufferAllocator(65536);
    private static final VertexConsumerProvider.Immediate vertexConsumers = VertexConsumerProvider.immediate(allocator);

    public static void render(WorldRenderContext context) {
        if (!render) return;

        if (client.player == null || client.getCameraEntity() == null) return;

        if (client.world == null) return;

        // get relative player coordinates
        Vec3d cam = context.gameRenderer().getCamera().getPos();

        Entity entity = client.getCameraEntity();
        //ChunkPos chunkPos = entity.getChunkPos();

        // coordinates relative to the world
        int worldX = entity.getBlockX();
        //int worldY = entity.getBlockY();
        int worldZ = entity.getBlockZ();

        // coordinates relative to the player's camera
        float playerX = (float) cam.getX() - 1;
        float playerY = (float) cam.getY();
        float playerZ = (float) cam.getZ() - 1;

        float lowestY = (float) ((double) client.world.getBottomY() - playerY - 1);
        float highestY = (float) ((entity.getY() + 1) - playerY);

        // matrix positioning system
        MatrixStack matrices = context.matrixStack();
        assert matrices != null;
        matrices.push();

        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(RenderLayer.getDebugLineStrip(1.0));
        Matrix4f matrix4f = matrices.peek().getPositionMatrix();

        float startX = 0;
        float startZ = 0;

        // create vertical lines
        for (int a = -size; a < (size); a++) {
            for (int b = -size; b < (size); b++) {
                startX = (worldX + a) - playerX;
                startZ = (worldZ + b) - playerZ;

                // trash
                vertexConsumer
                        .vertex(matrix4f, startX, lowestY, startZ)
                        .color(TRANSPARENT);

                // actual line
                vertexConsumer
                        .vertex(matrix4f, startX, lowestY, startZ)
                        .color(OLIVE);

                vertexConsumer
                        .vertex(matrix4f, startX, highestY, startZ)
                        .color(TURQUOISE);

                // trash
                vertexConsumer
                        .vertex(matrix4f, startX, highestY, startZ)
                        .color(TRANSPARENT);
            }
        }

        // trash
        vertexConsumer
                .vertex(matrix4f, startX, highestY, startZ)
                .color(TRANSPARENT);

        //set the floor y
        //float gridFloorY = (float) ((entity.getY() + 0) - playerY);
        float gridFloorY;
        if (client.player.isSneaking()) {
            gridFloorY = -1.25F;
        } else {
            gridFloorY = -1.59F;
        }

        // create floor grid
        for (int a = -size; a < (size); a++) {
            startX = (worldX + a) - playerX;

            // trash
            vertexConsumer
                    .vertex(matrix4f, startX, gridFloorY, (worldZ - size) - playerZ)
                    .color(TRANSPARENT);

            for (int b = -size; b < (size); b++) {
                startZ = (worldZ + b) - playerZ;

                // actual line
                vertexConsumer
                        .vertex(matrix4f, startX, gridFloorY, startZ)
                        .color(SKY);

                if ((a - 1) >= -size) {
                    vertexConsumer
                            .vertex(matrix4f, startX - 1, gridFloorY, startZ)
                            .color(SKY);

                    vertexConsumer
                            .vertex(matrix4f, startX, gridFloorY, startZ)
                            .color(SKY);
                }

            }

            // trash
            vertexConsumer
                    .vertex(matrix4f, startX, -1.25F, startZ)
                    .color(TRANSPARENT);
        }

        // trash
        vertexConsumer
                .vertex(matrix4f, startX, -1.25F, startZ)
                .color(TRANSPARENT);

        // render hit-box square
        float hitBoxSize = 0.6f;

        float halfSize = hitBoxSize / 2.0f;
        float x0 = (float) ((playerX + 1) - cam.getX()) - halfSize; //adding back 1 from subtracting last time
        float x1 = (float) ((playerX + 1) - cam.getX()) + halfSize;
        float z0 = (float) ((playerZ + 1) - cam.getZ()) - halfSize;
        float z1 = (float) ((playerZ + 1) - cam.getZ()) + halfSize;

        // trash
        vertexConsumer.vertex(matrix4f, x0, gridFloorY, z0).color(TRANSPARENT);

        vertexConsumer.vertex(matrix4f, x0, gridFloorY, z0).color(WHITE); // Bottom left
        vertexConsumer.vertex(matrix4f, x1, gridFloorY, z0).color(WHITE); // Bottom right
        vertexConsumer.vertex(matrix4f, x1, gridFloorY, z1).color(WHITE);// Top right
        vertexConsumer.vertex(matrix4f, x0, gridFloorY, z1).color(WHITE); // Top left

        vertexConsumer.vertex(matrix4f, x0, gridFloorY, z0).color(WHITE); // Complete box

        matrices.pop();
    }
}
