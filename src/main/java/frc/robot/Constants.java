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
  //Constants for our climbing mechanism
  public static final int CHAIN_MOTOR_ID = 10;
  public static final int BRAKE_SOLENOID = 0;
  public static final double CLIMB_SPEED = 0.5;
  public static final double ARM_TOLERANCE = 10;
  //Constants for button id's
  // all of these should corrospond with number buttons on the controller
  public static final int TRIGGER_BUTTON = 1;
  public static final int SOURCE_POSITION_BUTTON = 10;
  public static final int AMP_POSITION_BUTTON = 7;
  public static final int SPEAKER_POSITION_BUTTON = 3;
  public static final int TRAP_POSITION_BUTTON = 9;
  public static final int DEFAULT_POSITION_BUTTON = 2;
  public static final int CLIMB_UP = 11;
  public static final int CLIMB_DOWN = 12;

  //Motors and PID constants for our Arms
  public static final double SHOOT_SPEED = -0.8;
  public static final double INTAKE_SPEED = 0.2;
    public static final PIDConfiguration ARM_JOINT_CONTROLLER = new PIDConfiguration(0.05, 0.0, 0.0, 0.0, 0, 0, 0, 0, 0, 0, 0, -0.9, 0.9);
    public static final int WRIST_MOTOR_ID = 7;
    public static final int FOREARM_MOTOR_ID = 8;
    public static final int SHOULDER_MOTOR_ID = 9;
    public static final int PRIMARY_ROLLER_MOTOR_ID = 11;
    public static final int SECONDARY_ROLLER_MOTOR_ID = 12;
    //Position Constants for our arm, structured as ShoulderSetPoint, ForearmSetPoint, wristSetPoint
    public static final double[] SPEAKER_POSITION = {7, 1.6,1.3};
    public static final double[] AMP_POSITION = {0.0, 0.0, 0.0};
    public static final double[] SOURCE_POSITION = {0.0, 0.0, 0.0};
    public static final double[] DEFAULT_POSITION = {0.0, 0.0, 0.0};
    public static final double[] TRAP_POSITION = {0.0, 0.0 ,0.0};
    public static final double SHOOTER_NINETY = 0; //Undefined
    public static final double POSITION_TOLERANCE = 10; // this is encoder degrees. 10 encoder degrees of tolerance to start moving the arm in ArmSubsystem
  // Driving.
    public static final int DRIVE_CONTROLLER = 0;
    public static final double DRIVE_SPEED = 0.9;
    public static final double DRIVE_TURN_SPEED = 0.9;
  // Controller.
    public static final double DRIVE_CONTROLLER_DEAD_ZONE = 0.05;

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

    public static final PIDConfiguration SWERVE_MODULE_TURN_PID = new PIDConfiguration(0.005, 0.00, 0.0
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