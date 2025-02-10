import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Produtor {

    private static final String NOME_FILA = "filaOlaMundo";

    public static void main(String[] args) throws Exception {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        connectionFactory.setPort(5672);

        try (
                Connection connection = connectionFactory.newConnection();
                Channel channel = connection.createChannel()
        ) {
            channel.queueDeclare(NOME_FILA, false, false, false, null);

            String mensagem = "Olá mundo!";

            channel.basicPublish("", NOME_FILA, null, mensagem.getBytes("UTF-8"));

            System.out.println(" [x] Enviado: '" + mensagem + "'");
        }
    }
}
