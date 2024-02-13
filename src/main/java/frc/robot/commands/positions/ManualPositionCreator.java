// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.positions;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.commands.shootCommand;
import frc.robot.subsystems.ArmSubsystem;
import edu.wpi.first.wpilibj.XboxController;

public class ManualPositionCreator extends Command {
  /** Creates a new ManualPositionCreator. */
  private ArmSubsystem m_armSubsystem;
  private XboxController m_testController;
  public ManualPositionCreator(ArmSubsystem armSubsystem, XboxController testController  ) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_armSubsystem = armSubsystem;
    m_testController = testController;
    addRequirements(m_armSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //grab stick inputs, apply a deadzone, and use values to increment setPoint; <- that was an accident im getting more used to java
    double xStick = m_testController.getRawAxis(Constants.FLIGHT_STICK_X);
    double yStick = m_testController.getRawAxis(Constants.FLIGHT_STICK_Y);
    double zStick = m_testController.getRawAxis(Constants.FLIGHT_STICK_Z);
    
    // Apply dead zones to controller.
    if (Math.abs(xStick) < Constants.DRIVE_CONTROLLER_DEAD_ZONE) {
      xStick = 0.0;
    } 
    if (Math.abs(yStick) < Constants.DRIVE_CONTROLLER_DEAD_ZONE) {
      yStick = 0.0;
    } 
    if (Math.abs(zStick) < Constants.DRIVE_CONTROLLER_DEAD_ZONE) {
      zStick = 0.0;
    }
    //get currentPosition for each motor, increment setPoint by axis input, apply to PID controller
    double[] positionArray = m_armSubsystem.getCurrentPositions();
    positionArray[0] += xStick;
    positionArray[1] += yStick;
    positionArray[2] += zStick;
    m_armSubsystem.goToSetPoints(positionArray);
    
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
