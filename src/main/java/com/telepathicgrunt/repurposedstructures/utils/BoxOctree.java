package com.telepathicgrunt.repurposedstructures.utils;

import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.vector.Vector3i;

import java.util.ArrayList;
import java.util.List;

public class BoxOctree {

    private static final int subdivideThreshold = 10;
    private static final int maximumDepth = 3;

    private final AxisAlignedBB boundary;
    private final Vector3i size;
    private final int depth;
    private final List<AxisAlignedBB> innerBoxes = new ArrayList<>();
    private final List<BoxOctree> childrenOctants = new ArrayList<>();

    public BoxOctree(AxisAlignedBB axisAlignedBB){
        this(axisAlignedBB, 0);
    }

    private BoxOctree(AxisAlignedBB axisAlignedBB, int parentDepth){
        boundary = axisAlignedBB.move(0, 0, 0); // deep copy
        size = new Vector3i(boundary.getXsize(), boundary.getYsize(), boundary.getZsize());
        depth = parentDepth + 1;
    }

    private void subdivide(){
        if(!childrenOctants.isEmpty()){
            throw new UnsupportedOperationException("Repurposed Structures - Tried to subdivide when there are already children octants.");
        }

        int halfXSize = size.getX()/2;
        int halfYSize = size.getY()/2;
        int halfZSize = size.getZ()/2;

        childrenOctants.add(new BoxOctree(new AxisAlignedBB(
                boundary.minX, boundary.minY, boundary.minZ,
                boundary.minX + halfXSize, boundary.minY + halfYSize, boundary.minZ + halfZSize),
                depth));

        childrenOctants.add(new BoxOctree(new AxisAlignedBB(
                boundary.minX + halfXSize, boundary.minY, boundary.minZ,
                boundary.maxX, boundary.minY + halfYSize, boundary.minZ + halfZSize),
                depth));

        childrenOctants.add(new BoxOctree(new AxisAlignedBB(
                boundary.minX, boundary.minY + halfYSize, boundary.minZ,
                boundary.minX + halfXSize, boundary.maxY, boundary.minZ + halfZSize),
                depth));

        childrenOctants.add(new BoxOctree(new AxisAlignedBB(
                boundary.minX, boundary.minY, boundary.minZ + halfZSize,
                boundary.minX + halfXSize, boundary.minY + halfYSize, boundary.maxZ),
                depth));

        childrenOctants.add(new BoxOctree(new AxisAlignedBB(
                boundary.minX + halfXSize, boundary.minY + halfYSize, boundary.minZ,
                boundary.maxX, boundary.maxY, boundary.minZ + halfZSize),
                depth));

        childrenOctants.add(new BoxOctree(new AxisAlignedBB(
                boundary.minX, boundary.minY + halfYSize, boundary.minZ + halfZSize,
                boundary.minX + halfXSize, boundary.maxY, boundary.maxZ),
                depth));

        childrenOctants.add(new BoxOctree(new AxisAlignedBB(
                boundary.minX + halfXSize, boundary.minY, boundary.minZ + halfZSize,
                boundary.maxX, boundary.minY + halfYSize, boundary.maxZ),
                depth));

        childrenOctants.add(new BoxOctree(new AxisAlignedBB(
                boundary.minX + halfXSize, boundary.minY + halfYSize, boundary.minZ + halfZSize,
                boundary.maxX, boundary.maxY, boundary.maxZ),
                depth));

        for(AxisAlignedBB parentInnerBox : innerBoxes) {
            for (BoxOctree octree : childrenOctants) {
                if (octree.boundaryContainsFuzzy(parentInnerBox)) {
                    octree.addBox(parentInnerBox);
                }
            }
        }

        innerBoxes.clear();
    }

    public void addBox(AxisAlignedBB axisAlignedBB){
        if(depth < maximumDepth && innerBoxes.size() > subdivideThreshold){
            subdivide();
        }

        if(!childrenOctants.isEmpty()){
            for(BoxOctree octree : childrenOctants){
                if(octree.boundaryContainsFuzzy(axisAlignedBB)){
                    octree.addBox(axisAlignedBB);
                }
            }
        }
        else{
            // Prevent re-adding the same box if it already exists
            for(AxisAlignedBB parentInnerBox : innerBoxes) {
                if(parentInnerBox.equals(axisAlignedBB)){
                    return;
                }
            }

            innerBoxes.add(axisAlignedBB);
        }
    }

    public boolean boundaryContainsFuzzy(AxisAlignedBB axisAlignedBB){
        return boundary.inflate(axisAlignedBB.getSize() / 2).intersects(axisAlignedBB);
    }

    public boolean boundaryContains(AxisAlignedBB axisAlignedBB){
        return boundary.contains(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ) && boundary.contains(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ);
    }

    public boolean intersectsAnyBox(AxisAlignedBB axisAlignedBB){
        if(!childrenOctants.isEmpty()){
            for(BoxOctree octree : childrenOctants){
                if(octree.intersectsAnyBox(axisAlignedBB)){
                    return true;
                }
            }
        }
        else{
            for(AxisAlignedBB innerBox : innerBoxes) {
                if(innerBox.intersects(axisAlignedBB)){
                    return true;
                }
            }
        }

        return false;
    }


    public String toString() {
        return String.format("<%s | %s | %s | %s>", this.getClass().getSimpleName(), this.boundary.toString(), this.innerBoxes, this.childrenOctants);
    }
}
