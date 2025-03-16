package no.hvl.dat110.messagetransport;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MessagingServer {

	private ServerSocket welcomeSocket;

	public MessagingServer(int port) {
		try {
			this.welcomeSocket = new ServerSocket(port); // Initialize the ServerSocket
			System.out.println("Messaging server started on port " + port);
		} catch (IOException ex) {
			System.err.println("Messaging server: Failed to start. Error: " + ex.getMessage());
			ex.printStackTrace();
			this.welcomeSocket = null; // Ensure welcomeSocket is null if initialization fails
		}
	}

	// Accept an incoming connection from a client
	public Connection accept() {
		Connection connection = null;

		if (welcomeSocket == null) {
			System.err.println("Messaging server: ServerSocket is not initialized.");
			return null;
		}

		try {
			Socket connectionSocket = welcomeSocket.accept(); // Accept incoming connection
			connection = new Connection(connectionSocket); // Create a Connection object
			System.out.println("Messaging server: Accepted connection from " + connectionSocket.getInetAddress());
		} catch (IOException ex) {
			System.err.println("Messaging server: Failed to accept connection. Error: " + ex.getMessage());
			ex.printStackTrace();
			stop(); // Close the ServerSocket if an error occurs
		}

		return connection;
	}

	// Stop the messaging server
	public void stop() {
		if (welcomeSocket != null && !welcomeSocket.isClosed()) {
			try {
				welcomeSocket.close(); // Close the ServerSocket
				System.out.println("Messaging server stopped.");
			} catch (IOException ex) {
				System.err.println("Messaging server: Failed to stop. Error: " + ex.getMessage());
				ex.printStackTrace();
			}
		} else {
			System.err.println("Messaging server: ServerSocket is already closed or null.");
		}
	}
}