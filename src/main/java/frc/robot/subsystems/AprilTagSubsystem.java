// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import org.photonvision.PhotonCamera;
import org.photonvision.proto.Photon;
import org.photonvision.targeting.PhotonTrackedTarget;
import edu.wpi.first.math.geometry.Transform3d;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class AprilTagSubsystem extends SubsystemBase {
  /** Creates a new AprilTagSubsystem. */
  PhotonCamera camera;
  public AprilTagSubsystem() {
    camera = new PhotonCamera("test");
  }

  public PhotonTrackedTarget getBestTarget(){
    return(camera.getLatestResult().getBestTarget());
  }
  public int getTargetID(){
    if(getBestTarget() != null){
        return(getBestTarget().getFiducialId());
    } 
    return(-1);
  }

  public Transform3d getMovement(){
    PhotonTrackedTarget currentTarget = getBestTarget();
    if (currentTarget != null){
      Transform3d distanceInfofromTag = currentTarget.getBestCameraToTarget();
      return(distanceInfofromTag);
    }  
    return null;
}
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
