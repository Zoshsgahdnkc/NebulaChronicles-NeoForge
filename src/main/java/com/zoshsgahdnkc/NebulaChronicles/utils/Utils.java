package com.zoshsgahdnkc.NebulaChronicles.utils;

import net.minecraft.util.RandomSource;

public class Utils {
    public static float randomPitch(RandomSource random) {
        return slightlyRandom(1f, 0.2f, random);
    }

    public static float slightlyRandom(float target, float percentage, RandomSource random) {
        return ((random.nextFloat() - 0.5f) * 2 * percentage + 1) * target;
    }

    public static double slightlyRandom(double target, float percentage, RandomSource random) {
        return ((random.nextFloat() - 0.5f) * 2 * percentage + 1) * target;
    }
}
