package frc.robot.swerve;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;

import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.I2C.Port;
import java.lang.Math;
import utilities.MathTools;;

public class DriveTrainSub extends SubsystemBase {
  /** Creates a new DriveTrainSub. */
  private SwerveModule[] swerveModuleSubs = new SwerveModule[Constants.SWERVE_MODULE_COUNT];
  private AHRS navx;
  private TalonFX testMotor1;
  private TalonFX testMotor2;
  private double fieldCentricOffset = 0.0;

  public DriveTrainSub() {
    // Config swerve modules,
    testMotor1 = new TalonFX(7);
    testMotor2 = new TalonFX(8);
    testMotor1.setNeutralMode(NeutralModeValue.Coast);
    testMotor2.setNeutralMode(NeutralModeValue.Coast);
    for (int i = 0; i < Constants.SWERVE_MODULE_COUNT; ++i) {
      swerveModuleSubs[i] = new SwerveModule(Constants.SWERVE_MODULE_CONFIGS[i]);
    }

    // Gyro and field centric.
    navx = new AHRS(Port.kMXP);
    resetGyro();

    zeroFieldCentric();
  }
  public void test(double power){
    testMotor1.set(-power);
    testMotor2.set(power);
  }
  public void resetGyro() {
    navx.reset();
    navx.zeroYaw();
  }

  public double getFieldCentricYaw() {
    return getYaw() - fieldCentricOffset;
  }

  public void zeroFieldCentric() {
    fieldCentricOffset = getYaw();
  }

  public double getYaw() {
    return navx.getYaw();
  }


  // Look in Constants.java for ids.
  public SwerveModule getSwerveModuleFromId(int id) {
    return swerveModuleSubs[id];
  }

  public SwerveModule[] getSwerveModules() {
    return swerveModuleSubs;
  }

  private static double[] normalizeSpeeds(double[] speeds) {
    double[] normalizedSpeeds = speeds.clone();
    double max = normalizedSpeeds[0];

    // Get max.
    for (double v : normalizedSpeeds) {
      max = Math.max(max, v);
    }

    // Doesn't need to be normalized.
    if (max <= 1) {
      return normalizedSpeeds;
    }

    // Normalize.
    for (int i = 0; i < normalizedSpeeds.length; ++i) {
      normalizedSpeeds[i] /= max;
    }

    return normalizedSpeeds;
  }

  public void run() {
    // Run the swerve module run methods.
    for (SwerveModule module : swerveModuleSubs) {
      module.run();
    }

    SmartDashboard.putNumber("Yaw", getYaw());
  }
  public void setToZero(){
    for (SwerveModule module : swerveModuleSubs){
      module.resetTurnEncoder();
    }
  }
  
  // Usefull stuff: https://www.chiefdelphi.com/uploads/default/original/3X/e/f/ef10db45f7d65f6d4da874cd26db294c7ad469bb.pdf
  public void drive(double strafe, double speed, double rotation, boolean fieldCentric, double driveSpeed) {

    double x = strafe;
    double y = speed;
    double z = rotation;

    // Field centric.
    if (fieldCentric) {
      double yaw = Math.toRadians(getFieldCentricYaw());
      double angleCos = Math.cos(yaw);
      double angleSin = Math.sin(yaw);

      double temp = y * angleCos + x * angleSin;
      x = -y * angleSin + x * angleCos;
      y = temp;
    }
  
    double a = x - z * (Constants.VEHICLE_WHEELBASE / Constants.VEHICLE_RADIUS);
    double b = x + z * (Constants.VEHICLE_WHEELBASE / Constants.VEHICLE_RADIUS);
    double c = y - z * (Constants.VEHICLE_TRACKWIDTH / Constants.VEHICLE_RADIUS);
    double d = y + z * (Constants.VEHICLE_TRACKWIDTH / Constants.VEHICLE_RADIUS);

    // Calculate module speeds.
    double[] moduleSpeeds = {
      Math.hypot(b, c),
      Math.hypot(b, d),
      Math.hypot(a, c),
      Math.hypot(a, d)
    };

    // Calculate module angles.
    double[] moduleAngles = {
      Math.atan2(b, c),
      Math.atan2(b, d),
      Math.atan2(a, c),
      Math.atan2(a, d)
    };

    // Normalize speeds.
    moduleSpeeds = normalizeSpeeds(moduleSpeeds);

    // Set speed and angle of each module.
    for (int i = 0; i < moduleSpeeds.length; ++i) {
      // Covert angle unit.
      moduleAngles[i] = MathTools.makeNonNegAngle(Math.toDegrees(moduleAngles[i]));

      swerveModuleSubs[i].setWheelMotor(moduleSpeeds[i] * driveSpeed);
      
      swerveModuleSubs[i].setDesiredAngle(moduleAngles[i]);
    }
  }
  

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}