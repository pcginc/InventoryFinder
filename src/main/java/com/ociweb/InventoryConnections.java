package com.ociweb;

import static com.ociweb.iot.grove.analogdigital.AnalogDigitalTwig.UltrasonicRanger;
import static com.ociweb.iot.grove.simple_digital.SimpleDigitalTwig.Button;
import static com.ociweb.iot.maker.Port.A0;
import static com.ociweb.iot.maker.Port.D4;

import com.ociweb.iot.maker.FogApp;
import com.ociweb.iot.maker.FogRuntime;
import com.ociweb.iot.maker.Hardware;
import com.ociweb.iot.maker.Port;

public class InventoryConnections implements FogApp
{
    
	public static final Port BUTTON_PORT = D4;
	public static final Port ULTRASONIC_PORT = A0;
    
    
    @Override
    public void declareConnections(Hardware hardware) {
        ////////////////////////////
        //Connection specifications
        ///////////////////////////
        
        // // specify each of the connections on the hardware, eg which component is plugged into which connection.
    	hardware.connect(Button, BUTTON_PORT);
    	hardware.connect(UltrasonicRanger, A0);
    	hardware.useI2C(); //lcd rgb grove
    }
    
    
    @Override
    public void declareBehavior(FogRuntime runtime) {
        //////////////////////////////
        //Specify the desired behavior
        //////////////////////////////
    	
        runtime.registerListener(new PercentFull(runtime));
    }
}