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
  private double shootTimeout;
  private boolean done;
  private boolean grabbedNote;
  private int shotNumber;
  private boolean gotSpeakerPosition;
  private long startTime;
  private AprilTagSubsystem m_aprilTagSubsystem;
  private ShootSubsystem m_shootSubsystem;
  private int speakerPosition;
  private double rotationPID;
  private double xPID;
  private double yPID;
  private double startingYdistance;
  private double startingXdistance;
  private double startingAngle;
  private double currentX;
  private double currentY;
  private double currentRotation;
  private boolean loadedNote;
  private boolean readyToShoot;
  private boolean startTimer;
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
    done = false;
    shotNumber = 0;  
    gotSpeakerPosition = false;
    speakerPosition = 10;
    m_driveTrainSub.resetGyro();
    startTime = System.currentTimeMillis();
    loadedNote = true;
    readyToShoot = true;
    startTimer = true;
    shootTimeout = 0;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    PhotonTrackedTarget currentTarget = m_aprilTagSubsystem.getBestTarget();
    //if you dont see a tag and are running out of a time, just move foward. You should get out of the zone.
    if(currentTarget == null && System.currentTimeMillis() - startTime >=10){
      if (yPID > 0){
      }
      else{
        m_driveTrainSub.resetGyro();
        yPID = 0.5;
      }
    }
    if(loadedNote && readyToShoot) {
      if (startTimer){
      shootTimeout = System.currentTimeMillis();
      }
      m_shootSubsystem.shootSpeaker();
      if (shootTimeout - System.currentTimeMillis() >= 1){
        m_shootSubsystem.spinIntakeMotor(Constants.INTAKE_SPEED);
        loadedNote = false;
        readyToShoot = false;
        startTimer = false;
      }


    }




  if(currentTarget.getFiducialId() == 7 || currentTarget.getFiducialId() == 4){
    //if youre just starting, get all the starting info
    if(!gotSpeakerPosition){
      speakerPosition = m_aprilTagSubsystem.getSpeakerPosition(currentTarget);
      startingAngle = m_aprilTagSubsystem.getAngleDifference(currentTarget);
      startingXdistance = m_aprilTagSubsystem.xDistance(currentTarget);
      startingYdistance = m_aprilTagSubsystem.yDistance(currentTarget);
      m_driveTrainSub.fieldCentricOffset = 180 - (startingAngle * speakerPosition);
      gotSpeakerPosition = true;
    }
    currentX = m_aprilTagSubsystem.xDistance(currentTarget);
    currentY = m_aprilTagSubsystem.yDistance(currentTarget);

    //switch statement for which side of the speaker your on. think of it as choosing the autonomous by itself
    switch(speakerPosition){
      case -1:
        //insert code for auto nearest to the amp
        xPID = m_aprilTagSubsystem.xPID(speakerPosition, );
        yPID =




    }
  }

































    // //if you dont see a tag and are running out of a time, just move foward. You should get out of the zone.
    // if(currentTarget == null && System.currentTimeMillis() - startTime >=10){
    //   if (yPID > 0){
    //   }
    //   else{
    //     yPID = 0.5;
    //   }

    // }
    
    // //zero your field centric for driving based on april tag

    // if(currentTarget.getFiducialId() == 7 || currentTarget.getFiducialId() == 4){

    // if(!firstShot){
    //   m_shootSubsystem.shootSpeaker();
    //   if(System.currentTimeMillis() - startTime >=1 && !firstShot){
    //     m_shootSubsystem.spinIntakeMotor(Constants.INTAKE_SPEED);
      
    //     speakerPosition = m_aprilTagSubsystem.getSpeakerPosition(currentTarget);
    //     startingAngle = m_aprilTagSubsystem.getAngleDifference(currentTarget);
    //     m_driveTrainSub.fieldCentricOffset = 180 - (startingAngle * speakerPosition);



    //     firstShot = true;
    //     startingYdistance = m_aprilTagSubsystem.yDistance(currentTarget);
    //     startingXdistance = m_aprilTagSubsystem.xDistance(currentTarget);
    //   }
    //   double relativeYaw = currentTarget.getYaw();    
    //   double yDistance = m_aprilTagSubsystem.yDistance(currentTarget);
    // double xDistance = m_aprilTagSubsystem.xDistance(currentTarget);
    // if (firstShot){
    // switch(speakerPosition){
    //   case 1:
    //     rotationPID = m_aprilTagSubsystem.rotationPID(relativeYaw);
    //     xPID = m_aprilTagSubsystem.xPID(xDistance, 120);
    //     yPID = m_aprilTagSubsystem.yPID(yDistance,  75);

    //     //insert code if youre nearest to the "bad" april tag
    //     break;
    //   case 0:
    //     //allign ourselves with the april tag
    //     rotationPID = m_aprilTagSubsystem.rotationPID(relativeYaw);
    //     //if you havent backed up yet, back up and grab note
    //     if(yDistance > 120){
    //       grabbedNote = true; 
    //     }
    //     //if you havent got a note, back up and grab one
    //     if (!grabbedNote){
    //     m_shootSubsystem.spinIntakeMotor(Constants.INTAKE_SPEED);
    //     yPID = m_aprilTagSubsystem.yPID(yDistance, 120);
    //     }
    //     else {
    //     yPID = m_aprilTagSubsystem.yPID(yDistance, startingYdistance);
    //     if (yDistance < 40){
    //       m_shootSubsystem.shootSpeaker();
    //       m_shootSubsystem.spinIntakeMotor(Constants.INTAKE_SPEED);
    //       grabbedNote = false;
    //     }
    //     }
    //     break;
    //   case -1:
    //     if(System.currentTimeMillis()-startTime >= 10){
    //       rotationPID = m_aprilTagSubsystem.rotationPID(relativeYaw);
    //       xPID = m_aprilTagSubsystem.xPID(xDistance, startingXdistance);
    //       yPID = m_aprilTagSubsystem.yPID(yDistance, 50);

    //     }
    //     //inset code if youre closest to amp
    //     break;
    // }
    // }  
    // }
    // m_driveTrainSub.drive(xPID,yPID,rotationPID,false,1);
    // m_driveTrainSub.run();
  }
    

    

    

    


    


    // m_driveTrainSub.drive(0.0,0.5,0,false,1.0);

    // if (System.currentTimeMillis() - startTime >=2500) {
    //   done = true;
    // }

    // m_driveTrainSub.run();
   // }

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
