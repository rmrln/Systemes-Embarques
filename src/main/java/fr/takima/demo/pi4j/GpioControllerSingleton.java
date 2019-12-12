package fr.takima.demo.pi4j;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class GpioControllerSingleton {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * The GPIO controller.
     */
    //private final GpioController gpio; //TODO: quand rasberry co remettre cette ligne de code

    /**
     * List of the provisioned pins with the address as key.
     */
    private final Map provisionedPins = new HashMap<>();

    /**
     * Constructor which initializes the {@link GpioController}.
     */
    public GpioControllerSingleton() {
        //this.gpio = GpioFactory.getInstance(); //TODO: quand rasberry co remettre cette ligne de code
    }

    //...

    /*public boolean provisionDigitalOutputPin(final int address, final String name) {
        if (this.provisionedPins.containsKey(address)) {
            throw new IllegalArgumentException("There is already a provisioned pin at the given address");
        }

        final GpioPinDigitalOutput provisionedPin = this.gpio.provisionDigitalOutputPin(
                this.getPinByAddress(address), name, PinState.HIGH
        );
        provisionedPin.setShutdownOptions(true, PinState.LOW);

        if (provisionedPin != null) {
            this.provisionedPins.put(address, provisionedPin);

            return true;
        }

        return false;
    }*/ //TODO: quand rasberry co remettre ces ligne de code

    private Pin getPinByAddress(int address) {
        Pin pin = RaspiPin.getPinByAddress(address);

        if (pin == null) {
            logger.error("No pin available for address {}", address);
        }

        return pin;
    }

    //...
}