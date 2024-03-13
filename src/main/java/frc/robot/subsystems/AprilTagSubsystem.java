// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import org.photonvision.PhotonCamera;
import org.photonvision.targeting.PhotonTrackedTarget;
import java.util.Map;

import javax.lang.model.util.ElementScanner14;

import java.util.HashMap;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.apriltags.Position;
import utilities.ConfigurablePID;
import utilities.MeasurementConverters;
import edu.wpi.first.math.geometry.*;

import java.io.ObjectInputStream.GetField;
import java.lang.Math.*;
import frc.robot.Constants;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import java.util.List;

import frc.robot.Constants;
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
      if(listLength > i){ //fuck you kam if its fucked
        if (targets.get(i+1).getFiducialId() == 4 || targets.get(i+1).getFiducialId() == 7){
          return targets.get(i+1);
        }
      }
    }
    return targets.get(i);
    }
    return null;
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

  //public Trasform3d getRobotPosition(PhotonTrackedTarget currentTarget){
public double relativeYaw(PhotonTrackedTarget currentTarget){
  return currentTarget.getYaw();

}
public boolean hasTargets(){
  return camera.getLatestResult().hasTargets();
}
  public double xDistance(PhotonTrackedTarget currentTarget){
      Transform3d aprilTagDistances = currentTarget.getBestCameraToTarget();
      double x = MeasurementConverters.MeterstoInches(aprilTagDistances.getX());
      return((x));
  }
  public double yDistance(PhotonTrackedTarget currentTarget){
    Transform3d aprilTagDistances = currentTarget.getBestCameraToTarget();
      double y = MeasurementConverters.MeterstoInches(aprilTagDistances.getY());
      return(y);
  }

  public double zDistance(PhotonTrackedTarget currentTarget){
    Transform3d aprilTagDistances = currentTarget.getBestCameraToTarget();
      double z = MeasurementConverters.MeterstoInches(aprilTagDistances.getZ());
      return(z);
  }
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
    Rotation3d rotation = currentPositions.getRotation();
    double aprilAngleDifference = 180- rotation.getAngle()*57.2958;
    double acceptableRadius = totalDistance/(aprilAngleDifference/divisorAmount)+38;
    if (acceptableRadius > 66){
      acceptableRadius = 66;
    }
    if (totalDistance <= acceptableRadius && aprilAngleDifference < 70){
      return true;
    }

    return false;

  //   if (dis < Constants.SPEAKER_TOLERANCE){
  //     System.out.println(dis);
  //     return true;    }
  //   else{
  //     return false;
  //   }
  // }
  }


    

    



    
  

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
