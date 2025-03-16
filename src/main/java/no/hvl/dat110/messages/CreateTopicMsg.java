package no.hvl.dat110.messages;

public class CreateTopicMsg extends Message {

    // Message sent from client to create topic on the broker
    private String topic;

    public CreateTopicMsg(String user, String topic) {
        super(MessageType.CREATETOPIC, user); // Call the parent constructor
        this.topic = topic;
    }

    // Getter for the topic
    public String getTopic() {
        return topic;
    }

    // Setter for the topic
    public void setTopic(String topic) {
        this.topic = topic;
    }

    // toString method for logging
    @Override
    public String toString() {
        return "CreateTopicMsg [type=" + getType() + ", user=" + getUser() + ", topic=" + topic + "]";
    }
}