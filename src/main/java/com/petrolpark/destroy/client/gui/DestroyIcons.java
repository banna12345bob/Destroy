package com.petrolpark.destroy.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Matrix4f;
import com.petrolpark.destroy.Destroy;
import com.simibubi.create.foundation.gui.AllIcons;
import com.simibubi.create.foundation.utility.Color;

import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;

public class DestroyIcons extends AllIcons {

    public static final ResourceLocation DESTROY_ICON_ATLAS = Destroy.asResource("textures/gui/icons.png");
    public static final int DESTROY_ICON_ATLAS_SIZE = 256;

    // Duplicate fields as private in parent class
    protected final int iconX;
    protected final int iconY;

    public static final DestroyIcons
        RADIOACTIVITY = new DestroyIcons(0, 0),
        ACID_RAIN = new DestroyIcons(1, 0),
        OZONE_DEPLETION = new DestroyIcons(2, 0),
        GREENHOUSE = new DestroyIcons(3, 0),
        SMOG = new DestroyIcons(4, 0);


    public DestroyIcons(int x, int y) {
        super(x, y);
        iconX = x * 16;
        iconY = y * 16;
    };

    @Override
	public void bind() {
		RenderSystem.setShaderTexture(0, DESTROY_ICON_ATLAS);
	};

    /**
     * All copied from the {@link com.simibubi.create.foundation.gui.AllIcons#render Create source code}.
     */
    @Override
    public void render(PoseStack ms, MultiBufferSource buffer, int color) {
		VertexConsumer builder = buffer.getBuffer(RenderType.textSeeThrough(DESTROY_ICON_ATLAS));
		Matrix4f matrix = ms.last().pose();
		Color rgb = new Color(color);
		int light = LightTexture.FULL_BRIGHT;

		Vec3 vec1 = new Vec3(0, 0, 0);
		Vec3 vec2 = new Vec3(0, 1, 0);
		Vec3 vec3 = new Vec3(1, 1, 0);
		Vec3 vec4 = new Vec3(1, 0, 0);

		float u1 = iconX * 1f / DESTROY_ICON_ATLAS_SIZE;
		float u2 = (iconX + 16) * 1f / DESTROY_ICON_ATLAS_SIZE;
		float v1 = iconY * 1f / DESTROY_ICON_ATLAS_SIZE;
		float v2 = (iconY + 16) * 1f / DESTROY_ICON_ATLAS_SIZE;

		vertex(builder, matrix, vec1, rgb, u1, v1, light);
		vertex(builder, matrix, vec2, rgb, u1, v2, light);
		vertex(builder, matrix, vec3, rgb, u2, v2, light);
		vertex(builder, matrix, vec4, rgb, u2, v1, light);
	}

    /** 
     * All copied from the {@link com.simibubi.create.foundation.gui.AllIcons#vertex Create source code}.
     */
	private void vertex(VertexConsumer builder, Matrix4f matrix, Vec3 vec, Color rgb, float u, float v, int light) {
		builder.vertex(matrix, (float) vec.x, (float) vec.y, (float) vec.z)
			.color(rgb.getRed(), rgb.getGreen(), rgb.getBlue(), 255)
			.uv(u, v)
			.uv2(light)
			.endVertex();
	};
    
};