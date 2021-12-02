package com.telepathicgrunt.repurposedstructures.world.structures.codeconfigs;

import com.telepathicgrunt.repurposedstructures.world.structures.pieces.MansionPieces;

public class MansionCodeConfig {

    public final MansionPieces.MANSIONTYPE type;

    public MansionCodeConfig(MansionPieces.MANSIONTYPE type) {
        this.type = type;
    }

    public static class Builder<T extends Builder<T>> {
        protected final MansionPieces.MANSIONTYPE type;

        public Builder(MansionPieces.MANSIONTYPE type) {
            this.type = type;
        }

        @SuppressWarnings("unchecked")
        protected T getThis() {
            return (T) this;
        }

        public MansionCodeConfig build() {
            return new MansionCodeConfig(
                    type
            );
        }
    }
}