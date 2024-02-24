// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.positions;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.commands.ShootCommand;
import frc.robot.subsystems.ArmSubsytem;

public class DefaultPosition extends Command {
  /** Creates a new DefaultPosition. */
  ArmSubsytem m_armSubsytem;
  public DefaultPosition(ArmSubsytem armSubsytem) {
    armSubsytem = m_armSubsytem;
    addRequirements(m_armSubsytem);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    ShootCommand.currentArmPosition = 99; //set to an arbitrary number so that the switch case statement in shoot command is matched to "default". it does nothing
    m_armSubsytem.gotToSetPoints(Constants.DEFAULT_POSITION);
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
