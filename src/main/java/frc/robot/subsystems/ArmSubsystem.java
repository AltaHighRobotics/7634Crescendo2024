// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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
  
  public void resetEncoderValues(){ // This function is to reset the encoders; **ONLY USE WHEN ROBOT IS AT STARTING POSITION**
    wristMotor.setPosition(0);
    shoulderMotor.setPosition(0);
    forearmMotor.setPosition(0);
  }



  //I can feel the boiler plate already, but fuck it I need something to work off of, even if its ass
  //this function returns the current postions of all encoders in a list. its orderd shoulderMotor, forearmMotor, wristMotor
  public double[] getCurrentPositions(){
    double shoulderCurrentPosition = shoulderMotor.getPosition().refresh().getValueAsDouble();
    double forearmCurrentPosition = forearmMotor.getPosition().refresh().getValueAsDouble();
    double wristCurrentPosition = wristMotor.getPosition().refresh().getValueAsDouble();
    double[] positionArray = {shoulderCurrentPosition, forearmCurrentPosition, wristCurrentPosition};
    return positionArray;
  }




  public void goToSetPoints(double[] setPoints){
    //get current encoder values for all joints
    double[] positionArray = getCurrentPositions();
    //run a PID loop to calculate power to put each joint to its SetPoint
    double shoulderOutput = armJointPID.runPID(setPoints[0], positionArray[0]);
    double forearmOutput = armJointPID.runPID(setPoints[1], positionArray[1]);
    double wristOutput = armJointPID.runPID(setPoints[2], positionArray[2]);
    //Apply output of PID loops to motors, moving them to setpoint as well as push values to smartDashboard
    SmartDashboard.putNumberArray("Joint SetPoints: ", setPoints);
    SmartDashboard.putNumberArray("Joint Current Positions: ", positionArray);
    shoulderMotor.set(shoulderOutput);
    forearmMotor.set(forearmOutput);
    wristMotor.set(wristOutput);
  }



  @Override
  public void periodic() {
    // This method will be called once per scheduler run


  

  }
}
