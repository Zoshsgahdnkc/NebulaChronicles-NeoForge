package com.zoshsgahdnkc.NebulaChronicles.planet;

import com.zoshsgahdnkc.NebulaChronicles.NebulaChronicles;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

public class PlanetUtils {
    public static final double BASE_GRAVITY = 0.08D;
    private static final Planet SILVERBLANC = new Planet(0.15f,
            7,
            0.6f,
            1.2f,
            0.1f,
            0.1f);
    private static final Map<ResourceLocation, Planet> planets = new HashMap<>(Map.of(
            ResourceLocation.fromNamespaceAndPath(NebulaChronicles.MODID, "silverblanc"), SILVERBLANC
    ));

    public static void applyOrRemoveAttributes(LivingEntity entity) {
        Planet planet = getPlanet(entity);
        ResourceLocation safeFallRL = getRL("nch_safe_fall");
        ResourceLocation stepHeightRL = getRL("nch_step_height");
        AttributeInstance safeFall = entity.getAttribute(Attributes.SAFE_FALL_DISTANCE);
        AttributeInstance stepHeight = entity.getAttribute(Attributes.STEP_HEIGHT);
        if (planet != null) {
            if (safeFall != null && !safeFall.hasModifier(safeFallRL) && planet.additionalSafeFallDistance() > 0) {
                AttributeModifier safeFallModifier = new AttributeModifier(safeFallRL, planet.additionalSafeFallDistance(), AttributeModifier.Operation.ADD_VALUE);
                safeFall.addPermanentModifier(safeFallModifier);
            }
            if (stepHeight != null && !stepHeight.hasModifier(stepHeightRL) && planet.additionalStepHeight() > 0 && stepHeight.getValue() < planet.additionalStepHeight()) {
                AttributeModifier stepHeightModifier = new AttributeModifier(stepHeightRL, planet.additionalStepHeight(), AttributeModifier.Operation.ADD_MULTIPLIED_BASE);
                stepHeight.addPermanentModifier(stepHeightModifier);
            }
        } else {
            if (safeFall != null && safeFall.hasModifier(safeFallRL)) {
                safeFall.removeModifier(safeFallRL);
            }
            if (stepHeight != null && stepHeight.hasModifier(stepHeightRL)) {
                stepHeight.removeModifier(stepHeightRL);
            }
        }
    }
    public static ResourceLocation getRL(String name) {
        return ResourceLocation.fromNamespaceAndPath(NebulaChronicles.MODID, name);
    }

    public static float getGravityRatio(Planet planet) {
        if (planet != null) return planet.gravityRatio();
        return 1;
    }

    // Make entities fall faster when falling on a low-gravity planet
    public static double getGravityDecreaseFactor(Planet planet, double ySpeed) {
//        // linear function code
//        float gravityRatio = getGravityRatio(planet);
//        if (ySpeed < 0 && gravityRatio < 1) {
//            double factor = 1 + (ySpeed / (10 * gravityRatio));
//            return factor > 0 ? factor : 0;
//        }
//        return 1;
        return ySpeed < 0 ? 1.4 * (1 - Mth.sqrt((float) ((ySpeed / -3.4) + 0.08))) : 1;
    }
    @Nullable
    public static Planet getPlanet(Level level) {
        return planets.getOrDefault(level.dimension().location(), null);
    }

    @Nullable
    public static Planet getPlanet(Entity entity) {
        return getPlanet(entity.level());
    }

    public record Planet(float gravityRatio,
                         int additionalSafeFallDistance,
                         float additionalStepHeight,
                         float solarPowerStrength,
                         float darkMatterDensity,
                         float darkMatterAmplitude) {
    }
}
