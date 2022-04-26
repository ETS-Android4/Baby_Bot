package org.firstinspires.ftc.teamcode;

import static com.qualcomm.robotcore.hardware.DcMotorSimple.Direction.FORWARD;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import com.qualcomm.robotcore.util.Range;

public class Omni_DriveTrain {
//define motors
    static DcMotor TopLeft;
    static DcMotor TopRight;
    static DcMotor BackRight;
    static DcMotor BackLeft;


    public static void OmniDrive(String Dir, double Spd, int Dist) {
        TopRight = hardwareMap.dcMotor.get("Top_Right");
        TopRight.setDirection(FORWARD);

        TopLeft = hardwareMap.dcMotor.get("Top_Left");
        TopLeft.setDirection(FORWARD);

        BackRight = hardwareMap.dcMotor.get("Bottom_Right");
        BackRight.setDirection(FORWARD);

        BackLeft = hardwareMap.dcMotor.get("Bottom_Left");
        BackLeft.setDirection(FORWARD);
        telemetry.addData("Direction",Dir);
        telemetry.update();

        TopLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        TopRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BackLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BackRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        switch(Dir)

        {
            case "Forward":
                TopLeft.setDirection(DcMotorSimple.Direction.FORWARD);
                TopRight.setDirection(DcMotorSimple.Direction.REVERSE);
                BackRight.setDirection(DcMotorSimple.Direction.REVERSE);
                BackLeft.setDirection(DcMotorSimple.Direction.FORWARD);
                break;
            case "Backward":
                TopLeft.setDirection(DcMotorSimple.Direction.REVERSE);
                TopRight.setDirection(DcMotorSimple.Direction.FORWARD);
                BackRight.setDirection(DcMotorSimple.Direction.FORWARD);
                BackLeft.setDirection(DcMotorSimple.Direction.REVERSE);
                break;
            case "Left":
                TopLeft.setDirection(DcMotorSimple.Direction.REVERSE);
                TopRight.setDirection(DcMotorSimple.Direction.REVERSE);
                BackRight.setDirection(DcMotorSimple.Direction.FORWARD);
                BackLeft.setDirection(DcMotorSimple.Direction.FORWARD);
                break;
            case "Right":
                TopLeft.setDirection(DcMotorSimple.Direction.FORWARD);
                TopRight.setDirection(DcMotorSimple.Direction.FORWARD);
                BackRight.setDirection(DcMotorSimple.Direction.REVERSE);
                BackLeft.setDirection(DcMotorSimple.Direction.REVERSE);
                break;
            default:
                telemetry.addData("Invalid Direction", Dir);
                telemetry.update();
                return;
        }

        Dist=Math.abs(Dist);
        TopLeft.setTargetPosition(Dist);
        TopRight.setTargetPosition(Dist);
        BackLeft.setTargetPosition(Dist);
        BackRight.setTargetPosition(Dist);

        TopLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        TopRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        BackLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        BackRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        Spd=Range.clip(Spd,0,1);
        TopLeft.setPower(Spd);
        TopRight.setPower(Spd);
        BackLeft.setPower(Spd);
        BackRight.setPower(Spd);



        while(TopLeft.isBusy())   //leftMotor.getCurrentPosition() < leftMotor.getTargetPosition())

        {
            telemetry.addData("encoder-fwd-left", TopLeft.getCurrentPosition() + "  busy=" + TopLeft.isBusy());
            telemetry.addData("encoder-fwd-right", TopRight.getCurrentPosition() + "  busy=" + TopRight.isBusy());
            telemetry.update();
           // idle();
        }
        //stop
        TopLeft.setPower(0);
        TopRight.setPower(0);
        BackLeft.setPower(0);
        BackRight.setPower(0);

    }


    public static void OmniTurn(String DirT, double SpdT, int Deg) {
        //These values are dependent on the size of your robot
        double RobotDiameter = 20; //Max robot size is 18x18 with max diagonal width of 25.46 in)
        //Robot spins in a circle, rough diameter of robot's circle can be no more than 25.42 (diagonal)
        double RobotCircumference = RobotDiameter * 3.14;//Max circumference of Robot (d * pi) = 80 in
        double WheelSize = 4;  //diameter in inches of wheels (the engineers like 4in)
        double WheelCircumference = WheelSize*3.14; //Circumference (d * pi) of wheel (distance wheel travels for 1 rotation)
        double RotationsPerCircle = RobotCircumference/WheelCircumference;// wheel rotations to turns in complete circle

        int DriveTicks = 480;  //1 wheel rotation = DriveTicks - based on motor and gear ratio  => 1 Tetrix DC motor 60:1 revolution = 1440 encoder ticks (20:1 = 480 ticks (divide by 60/20) or 400 ticks = 1 foot)
        //DriveTicks * RotationsPerCircle = 360 degrees
        //Rotations per degree
        int TicksPerDegree = (int) Math.round((DriveTicks * RotationsPerCircle)/360);

        //number of ticks for a turn of 1 degree TicksPerDegree
        //if I want to turn more than 1 degree; multiple by number of degrees I want to turn
        //had to convert Rotate to int to use in setTargetPosition below
        int Rotate = (int) Math.round(Deg * TicksPerDegree);

        TopLeft = hardwareMap.dcMotor.get("TL");
        TopRight = hardwareMap.dcMotor.get("TR");
        BackRight = hardwareMap.dcMotor.get("BR");
        BackLeft = hardwareMap.dcMotor.get("BL");

        telemetry.addData("Rotating", Rotate + " ticks or " + Deg + " degrees");
        telemetry.update();

        TopLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        TopRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BackLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BackRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        if (DirT.equals("Left")) {
            TopLeft.setDirection(DcMotorSimple.Direction.REVERSE);
            TopRight.setDirection(DcMotorSimple.Direction.REVERSE);
            BackRight.setDirection(DcMotorSimple.Direction.REVERSE);
            BackLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        }
        if (DirT.equals("Right")) {
            TopLeft.setDirection(DcMotorSimple.Direction.FORWARD);
            TopRight.setDirection(DcMotorSimple.Direction.FORWARD);
            BackRight.setDirection(DcMotorSimple.Direction.FORWARD);
            BackLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        }

        Rotate = Math.abs(Rotate);
        TopLeft.setTargetPosition(Rotate);
        TopRight.setTargetPosition(Rotate);
        BackLeft.setTargetPosition(Rotate);
        BackRight.setTargetPosition(Rotate);

        TopLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        TopRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        BackLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        BackRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        SpdT = Range.clip(SpdT, 0, 1);
        TopLeft.setPower(SpdT);
        TopRight.setPower(SpdT);
        BackLeft.setPower(SpdT);
        BackRight.setPower(SpdT);


        while (TopLeft.isBusy())   //leftMotor.getCurrentPosition() < leftMotor.getTargetPosition())
        {
            telemetry.addData("encoder-fwd-left", TopLeft.getCurrentPosition() + "  busy=" + TopLeft.isBusy());
            telemetry.addData("encoder-fwd-right", TopRight.getCurrentPosition() + "  busy=" + TopRight.isBusy());
            telemetry.update();
          //  idle();
        }
        //stop
        TopLeft.setPower(0);
        TopRight.setPower(0);
        BackLeft.setPower(0);
        BackRight.setPower(0);

    }
}
//**********************************************************************************************