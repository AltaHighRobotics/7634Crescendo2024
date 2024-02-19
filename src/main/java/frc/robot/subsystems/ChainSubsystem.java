// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;

public class ChainSubsystem extends SubsystemBase {
  /** Creates a new ChainSubsystem. */
  private TalonFX chainMotor;
  public ChainSubsystem() {
    chainMotor = new TalonFX(Constants.CHAIN_MOTOR_ID);


  }


  public void liftChainArm(double speed){
    chainMotor.set(speed);
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
