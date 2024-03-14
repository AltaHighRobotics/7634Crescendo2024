// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import org.photonvision.targeting.PhotonTrackedTarget;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.AprilTagSubsystem;
import frc.robot.subsystems.ShootSubsystem;
import frc.robot.swerve.DriveTrainSub;

public class SillyAuto extends Command {
  /** Creates a new AutoCommand. */
  private DriveTrainSub m_driveTrainSub;
  private boolean done;
  private boolean shot;
  private long startTime;
  private AprilTagSubsystem m_aprilTagSubsystem;
  private ShootSubsystem m_shootSubsystem;
  private int speakerPosition;
  public SillyAuto(DriveTrainSub driveTrainSub, AprilTagSubsystem aprilTagSubsystem, ShootSubsystem shootSubsystem) {
    m_driveTrainSub = driveTrainSub;
    m_aprilTagSubsystem = aprilTagSubsystem;
    m_shootSubsystem = shootSubsystem;
    addRequirements(m_driveTrainSub, m_shootSubsystem);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    done = false;
    shot = false;
    speakerPosition = 10;
    m_driveTrainSub.resetGyro();
    startTime = System.currentTimeMillis();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    PhotonTrackedTarget currentTarget = m_aprilTagSubsystem.getBestTarget();
    //if you dont see a tag and are running out of a time, just move foward. You should get out of the zone.
    if(currentTarget == null && System.currentTimeMillis() - startTime >=10){
      m_driveTrainSub.drive(0,0.5,0,false,1);
    }
    //zero your gyroscope for driving based on april tag
    if(!shot){
      m_shootSubsystem.shootSpeaker();
      if(System.currentTimeMillis() - startTime >=2){
        m_shootSubsystem.spinIntakeMotor(Constants.INTAKE_SPEED);
      }
      if(System.currentTimeMillis() - startTime >= 3){
        shot = true;
      }
    }
    else if(currentTarget.getFiducialId() == 7 || currentTarget.getFiducialId() == 4){
    speakerPosition = m_aprilTagSubsystem.getSpeakerPosition(currentTarget);
    double startingAngle = m_aprilTagSubsystem.getAngleDifference(currentTarget);
    m_driveTrainSub.fieldCentricOffset = 180 - (startingAngle * speakerPosition);
    switch(speakerPosition){
      case 1:
        //insert code if youre nearest to the "bad" april tag"
    }
    }
    

    

    

    


    


    // m_driveTrainSub.drive(0.0,0.5,0,false,1.0);

    // if (System.currentTimeMillis() - startTime >=2500) {
    //   done = true;
    // }

    // m_driveTrainSub.run();
    }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_driveTrainSub.resetGyro();
    m_driveTrainSub.drive(0.5, 0.0, 0.0, false, 0.0);// stop the robot (:
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return done;
  }
}
