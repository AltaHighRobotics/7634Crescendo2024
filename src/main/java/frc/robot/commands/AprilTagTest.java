// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.AprilTagSubsystem;

public class AprilTagTest extends Command {
  /** Creates a new AprilTagTest. */
  AprilTagSubsystem m_aprilTagSubsystem;
  public AprilTagTest(AprilTagSubsystem aprilTagSubsystem) {
    m_aprilTagSubsystem = aprilTagSubsystem;
    addRequirements(m_aprilTagSubsystem);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(m_aprilTagSubsystem.getTargetID() == 4){
      System.out.println("april tag 4");
    }
    else{
      System.err.println(m_aprilTagSubsystem.getMovement());
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
