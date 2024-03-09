// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.AprilTagSubsystem;
import frc.robot.swerve.DriveTrainSub;

public class AprilTagTest extends Command {
  /** Creates a new AprilTagTest. */
  int allianceColor;
  AprilTagSubsystem m_aprilTagSubsystem;
  DriveTrainSub m_driveTrainSub;
  public AprilTagTest(AprilTagSubsystem aprilTagSubsystem, DriveTrainSub driveTrainSub) {
    m_aprilTagSubsystem = aprilTagSubsystem;
    m_driveTrainSub = driveTrainSub;
    addRequirements(m_driveTrainSub);
    addRequirements(m_aprilTagSubsystem);
    allianceColor = 0;
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(allianceColor == 0){

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
