// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.ctre.phoenix6.hardware.TalonFX;

public class ShootSubsystem extends SubsystemBase {
  /** Creates a new ShootSubsystem. */
  TalonFX leftFlywheel;
  TalonFX rightFlywheel;
  TalonFX intakeMotor;
  public ShootSubsystem() {
    leftFlywheel = new TalonFX(Constants.LEFT_FLYWHEEL_ID);
    rightFlywheel = new TalonFX(Constants.RIGHT_FLYWHEEL_ID);
    intakeMotor = new TalonFX(Constants.INTAKE_MOTOR_ID);
  }
public void shootSpeaker(){
  leftFlywheel.set(Constants.SPEAKER_SPEED);
  rightFlywheel.set(-Constants.SPEAKER_SPEED);
}
public void shootAmp(){
  rightFlywheel.set(Constants.AMP_SPEED);
  leftFlywheel.set(-Constants.AMP_SPEED);
}
public void setFlywheel(double power){
  rightFlywheel.set(power);
  leftFlywheel.set(-power);
}
public double FlyWheelEncoder(){
  return leftFlywheel.getPosition().getValueAsDouble();
  
}
public void spinIntakeMotor(double power){
  intakeMotor.set(power);
}







  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
