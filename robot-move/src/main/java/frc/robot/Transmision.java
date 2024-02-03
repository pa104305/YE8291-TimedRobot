package frc.robot;

import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Transmision {
  // Cuatro objetos del modulo swerve
  private swerve leftFront = new swerve();
  private swerve leftBack = new swerve();
  private swerve rightFront = new swerve();
  private swerve rightBack = new swerve();
  // Velocidad maxima del giro y avance del robot
  private double turn_vel = 0.5;
  private double drive_vel = 0.6;
  // Angulo deseado del robot
  private int deg = 0;
  // Arreglos para operar sobre los 4 ejes del plano cartesiano
  private int[] lims = {0, 180, -270, 360};
  // Arreglo para los 4 angulos utilizados para el giro del robot sobre su eje
  private int[] angles = {0, 45, 135, 225, 315};
  // Variable para determinar en que cuadrante del plano cartesiano estan las llantas
  private int quadrant;

  // Metodo para establecer las posiciones de los controladores de la transmision swerve
  public void set_modules(MotorType type, int d_lf, int t_lf, int abs_lf, int d_lb, int t_lb, int abs_lb, int d_rf, int t_rf, int abs_rf, int d_rb, int t_rb, int abs_rb){
    // Establece las posiciones de los controladores de la transmicion swerve
    leftFront.set_parts(d_lf, t_lf, type, abs_lf);
    leftBack.set_parts(d_lb, t_lb, type, abs_lb);
    rightFront.set_parts(d_rf, t_rf, type, abs_rf);
    rightBack.set_parts(d_rb, t_rb, type, abs_rb);
  }
  // Cambiar la velocidad de giro
  public void set_turn_vel(double vel){
    turn_vel = vel;
  }
  // Cambiar la velocidad de avance
  public void set_drive_vel(double vel){
    drive_vel = vel;
  }
  // Ejecutar el movimiento de robot hacia la direccion especificada
  public void move(double x, double y, double vel){
    // Condicionar if para establecer en que cuadrante se va a trabajar
    if(x >= 0 && y > 0){
      quadrant = 0;
    }else if(x < 0 && y > 0){
      quadrant = 1;
    }else if(x < 0 && y < 0){
      quadrant = 2;
    }else if(x > 0 && y < 0){
      quadrant = 3;
    }
    // Calculo para determinar la cantidad de grados con los que se va a mover la transmision
    deg = (int) Math.atan(y / x) + lims[quadrant];
    // Girar los modulos swerve al angulo indicado
    leftFront.turn_wheel(turn_vel, deg);
    leftBack.turn_wheel(turn_vel, deg);
    rightFront.turn_wheel(turn_vel, deg);
    rightBack.turn_wheel(turn_vel, deg);
    // Avanzar los modulos swerve, multiplicando el limitante por la velocidad indicada
    leftFront.move_wheel(drive_vel * vel, deg);
    leftBack.move_wheel(drive_vel * vel, deg);
    rightFront.move_wheel(drive_vel * vel, deg);
    rightBack.move_wheel(drive_vel * vel, deg);
  }
  // Metodo para girar el robot sobre su eje
  public void turn(double turn){
    // Girar las llantas a su posicion para girar el robot sobre su eje
    leftFront.turn_wheel(turn_vel, angles[1]);
    leftBack.turn_wheel(turn_vel, angles[2]);
    rightFront.turn_wheel(turn_vel, angles[3]);
    rightBack.turn_wheel(turn_vel, angles[4]);
    // Acelerar el robot para que gire sobre su propio eje
    leftFront.move_wheel(drive_vel * turn, angles[1]);
    leftBack.move_wheel(drive_vel * turn, angles[2]);
    rightFront.move_wheel(drive_vel * turn, angles[3]);
    rightBack.move_wheel(drive_vel * turn, angles[4]);
  }
}