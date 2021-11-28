package com.telepathicgrunt.repurposedstructures.misc;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StructurePieceCountsObj {
    @Expose
    @SerializedName("nbt_piece_name")
    public String nbtPieceName;

    @Expose
    @SerializedName("always_spawn_this_many")
    public Integer alwaysSpawnThisMany;

    @Expose
    @SerializedName("never_spawn_more_than_this_many")
    public Integer neverSpawnMoreThanThisMany;

    @Expose
    @SerializedName("minimum_distance_from_center_piece")
    public Integer minimumDistanceFromCenterPiece;

    public StructurePieceCountsObj(String nbtPieceName, Integer alwaysSpawnThisMany, Integer neverSpawnMoreThanThisMany, Integer minimumDistanceFromCenterPiece) {
        this.nbtPieceName = nbtPieceName;
        this.alwaysSpawnThisMany = alwaysSpawnThisMany;
        this.neverSpawnMoreThanThisMany = neverSpawnMoreThanThisMany;
        this.minimumDistanceFromCenterPiece = minimumDistanceFromCenterPiece;
    }
}
