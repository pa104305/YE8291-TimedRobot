package frc.robot;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class Sensors {
    // Giroscopio
    // Encoders
    // Limelight
    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    NetworkTableEntry tx = table.getEntry("tx");
    NetworkTableEntry ty = table.getEntry("ty");
    NetworkTableEntry ta = table.getEntry("ta");
    NetworkTableEntry tid = table.getEntry("tid");

    public double[] ll_values(){
        double x = tx.getDouble(0.0);
        double y = tx.getDouble(0.0);
        double a = ta.getDouble(0.0);
        double id = tid.getDouble(0.0);
        double[] values = {x, y, a, id};
        return values;
    }
}
