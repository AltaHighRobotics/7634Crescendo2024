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
  //Constants for button id's
  // all of these should corrospond with number buttons on the controller
  public static final int TRIGGER_BUTTON = 1;
  public static final int SOURCE_POSITION_BUTTON = 3;
  public static final int FLOOR_RETRIEVAL_BUTTON = 2;
  public static final int AMP_POSITION_BUTTON = 12;
  public static final int SPEAKER_POSITION_BUTTON = 11;
  public static final int TRAP_POSITION_BUTTON = 10;
  public static final int DEFAULT_POSITION_BUTTON = 4;

  //Motors and PID constants for our Arm
    public static final PIDConfiguration ARM_JOINT_CONTROLLER = new PIDConfiguration(0.000000, 0.0, 0.0, 0.0, 0, 0, 0, 0, 0, 0, 0, -0.5, 0.5);
    public static final int WRIST_MOTOR_ID = 0;
    public static final int FOREARM_MOTOR_ID = 0;
    public static final int SHOULDER_MOTOR_ID = 0;
    public static final int PRIMARY_ROLLER_MOTOR_ID = 0;
    public static final int SECONDARY_ROLLER_MOTOR_ID = 0;
    //Position Constants for our arm, structured as ShoulderSetPoint, ForearmSetPoint, wristSetPoint
    public static final double[] STARTING_POSITION = {0.0, 0.0, 0.0};
    public static final double[] SPEAKER_POSITION = {0.0, 0.0, 0.0};
    public static final double[] AMP_POSITION = {0.0, 0.0, 0.0};
    public static final double[] SOURCE_POSITION = {0.0, 0.0, 0.0};
    public static final double[] DEFAULT_POSTION = {0.0, 0.0, 0.0};
    //tolerance for the .brake Method on shaydns suggested changes in armSubsystem
    public static final double JOINT_TOLERANCE = 0; //0 is default and will break robot. configure as needed

  // Driving.
    public static final int DRIVE_CONTROLLER = 0;
    public static final double DRIVE_SPEED = 0.5;
    public static final double DRIVE_TURN_SPEED = 0.7; // increased to 0.7; use 0.5 if issues arise.

  // Controller.
    public static final double DRIVE_CONTROLLER_DEAD_ZONE = 0.3;

    public static final int RIGHT_STICK_Y = 5;
    public static final int RIGHT_STICK_X = 4;
    public static final int LEFT_STICK_Y = 1;
    public static final int LEFT_STICK_X = 0;

    public static final int FLIGHT_STICK_X = 0;
    public static final int FLIGHT_STICK_Y = 1;
    public static final int FLIGHT_STICK_Z = 2;
    public static final int FLIGHT_STICK_SLIDER = 3;

    public static final int XBOX_A_BUTTON = 1; 
    public static final int XBOX_B_BUTTON = 2;
    public static final int XBOX_X_BUTTON = 3;
    public static final int XBOX_Y_BUTTON = 4;

    public static final int XBOX_LEFT_BUMPER = 5;
    public static final int XBOX_RIGHT_BUMPER = 6;

  // Swerve module.
    public static final double SWERVE_MODULE_TURN_ENCODER_DISTANCE_PER_PULSE = 0.267745716 * 36.0;
    // 1.0/gear_thingy * 360.0
    public static final double SWERVE_MODULE_WHEEL_CIRCUMFERENCE = 0.3092112569295754;
    //public static final double SWERVE_MODULE_WHEEL_ENCODER_DISTANCE_PER_PULSE = 0;

    //public static final double SWERVE_MODULE_WHEEL_CURRENT_LIMIT = 50.0;

    public static final PIDConfiguration SWERVE_MODULE_TURN_PID = new PIDConfiguration(0.01, 0.00, 0.0
    , 0.0, 0, 0, 0, 0, 0, 0, 0, -0.8, 0.8);

    public static final int FRONT_RIGHT_MODULE = 0;
    public static final int FRONT_LEFT_MODULE = 1;
    public static final int BACK_RIGHT_MODULE = 2;
    public static final int BACK_LEFT_MODULE = 3;
    public static final int SWERVE_MODULE_COUNT = 4;

    public static final SwerveModuleConfig []SWERVE_MODULE_CONFIGS = {
      new SwerveModuleConfig(4, 40, true, false), // Front right
      new SwerveModuleConfig(6, 60, false, false), // Front left
      new SwerveModuleConfig(5, 50, true, false), // Back right
      new SwerveModuleConfig(3, 30, false, false) // Back left
    };
    
    public static final double VEHICLE_WHEELBASE = 1.0;
    public static final double VEHICLE_TRACKWIDTH = 1.0;
    public static final double VEHICLE_RADIUS = Math.hypot(VEHICLE_WHEELBASE, VEHICLE_TRACKWIDTH);

    public static final double DRIVE_GEAR_RATIO = 6.54;
    public static final double TURN_GEAR_RATIO = 33.94;
  }