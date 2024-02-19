// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.ArmSubsytem;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj.XboxController;
public class ManualPositionCreator extends Command {
  /** Creates a new ManualPositionCreator. */
  //create controller
  private XboxController m_driveController;
  //create SetPoints
  private double shoulderIncrement = 0;
  private double forearmIncrement = 0;
  private double wristIncrement = 0;
  //create jpystick buttons
  JoystickButton shoulderButtonIncrementer;
  JoystickButton shoulderButtonDecrementer;
  JoystickButton forearmButtonDecrementer;
  JoystickButton forearmButtonIncrementer;
  JoystickButton wristBUttonIncrementer;
  JoystickButton wristButtonDecrementer;
  ArmSubsytem m_armSubsystem;
  public ManualPositionCreator(ArmSubsytem armSubsytem, XboxController driveController) {
    m_armSubsystem = armSubsytem;
    m_driveController = driveController;
    addRequirements(m_armSubsystem);
    shoulderButtonDecrementer = new JoystickButton(m_driveController,7);
    shoulderButtonIncrementer = new JoystickButton(m_driveController, 8);
    forearmButtonDecrementer = new JoystickButton(m_driveController, 9);
    forearmButtonIncrementer = new JoystickButton(m_driveController, 10);
    wristBUttonIncrementer = new JoystickButton(m_driveController, 11);
    wristButtonDecrementer = new JoystickButton(m_driveController, 12);

    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double[] armSetPoints = m_armSubsystem.getCurrentPositions();

    //System.out.println(shoulderButtonDecrementer.getAsBoolean());
    if (shoulderButtonDecrementer.getAsBoolean() == true){
      armSetPoints[0] -= 1;
      //System.out.println(shoulderIncrement);
    }
    if (shoulderButtonIncrementer.getAsBoolean() == true){
      armSetPoints[0] +=1;
      //System.out.println(shoulderIncrement);
    }
    if (forearmButtonDecrementer.getAsBoolean() == true){
      armSetPoints[1] -= 1;
      //System.out.println(shoulderIncrement);
    }
    if (forearmButtonIncrementer.getAsBoolean() == true){
      armSetPoints[1] += 1;
      //System.out.println(shoulderIncrement);
    }
    if (wristBUttonIncrementer.getAsBoolean() == true){
      armSetPoints[2] +=1;
    }
    if (wristButtonDecrementer.getAsBoolean() == true){
      armSetPoints[2] -= 1;
    }

    m_armSubsystem.gotToSetPoints(armSetPoints);
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
