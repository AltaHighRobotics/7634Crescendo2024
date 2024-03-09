// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.apriltags;

/** Add your docs here. */
public class Position {
    private double x;
    private double y;
    private double z;
    private int rotation;
    public Position(double x, double y, double z, int rotation){
        this.x = x;
        this.y = y;
        this.z = z;
        this.rotation = rotation;
    }
    public double getX(){
        return this.x;
    }
    public double getY(){
        return this.y;
    }
    public double getZ(){
        return this.z;
    }
    public double getRotation(){
        return this.rotation;
    }
}
