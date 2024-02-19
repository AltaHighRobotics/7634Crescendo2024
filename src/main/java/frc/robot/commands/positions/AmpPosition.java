// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.positions;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.commands.shootCommand;
import frc.robot.subsystems.ArmSubsytem;

public class AmpPosition extends Command {
  ArmSubsytem m_armSubsystem;
  /** Creates a new AmpPosition. */

  public AmpPosition(ArmSubsytem armSubsytem) {
    m_armSubsystem = armSubsytem;
    addRequirements(m_armSubsystem);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    shootCommand.currentArmPosition = 0;
    m_armSubsystem.gotToSetPoints(Constants.AMP_POSITION);
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
