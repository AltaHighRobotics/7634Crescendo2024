// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ArmSubsytem;

public class shootCommand extends Command {
  ArmSubsytem m_armSubsytem;
  /** Creates a new shootCommand. */
  public static int currentArmPosition;
  public shootCommand(ArmSubsytem armSubsytem) {
    m_armSubsytem = armSubsytem;
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    switch(currentArmPosition){
      default: //when in default position
        break;
      case 0: // amp position
        //insert code to shoot at amp
      case 1: // source position
        //insert code to PICKUP at speaker
      case 2: // speaker position
        //insert code to shoot at speaker
      case 3: // trap position
        //insert code to shoot at trap


    }
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
