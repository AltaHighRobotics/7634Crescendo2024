// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import org.photonvision.targeting.PhotonTrackedTarget;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.AprilTagSubsystem;
import frc.robot.subsystems.ShootSubsystem;


public class ShootCommand extends Command {
  /** Creates a new ShootCommand. */
  ShootSubsystem m_shootSubsystem;
  AprilTagSubsystem m_aprilTagSubsystem;
  public ShootCommand(ShootSubsystem shootSubsystem, AprilTagSubsystem aprilTagSubsystem) {
    m_aprilTagSubsystem = aprilTagSubsystem;
    m_shootSubsystem = shootSubsystem;
    addRequirements(m_shootSubsystem, m_aprilTagSubsystem);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    PhotonTrackedTarget currentTarget = m_aprilTagSubsystem.getBestTarget();
    int targetId =currentTarget.getFiducialId();
    if (m_aprilTagSubsystem.testDistance(currentTarget)){
    switch(targetId){
      default:
        break;
      case 4:
        m_shootSubsystem.shootSpeaker();
        break;
      case 5:
        m_shootSubsystem.shootAmp();
        break;
      case 6:
        m_shootSubsystem.shootAmp();
        break;
      case 7:
        m_shootSubsystem.shootSpeaker();
        break;        
      }
    
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
