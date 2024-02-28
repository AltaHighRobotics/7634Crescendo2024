// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

//import frc.robot.Constants;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
//import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.*;
import frc.robot.commands.positions.*;
import frc.robot.subsystems.ArmSubsytem;
import frc.robot.subsystems.ChainSubsystem;
import frc.robot.swerve.*;
/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  //private final ArmSubsystem m_armSubsystem = new ArmSubsystem();
  /* 
  
  // Replace with CommandPS4Controller or CommandJoystick if needed
*/private final ArmSubsytem m_armSubsystem = new ArmSubsytem();
private ChainSubsystem m_chainSubsystem = new ChainSubsystem();
  private final XboxController m_driveController = new XboxController(Constants.DRIVE_CONTROLLER);
  private final ManualPositionCreator m_ManualPositionCreator = new ManualPositionCreator(m_armSubsystem, m_driveController);
  private final DriveTrainSub m_driveTrainSub = new DriveTrainSub();
  private final DriveCommand m_driveCommand = new DriveCommand(m_driveTrainSub, m_driveController);
  // Every Button Command
  private final DefaultPosition m_defaultPosition = new DefaultPosition(m_armSubsystem);
  private final SpeakerPosition m_speakerPosition = new SpeakerPosition(m_armSubsystem);
  private final AmpPosition m_ampPosition = new AmpPosition(m_armSubsystem);
  private final SourcePosition m_sourcePosition = new SourcePosition(m_armSubsystem);
  private final TrapPosition m_trapPosition = new TrapPosition(m_armSubsystem);
  private final ShootCommand m_shootCommand = new ShootCommand(m_armSubsystem);
  private final ClimbCommand m_climbCommand = new ClimbCommand(m_chainSubsystem, m_driveController);

  //subsytems
  /** The container for the rot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();
    CommandScheduler.getInstance().setDefaultCommand(m_driveTrainSub, m_driveCommand);
    //CommandScheduler.getInstance().setDefaultCommand(m_armSubsystem, m_ManualPositionCreator);
    CommandScheduler.getInstance().setDefaultCommand(m_chainSubsystem, m_climbCommand);

  
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
    final JoystickButton trapButton = new JoystickButton(m_driveController, Constants.TRAP_POSITION_BUTTON);
    final JoystickButton defaultButton = new JoystickButton(m_driveController, Constants.DEFAULT_POSITION_BUTTON);
  
    defaultButton.onTrue(m_defaultPosition);
    speakerButton. whileTrue(m_speakerPosition);
    ampButton.onTrue(m_ampPosition);
    sourceButton.onTrue(m_sourcePosition);
    trapButton.onTrue(m_trapPosition);
    shootButton.whileTrue(m_shootCommand);



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
