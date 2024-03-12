// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.ShootSubsystem;
import java.lang.Math;
public class IntakeCommand extends Command {
  /** Creates a new IntakeCommand. */
  ShootSubsystem m_shootSubsystem;
  double startingPosition;
  int backRotations;
  public IntakeCommand(ShootSubsystem shootSubsystem) {
    m_shootSubsystem = shootSubsystem;
    addRequirements(m_shootSubsystem);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    startingPosition = m_shootSubsystem.FlyWheelEncoder();
    backRotations = 0;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double endPosition = m_shootSubsystem.FlyWheelEncoder();
    if (Math.abs(endPosition-startingPosition) > Constants.ENCODER_TOLERANCE){
      if (backRotations < Constants.MAX_BACK_ROTATIONS){
        m_shootSubsystem.spinIntakeMotor(-Constants.INTAKE_SPEED);
        backRotations++;
      }
      else{
        m_shootSubsystem.spinIntakeMotor(0);
      }
    }
    else
    {
      m_shootSubsystem.spinIntakeMotor(Constants.INTAKE_SPEED);
    }

    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
