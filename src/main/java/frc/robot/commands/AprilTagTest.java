// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import org.photonvision.targeting.PhotonTrackedTarget;

import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Default;

import edu.wpi.first.units.Measure;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.AprilTagSubsystem;
import frc.robot.swerve.DriveTrainSub;

public class AprilTagTest extends Command {
  /** Creates a new AprilTagTest. */
  int allianceColor;
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
    PhotonTrackedTarget currentTarget = m_aprilTagSubsystem.getBestTarget();
    if(currentTarget == null){
      return;
    }
    System.out.println(m_aprilTagSubsystem.testDistance(currentTarget));
    int targetId = currentTarget.getFiducialId();
    switch(targetId){
      default:
        break;
      case 7:
        //insert code to shoto at speaker
        break;
      case 4:
        //insert code to shoot at speaker
        break;
      case 6:
        //insert code to shoot at amp
        break;
      case 5:
        //insert code to shoot at amp
        break;
      


    }
    // System.out.println(m_aprilTagSubsystem.xDistance(currentTarget));
    // System.out.println(m_aprilTagSubsystem.yDistance(currentTarget)); 
    
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
