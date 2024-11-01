package com.zoshsgahdnkc.NebulaChronicles.effect;

import com.zoshsgahdnkc.NebulaChronicles.NebulaChronicles;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.*;

public class CrippleEffect extends MobEffect {
    private static final ResourceLocation rl = ResourceLocation.fromNamespaceAndPath(NebulaChronicles.MODID, "cripple");

    public CrippleEffect(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor, ParticleTypes.ASH);
    }

    protected void addEffect(AttributeMap attributeMap, int amplifier) {
        AttributeInstance jumpStrength = attributeMap.getInstance(Attributes.JUMP_STRENGTH);
        if (jumpStrength != null) {
            double decrease = Mth.clamp(-0.4 * Mth.sqrt(amplifier), -1, 0);
            AttributeModifier cripple = new AttributeModifier(rl, decrease, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
            jumpStrength.removeModifier(cripple);
            jumpStrength.addPermanentModifier(cripple);
        }
    }

    @Override
    public void addAttributeModifiers(AttributeMap pAttributeMap, int pAmplifier) {
        super.addAttributeModifiers(pAttributeMap, pAmplifier);
        addEffect(pAttributeMap, pAmplifier);
    }

    @Override
    public void removeAttributeModifiers(AttributeMap pAttributeMap) {
        super.removeAttributeModifiers(pAttributeMap);
        AttributeInstance attributeinstance = pAttributeMap.getInstance(Attributes.JUMP_STRENGTH);
        if (attributeinstance != null) attributeinstance.removeModifier(rl);
    }

    @Override
    public MobEffect addAttributeModifier(Holder<Attribute> pAttribute, ResourceLocation pId, double pAmount, AttributeModifier.Operation pOperation) {
        return super.addAttributeModifier(pAttribute, pId, pAmount, pOperation);
    }
}
