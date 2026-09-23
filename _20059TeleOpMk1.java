package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "_20059TeleOpMk1", group = "Linear OpMode")
public class _20059TeleOpMk1 extends LinearOpMode {

//telemetry.addLine("A/B for On/Off Instructions");
//telemetry.addLine("X/Y for On/Off ZPB");
  
  @Override
  public void runOpMode() {
    /*INITIALIZATION*/
    //RUNS ONCE on INT (Before run)
    //motor definitions
    int instructions = 1; //turn on off instructions
    int ZPB = 1; //turn on off fast stop (Zero Power Behavior)
    int debug = 0;
    int speed = 1; //1slow 2fast
    DcMotor FL;//namehere//
    DcMotor FR;
    DcMotor BL;
    DcMotor BR;
    DcMotor Intake;
    //map motor to variable
    FL = hardwareMap.get(DcMotor.class, "FL");
    FR = hardwareMap.get(DcMotor.class, "FR");
    BL = hardwareMap.get(DcMotor.class, "BL");
    BR = hardwareMap.get(DcMotor.class, "BR");
    Intake = hardwareMap.get(DcMotor.class, "intake");
    telemetry.addData("Instructions", instructions);
    telemetry.addData("ZPB", ZPB);
    telemetry.addLine("B for On/Off Instructions");
    telemetry.addLine("X for On/Off ZPB");
    telemetry.addLine("Y for On/Off Debug");

    telemetry.update();
    while (opModeInInit()) {
      if (gamepad1.b) {
        if (instructions == 1) {
          instructions = 0;
        } else {
          instructions = 1;
        }
      }
      if (gamepad1.x) {
        if (ZPB == 1) {
          ZPB = 0;
        } else {
          ZPB = 1;
        }
      }
      if (gamepad1.y) {
        if (debug == 1) {
          debug = 0;
        } else {
          debug = 1;
        }
      }
      telemetry.addLine("B for On/Off Instructions");
      telemetry.addLine("X for On/Off ZPB");
      telemetry.addLine("Y for On/Off Debug");
      telemetry.addData("Instructions", instructions);
      telemetry.addData("ZPB", ZPB);
      telemetry.addData("Debug", debug);
      telemetry.update();
    }
    
    waitForStart();
    if (opModeIsActive()); {
      /*PLAY ONCE*/
      // RUNS ONCE on PLAY (After run)
      String zpbMode = "On";
      if (ZPB == 1) {
        zpbMode = "On";
      } else {
        zpbMode = "Off";
      }

      while (opModeIsActive()) {
        /*PLAY FOREVER*/
        //RUNS FOREVER (as long as programs runs)
        //joystick readouts
        float leftX = -gamepad1.left_stick_x/2;
        float leftY = -gamepad1.left_stick_y/2;
        float rightX = -gamepad1.right_stick_x/2;
        float rightY = -gamepad1.right_stick_y/2;
        float rightTrigger = -gamepad1.right_trigger;
        float leftTrigger = -gamepad1.left_trigger;
        //speed controls
        if (gamepad1.y) {
          //slow (not very speed)
          speed = 1;
        } else if (gamepad1.x) {
          //fast (very speed)
          speed = 2;
        }

        if (leftY != 0) {
          //foward backward
          FL.setPower(-leftY*speed);
          FR.setPower(leftY*speed);
          BL.setPower(-leftY*speed);
          BR.setPower(leftY*speed);
        } else {
          //stopping is desireable
          FL.setPower(0);
          FR.setPower(0);
          BL.setPower(0);
          BR.setPower(0);
          if (ZPB == 1) {
            FL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            FR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            BL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            BR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
          }
        }
        
        if (rightX != 0) {
          //rotate left right
          FL.setPower(rightX*speed);
          BL.setPower(rightX*speed);
          FR.setPower(rightX*speed);
          BR.setPower(rightX*speed);
        }
        
        if (leftX != 0) {
          //strafe left right
          FL.setPower(leftX*speed);
          FR.setPower(leftX*speed);
          BL.setPower(-leftX*speed);
          BR.setPower(-leftX*speed);
        }

        if (gamepad1.right_trigger > 0) {
          Intake.setPower(rightTrigger);
        } else {
          Intake.setPower(0);
        }
       
        if (gamepad1.left_trigger > 0) {
          Intake.setPower(-leftTrigger);
        } else {
          Intake.setPower(0);
        }
        
        //TELEMETRY (read the readme)
        //text on side is telemetry
        //telemetry.addData("-----------------");
        if (instructions == 1) {
          telemetry.addLine("CSM stands for 'Current Speed Mode'");
          telemetry.addData("ZPB stands for 'Zero Power Behavior' (Auto Dampeners)", zpbMode);
          telemetry.addLine("----------");
        }
        telemetry.addData("CSM", speed);
        if (debug == 1) {
          telemetry.addLine("----------");
          telemetry.addData("Gamepad 1 LeftX", leftX);
          telemetry.addData("Gamepad 1 LeftY", leftY);
          telemetry.addData("Gamepad 1 RightX", rightX);
          telemetry.addData("Gamepad 1 RightY", rightY);
        }
        telemetry.update();
      }
    }
  }
}