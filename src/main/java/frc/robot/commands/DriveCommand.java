// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.AprilTagSubsystem;
import frc.robot.swerve.DriveTrainSub;

import org.photonvision.targeting.PhotonTrackedTarget;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
public class DriveCommand extends Command {
  private DriveTrainSub m_driveTrainSub;
  private XboxController m_driveController;
  private JoystickButton zeroField;
  private boolean doInitGyro = false;
  private AprilTagSubsystem m_aprilTagSubsystem;
  private double strafe;
  private double rotation;
  private double speed;
  private JoystickButton autoRotate;
  public DriveCommand(DriveTrainSub driveTrainSub, XboxController driveController, AprilTagSubsystem aprilTagSubsystem)  {
    m_driveTrainSub = driveTrainSub;
    m_driveController = driveController;
    m_aprilTagSubsystem =aprilTagSubsystem;
    zeroField = new JoystickButton(m_driveController, Constants.ZERO_FIELD);
    autoRotate = new JoystickButton(m_driveController, 4);

    // uwu


    addRequirements(m_driveTrainSub,m_aprilTagSubsystem);
    // Use addRequirements() here to declare subsystem dependencies.
  }
  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }
/* 
  private void test(){
    SmartDashboard.putNumber("Encoder change #1 in degrees: ", m_driveTrainSub.getSwerveModuleFromId(0).getTurnEncoderPosition());
    SmartDashboard.putNumber("Encoder change #2 in degrees: ", m_driveTrainSub.getSwerveModuleFromId(1).getTurnEncoderPosition());
    SmartDashboard.putNumber("Encoder change #3 in degrees: ", m_driveTrainSub.getSwerveModuleFromId(2).getTurnEncoderPosition());
    SmartDashboard.putNumber("Encoder change #4 in degrees: ", m_driveTrainSub.getSwerveModuleFromId(3).getTurnEncoderPosition());

    double setpoint = -(m_driveController.getRawAxis(Constants.FLIGHT_STICK_SLIDER) - 1) * 180;

    SmartDashboard.putNumber("Slider value: ", setpoint);

    SwerveModule module = m_driveTrainSub.getSwerveModuleFromId(Constants.FRONT_RIGHT_MODULE);
    module.setDesiredAngle(setpoint);
    module.run();

  }
 */
  /* */
  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    
    m_driveTrainSub.zeroFieldCentric(zeroField.getAsBoolean());
    //test();

   // The gyro wants to be reset during runtime but I only want to do it once.
    // gyro determines field centric during auto, should transfer to teleop
    if (doInitGyro) {
      doInitGyro = false;
      m_driveTrainSub.resetGyro();
      m_driveTrainSub.zeroFieldCentric(true);
    }

    // Get joystick values.
    // double flightStickX = m_driveController.getRawAxis(Constants.LEFT_STICK_X);
    // double flightStickY = m_driveController.getRawAxis(Constants.LEFT_STICK_Y);
    // double flightStickZ = m_driveController.getRawAxis(Constants.RIGHT_STICK_X);
    double flightStickX = m_driveController.getRawAxis(Constants.FLIGHT_STICK_X);
    double flightStickY = m_driveController.getRawAxis(Constants.FLIGHT_STICK_Y);
    double flightStickZ = m_driveController.getRawAxis(Constants.FLIGHT_STICK_Z);
    //m_driveTrainSub.test(flightStickX);

    // Apply dead zones to controller.
    if (Math.abs(flightStickX) < Constants.DRIVE_CONTROLLER_DEAD_ZONE) {
      flightStickX = 0.0;
    } if (Math.abs(flightStickY) < Constants.DRIVE_CONTROLLER_DEAD_ZONE) {
      flightStickY = 0.0;
    } if (Math.abs(flightStickZ) < Constants.DRIVE_CONTROLLER_DEAD_ZONE*5) {
      flightStickZ = 0.0;
    }

    //you smel;l

    // System.out.println(flightStickX);
    // System.out.println(flightStickY);
    // System.out.println(flightStickZ);
    

     strafe = flightStickX;
     speed = flightStickY;
     rotation = flightStickZ;
    if(m_aprilTagSubsystem.hasTargets() && autoRotate.getAsBoolean() == true){
      PhotonTrackedTarget currentTarget = m_aprilTagSubsystem.getBestTarget();
      if(currentTarget == null){
        rotation = flightStickZ;
      }
      else{
      double yaw = m_aprilTagSubsystem.relativeYaw(currentTarget);
      rotation = m_aprilTagSubsystem.rotationPID(yaw);
      System.out.println(rotation);}
    }



    m_driveTrainSub.drive(
      Math.pow(strafe, 2.0) * Math.signum(strafe), //signum returns -1 if neg, 0 if 0, 1 if pos.
      -Math.pow(speed, 2.0) * Math.signum(speed), // basically, he smooths out speed, gtes the direction (signum)
      Math.pow(rotation, 2.0) * Math.signum(rotation) * Constants.DRIVE_TURN_SPEED,
      true,
      Constants.DRIVE_SPEED
    );
    
    m_driveTrainSub.run();
    
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
