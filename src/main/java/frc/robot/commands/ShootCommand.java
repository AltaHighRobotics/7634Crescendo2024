// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.ShootSubsystem;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
public class ShootCommand extends Command {
  /** Creates a new shootCommand. */
  ShootSubsystem m_shootSubsystem;
  JoystickButton shoot;
  long start;

  public ShootCommand(ShootSubsystem shootSubsystem) {
    m_shootSubsystem = shootSubsystem;
    addRequirements(m_shootSubsystem);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    start = System.currentTimeMillis();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(System.currentTimeMillis() - start >= 500){
      m_shootSubsystem.spinIntakeMotor(Constants.INTAKE_SPEED);
    }
    m_shootSubsystem.setFlywheel(0.9);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_shootSubsystem.setFlywheel(0);
    m_shootSubsystem.spinIntakeMotor(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
