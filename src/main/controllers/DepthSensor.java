package com.ociweb.grove;

import static com.ociweb.iot.grove.analogdigital.AnalogDigitalTwig.UltrasonicRanger;
import static com.ociweb.iot.maker.Port.A0;

import com.ociweb.iot.maker.FogRuntime;
import com.ociweb.iot.maker.Hardware;
import com.ociweb.iot.eemaker.FogApp;

public class IoTApp implements FogApp
{
    
	private static final Port BUTTON_PORT = D1;
    
    
    @Override
    public void declareConnections(Hardware c) {
        ////////////////////////////
        //Connection specifications
        ///////////////////////////
        
        // // specify each of the connections on the hardware, eg which component is plugged into which connection.
    	c.connect(Button, BUTTON_PORT);
    	c.connect(UltrasonicRanger, A0);
    	c.connect(,) //lcd rgb grove
    }
    
    
    @Override
    public void declareBehavior(FogRuntime runtime) {
        //////////////////////////////
        //Specify the desired behavior
        //////////////////////////////
    	
        runtime.registerListener(new DepthReporter(runtime));
        runtime.registerListener(new LCDRGB_Behavior(runtime));
    }
}