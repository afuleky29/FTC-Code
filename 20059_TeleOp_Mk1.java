package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "_20059TeleOpMk1", group = "Linear OpMode")
public class _20059TeleOpMk1 extends LinearOpMode {

  @Override
  public void runOpMode() {
    /*INITIALIZATION*/
    //RUNS ONCE on INT (Before run)
    //for example vArIaBlEs
    int speed = 1; //1:slow 2:fast
    boolean instructions = true; //turn on off instructions
    if (instructions == true) {
      telemetry.addData("CSM stands for 'Current Speed Mode', the gamepad 1 leftY and leftX are for debugging, nevermind them");
    }

    waitForStart();
    if (opModeIsActive()); {
      /*PLAY ONCE*/
      // RUNS ONCE on PLAY (After run)
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
        }
        
        if (rightX != 0) {
          //rotate left right
          FL.setPower(rightX*speed);
          BL.setPower(rightX*speed);
          FR.setPower(righX*speed);
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
        telemetry.addData("-----------------");
        telemetry.addData("CSM", speed);
        telemetry.addData("Gamepad 1 LeftY", leftY);
        telemetry.addData("Gamepad 1 LeftX", leftX);
        telemetry.update();
      }
    }
  }
}