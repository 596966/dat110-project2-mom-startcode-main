package no.hvl.dat110.iotsystem;

import no.hvl.dat110.client.Client;
import no.hvl.dat110.messages.Message;
import no.hvl.dat110.messages.PublishMsg;
import no.hvl.dat110.common.TODO;

public class DisplayDevice {

	private static final int COUNT = 10;

	public static void main (String[] args) {

		System.out.println("Display starting ...");

		// TODO - START

		// create a client object and use it to
		Client displayClient = new Client("display",Common.BROKERHOST,Common.BROKERPORT);

		// - connect to the broker - use "display" as the username
		displayClient.connect();

		// - create the temperature topic on the broker
		displayClient.createTopic(Common.TEMPTOPIC);

		// - subscribe to the topic
		displayClient.subscribe(Common.TEMPTOPIC);

		// - receive messages on the topic
		for (int i = 0; i < COUNT; i++) {
			Message msg = displayClient.receive();
			if (msg instanceof PublishMsg) {
				PublishMsg pubMsg = (PublishMsg) msg;
				System.out.println("DISPLAY: " + pubMsg.getMessage());
			}
		}

		// - unsubscribe from the topic
		displayClient.unsubscribe(Common.TEMPTOPIC);

		// - disconnect from the broker
		displayClient.disconnect();

		// TODO - END

		System.out.println("Display stopping ... ");


	}
}
