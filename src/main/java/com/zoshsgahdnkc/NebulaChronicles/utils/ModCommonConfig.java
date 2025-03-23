package com.zoshsgahdnkc.NebulaChronicles.utils;

import net.neoforged.neoforge.common.ModConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class ModCommonConfig {
    public static ModCommonConfig COMMON;
    public static ModConfigSpec COMMON_SPEC;

    public static ModConfigSpec.ConfigValue<Float> SOLAR_POWER_GENERATOR_BASE_POWER;

    public static ModConfigSpec.ConfigValue<Integer> ATTACK_RANGE_SQR;
    public static ModConfigSpec.ConfigValue<Integer> RANGED_ATTACK_INTERVAL;
    public static ModConfigSpec.ConfigValue<Integer> HARD_RANGED_ATTACK_INTERVAL;

    static {
        Pair<ModCommonConfig, ModConfigSpec> pair =
                new ModConfigSpec.Builder().configure(ModCommonConfig::new);
        //Store the resulting values
        COMMON = pair.getLeft();
        COMMON_SPEC = pair.getRight();
    }

    private ModCommonConfig(ModConfigSpec.Builder builder) {
        builder.comment("Common Configs for NebulaChronicles");

        builder.push("--Industry Settings--");
        builder.push("Generator Settings");
        SOLAR_POWER_GENERATOR_BASE_POWER = builder.comment("Base power output of solar power generator.","10.0f by default")
                        .define("Solar Power Generator Base Power", 10.0f);
        builder.pop();

        builder.push("--World Settings--");
        builder.push("Spiked Verdhelm Beetle Settings");
        ATTACK_RANGE_SQR = builder.comment("The range squared of a beetle shoots stone.","196 by default.")
                .define("Attack Range Squared",196);
        RANGED_ATTACK_INTERVAL = builder.comment("The time (by tick) interval of a beetle shoots stone.","Not work in 'Hard' difficulty.","80 by default.")
                .define("Ranged Attack Interval",80);
        HARD_RANGED_ATTACK_INTERVAL = builder.comment("The time (by tick) interval of a beetle shoots stone.","Work only in 'Hard' difficulty.","50 by default.")
                .define("Ranged Attack Interval in Hard Difficulty",50);
        builder.pop();
        COMMON_SPEC = builder.build();
    }
}
