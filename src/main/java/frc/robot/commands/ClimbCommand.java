// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ChainSubsystem;
import frc.robot.commands.*;
import frc.robot.commands.positions.DefaultPosition;

public class ClimbCommand extends Command {
  DefaultPosition m_defaultPosition;
  /** Creates a new ClimbCommand. */
  boolean activated;
  ChainSubsystem m_chainSubsystem;
  public ClimbCommand(ChainSubsystem chainSubsystem, DefaultPosition defaultPosition) {
    m_chainSubsystem = chainSubsystem;
    m_defaultPosition = defaultPosition;
    addRequirements(m_chainSubsystem);
    activated = false;
    
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(activated){
      m_chainSubsystem.brakeOff();
      m_chainSubsystem.setChainArm(0);
      
      

      





    }

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_defaultPosition.schedule();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
