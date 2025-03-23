package com.zoshsgahdnkc.NebulaChronicles.block;

import com.zoshsgahdnkc.NebulaChronicles.planet.PlanetUtils;
import com.zoshsgahdnkc.NebulaChronicles.utils.ModCommonConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;

public class SolarPowerGeneratorBlock extends AbstractGeneratorBlock {

    public SolarPowerGeneratorBlock(Properties properties) {
        super(properties);
    }

    @Override
    public boolean canGeneratePower(BlockState state, LevelReader level, BlockPos pos) {
        return !state.getValue(POWERED) && level.canSeeSky(pos.above());
    }

    @Override
    public float getPower(BlockState state, Level level, BlockPos pos, RandomSource random) {
        PlanetUtils.Planet planet = PlanetUtils.getPlanet(level);
        if (planet != null) {
            return planet.solarPowerStrength() * ModCommonConfig.SOLAR_POWER_GENERATOR_BASE_POWER.get();
        }
        return 0;
    }
}
