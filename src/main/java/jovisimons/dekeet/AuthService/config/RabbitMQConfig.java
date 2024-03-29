package jovisimons.dekeet.AuthService.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMQConfig {

    @Bean
    public Declarables createKeetSchema(){
        return new Declarables(
                new DirectExchange("x.de-keet"),
                new Queue("q.userRegister" ),
                new Queue("q.userUpdate" ),
                new Queue("q.event_userDelete" ),
                new Queue("q.event_userUpdate" ),
                new Queue("q.userDelete" ),
                new Binding("q.userRegister", Binding.DestinationType.QUEUE, "x.de-keet", "userRegister", null),
                new Binding("q.userUpdate", Binding.DestinationType.QUEUE, "x.de-keet", "userUpdate", null),
                new Binding("q.event_userUpdate", Binding.DestinationType.QUEUE, "x.de-keet", "userUpdate", null),
                new Binding("q.event_userDelete", Binding.DestinationType.QUEUE, "x.de-keet", "userDelete", null),
                new Binding("q.userDelete", Binding.DestinationType.QUEUE, "x.de-keet", "userDelete", null));
    }
}
