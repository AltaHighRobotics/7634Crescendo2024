// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix6.hardware.TalonFX;
import frc.robot.Constants;

public class ArmSubsystem extends SubsystemBase {
  /** Creates a new ArmSubsystem. */
  //create motors for each arm segment; Shoulder, Forearm, and wrist
  private TalonFX shoulderMotor;
  private TalonFX forearmMotor;
  private TalonFX wristMotor;
  
  public ArmSubsystem() {
    //initilizing the motors
    wristMotor = new TalonFX(Constants.WRIST_MOTOR_ID);
    forearmMotor = new TalonFX(Constants.FOREARM_MOTOR_ID);
    shoulderMotor = new TalonFX(Constants.SHOULDER_MOTOR_ID);
  }
  public void setShoulderMotor(double power){
    shoulderMotor.set(power);
  }
  public void setForearmMotor(double power){
    forearmMotor.set(power);
  }
  public void setWristMotor(double power){
    wristMotor.set(power);
  }
  
  @Override
  public void periodic() {
    // This method will be called once per scheduler run


  

  }
}
