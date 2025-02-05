package br.iwaiter.modulos.pedido;

import br.iwaiter.modulos.pedido.producer.dto.RegisterPedidoDTO;
import br.iwaiter.modulos.pedido.producer.payload.PedidoRegisteredPayload;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@RestController
@RequestMapping("/api/pedido")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    static String QUEUE_NAME = "queue.pedido.iwaiter";
    private final RabbitTemplate rabbitTemplate;

    public PedidoController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(PedidoDTO pedidoDTO) {
        try {
            PedidoEntity pedido = this.pedidoService.save(pedidoDTO);

            RegisterPedidoDTO registerPedidoDTO = new RegisterPedidoDTO(pedido); //instancia classe de registro
            this.register(registerPedidoDTO); //manda registro pra fila
            return new ResponseEntity<>("Pedido criado com sucesso! ID: " + pedido.getId() + " | Total: " + pedido.getTotal() + "\nAguardando pagamento...", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private void register(RegisterPedidoDTO registerPedidoDTO) throws JsonProcessingException {

        Random random = new Random();

        int confirmationCode = random.nextInt(900000) + 100000;
        PedidoRegisteredPayload queuePayload = new PedidoRegisteredPayload(

                registerPedidoDTO.pedido(),
                confirmationCode
        );
        ObjectMapper objectMapper = new ObjectMapper();
        String queuePayloadString = objectMapper.writeValueAsString(queuePayload);
        rabbitTemplate.convertAndSend(QUEUE_NAME, queuePayloadString);
    }
}
