import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

public class Consumidor {

    private static final String NOME_FILA = "filaOlaMundo";

    public static void main(String[] args) throws Exception {
        System.out.println("Consumidor iniciado...");

        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        connectionFactory.setPort(5672);

        Connection conexao = connectionFactory.newConnection();

        Channel canal = conexao.createChannel();
        canal.queueDeclare(NOME_FILA, false, false, false, null);

        DeliverCallback callback = (consumerTag, delivery) -> {
            String mensagem = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Recebido: '" + mensagem + "'");
        };

        canal.basicConsume(NOME_FILA, true, callback, consumerTag -> {});

        System.out.println("Continuarei executando outras atividades enquanto não chega mensagem...");
    }
}
