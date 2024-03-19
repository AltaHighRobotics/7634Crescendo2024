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
    System.out.println(yPID);
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
    currentRotation = 0;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    yPID = 0;
    xPID = 0;
    rotationPID = 0;
    PhotonTrackedTarget currentTarget = m_aprilTagSubsystem.getBestTarget();
    //if you dont see a tag and are running out of a time, just move foward. You should get out of the zone.


    if(loadedNote && readyToShoot) {
      if (startTimer){
      shootTimeout = System.currentTimeMillis();
      startTimer = false;
      }
      System.out.println(System.currentTimeMillis() - shootTimeout);
      if(System.currentTimeMillis() - shootTimeout <= 1000){
      m_shootSubsystem.shootSpeaker();}
      if (System.currentTimeMillis() - shootTimeout >= 1000){
        m_shootSubsystem.spinIntakeMotor(Constants.INTAKE_SPEED);
        if(System.currentTimeMillis() - shootTimeout >= 1100){
          loadedNote = false;
          readyToShoot = false;
          startTimer = false;
          shotNumber++;
          shootTimeout = 0;
         m_shootSubsystem.setFlywheel(0);
         m_shootSubsystem.spinIntakeMotor(0);
        }
       m_shootSubsystem.setFlywheel(0);
         m_shootSubsystem.spinIntakeMotor(0);
      }
    }


    




  if(currentTarget != null && (currentTarget.getFiducialId() == 7 || currentTarget.getFiducialId() == 4)){
    //if youre just starting, get all the starting info
    if(!gotSpeakerPosition){
      speakerPosition = m_aprilTagSubsystem.getSpeakerPosition(currentTarget);
      startingAngle = m_aprilTagSubsystem.getAngleDifference(currentTarget);
      startingXdistance = m_aprilTagSubsystem.xDistance(currentTarget);
      startingYdistance = m_aprilTagSubsystem.yDistance(currentTarget);
      m_driveTrainSub.fieldCentricOffset = 180 - (startingAngle * speakerPosition); // set field oriented offset to 180 - where we are.
      gotSpeakerPosition = true;
        }
    currentX = m_aprilTagSubsystem.xDistance(currentTarget);
    currentY = m_aprilTagSubsystem.yDistance(currentTarget);
    currentRotation = currentTarget.getYaw();

    //switch statement for which side of the speaker your on. think of it as choosing the autonomous by itself
    switch(speakerPosition){
      case -1:
        //insert code for auto nearest to the amp
        if (startTime - System.currentTimeMillis() >= 10){ // wait 10 seconds  to go to make sure that we dont hit any other auto bots getting notes
        xPID = m_aprilTagSubsystem.xPID(currentX, -40); // go to roughly the amp
        yPID = m_aprilTagSubsystem.yPID(currentY, 120); // go slightly above the auto
        //rotationPID = m_aprilTagSubsystem.rotationPID(currentRotation);// always face apriltag
       break;
        }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////
      case 1:
        //code for auto farthest from amp
        xPID = m_aprilTagSubsystem.xPID(currentX, 120);
        yPID = m_aprilTagSubsystem.yPID(currentY, 75);
        rotationPID = m_aprilTagSubsystem.rotationPID(currentRotation);
////////////////////////////////////////////////////////////////////////////////////////////
      case 0:
              //4 note auto code. get ready for a ride
              System.out.println("I am Head On!");
              System.out.println("ShotNumber: "+shotNumber);
              
              switch(shotNumber){
                  case 1:
                    xPID = m_aprilTagSubsystem.xPID(currentX, 56);
                    //yPID = m_aprilTagSubsystem.yPID(currentY, 0);
                    rotationPID = m_aprilTagSubsystem.rotationPID(currentRotation);
                    // m_shootSubsystem.spinIntakeMotor(Constants.INTAKE_SPEED);
                    // if (currentY >= 125){
                    //   loadedNote = true;
                    // }
                    // if (loadedNote){ //if we got the note, then go back and shoot.
                    //   xPID = m_aprilTagSubsystem.xPID(currentX, startingXdistance);
                    //   yPID = m_aprilTagSubsystem.yPID(currentY, startingYdistance);
                    //   if (currentY < 40){ //if were close enough to shoot, shoot
                    //     readyToShoot = true;
                    //     startTimer = true;
                    //   }
                    // }
                    break;
                  case 2:
                    // go the the back left note
                    xPID = m_aprilTagSubsystem.xPID(currentX, -57);
                    yPID = m_aprilTagSubsystem.yPID(currentY, 130);
                    rotationPID = m_aprilTagSubsystem.rotationPID(currentRotation);
                    // if youre roughly there, assume you got it
                    if (currentY >= 125 && xPID <= -55){
                      loadedNote = true;
                    // if youve got it, go back to the start
                    }
                    if(loadedNote){
                      xPID = m_aprilTagSubsystem.xPID(currentX, startingXdistance);
                      yPID = m_aprilTagSubsystem.yPID(currentY, startingYdistance);
                      rotationPID = m_aprilTagSubsystem.rotationPID(currentRotation);
                    }
                    // if youre close enough to shoot, shoot.
                    if(currentY <= 40 && Math.abs(currentX) - Constants.REZERO_TOLERANCE <= 4){
                      readyToShoot = true;
                      startTimer = true;
                    }
                    break;
                  case 3:
                    xPID = m_aprilTagSubsystem.xPID(currentX, 57);
                    yPID = m_aprilTagSubsystem.yPID(currentY, 130);
                    rotationPID = m_aprilTagSubsystem.rotationPID(currentRotation);
                    if (currentY >= 125 && xPID >= 55){
                      loadedNote = true;
                    }
                    if(loadedNote){
                      xPID = m_aprilTagSubsystem.xPID(currentX, startingXdistance);
                      yPID = m_aprilTagSubsystem.yPID(currentY, startingYdistance);
                      rotationPID = m_aprilTagSubsystem.rotationPID(currentRotation);
                    }
                    if (currentY <= 40 && Math.abs(currentX)- Constants.REZERO_TOLERANCE <= 4){
                      readyToShoot = true;
                      startTimer = true;
                    }
                  



                      
               
                        









              }













    }
  }
  m_driveTrainSub.drive(yPID, xPID, rotationPID, true,1);
  m_driveTrainSub.run();
































  
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
