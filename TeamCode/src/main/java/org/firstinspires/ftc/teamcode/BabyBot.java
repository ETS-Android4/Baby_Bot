package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.internal.network.NetworkStatus;

import static com.qualcomm.robotcore.hardware.DcMotorSimple.Direction.FORWARD;
import static com.qualcomm.robotcore.hardware.DcMotorSimple.Direction.REVERSE;

@TeleOp(name = "BabyBot", group= "team")
public class BabyBot extends LinearOpMode {

    //Define Mechanical objects
    DcMotor Top_Right;
    DcMotor Top_Left;
    DcMotor Bottom_Left;
    DcMotor Bottom_Right;
    //Sets Variable for Motor power


    double drivepower = 1.0;
    double armpower = 1.0;
    double Top_Left_Power = 1.0;
    double Top_Right_Power = 1.0;

    double Bottom_Right_Power = 1.0;
    double Bottom_Left_Power = 1.0;

    double Zero = 1.0;
    double One = 1.0;
    double Two = 1.0;
    double Three = 1.0;

    //double rb = gamepad1.right_bumper ? 1 : 0;
    //double lb = gamepad1.left_bumper ? 1 : 0;

    double PAmax = 100;
    double PAmin = 0;
    double PAmain = 0.00;
    double PAposition = 0;
    double target = 0;
    double setpower = .1;

    double LinearSlidePower = 1.0;

    //double PivotArmPower = 1.0;
    //initializes motors and servos
    @Override
    public void runOpMode() throws InterruptedException {

        Top_Right = hardwareMap.dcMotor.get("Top_Right");
        Top_Right.setDirection(FORWARD);

        Top_Left = hardwareMap.dcMotor.get("Top_Left");
        Top_Left.setDirection(FORWARD);

        Bottom_Right = hardwareMap.dcMotor.get("Bottom_Right");
        Bottom_Right.setDirection(FORWARD);

        Bottom_Left = hardwareMap.dcMotor.get("Bottom_Left");
        Bottom_Left.setDirection(FORWARD);


  /*      Top_Right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Top_Left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Bottom_Right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Bottom_Left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


        Top_Right.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Top_Left.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Bottom_Right.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Bottom_Left.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
*/


        waitForStart();
        while (opModeIsActive()) {

/*
        if (gamepad1.a) {
            drivepower = 1.0;
        }
--------------------------------------------------------------------------------------------------------------------------------------------
        if (gamepad1.b) {
            drivepower = 0.75;
        }

        if (gamepad1.y) {
            drivepower = 0.50;
        }

        if (gamepad2.a) {
            setpower = 1;
        }

        if (gamepad2.b) {
            setpower = 0.5;
        }

    */


            One = -gamepad1.left_stick_y + gamepad1.right_stick_x + gamepad1.right_trigger - gamepad1.left_trigger;
            Two = gamepad1.left_stick_y - gamepad1.right_stick_x + gamepad1.right_trigger - gamepad1.left_trigger;
            Three = -gamepad1.left_stick_y - gamepad1.right_stick_x + gamepad1.right_trigger - gamepad1.left_trigger;
            Zero = gamepad1.left_stick_y + gamepad1.right_stick_x + gamepad1.right_trigger - gamepad1.left_trigger;


            {

                Top_Left_Power = Range.clip(One, -1, 1);
                Top_Right_Power = Range.clip(Zero, -1, 1);
                Bottom_Right_Power = Range.clip(Three, -1, 1);
                Bottom_Left_Power = Range.clip(Two, -1, 1);

            }


    /*
        {

            Top_Right_Power = Range.clip(Math.pow(gamepad1.right_stick_y, 3), -drivepower, drivepower); //1
            Top_Left_Power = Range.clip(Math.pow(gamepad1.left_stick_y, 3), -drivepower, drivepower); //0
            Bottom_Right_Power = Range.clip(Math.pow(gamepad1.right_stick_y, 3), -drivepower, drivepower); //2
            Bottom_Left_Power = Range.clip(Math.pow(gamepad1.left_stick_y, 3), -drivepower, drivepower); //3


        }
    */
//Backwards (Left Up, Right Down both joysticks)
            //Top_Left_Power and Bottom_Left_Power positive


            Top_Right.setPower(Top_Right_Power);
            Top_Left.setPower(Top_Left_Power);
            Bottom_Right.setPower(Bottom_Right_Power);
            Bottom_Left.setPower(Bottom_Left_Power);


            //Lets you see Current Speed on phone
            telemetry.addData("Current Speed", drivepower);

            /*top right bottom left*/


        }


    }
}

