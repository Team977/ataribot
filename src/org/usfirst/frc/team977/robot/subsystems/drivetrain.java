package org.usfirst.frc.team977.robot.subsystems;

import org.usfirst.frc.team977.robot.commands.arcadeDriveControl;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 *
 */
public class drivetrain extends Subsystem {

	private SpeedController leftMotors;
	private SpeedController rightMotors;
	public DifferentialDrive drive;
	public Encoder leftEnc;
	public Encoder rightEnc;
	
	
	public drivetrain() {
		super();
		leftMotors = new VictorSP(0);
		rightMotors = new VictorSP(1);
		drive = new DifferentialDrive(leftMotors, rightMotors);
		
		leftEnc = new Encoder(0, 1, false, Encoder.EncodingType.k4X);
	
		leftEnc.setMinRate(.5);
		leftEnc.setDistancePerPulse(Math.PI/720);
		leftEnc.setSamplesToAverage(7);
		
		rightEnc = new Encoder(2, 3, true, Encoder.EncodingType.k4X);
		
		rightEnc.setMinRate(.5);
		rightEnc.setDistancePerPulse(Math.PI/720);
		rightEnc.setSamplesToAverage(7);
		
	}
	
	public void drive(Joystick joy) {
		drive(joy.getRawAxis(1), -joy.getRawAxis(0));
	}
	
	public void drive(double throttle, double turn) {
		drive.arcadeDrive(throttle, turn, true);
	}
	
	public double[] getEnc() {
		double[] enc = {leftEnc.getDistance(), rightEnc.getDistance()};
		return enc;
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new arcadeDriveControl());
    }
}

