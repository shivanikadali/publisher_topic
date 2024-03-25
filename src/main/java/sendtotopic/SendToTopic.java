package sendtotopic;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class SendToTopic {

  private static final String url = ActiveMQConnection.DEFAULT_BROKER_URL;

  private static final String topicName = "MESSAGE_TOPIC";

  public static void sendMessage() throws JMSException {

    ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(url);

    try {
      Connection connection = activeMQConnectionFactory.createConnection();
      connection.start();

      Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

      Destination destination = session.createTopic(topicName);

      MessageProducer producer = session.createProducer(destination);

      TextMessage greetingMessage = session.createTextMessage("Hiiii");
      producer.send(greetingMessage, 2, 1, 0);

      System.out.println("message sent successfully");
      session.close();
      connection.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
