// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

//import frc.robot.Constants;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
//import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.*;
import frc.robot.commands.positions.AmpPosition;
import frc.robot.commands.positions.RetrievalPosition;
import frc.robot.commands.positions.SourcePosition;
import frc.robot.commands.positions.SpeakerPosition;
import frc.robot.commands.positions.StartingPosition;
import frc.robot.commands.positions.TrapPosition;
import frc.robot.commands.positions.defaultPosition;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.swerve.*;
/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final ArmSubsystem m_armSubsystem = new ArmSubsystem();
  private final AmpPosition m_ampPosition = new AmpPosition(m_armSubsystem);
  private final shootCommand m_shootCommand = new shootCommand(m_armSubsystem);
  private final SpeakerPosition m_speakerPosition = new SpeakerPosition(m_armSubsystem);
  private final StartingPosition m_startingPosition = new StartingPosition(m_armSubsystem);
  private final TrapPosition m_trapPosition = new TrapPosition(m_armSubsystem);
  private final SourcePosition m_sourcePosition = new SourcePosition(m_armSubsystem);
  private final RetrievalPosition m_retrievalPosition = new RetrievalPosition(m_armSubsystem);
  private final defaultPosition m_defaultPosition = new defaultPosition(m_armSubsystem);
  // Replace with CommandPS4Controller or CommandJoystick if needed
  private final XboxController m_driveController = new XboxController(Constants.DRIVE_CONTROLLER);
  //subsytems
  private final DriveTrainSub m_driveTrainSub = new DriveTrainSub();
  private final DriveCommand m_driveCommand = new DriveCommand(m_driveTrainSub, m_driveController);
  /** The container for the rot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();
    CommandScheduler.getInstance().setDefaultCommand(m_driveTrainSub, m_driveCommand);
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    final JoystickButton shootButton = new JoystickButton(m_driveController, Constants.TRIGGER_BUTTON);
    final JoystickButton ampButton = new JoystickButton(m_driveController, Constants.AMP_POSITION_BUTTON);
    final JoystickButton speakerButton = new JoystickButton(m_driveController, Constants.SPEAKER_POSITION_BUTTON);
    final JoystickButton sourceButton = new JoystickButton(m_driveController, Constants.SOURCE_POSITION_BUTTON);
    final JoystickButton floorButton = new JoystickButton(m_driveController, Constants.FLOOR_RETRIEVAL_BUTTON);
    final JoystickButton trapButton = new JoystickButton(m_driveController, Constants.TRAP_POSITION_BUTTON);
    final JoystickButton defaultButton = new JoystickButton(m_driveController, Constants.DEFAULT_POSITION_BUTTON);

    shootButton.onTrue(m_shootCommand);
    ampButton.onTrue(m_ampPosition);
    speakerButton.onTrue(m_speakerPosition);
    sourceButton.onTrue(m_sourcePosition);
    floorButton.whileTrue(m_retrievalPosition);
    floorButton.onFalse(m_defaultPosition);
    trapButton.onTrue(m_trapPosition);
    defaultButton.onTrue(m_defaultPosition);
    
    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`
    
    // Schedule `exampleMethodCommand` when the Xbox controller's B button is pressed,
    // cancelling on release.
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return null;
  }
}
