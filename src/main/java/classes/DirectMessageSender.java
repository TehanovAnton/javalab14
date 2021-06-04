package classes;


import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Hashtable;

public class DirectMessageSender {
    private Context context;
    private ConnectionFactory fac;
    private Connection connection;
    private Session session;
    private Destination dest;
    private MessageProducer producer;

    //Point to point
    public DirectMessageSender() {
        try {
            Hashtable env = new Hashtable();
            env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.fscontext.RefFSContextFactory");
            env.put(Context.PROVIDER_URL, "file:\\C:\\Users\\Anton\\source\\repos\\pacei_NV_sovremenueTehnologiyVInternet\\лабораторные\\Lab14(JMS_MOM)\\imq_admin_objects");

            context = new InitialContext(env);
            fac = (ConnectionFactory) context.lookup("MyCinnectionFactory");
            connection = fac.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            dest = (Destination) context.lookup("MyQueue");
            producer = session.createProducer(dest);

            Channel ch = new Channel("PUBG_LOVERS", "CHICKEN");
            ObjectMessage outObj = session.createObjectMessage(ch);
            System.out.println("Object message: " + ch.getName());
            producer.send(outObj);
            connection.start();
        } catch (NamingException | JMSException e) {
            e.printStackTrace();
        }
    }

    //Pub\sub + filters
    public DirectMessageSender(String topic, String filter) {
        try {
            Hashtable env = new Hashtable();
            env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.fscontext.RefFSContextFactory");
            env.put(Context.PROVIDER_URL, "file:\\C:\\Users\\Anton\\source\\repos\\pacei_NV_sovremenueTehnologiyVInternet\\лабораторные\\Lab14(JMS_MOM)\\imq_admin_objects");

            context = new InitialContext(env);
            fac = (ConnectionFactory) context.lookup("MyCinnectionFactory");
            connection = fac.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            dest = session.createTopic(topic);
            producer = session.createProducer(dest);

            Channel ch = new Channel("PUBG_LOVERS", "CHICKEN");
            TextMessage outObj = session.createTextMessage();
            outObj.setText(ch.getOwner());
            outObj.setStringProperty("symbol", filter);
            System.out.println("Object message: " + ch.getName());
            producer.send(outObj);
            connection.start();
        } catch (NamingException | JMSException e) {
            e.printStackTrace();
        }
    }

    //SessionMode + Priority + DeliveryMode
    public DirectMessageSender(int sessionMode) {
        try {
            Hashtable env = new Hashtable();
            env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.fscontext.RefFSContextFactory");
            env.put(Context.PROVIDER_URL, "file:\\C:\\Users\\Anton\\source\\repos\\pacei_NV_sovremenueTehnologiyVInternet\\лабораторные\\Lab14(JMS_MOM)\\imq_admin_objects");

            context = new InitialContext(env);
            fac = (ConnectionFactory) context.lookup("MyCinnectionFactory");
            connection = fac.createConnection();
            session = connection.createSession(false, sessionMode);
            dest = (Destination) context.lookup("MyQueue");
            producer = session.createProducer(dest);    producer.setDeliveryMode(DeliveryMode.PERSISTENT);      producer.setPriority(2);

            Channel ch = new Channel("PUBG_LOVERS", "CHICKEN");
            ObjectMessage outObj = session.createObjectMessage(ch);
            System.out.println("Object message: " + ch.getName());
            producer.send(outObj);
            connection.start();
        } catch (NamingException | JMSException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
//        new DirectMessageSender();

//        new DirectMessageSender("Topic1", "filter");

        new DirectMessageSender(Session.CLIENT_ACKNOWLEDGE);
//        try {
//            Hashtable env = new Hashtable();
//            env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.fscontext.RefFSContextFactory");
//            env.put(Context.PROVIDER_URL, "file:\\C:\\Users\\Anton\\source\\repos\\pacei_NV_sovremenueTehnologiyVInternet\\лабораторные\\Lab14(JMS_MOM)\\imq_admin_objects");
//
//            Context context = new InitialContext(env);
//            ConnectionFactory myFactory = (ConnectionFactory) context.lookup("MyCinnectionFactory");
//            Connection myConnection = myFactory.createConnection();
//            Session mySession = myConnection.createSession(false, Session.AUTO_ACKNOWLEDGE);
//            Destination myDestination = (Destination) context.lookup("MyQueue");
//            MessageProducer myProducer = mySession.createProducer(myDestination);
//            MessageConsumer myConsumer = mySession.createConsumer(myDestination);
//
//            Channel ch = new Channel("PUBG_LOVERS", "CHICKEN");
//            ObjectMessage outObj = mySession.createObjectMessage(ch);
//            System.out.println("Object message: " + ch.getName());
//            myProducer.send(outObj);
//            myConnection.start();
//
//            Message inObj = myConsumer.receive();
//            if (inObj instanceof ObjectMessage) {
//                ObjectMessage obj = (ObjectMessage) inObj;
//                Channel inCh = (Channel) obj.getObject();
//                System.out.println("Received message: " + inCh.getOwner());
//            }
//        } catch (NamingException | JMSException e) {
//            e.printStackTrace();
//        }

//        try {
//            ConnectionFactory factory = new ConnectionFactory();
//            factory.setProperty(ConnectionConfiguration.imqAddressList, "mq://127.0.0.1:7676,mq://127.0.0.1:7676");
//            JMSContext context =  factory.createContext("admin", "admin");
//            Destination cardsQueue = context.createQueue("BankOderDestination");
//            JMSProducer producer = context.createProducer();
//            producer.send(cardsQueue, "PNV 100 5634234");
//            System.out.println("Placed an information about card transaction to BankCardQueue");
//        } catch (JMSException e) {
//            e.printStackTrace();
//        }
    }
}
