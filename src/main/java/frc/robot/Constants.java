// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.swerve.SwerveModuleConfig;
import utilities.PIDConfiguration;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  //Motors and constants for our Arm
  public static final int WRIST_MOTOR_ID = 0;
  public static final int FOREARM_MOTOR_ID = 0;
  public static final int SHOULDER_MOTOR_ID = 0;

  // Driving.
  public static final int DRIVE_CONTROLLER = 0;
  public static final double DRIVE_SPEED = 0.5;
  public static final double DRIVE_TURN_SPEED = 0.5;

  // Controller.
  public static final double DRIVE_CONTROLLER_DEAD_ZONE = 0.15;

  public static final int RIGHT_STICK_Y = 3;
  public static final int RIGHT_STICK_X = 4;
  public static final int LEFT_STICK_Y = 1;
  public static final int LEFT_STICK_X = 0;

  public static final int FLIGHT_STICK_X = 0;
  public static final int FLIGHT_STICKY_Y = 1;
  public static final int FLIGHT_STICK_Z = 2;
  public static final int FLIGHT_STICK_SLIDER = 3;

  public static final int XBOX_A_BUTTON = 1; 
  public static final int XBOX_B_BUTTON = 2;
  public static final int XBOX_X_BUTTON = 3;
  public static final int XBOX_Y_BUTTON = 4;

  public static final int XBOX_LEFT_BUMPER = 5;
  public static final int XBOX_RIGHT_BUMPER = 6;

  // Swerve module.
  public static final double SWERVE_MODULE_TURN_ENCODER_DISTANCE_PER_PULSE = 0;
  public static final double SWERVE_MODULE_WHEEL_CIRCUMFERENCE = 0;
  public static final double SWERVE_MODULE_WHEEL_ENCODER_DISTANCE_PER_PULSE = 0;

  //public static final double SWERVE_MODULE_WHEEL_CURRENT_LIMIT = 50.0;

  public static final PIDConfiguration SWERVE_MODULE_TURN_PID = new PIDConfiguration(0.0, 0.0, 0.0, 0.0, 0, 0, 0, 0, 0, 0, 0, 0.0, 0.0);

  public static final int FRONT_RIGHT_MODULE = 0;
  public static final int FRONT_LEFT_MODULE = 1;
  public static final int BACK_RIGHT_MODULE = 2;
  public static final int BACK_LEFT_MODULE = 3;
  public static final int SWERVE_MODULE_COUNT = 4;

  public static final SwerveModuleConfig []SWERVE_MODULE_CONFIGS = {
    new SwerveModuleConfig(0, 0, false, false), // Front right
    new SwerveModuleConfig(0, 0, false, false), // Front left
    new SwerveModuleConfig(0, 0, false, false), // Back right
    new SwerveModuleConfig(0, 0, false, false) // Back left
};
  
  public static final double VEHICLE_WHEELBASE = 1.0;
  public static final double VEHICLE_TRACKWIDTH = 1.0;
  public static final double VEHICLE_RADIUS = Math.hypot(VEHICLE_WHEELBASE, VEHICLE_TRACKWIDTH);
}