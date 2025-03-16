package no.hvl.dat110.iotsystem;

import no.hvl.dat110.client.Client;

public class TemperatureDevice {

	private static final int COUNT = 10; // Number of temperature readings to publish

	public static void main(String[] args) {

		// Simulated / virtual temperature sensor
		TemperatureSensor sn = new TemperatureSensor();

		System.out.println("Temperature device starting ...");

		// Create a client for the temperature sensor
		Client sensorClient = new Client("sensor", Common.BROKERHOST, Common.BROKERPORT);

		// Connect to the broker
		if (!sensorClient.connect()) {
			System.err.println("Temperature device: Failed to connect to the broker.");
			return;
		}

		// Publish temperature readings to the "temperature" topic
		for (int i = 0; i < COUNT; i++) {
			int temperature = sn.read(); // Read temperature from the sensor
			System.out.println("READING: " + temperature);

			// Publish the temperature to the "temperature" topic
			sensorClient.publish(Common.TEMPTOPIC, String.valueOf(temperature));

			try {
				Thread.sleep(1000); // Wait for 1 second between readings
			} catch (InterruptedException e) {
				System.err.println("Temperature device: Sleep interrupted. Error: " + e.getMessage());
			}
		}

		// Disconnect from the broker
		sensorClient.disconnect();

		System.out.println("Temperature device stopping ...");
	}
}