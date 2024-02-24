// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkBase.IdleMode;

public class ChainSubsystem extends SubsystemBase {
  /** Creates a new ChainSubsystem. */
  private CANSparkMax chainMotor;
  private Solenoid brakeSolenoid;  
  public ChainSubsystem() {
    chainMotor = new CANSparkMax(Constants.CHAIN_MOTOR_ID, MotorType.kBrushless);
    brakeSolenoid = new Solenoid(PneumaticsModuleType.CTREPCM, Constants.BRAKE_SOLENOID); //
    chainMotor.setIdleMode(IdleMode.kCoast);

  }
  public double getEncoderPosition(){
    return chainMotor.getEncoder().getPosition();
  }
  
  public boolean getBrakePos(){
    return brakeSolenoid.get();

  }
  public void brakeOn(){
    brakeSolenoid.set(true);
  }
  public void brakeOff(){
    brakeSolenoid.set(false);
  }
  public void toggleBrake(){
    brakeSolenoid.toggle();

  }
  public void setChainArm(double speed){
    chainMotor.set(speed);
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
