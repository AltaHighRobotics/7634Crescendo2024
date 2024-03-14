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
  private boolean outOfZone;
  private boolean grabbedNote;
  private long startTime;
  private AprilTagSubsystem m_aprilTagSubsystem;
  private ShootSubsystem m_shootSubsystem;
  private int speakerPosition;
  private double rotationPID;
  private double xPID;
  private double yPID;
  private double startingYdistance;
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
    startingYdistance = Constants.Y_DIS_SPEAKER;
    rotationPID = 0;
    xPID = 0;
    yPID = 0;
    outOfZone = false;
    done = false;
    firstShot = false;
    secondShot = false;
    grabbedNote = false;
    
    speakerPosition = 10;
    m_driveTrainSub.resetGyro();
    startTime = System.currentTimeMillis();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    PhotonTrackedTarget currentTarget = m_aprilTagSubsystem.getBestTarget();
    //if you dont see a tag and are running out of a time, just move foward. You should get out of the zone.
    if(currentTarget == null && System.currentTimeMillis() - startTime >=10 && !outOfZone){
      yPID = 0.5;
    }
    if(!firstShot){
      m_shootSubsystem.shootSpeaker();
      if(System.currentTimeMillis() - startTime >=2){
        m_shootSubsystem.spinIntakeMotor(Constants.INTAKE_SPEED);
      }
      if(System.currentTimeMillis() - startTime >= 3){
        
        firstShot = true;
        startingYdistance = m_aprilTagSubsystem.yDistance(currentTarget);
      }
    }
    //zero your field centric for driving based on april tag

    else if(currentTarget.getFiducialId() == 7 || currentTarget.getFiducialId() == 4){
    speakerPosition = m_aprilTagSubsystem.getSpeakerPosition(currentTarget);
    double startingAngle = m_aprilTagSubsystem.getAngleDifference(currentTarget);
      double relativeYaw = currentTarget.getYaw();
    m_driveTrainSub.fieldCentricOffset = 180 - (startingAngle * speakerPosition);
    double yDistance = m_aprilTagSubsystem.yDistance(currentTarget);
    double xDistance = m_aprilTagSubsystem.xDistance(currentTarget);
    switch(speakerPosition){
      case 1:
        //insert code if youre nearest to the "bad" april tag
        break;
      case 0:
        //allign ourselves with the april tag
        xPID = m_aprilTagSubystem.xPID(xDistance, 0);
        rotationPID = m_aprilTagSubsystem.rotationPID(yaw)
        //if you havent backed up yet, back up and grab note
        if(yDistance > 115){
          grabbedNote = true; 
        }
        //if you havent got a note, back up and grab one
        if (!grabbedNote){
        m_shootSubsystem.spinIntakeMotor(Constants.INTAKE_SPEED);
        yPID = m_aprilTagSubsystem.yPID(yDistance, 120);
        }
        else {
        yPID = m_aprilTagSubsyem.yPID(yDistance, startingYdistance);
        }
        
        
        //insert code if youre head on with speaker
        break;
      case -1:
        //inset code if youre closest to amp
        break;
    }  
    }
    m_driveTrainSub.drive(xPID,yPID,rotationPID,false,1);
    m_driveTrainSub.run();
    

    

    

    


    


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
