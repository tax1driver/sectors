package com.github.tax1driver.sectors.objects;

import java.security.InvalidParameterException;

public class SimplifiedLocation {
    private float x, y, z;

    SimplifiedLocation(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }

    public void add(SimplifiedLocation sl) {
        this.x += sl.getX();
        this.y += sl.getY();
        this.z += sl.getZ();
    }

    public void subtract(SimplifiedLocation sl) {
        this.x -= sl.getX();
        this.y -= sl.getY();
        this.z -= sl.getZ();
    }

    public void multiply(SimplifiedLocation sl) {
        this.x *= sl.getX();
        this.y *= sl.getY();
        this.z *= sl.getZ();
    }

    public static SimplifiedLocation fromString(String str) {
        String[] split = str.split(",");

        if (split.length < 3)
            throw new InvalidParameterException("the str is not in format \"x,y,z\"");

        return new SimplifiedLocation(Float.parseFloat(split[0]), Float.parseFloat(split[1]), Float.parseFloat(split[2]));
    }

    public String toString() {
        return Float.toString(x) + "," + Float.toString(y) + "," + Float.toString(z);
    }

}
