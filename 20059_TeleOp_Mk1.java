package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "_20059TeleOpMk1", group = "Linear OpMode")
public class _20059TeleOpMk1 extends LinearOpMode {

//telemetry.addLine("A/B for On/Off Instructions");
//telemetry.addLine("X/Y for On/Off ZPB");

//for example vArIaBlEs
boolean instructions = true; //aturn on off instructions
boolean ZPB = true; //turn on off fast stop (Zero Power Behavior)
int speed = 1; //1:slow 2:fast

//telemetry.addData("Instructions", instructions)
//telemetry.addData("ZPB", ZPB)
//telemetry.update();
//HERE TO 
//wayyyy overcomplicated options
  @Override
  public void init_loop() {
    if (gamepad1.b) {
      boolean instructions = false;
      telemetry.update();
    }
    if (gamepad1.a) {
      boolean instructions = true;
      telemetry.update();
    }
    if (gamepad1.y) {
      boolean ZPB = false;
      telemetry.update();
    }
    if (gamepad1.x) {
      boolean ZPB = true;
      telemetry.update();
    }
  }
//HERE IS UNESSARCY BUT COOL//

  @Override
  public void runOpMode() {
    /*INITIALIZATION*/
    //RUNS ONCE on INT (Before run)
    //motor definitions
    DcMotor FL;//namehere//
    DcMotor FR;
    DcMotor BL;
    DcMotor BR;
    //map motor to variable
    FL = hardwareMap.get(DcMotor.class, "FL");
    FR = hardwareMap.get(DcMotor.class, "FR");
    BL = hardwareMap.get(DcMotor.class, "BL");
    BR = hardwareMap.get(DcMotor.class, "BR");
    telemetry.clear();
    if (instructions == true) {
      //telemetry.addData("CSM stands for 'Current Speed Mode', the gamepad 1 leftY and leftX are for debugging, nevermind them");
    }
    telemetry.addData("ZPB", ZPB);

    waitForStart();
    if (opModeIsActive()); {
      /*PLAY ONCE*/
      // RUNS ONCE on PLAY (After run)
      
      while (opModeIsActive()) {
        /*PLAY FOREVER*/
        //RUNS FOREVER (as long as programs runs)
        //joystick readouts
        float leftX = -gamepad1.left_stick_x/2;
        float leftY = -gamepad1.left_stick_y/2;
        float rightX = -gamepad1.right_stick_x/2;
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
          if (ZPB == true) {
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
        //TELEMETRY (read the readme)
        //text on side is telemetry
        //telemetry.addData("-----------------");
        telemetry.addData("CSM", speed);
        telemetry.addData("Gamepad 1 LeftY", leftY);
        telemetry.addData("Gamepad 1 LeftX", leftX);
        telemetry.update();
      }
    }
  }
}