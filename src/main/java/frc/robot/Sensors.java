package frc.robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class Sensors {
    // Giroscopio
    // Encoders
    
    // Obtener una tabla de red de la limelight
    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    // Obtener las entradas correspondientes de la tabla de la limelight
    NetworkTableEntry tx = table.getEntry("tx");
    NetworkTableEntry ty = table.getEntry("ty");
    NetworkTableEntry ta = table.getEntry("ta");
    NetworkTableEntry tid = table.getEntry("tid");
    // Objeto giroscopio NavX
    AHRS gyro = new AHRS();

    // Metodo para retornar los valores en un array obtenidos por la limelight
    public double[] ll_values(){
        // Obtener los valores de las entradas de la limelight
        double x = tx.getDouble(0.0);
        double y = tx.getDouble(0.0);
        double a = ta.getDouble(0.0);
        double id = tid.getDouble(0.0);
        // Crear el array con los valores obtenidos
        double[] values = {x, y, a, id};
        // Retornar el array
        return values;
    }
}
