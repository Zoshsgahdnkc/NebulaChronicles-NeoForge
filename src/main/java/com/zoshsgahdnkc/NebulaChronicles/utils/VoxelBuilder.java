package com.zoshsgahdnkc.NebulaChronicles.utils;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.ArrayList;
import java.util.List;

public class VoxelBuilder {
    List<List<Double>> shapes = new ArrayList<>();
    public VoxelShape buildNorth () {
        VoxelShape[] array = new VoxelShape[shapes.size()];
        for (int i = 0; i < shapes.size(); i++) {
            List<Double> list = shapes.get(i);
            VoxelShape shape = Block.box(list.get(0),list.get(1),list.get(2),list.get(3),list.get(4),list.get(5));
            array[i] = shape;
        }
        return Shapes.or(Block.box(0,0,0,0,0,0), array);
    }
    public VoxelShape buildEast () {
        VoxelShape[] array = new VoxelShape[shapes.size()];
        for (int i = 0; i < shapes.size(); i++) {
            List<Double> list = shapes.get(i);
            VoxelShape shape = Block.box(16 - get(list, P.zend), get(list, P.y), get(list, P.x),
                    16 - get(list, P.z), get(list, P.yend), get(list, P.xend));
            array[i] = shape;
        }
        return Shapes.or(Block.box(0,0,0,0,0,0), array);
    }
    public VoxelShape buildWest () {
        VoxelShape[] array = new VoxelShape[shapes.size()];
        for (int i = 0; i < shapes.size(); i++) {
            List<Double> list = shapes.get(i);
            VoxelShape shape = Block.box(get(list, P.z), get(list, P.y), 16 - get(list, P.xend),
                    get(list, P.zend), get(list, P.yend), 16 - get(list, P.x));
            array[i] = shape;
        }
        return Shapes.or(Block.box(0,0,0,0,0,0), array);
    }
    public VoxelShape buildSouth () {
        VoxelShape[] array = new VoxelShape[shapes.size()];
        for (int i = 0; i < shapes.size(); i++) {
            List<Double> list = shapes.get(i);
            VoxelShape shape = Block.box(16 - get(list,P.xend), get(list, P.y), 16 - get(list, P.zend),
                    16 - get(list, P.x), get(list, P.yend), 16 - get(list, P.z));
            array[i] = shape;
        }
        return Shapes.or(Block.box(0,0,0,0,0,0), array);
    }

    public VoxelBuilder add(double x, double y, double z, double x_length, double y_length, double z_length) {
        shapes.add(List.of(x, y, z, x_length + x, y_length + y, z_length + z));
        return this;
    }

    private double get(List<Double> list, P param) {
        switch (param) {
            case x -> { return list.get(0); }
            case y -> { return list.get(1); }
            case z -> { return list.get(2); }
            case xend -> { return list.get(3); }
            case yend -> { return list.get(4); }
            case zend -> { return list.get(5); }
            case xlength -> { return list.get(3) - list.get(0); }
            case ylength -> { return list.get(4) - list.get(1); }
            case zlength -> { return list.get(5) - list.get(2); }
            default -> { return 0; }
        }
    }
    enum P {
        x, y, z, xlength, ylength, zlength, xend, yend, zend
    }
}
