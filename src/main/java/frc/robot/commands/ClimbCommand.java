// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ChainSubsystem;
import frc.robot.Constants;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class ClimbCommand extends Command {
  /** Creates a new ClimbCommand. */
  XboxController m_xboxController;
  boolean activated;
  ChainSubsystem m_chainSubsystem;
double chainDown;
double chainUp;
    public ClimbCommand(ChainSubsystem chainSubsystem, XboxController xboxController ) {
    m_chainSubsystem = chainSubsystem;
    addRequirements(m_chainSubsystem);
    m_xboxController = xboxController;


    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
   
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    chainDown = -m_xboxController.getRawAxis(Constants.LEFT_TRIGGER);
    chainUp = -m_xboxController.getRawAxis(Constants.RIGHT_TRIGGER);
    if (m_chainSubsystem.getLimitSwitch() && chainDown < 0){
      m_chainSubsystem.setChainArm(0);
      return;
    }
    m_chainSubsystem.setChainArm(chainUp-chainDown);


    }  
    

   

    

  

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
