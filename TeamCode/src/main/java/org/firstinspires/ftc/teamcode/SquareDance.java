package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

import static com.qualcomm.robotcore.hardware.DcMotorSimple.Direction.FORWARD;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

@Autonomous(name = "Square", group = "team")

public class SquareDance extends LinearOpMode {
//Define motors
    Servo Flick;
    static DcMotor TopLeft;
    static DcMotor TopRight;
    static DcMotor BackRight;
    static DcMotor BackLeft;

    double Flick_Power;
    public final static double ARM_FLICKED = 0.8;
    public final static double ARM_HOME = 0.2; //sets the starting position for the servo. it will go to this position when robot starts
    public final static double ARM_MIN_RANGE = 0;
    public final static double ARM_MAX_RANGE = 1;
    double FlickPosition = ARM_HOME;


    int currentStep = 0;



    public void runOpMode() {
        //Hardware Mapping and initiation state
        Flick = hardwareMap.servo.get("S");
        Flick.setDirection(Servo.Direction.FORWARD);
        Flick_Power = 0.2;
        Flick.setPosition(ARM_HOME);
        TopRight = hardwareMap.dcMotor.get("Top_Right");
        TopRight.setDirection(FORWARD);

        TopLeft = hardwareMap.dcMotor.get("Top_Left");
        TopLeft.setDirection(FORWARD);

        BackRight = hardwareMap.dcMotor.get("Bottom_Right");
        BackRight.setDirection(FORWARD);

        BackLeft = hardwareMap.dcMotor.get("Bottom_Left");
        BackLeft.setDirection(FORWARD);


        waitForStart();

        while (opModeIsActive()) {


                    if (currentStep == 0) {
                        //1 square forward
                        telemetry.addData("inside step", "");
                        telemetry.update();

                        Omni_DriveTrain.OmniDrive("Forward", 0.125, 2000);

                        currentStep++;
                    }

                    if (currentStep == 1) {
                        //1 square Left
                        telemetry.addData("inside step", "");
                        telemetry.update();

                        Omni_DriveTrain.OmniDrive("Left", 0.25, 2000);

                        currentStep++;
                    }

                    if (currentStep == 2){
                        //1 square Backward
                        telemetry.addData("inside step", "");
                        telemetry.update();

                        currentStep++;
                    }

                    if (currentStep == 3) {
                        //1 square right
                        telemetry.addData("inside step", "");
                        telemetry.update();


                        Omni_DriveTrain.OmniDrive("Right", 0.25, 2000);


                        currentStep++;
                    }

                    if (currentStep == 4) {
                        //1 square forward
                        telemetry.addData("inside step", "");
                        telemetry.update();

                        Omni_DriveTrain.OmniTurn("Left", 0.25, 35);
                        currentStep++;
                    }


                    if (currentStep == 5) {
                        //1 square Flag Up
                        telemetry.addData("inside step", "");
                        telemetry.update();

                        FlickPosition = (ARM_FLICKED);
                        FlickPosition = Range.clip(FlickPosition, ARM_MIN_RANGE, ARM_MAX_RANGE);
                        Flick.setPosition(FlickPosition);

                        currentStep++;
                    }


                    if (currentStep == 6) {
                        //Turn to the Left then Right then return to start position
                        telemetry.addData("inside step", "");
                        telemetry.update();

                        Omni_DriveTrain.OmniTurn("Left", 0.25, 90);
                        Omni_DriveTrain.OmniTurn("Right", 0.25, 180);
                        Omni_DriveTrain.OmniTurn("Left", 0.25, 90);

                        currentStep++;
                    }
                    if (currentStep == 7){
                        telemetry.addData("inside step", "");
                        telemetry.update();
                        FlickPosition = (ARM_HOME);
                        FlickPosition = Range.clip(FlickPosition, ARM_MIN_RANGE, ARM_MAX_RANGE);
                        Flick.setPosition(FlickPosition);
                        sleep (500);

                    }

            }

        }

    }