// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.Talon;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix6.hardware.TalonFX;
import frc.robot.Constants;
import utilities.ConfigurablePID;

public class ArmSubsytem extends SubsystemBase {
  /** Creates a new ArmSubsytem. */
  //Motors 
  private TalonFX shoulderMotor;
  private TalonFX forearmMotor;
  private TalonFX wristMotor;
  //private TalonFX[] armMotors = {shoulderMotor, forearmMotor, wristMotor};
  private TalonFX primaryRollerMotor;
  private TalonFX secondaryRollerMotor;
  private ConfigurablePID armJointPID;
  public ArmSubsytem() {
    shoulderMotor = new TalonFX(Constants.SHOULDER_MOTOR_ID);
    forearmMotor = new TalonFX(Constants.FOREARM_MOTOR_ID);
    wristMotor = new TalonFX(Constants.WRIST_MOTOR_ID);
    primaryRollerMotor = new TalonFX(Constants.PRIMARY_ROLLER_MOTOR_ID);
    secondaryRollerMotor = new TalonFX(Constants.SECONDARY_ROLLER_MOTOR_ID);
    armJointPID = new ConfigurablePID(Constants.ARM_JOINT_CONTROLLER);

  }

  public double[] getCurrentPositions(){
    double shoulderCurrentPosition = shoulderMotor.getPosition().refresh().getValueAsDouble();
    double forearmCurrentPosition = forearmMotor.getPosition().refresh().getValueAsDouble();
    double wristCurrentPosition = wristMotor.getPosition().refresh().getValueAsDouble();
    double[] positionArray = {shoulderCurrentPosition, forearmCurrentPosition, wristCurrentPosition};
    return positionArray;
  }
public void gotToSetPoints(double[] setPoints) {
  double[] currentPositions = getCurrentPositions();
  double shoulderOutput = armJointPID.runPID(setPoints[0], currentPositions[0]);
  double forearmOutput = armJointPID.runPID(setPoints[1], currentPositions[1]);
  shoulderMotor.set(shoulderOutput);
  forearmMotor.set(forearmOutput);
  //System.out.println(shoulderOutput);
    }



  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
