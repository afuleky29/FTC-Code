package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "_20059TeleOpMk1", group = "Linear OpMode")
public class _20059TeleOpMk1 extends LinearOpMode {

  /**
   * This sample contains the bare minimum Blocks for any regular OpMode. The 3 blue
   * Comment Blocks show where to place Initialization code (runs once, after touching the
   * DS INIT button, and before touching the DS Start arrow), Run code (runs once, after
   * touching Start), and Loop code (runs repeatedly while the OpMode is active, namely not
   * Stopped).
   */
  @Override
  public void runOpMode() {
    //initialization code here
    //for example vArIaBlEs
    int speed = 1; //1:slow 2:fast
    bool instructions = true; //turn on off instructions
    waitForStart();
    if (opModeIsActive()); {
      // run code ONCE here
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
        // loop code here
        //joystick readouts
        float leftX = -gamepad1.left_stick_x/2;
        float leftY = -gamepad1.left_stick_y/2;
        float rightX = -gamepad1.right_stick_x/2;
        //speed controls
        if (gamepad1.y) {
          //slow (not very speed)
          speed = 1;
        } elif (gamepad1.x) {
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
        
        if (leftX != 0) {
          //rotate left right
          FL.setPower(leftX*speed);
          BL.setPower(leftX*speed);
          FR.setPower(leftX*speed);
          BR.setPower(leftX*speed);
        }
        
        if (rightX != 0) {
          //strafe left right
          FL.setPower(rightX*speed);
          FR.setPower(rightX*speed);
          BL.setPower(-rightX*speed);
          BR.setPower(-rightX*speed);
        }
        //text on side
        if (instructions == true) {
          //this is way overcomplicated its not really necessary (but exactly 100 lines)
          telemetry.item instructions = telemetry.addData("CSM stands for 'Current Speed Mode', the gamepad 1 leftY and leftX are for debugging, nevermind them");
          telemetry.addItem(instructions);
        } else {
          telemetry.removeItem(instructions);
        }
        telemetry.addData("-----------------");
        telemetry.addData("CSM", speed);
        telemetry.addData("Gamepad 1 LeftY", leftY);
        telemetry.addData("Gamepad 1 LeftX", leftX);
        telemetry.update();
      }
    }
  }
}