// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.positions;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
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
    double xStick = m_testController.getRawAxis(Constants.FLIGHT_STICK_X);
    double yStick = m_testController.getRawAxis(Constants.FLIGHT_STICK_Y);
    double zStick = m_testController.getRawAxis(Constants.FLIGHT_STICK_Z);

    SmartDashboard.putNumber("Shoulder Setpoint", xStick);
    SmartDashboard.putNumber("Forearm Setpoint", yStick);
    SmartDashboard.putNumber("Wrist Setpoint", zStick);
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
