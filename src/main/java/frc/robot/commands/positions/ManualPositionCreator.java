// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.positions;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.ArmSubsystem;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class ManualPositionCreator extends Command {
  /** Creates a new ManualPositionCreator. */
  private ArmSubsystem m_armSubsystem;
  private XboxController m_testController; 
  private JoystickButton shoulderButtonSubtract;
  private JoystickButton forearmButtonSubtract;
  private JoystickButton wristButtonSubtract;
  private JoystickButton shoulderButtonAdd;
  private JoystickButton forearmButtonAdd;
  private JoystickButton wristButtonAdd;
  private double shoulderIncrement;
  private double forearmIncrement;
  private double wristIncrement;
  public ManualPositionCreator(ArmSubsystem armSubsystem, XboxController testController  ) {
    wristButtonAdd = new JoystickButton(m_testController, 11);
    shoulderButtonAdd = new JoystickButton(m_testController, 7);
    forearmButtonAdd = new JoystickButton(m_testController, 9);
    wristButtonSubtract = new JoystickButton(m_testController, 12);
    forearmButtonSubtract = new JoystickButton(m_testController, 10);
    shoulderButtonSubtract = new JoystickButton(m_testController, 8);
    // Use addRequirements() here to declare subsystem dependencies.
    m_armSubsystem = armSubsystem;
    m_testController = testController;
    shoulderIncrement = 0;
    forearmIncrement = 0;
    wristIncrement = 0;
    addRequirements(m_armSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double[] test = {shoulderIncrement, forearmIncrement, wristIncrement};
    if (shoulderButtonAdd.getAsBoolean() == true) {
      shoulderIncrement += 0.5;
    }
    if (shoulderButtonSubtract.getAsBoolean() == true) {
      shoulderIncrement -= 0.5;
    }
    if (forearmButtonAdd.getAsBoolean() == true) {
      shoulderIncrement += 0.5;
    }
    if (forearmButtonSubtract.getAsBoolean() == true) {
      shoulderIncrement -= 0.5;
    }
    if (wristButtonAdd.getAsBoolean() == true) {
      shoulderIncrement += 0.5;
    }
    if (wristButtonSubtract.getAsBoolean() == true) {
      shoulderIncrement -= 0.5;
    }
    //grab stick inputs, apply a deadzone, and use values to increment setPoint; <- that was an accident im getting more used to java
  
    //System.out.println(xStick);
    
    // Apply dead zones to controller.
    // if (Math.abs(xStick) < Constants.DRIVE_CONTROLLER_DEAD_ZONE) {
    //   xStick = 0.0;
    // } 
    // if (Math.abs(yStick) < Constants.DRIVE_CONTROLLER_DEAD_ZONE) {
    //   yStick = 0.0;
    // } 
    // if (Math.abs(zStick) < Constants.DRIVE_CONTROLLER_DEAD_ZONE) {
    //   zStick = 0.0;
    // }
    //get currentPosition for each motor, increment setPoint by axis input, apply to PID controller
    m_armSubsystem.goToSetPoints(test);
    //System.out.println(m_armSubsystem.getCurrentPositions()[0]);
    
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
