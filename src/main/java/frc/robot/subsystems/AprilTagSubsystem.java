// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import org.photonvision.PhotonCamera;
import org.photonvision.targeting.PhotonTrackedTarget;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import utilities.ConfigurablePID;
import utilities.MeasurementConverters;
import edu.wpi.first.math.geometry.*;

import frc.robot.Constants;
import java.util.List;
public class AprilTagSubsystem extends SubsystemBase {
  /** Creates a new AprilTagSubsystem. */
  PhotonCamera camera;
  //final Map<Integer, Position> tagPositions = new HashMap<>();
    //makes a dictonary of all april tag ID's and Positions on the field
  ConfigurablePID autoPID;
  public AprilTagSubsystem() {
    camera = new PhotonCamera("test");
    // tagPositions.put(1, new Position(593.68, 9.68, 53.38, 120));
    // tagPositions.put(2, new Position(637.21, 34.79, 53.38, 120));
    // tagPositions.put(3, new Position(652.73, 196.17, 57.13, 180));
    // tagPositions.put(4, new Position(652.73, 218.42, 57.13, 180));
    // tagPositions.put(5, new Position(578.77, 323.00, 53.38, 270));
    // tagPositions.put(6, new Position(72.5, 323.00, 53.38, 270));
    // tagPositions.put(7, new Position(-1.50, 218.42, 57.13, 0));
    // tagPositions.put(8, new Position(-1.50, 196.17, 57.13, 0));
    // tagPositions.put(9, new Position(14.02, 34.79, 53.38, 60));
    // tagPositions.put(10, new Position(57.54, 9.68, 53.38, 60));
    // tagPositions.put(11, new Position(468.69, 146.19, 52.00, 300));
    // tagPositions.put(12, new Position(468.69, 177.10, 52.00, 60));
    // tagPositions.put(13, new Position(441.74, 161.62, 52.00, 180));
    // tagPositions.put(14, new Position(209.48, 161.62, 52.00, 0));
    // tagPositions.put(15, new Position(182.73, 177.10, 52.00, 120));
    autoPID = new ConfigurablePID(Constants.AUTO_PID);
    // tagPositions.put(16, new Position(182.73, 146.19, 52.00, 240));
  }
  public PhotonTrackedTarget getBestTarget(){
    List<PhotonTrackedTarget> targets = camera.getLatestResult().getTargets();
    int listLength = targets.size();
    if (listLength == 0){
      return null;
    }
    for(int i = 0; i < listLength; i++){  
    PhotonTrackedTarget bestTarget = targets.get(i);
    if (bestTarget.getFiducialId() == 3 || bestTarget.getFiducialId() == 8){
      if(listLength-1> i){
        if (targets.get(i+1).getFiducialId() == 4 || targets.get(i+1).getFiducialId() == 7){
          return targets.get(i+1);
        }
      }
      return null;
    }
    return targets.get(i);
    }
    return null;
    //return camera.getLatestResult().getBestTarget();
  }
  public double[] getPresetPosition(PhotonTrackedTarget currentTarget){
    double xDistanceFromTarget = xDistance(currentTarget);
    double yDistanceFromTarget = yDistance(currentTarget);
    double zAngleFromTarget = currentTarget.getYaw();
    double xPIDOutput = 0;
    double yPIDOutput = 0;
    double zPIDOutput = 0;
    int targetID = currentTarget.getFiducialId();
    switch(targetID){
      case 7:
        //speaker stuff
        xPIDOutput = xPID(xDistanceFromTarget, Constants.X_DIS_SPEAKER);
        yPIDOutput = yPID(yDistanceFromTarget, Constants.Y_DIS_SPEAKER);
        zPIDOutput = rotationPID(zAngleFromTarget);
        break;
      case 14:
        //speaker stuff
        xPIDOutput = xPID(xDistanceFromTarget, Constants.X_DIS_SPEAKER);
        yPIDOutput = yPID(yDistanceFromTarget, Constants.Y_DIS_SPEAKER);
        zPIDOutput = rotationPID(zAngleFromTarget);
        break;
      case 4:
        //ampstuff
        break;
    }
      double[] PIDPowers = {xPIDOutput, yPIDOutput, zPIDOutput};
      return(PIDPowers);
    
    


  }
  
  public double yPID(double yPos, double ySetPoint){
    return (autoPID.runPID(yPos, ySetPoint));
  }

  public double xPID(double xPos, double xSetPoint){
    return(autoPID.runPID(xPos, xSetPoint));
  }
  public double rotationPID(double yaw){
    
    return(autoPID.runPID(yaw, 0));

  }

  public Transform3d getAprilTagDistance(PhotonTrackedTarget currentTarget){
      Transform3d aprilTagDistances = currentTarget.getBestCameraToTarget();
      double x = MeasurementConverters.MeterstoInches(aprilTagDistances.getX());
      double y = MeasurementConverters.MeterstoInches(aprilTagDistances.getY());
      double z = MeasurementConverters.MeterstoInches(aprilTagDistances.getZ());
      Rotation3d rotation = aprilTagDistances.getRotation();
      Transform3d DistanceInInches = new Transform3d(x,y,z,rotation);
      return(DistanceInInches);
  }

  public boolean withinRotationTolerance(PhotonTrackedTarget currentTarget){
    if(currentTarget.getYaw() <= Constants.ROTATION_TOLERANCE){
      return true;
    }
    return false;
  }

public double relativeYaw(PhotonTrackedTarget currentTarget){
  return currentTarget.getYaw();

}
public boolean closeEnoughtoShoot(double startingX, double startingY, double currentX, double currentY){
  if (Math.abs(currentY) - Constants.REZERO_TOLERANCE <= Math.abs(startingY) && Math.abs(currentY)- Constants.REZERO_TOLERANCE <= Math.abs(startingY)){
    return true;
  }
  return false;
}
public int getSpeakerPosition(PhotonTrackedTarget currentTarget){
  PhotonTrackedTarget inclusiveBestTarget = camera.getLatestResult().getBestTarget();
  int targetID = 0;
  if(inclusiveBestTarget != null){
    targetID = inclusiveBestTarget.getFiducialId();
    if (targetID == 8 || targetID == 3){
      return 1; //right side, closer to offset april tag
    }
    double currentAngle = currentTarget.getYaw();
    if(Math.abs(currentAngle) >15){
      System.out.println(currentAngle);
      return -1;
    }
    
  }
  return 0;
}
public boolean hasTargets(){
  PhotonTrackedTarget bestTarget = getBestTarget();
  if (bestTarget == null){
    return false;
  }
  return true;
}//in photonvision, X is how far away you are head on, y is is lateral movement... I switched that
  public double xDistance(PhotonTrackedTarget currentTarget){
      Transform3d aprilTagDistances = currentTarget.getBestCameraToTarget();
      double x = MeasurementConverters.MeterstoInches(aprilTagDistances.getY());
      return((x));
  }
  public double yDistance(PhotonTrackedTarget currentTarget){
    Transform3d aprilTagDistances = currentTarget.getBestCameraToTarget();
      double y = MeasurementConverters.MeterstoInches(aprilTagDistances.getX());
      return(y+Constants.CAMERA_OFFSET);
  }

  public double zDistance(PhotonTrackedTarget currentTarget){
    Transform3d aprilTagDistances = currentTarget.getBestCameraToTarget();
      double z = MeasurementConverters.MeterstoInches(aprilTagDistances.getZ());
      return(z);
  }
  public double getAngleDifference(PhotonTrackedTarget currentTarget){
    Transform3d bestToCamera = currentTarget.getBestCameraToTarget();
    double rotation = bestToCamera.getRotation().getAngle()*57.2958;
    double aprilAngleDifference = 180 - rotation;
    return aprilAngleDifference;
  }
  // You smell like peee
  public boolean testDistance(PhotonTrackedTarget currentTarget){
    double divisorAmount;
    int targetID = currentTarget.getFiducialId();
    if (targetID == 4 || targetID == 7){
       divisorAmount = Constants.SPEAKER_DIVISOR;
    }
    else if(targetID == 5 || targetID == 6){
       divisorAmount = Constants.AMP_DIVISOR;
    }
    else{
       divisorAmount = 1;
    }
    Transform3d currentPositions = getAprilTagDistance(currentTarget);
    double x = Math.pow(currentPositions.getX(),2);
    double y = Math.pow(currentPositions.getY(),2);
    double totalDistance = Math.sqrt(x+y);
    double aprilAngleDifference = getAngleDifference(currentTarget);
    double acceptableRadius = totalDistance/(aprilAngleDifference/divisorAmount)+38;
    if (acceptableRadius > 66){
      acceptableRadius = 66;
    }
    if (totalDistance <= acceptableRadius && aprilAngleDifference < 70){
      return true;
    }

    return false;
  }


    

    



    
  

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
