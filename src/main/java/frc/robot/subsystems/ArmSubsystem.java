// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix6.hardware.TalonFX;
import frc.robot.Constants;
import utilities.ConfigurablePID;


public class ArmSubsystem extends SubsystemBase {
  /** Creates a new ArmSubsystem. */
  //define the PID to use for joints
  //create motors for each arm segment; Shoulder, Forearm, and wrist
  private TalonFX shoulderMotor;
  private TalonFX forearmMotor;
  private TalonFX wristMotor;
  private ConfigurablePID armJointPID;
  //not totally understanding what im supposed to do here
  //im gonna make one ConfigurablePID and use .runPID on it in 3 diff places :)
  public ArmSubsystem() {
    //initilizing the motors
    armJointPID = new ConfigurablePID(Constants.ARM_JOINT_CONTROLLER);
    wristMotor = new TalonFX(Constants.WRIST_MOTOR_ID);
    forearmMotor = new TalonFX(Constants.FOREARM_MOTOR_ID);
    shoulderMotor = new TalonFX(Constants.SHOULDER_MOTOR_ID);
  }
  // public void setShoulderMotor(double power){
  //   shoulderMotor.set(power);
  // }
  // public void setForearmMotor(double power){
  //   forearmMotor.set(power);
  // }
  // public void setWristMotor(double power){
  //   wristMotor.set(power);
  public void resetEncoderValues(){ // This function should only apply to StartingPosition.java. Its to make sure the encoders are 0'd on startup
    wristMotor.setPosition(0);
    shoulderMotor.setPosition(0);
    forearmMotor.setPosition(0);
  }
  
  public void shoulderPID(double encoderSetPoint){ // The Docs for Refresh/getPosition/getValueAsDouble: https://api.ctr-electronics.com/phoenix6/release/java/com/ctre/phoenix6/StatusSignal.html#refresh()
    //encoderCurrentPosition = TalonFX.getPosition(). This is meant to get the encoders current position
    //these functions are PID controllers that take {whereEncoderShouldbe} versus {whereEncoderIs} and tries to fix it :)
    double encoderCurrentPosition = shoulderMotor.getPosition().refresh().getValueAsDouble();
    double shoulderOutput = armJointPID.runPID(encoderSetPoint,encoderCurrentPosition);
    shoulderMotor.set(shoulderOutput);

  }
  public void forearmPID(double encoderSetPoint){ // The Docs for Refresh/getPosition/getValueAsDouble: https://api.ctr-electronics.com/phoenix6/release/java/com/ctre/phoenix6/StatusSignal.html#refresh()
    //encoderCurrentPosition = TalonFX.getPosition(). This is meant to get the encoders current position
    //these functions are PID controllers that take {whereEncoderShouldbe} versus {whereEncoderIs} and tries to fix it :)
    double encoderCurrentPosition = forearmMotor.getPosition().refresh().getValueAsDouble();
    double forearmOutput = armJointPID.runPID(encoderSetPoint, encoderCurrentPosition);
    forearmMotor.set(forearmOutput);
  }
  public void wristPID(double encoderSetPoint){ // The Docs for Refresh/getPosition/getValueAsDouble: https://api.ctr-electronics.com/phoenix6/release/java/com/ctre/phoenix6/StatusSignal.html#refresh()
    //encoderCurrentPosition = TalonFX.getPosition(). This is meant to get the encoders current position
    //these functions are PID controllers that take {whereEncoderShouldbe} versus {whereEncoderIs} and tries to fix it :)
    double encoderCurrentPosition = wristMotor.getPosition().refresh().getValueAsDouble();
    double wristOutput = armJointPID.runPID(encoderSetPoint, encoderCurrentPosition);
    wristMotor.set(wristOutput);
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run


  

  }
}
