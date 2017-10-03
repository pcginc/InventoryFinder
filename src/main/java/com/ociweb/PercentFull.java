package com.ociweb;

import static com.ociweb.iot.grove.analogdigital.AnalogDigitalTwig.UltrasonicRanger;
import static com.ociweb.iot.grove.simple_digital.SimpleDigitalTwig.Button;
import com.ociweb.gl.api.GreenCommandChannel;
import static com.ociweb.iot.maker.FogCommandChannel.*;
import com.ociweb.iot.grove.lcd_rgb.*;
import com.ociweb.iot.maker.AnalogListener;
import com.ociweb.iot.maker.DigitalListener;
import com.ociweb.iot.maker.FogCommandChannel;
import com.ociweb.iot.maker.FogRuntime;
import com.ociweb.iot.maker.Port;
import com.ociweb.pronghorn.util.Appendables;

public class PercentFull implements AnalogListener, DigitalListener {
	
    private final FogCommandChannel channel;
    private boolean setSize;
    private int fullTank;
    private StringBuilder builder = new StringBuilder();
    //full tank needs to be the input at time of calibration or initial input for the range finder
    //grove lcd member
    private final FogCommandChannel ch;

    public PercentFull(FogRuntime runtime) {
        
        channel = runtime.newCommandChannel();
        
   
        @Override
    	public void digitalEvent(Port port, long time, long durationMillis, int value) {
    		setSize = (1 == value);
    	}
        
        @Override
        public void analogEvent(Port port, long time, long durationMillis, int average, int value) {
        // if button = true, store initial value as full tank, else run rest of command
    	if (setSize) {
    		fullTank = value;
    	}
    	// need to store this value and then carry out the calculations
        else if (value>fullTank) {
            System.out.println("Check equipment, tank is deeper than expected");
        } else {
            int remainingDepth = 100*((fullTank-value)/fullTank);
            builder.setLength(0);
            //builder is a instance to avoid garbage collection so we recycle (foglight emphasis)
            Appendables.appendFixedDecimalDigits(builder, remainingDepth, 100);
            // double check this
            builder.append("% Full");
            
            Grove_LCD_RGB.commandForColor(channel, 200, 200, 180);
            Grove_LCD_RGB.commandForText(channel, builder);
            
            
            
        }
        
        
        
    }
    
}