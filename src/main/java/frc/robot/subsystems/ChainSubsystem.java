// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkBase.IdleMode;

public class ChainSubsystem extends SubsystemBase {
  /** Creates a new ChainSubsystem. */
  private DigitalInput bottomLimitSwitch;
  private CANSparkMax chainMotor;
  public ChainSubsystem() {
    chainMotor = new CANSparkMax(Constants.CHAIN_MOTOR_ID, MotorType.kBrushless);
    chainMotor.setIdleMode(IdleMode.kBrake);
    bottomLimitSwitch = new DigitalInput(0);

  }
  public double getEncoderPosition(){
    return chainMotor.getEncoder().getPosition();
  }
  public boolean getLimitSwitch(){
    if(!bottomLimitSwitch.get()){
      return true;
    }
    return false;
  }
  
  public void setChainArm(double speed){
    chainMotor.set(speed);
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
