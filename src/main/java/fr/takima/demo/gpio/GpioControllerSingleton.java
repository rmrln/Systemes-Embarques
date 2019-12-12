package fr.takima.demo.gpio;

import com.pi4j.io.gpio.*;

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
    //TODO: quand rasberry co remettre cette ligne de code
    //private final GpioController gpio;

    /**
     * List of the provisioned pins with the address as key.
     */
    private final Map provisionedPins = new HashMap<>();

    /**
     * Constructor which initializes the {@link GpioController}.
     */
    public GpioControllerSingleton() {
        //TODO: quand rasberry co remettre cette ligne de code
        //this.gpio = GpioFactory.getInstance();
    }

    //...

    /**
     * Provision a GPIO as digital output pin.
     *
     * @param address The address of the GPIO pin.
     * @param name The name of the GPIO pin.
     *
     * @return True if successful.
     */
    //TODO: quand rasberry co remettre ces ligne de code
    public boolean provisionDigitalOutputPin(final int address, final String name) {
        /*if (this.provisionedPins.containsKey(address)) {
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
*/
        return false;
    }

    /**
     * Get the pin for the given address.
     *
     * @param address The address of the GPIO pin.
     *
     * @return The {@link Pin} or null when not found.
     */
    private Pin getPinByAddress(int address) {
        Pin pin = RaspiPin.getPinByAddress(address);

        if (pin == null) {
            logger.error("No pin available for address {}", address);
        }

        return pin;
    }

    /**
     * Get the list of provisioned pins.
     *
     * @return Map<Integer, Object>
     */
    public Map<Integer, Object> getProvisionedPins() {
        return this.provisionedPins;
    }

    /**
     * Provision a GPIO as digital output pin.
     *
     * @param address The address of the GPIO pin.
     * @param name The name of the GPIO pin.
     *
     * @return True if successful.
     */
    //TODO: quand rasberry co remettre ces ligne de code
    public boolean provisionDigitalInputPin(final int address, final String name) {
        /*if (this.provisionedPins.containsKey(address)) {
            throw new IllegalArgumentException("There is already a provisioned pin at the given address");
        }

        final GpioPinDigitalInput provisionedPin = this.gpio.provisionDigitalInputPin(this.getPinByAddress(address), name );

        if (provisionedPin != null) {
            this.provisionedPins.put(address, provisionedPin);

            return true;
        }
*/
        return false;
    }

    /**
     * Get the state of the GPIO pin at the given address.
     *
     * @param address The address of the GPIO pin.
     *
     * @return The {@link PinState}.
     */
    public PinState getState(final int address) {
        logger.info("Get pin state requested for address {}", address);

        Object provisionedPin = this.provisionedPins.get(address);

        if (provisionedPin == null) {
            throw new IllegalArgumentException("There is no pin provisioned at the given address");
        } else {
            if (provisionedPin instanceof GpioPinDigitalInput) {
                return ((GpioPinDigitalInput) provisionedPin).getState();
            } else {
                throw new IllegalArgumentException("The provisioned pin at the given address is not of the type GpioPinDigitalInput");
            }
        }
    }

    /**
     * Set the state of a pin.
     *
     * @param address The address of the GPIO pin.
     * @param value The value, possible values 1 (= HIGH) or 0 and all other (= LOW)
     *
     * @return True if successful.
     */
    public boolean setPinDigitalState(final int address, final int value) {
        logger.info("Set pin digital state requested for address {} to value {}", address, value);

        Object provisionedPin = this.provisionedPins.get(address);

        if (provisionedPin == null) {
            throw new IllegalArgumentException("There is no pin provisioned at the given address");
        } else {
            if (provisionedPin instanceof GpioPinDigitalOutput) {
                if (value == 1) {
                    ((GpioPinDigitalOutput) provisionedPin).high();
                } else {
                    ((GpioPinDigitalOutput) provisionedPin).low();
                }

                return true;
            } else {
                throw new IllegalArgumentException("The provisioned pin at the given address is not of the type GpioPinDigitalOutput");
            }
        }
    }

    /**
     * Toggle a pin.
     *
     * @param address The address of the GPIO pin.
     *
     * @return True if successful.
     */
    public boolean togglePin(final int address) {
        logger.info("Toggle pin requested for address {}", address);

        Object provisionedPin = this.provisionedPins.get(address);

        if (provisionedPin == null) {
            throw new IllegalArgumentException("There is no pin provisioned at the given address");
        } else {
            if (provisionedPin instanceof GpioPinDigitalOutput) {
                ((GpioPinDigitalOutput) provisionedPin).toggle();

                return true;
            } else {
                throw new IllegalArgumentException("The provisioned pin at the given address is not of the type GpioPinDigitalOutput");
            }
        }
    }

    /**
     * Pulse a pin for the given duration.
     *
     * @param address The address of the GPIO pin.
     * @param duration The duration in milliseconds.
     *
     * @return True if successful.
     */
    public boolean pulsePin(final int address, final long duration) {
        logger.info("Pulse pin requested for address {} with duration {}", address, duration);

        Object provisionedPin = this.provisionedPins.get(address);

        if (provisionedPin == null) {
            throw new IllegalArgumentException("There is no pin provisioned at the given address");
        } else {
            if (provisionedPin instanceof GpioPinDigitalOutput) {
                ((GpioPinDigitalOutput) provisionedPin).pulse(duration);

                return true;
            } else {
                throw new IllegalArgumentException("The provisioned pin at the given address is not of the type GpioPinDigitalOutput");
            }
        }
    }
}