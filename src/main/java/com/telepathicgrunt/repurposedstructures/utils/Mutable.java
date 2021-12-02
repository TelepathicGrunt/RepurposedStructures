package com.telepathicgrunt.repurposedstructures.utils;

// Source is org.apache.commons.lang3.mutable.MutableObject
// On Forge, we don't have that package for some reason.
public class Mutable<T> {
    private T value;

    public Mutable() {
    }

    public Mutable(T value) {
        this.value = value;
    }

    public T getValue() {
        return this.value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        else if (this == obj) {
            return true;
        }
        else if (this.getClass() == obj.getClass()) {
            Mutable<?> that = (Mutable<?>) obj;
            return this.value.equals(that.value);
        }
        else {
            return false;
        }
    }
}
