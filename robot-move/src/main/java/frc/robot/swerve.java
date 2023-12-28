package frc.robot;

import com.ctre.phoenix.sensors.CANCoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class swerve {
    // Controladores de motor
    private CANSparkMax driveEngine;
    private CANSparkMax turnEngine;
    // Encoders integrados
    private RelativeEncoder driveEncoder;
    private RelativeEncoder turnEncoder;
    // Encoder absoluto
    private CANCoder turnAbsoluteEncoder;
    // Metodo para crear los objetos declarados anteriormente
    public void set_parts(int drive, int turn, MotorType type, int absEncoder){
        // Motor y encoder del movimiento del robot 
        driveEngine = new CANSparkMax(drive, type);
        driveEncoder = driveEngine.getEncoder();
        // Motor y encoder del movimiento de las llantas
        turnEngine = new CANSparkMax(turn, type);
        turnEncoder = turnEngine.getEncoder();
        // Encoder absoluto
        turnAbsoluteEncoder = new CANCoder(absEncoder);
        // Conversion de la lectura del encoder (a grados)
        turnAbsoluteEncoder.configFeedbackCoefficient(360.0/4096.0, null, null);
    }
    // Metodo para iniciar el avance de las ruedas
    public void move_wheel(double vel, int deg){
        // Comprueba si el angulo es mayor a 180
        if(deg > 180){
            // Si es mayor a 180 el motor avanza al lado inverso
            driveEngine.set(-vel);
        }else{
            // Si es menor a 180 el motor avanza a su lado normal
            driveEngine.set(vel);
        }
    }
    // Metodo para obtener la velocidad del robot
    public double get_move_velocity(){
        // Retornar la velocidad
        return driveEncoder.getVelocity();
    }
    // Metodo para el giro de la rueda
    public void turn_wheel(double vel, int deg){
        // Comprobar si el angulo indicado es mayor a 180
        if(deg > 180){
            // Si es mayor a 180 se resta los 180 para que de esta forma el modulo solo gire 180 grados
            // y asi se evite el giro inecesario y este este mas optimizado
            deg = deg - 180;
            turn_wheel(vel, deg);
        }
        // Comprobar si la posicion a lograr es mayor o menor, para evitar giros inecesarios
        if(deg > get_turn_absolute_position()){
            // Si es mayor avanzar el motor con voltaje positivo
            turnEngine.set(vel);
        }else if(deg < get_turn_absolute_position()){
            // Si es menor avanzar el motor con voltaje negativo
            turnEngine.set(-vel);
        }
    }
    // Obtener la posicion del motor de giro con el encoder relativo
    public double get_turn_relative_position(){
        // Retorna la posicion del encoder relativo
        return turnEncoder.getPosition();
    }
    // Obtener la posicion del motor de giro con el encoder absoluto
    public double get_turn_absolute_position(){
        // Retornar la posicion del encoder absoluto
        return turnAbsoluteEncoder.getPosition();
    }
}