// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.lang.annotation.Target;

import org.photonvision.targeting.PhotonTrackedTarget;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.AprilTagSubsystem;
import frc.robot.subsystems.ShootSubsystem;


public class ShootCommand extends Command {
  /** Creates a new ShootCommand. */
  ShootSubsystem m_shootSubsystem;
  AprilTagSubsystem m_aprilTagSubsystem;
  double startTime;
  public ShootCommand(ShootSubsystem shootSubsystem, AprilTagSubsystem aprilTagSubsystem) {
    m_aprilTagSubsystem = aprilTagSubsystem;
    m_shootSubsystem = shootSubsystem;
    addRequirements(m_shootSubsystem, m_aprilTagSubsystem);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    startTime = System.currentTimeMillis();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double endTime = System.currentTimeMillis();
    int targetId;
    if(endTime-startTime > Constants.SPIN_TIME)
    {
      m_shootSubsystem.spinIntakeMotor(Constants.SPEAKER_SPEED);
    }
    PhotonTrackedTarget currentTarget = m_aprilTagSubsystem.getBestTarget();
    if(currentTarget == null){
      targetId = -1;
    }
    else{
      targetId =currentTarget.getFiducialId();
    }
    if (m_aprilTagSubsystem.testDistance(currentTarget) && targetId >-1){
    SmartDashboard.putBoolean("Can Shoot", (m_aprilTagSubsystem.testDistance(currentTarget)));
    switch(targetId){
      default:
      m_shootSubsystem.shootSpeaker();
        break;
      case 5:
        m_shootSubsystem.shootAmp();
        break;
      case 6:
        m_shootSubsystem.shootAmp();
        break;      
      }
    }
    else{
      m_shootSubsystem.shootSpeaker();
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
