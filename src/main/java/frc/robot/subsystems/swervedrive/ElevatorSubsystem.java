package frc.robot.subsystems.swervedrive;
 


import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;


public class ElevatorSubsystem extends TimedRobot {
    // Define the motor controller and Xbox controller
    private SparkMax CageMotor;
    private SparkMax leftIntakeMotor;
    private SparkMax rightIntakeMotor;

    private XboxController xboxController;

    @Override
    public void robotInit() {
        // Initialize the motor controller on CAN ID 1
        CageMotor = new SparkMax(15, MotorType.kBrushed);
        
        // Initialize the motor controllers on their respective CAN IDs
        leftIntakeMotor = new SparkMax(13, MotorType.kBrushless);
        rightIntakeMotor = new SparkMax(14, MotorType.kBrushless);
        
        // Initialize the Xbox controller on port 0
        xboxController = new XboxController(0);
    }

    @Override
    public void teleopPeriodic() {

        //Cage pivot controls

        // Get the trigger values
        double leftTrigger = xboxController.getLeftTriggerAxis();
        double rightTrigger = xboxController.getRightTriggerAxis();

        // Set the motor speed based on trigger values
        if (rightTrigger > 0.1) {
            // Move motor forward
            CageMotor.set(rightTrigger * 0.5); // Scale speed down to 50%
        } else if (leftTrigger > 0.1) {
            // Move motor backward
            CageMotor.set(-leftTrigger * 0.5); // Scale speed down to 50%
        } else {
            // Stop motor
            CageMotor.set(0);
        }


        // Intake cage controls



         // Check if the left bumper is pressed
         boolean leftBumperPressed = xboxController.getLeftBumperButtonPressed();
         // Check if the right bumper is pressed
         boolean rightBumperPressed = xboxController.getRightBumperButtonPressed();
 
         // Control the motors based on bumper inputs
         if (leftBumperPressed) {
             // Spin motors forward
             leftIntakeMotor.set(50);  // 50% speed forward
             rightIntakeMotor.set(50); // 50% speed forward
         } else if (rightBumperPressed) {
             // Spin motors backward
             leftIntakeMotor.set(-50);  // 50% speed backward
             rightIntakeMotor.set(-50); // 50% speed backward
         } else {
             // Stop motors
             leftIntakeMotor.set(0);
             rightIntakeMotor.set(0);
         }

    }
}