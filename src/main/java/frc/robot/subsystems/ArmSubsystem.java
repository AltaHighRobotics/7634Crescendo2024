// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.ctre.phoenix6.hardware.TalonFX;
import frc.robot.Constants;
import utilities.ConfigurablePID;
import java.lang.Math;


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

    shoulderMotor.setNeutralMode(NeutralModeValue.Brake);
    forearmMotor.setNeutralMode(NeutralModeValue.Brake);
    wristMotor.setNeutralMode(NeutralModeValue.Brake);
  }
  // public void setShoulderMotor(double power){
  //   shoulderMotor.set(power);
  // }
  // public void setForearmMotor(double power){
  //   forearmMotor.set(power);
  // }
  // public void setWristMotor(double power){
  //   wristMotor.set(power);

  // I smell of old weed stored in wet socks

  // useful video that helped us a lot program this robot: https://youtu.be/hvL1339luv0fgvbnm,./
  
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



// shadyn suggested that when a joint is within a certain tolerance, we lock the joint to prevent it from swaying.
//The function below is purely PID controllers, no breaking.
 /*  public void goToSetPoints(double[] setPoints){
    //get current encoder values for all joints
    double[] positionArray = getCurrentPositions();
    //run a PID loop to calculate power to put each joint to its SetPoint
    double shoulderOutput = armJointPID.runPID(setPoints[0], positionArray[0]);
    double forearmOutput = armJointPID.runPID(setPoints[1], positionArray[1]);
    double wristOutput = armJointPID.runPID(setPoints[2], positionArray[2]);
    //Apply output of PID loops to motors, moving them to setpoint as well as push values to smartDashboard
    SmartDashboard.putNumberArray("Joint SetPoints: ", setPoints);
    SmartDashboard.putNumberArray("Joint Current Positions: ", positionArray);
    shoulderMotor.set(VictorSPXControlMode.PercentOutput, shoulderOutput);
    forearmMotor.set(VictorSPXControlMode.PercentOutput, forearmOutput);
    wristMotor.set(VictorSPXControlMode.PercentOutput, wristOutput);
  }
*/
// This function implements shaydns suggestion as described in the function above.
//its also a villion times better

public void goToSetPoints(double[] setPoints){
  TalonFX[] jointMotors = {shoulderMotor, forearmMotor, wristMotor};
  // this loops over each motor, moves it to position, and checks if its within tolerance, and stops the motors
  //the thinking is that instead of using a pid controller to keep it steady, we stop the motors from moving at all
  for (int i = 0; i < jointMotors.length; ++i) {
    double currentPosition = jointMotors[i].getPosition().refresh().getValueAsDouble();

    if (Math.abs(currentPosition) < Constants.JOINT_TOLERANCE) {
      jointMotors[i].set(0.0);
      //motors are config'd to stay in the same spot when given no output
    }
    else {
      jointMotors[i].set(armJointPID.runPID(setPoints[i], currentPosition));
    }

    SmartDashboard.putNumber("Join motor: " + i, currentPosition);
  }

  }


  




  @Override
  public void periodic() {
    // This method will be called once per scheduler run


  

  }
}