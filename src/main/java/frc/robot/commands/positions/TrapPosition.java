// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.positions;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.commands.ShootCommand;
import frc.robot.subsystems.ArmSubsytem;

public class TrapPosition extends Command {
  /** Creates a new TrapPosition. */
  ArmSubsytem m_armSubsytem;
  public TrapPosition(ArmSubsytem armSubsytem){
    m_armSubsytem = armSubsytem;
    addRequirements(m_armSubsytem);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    ShootCommand.currentArmPosition = 3;
    m_armSubsytem.gotToSetPoints(Constants.TRAP_POSITION);
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
