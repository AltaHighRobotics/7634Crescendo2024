// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.ShootSubsystem;

public class OuttakeCommand extends Command {
  /** Creates a new OuttakeCommand. */
  ShootSubsystem m_ShootSubsystem;
  public OuttakeCommand(ShootSubsystem shootSubsystem) {
    m_ShootSubsystem = shootSubsystem;
    addRequirements(m_ShootSubsystem);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_ShootSubsystem.setFlywheel(-0.9);
    m_ShootSubsystem.spinIntakeMotor(-Constants.INTAKE_SPEED);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_ShootSubsystem.setFlywheel(0);
    m_ShootSubsystem.spinIntakeMotor(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
