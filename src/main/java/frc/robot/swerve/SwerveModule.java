package frc.robot.swerve;

import utilities.ConfigurablePID;
import com.ctre.phoenix6.signals.NeutralModeValue;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel;

import frc.robot.Constants;
import utilities.MathTools;

public class SwerveModule {
  /** Creates a new SwerveModuleSub. */
  private static final double FORWARD = 1.0;
  private static final double BACKWARD = -1.0;

  // Wheel.
  private double wheelDirection = FORWARD;

  // Turn.
  private ConfigurablePID turnPid;

  private double desiredAngle = 0.0;
  private double turnAngle = 0.0;

  private CANSparkMax turnMotor;
  private RelativeEncoder turnEncoder;

  public SwerveModule(SwerveModuleConfig config) {
    // Wheel motor.

    // Config wheel motor.
    resetWheelEncoder();

    // Turn motor.
    turnMotor = new CANSparkMax(config.turnMotorId, CANSparkLowLevel.MotorType.kBrushless);
    turnMotor.setIdleMode(IdleMode.kCoast);
    turnMotor.setInverted(config.invertTurnMotor);

    // // Turn encoder.
    turnEncoder = turnMotor.getEncoder();
    turnEncoder.setPositionConversionFactor(Constants.SWERVE_MODULE_TURN_ENCODER_DISTANCE_PER_PULSE);
    resetTurnEncoder();

    // Turn pid.
    turnPid = new ConfigurablePID(Constants.SWERVE_MODULE_TURN_PID);
  }

  // TODO: Full the wheel motor wrappers with stuff UwU
  public void setWheelMotor(double speed) {
  }

  public void stopWheelMotor() {
  }

  public void setTurnMotor(double speed) {
    turnMotor.set(speed);
  }

  public void stopTurnMotor() {
    turnMotor.stopMotor();
  }

  public void stop() {
    stopWheelMotor();
    stopTurnMotor();
  }

  public void resetTurnEncoder() {
    turnEncoder.setPosition(0.0);
  }

  public double getAngle() {
    return MathTools.wrapAngle(getTurnEncoderPosition());
  }

  public double getTurnEncoderPosition() {
    return turnEncoder.getPosition();
  }

  public void setTurnEncoderPosition(double position) {
    turnEncoder.setPosition(position);
  }
  //I Love potato
  // tostinos tostinos hot pizza rolls
  //tostinos tostinos everybody's talking bout tostinos tostinos hot pizza rolls

  //ni how ma
  //hoa
  //robot worky pls
  //rock paper sciccor shoot

  // TODO: Start using fancy stuff.
  public void setDesiredAngle(double desiredAngle) {
    this.desiredAngle = desiredAngle;

    // this.turnAngle = desiredAngle;
    // double turnDis = MathTools.angleDis(MathTools.wrapAngle(desiredAngle), getAngle());

    // if (Math.abs(turnDis) > 90.0) {
    //   this.desiredAngle = MathTools.getAngleSetPoint(
    //     MathTools.wrapAngle(desiredAngle - turnDis), 
    //     getTurnEncoderPosition()
    //   );

    //   wheelDirection = BACKWARD;
    // } else {
    //   this.desiredAngle = MathTools.getAngleSetPoint(desiredAngle, getTurnEncoderPosition());
    //   wheelDirection = FORWARD;
    // }
  }

  public double getDesiredAngle() {
    return this.desiredAngle;
  }

  public double getTurnAngle() {
    return this.turnAngle;
  }

  public double getWheelEncoder() {
    return 0.0;
  }

  public void resetWheelEncoder() {
  }

  public double getSpeed() {
    return 0.0;
  }

  public double getAngleError() {
    return turnPid.getError();
  }
  
  public void run() {
    setTurnMotor(turnPid.runPID(desiredAngle, getTurnEncoderPosition()));
  }
}