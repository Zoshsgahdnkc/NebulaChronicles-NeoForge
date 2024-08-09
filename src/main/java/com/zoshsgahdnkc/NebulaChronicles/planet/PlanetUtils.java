package com.zoshsgahdnkc.NebulaChronicles.planet;

import com.zoshsgahdnkc.NebulaChronicles.NebulaChronicles;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;

import java.util.HashMap;
import java.util.Map;

public class PlanetUtils {
    public static final double BASE_GRAVITY = 0.08D;
    private static final Planet SILVERBLANC = new Planet(0.15F);
    private static final Map<ResourceLocation, Planet> planets = new HashMap<>(Map.of(
            ResourceLocation.fromNamespaceAndPath(NebulaChronicles.MODID, "silverblanc"), SILVERBLANC
    ));

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
        return ySpeed < 0 ? 1.33 * (1 - Mth.sqrt((float) ((ySpeed / -3.4) + 0.08))) : 1;
    }
    public static Planet getPlanet(Level level) {
        return planets.getOrDefault(level.dimension().location(), null);
    }

    public static Planet getPlanet(Entity entity) {
        return getPlanet(entity.level());
    }
}
