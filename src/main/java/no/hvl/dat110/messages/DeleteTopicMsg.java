package no.hvl.dat110.messages;

public class DeleteTopicMsg extends Message {

    // Message sent from client to delete a topic on the broker
    private String topic;

    public DeleteTopicMsg(String user, String topic) {
        super(MessageType.DELETETOPIC, user); // Call the parent constructor
        this.topic = topic;
    }

    // Correct getter method (no arguments)
    public String getTopic() {
        return this.topic; // Return the field, not a parameter
    }

    // Setter method
    public void setTopic(String topic) {
        this.topic = topic;
    }

    // toString method for logging
    @Override
    public String toString() {
        return "DeleteTopicMsg [type=" + getType() + ", user=" + getUser() + ", topic=" + topic + "]";
    }
}