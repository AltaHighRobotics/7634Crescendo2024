package frc.robot.swerve;

import utilities.ConfigurablePID;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel;

import frc.robot.Constants;
import utilities.MathTools;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.ctre.phoenix.motorcontrol.NeutralMode;


public class SwerveModule {
  /** Creates a new SwerveModuleSub. */
  private static final double FORWARD = 1.0;
  private static final double BACKWARD = -1.0;
  
  // Wheel.
  private double wheelDirection = FORWARD;
  private WPI_VictorSPX wheelMotor;


  // Turn.
  private ConfigurablePID turnPid;

  private double desiredAngle = 0.0;
  private double turnAngle = 0.0;

  private CANSparkMax turnMotor;
  private RelativeEncoder turnEncoder;

  public SwerveModule(SwerveModuleConfig config) {
    // Wheel motor.
    wheelMotor = new WPI_VictorSPX(config.wheelMotorId);
    // Config wheel motor.
    wheelMotor.setInverted(config.invertWheelMotor);
    wheelMotor.setNeutralMode(NeutralMode.Coast);

    


    // Are soul is nothing magic yet its much greater then any magic.
    // The soul is made up of electric signals and frequencies that come together to create complex thought and feelings.
    // Its crazy to think that small things can sum up to great things without the need of magic.

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
    wheelMotor.set(speed*wheelDirection);  
    
  }

  public void stopWheelMotor() {
    wheelMotor.stopMotor();
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

  public ConfigurablePID getTurnPID() {
    return turnPid;
  }


  public void setDesiredAngle(double desiredAngle) {
    //this.desiredAngle = desiredAngle;
    
    this.desiredAngle = MathTools.getAngleSetPoint(desiredAngle, getTurnEncoderPosition());

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


  public double getAngleError() {
    return turnPid.getError();
  }
  
  public void run() {
    setTurnMotor(turnPid.runPID(desiredAngle, getTurnEncoderPosition()));
    
  }

}