package academy.devdojo.controller;

import academy.devdojo.domain.Producer;
import academy.devdojo.mapper.ProducerMapper;
import academy.devdojo.request.ProducerPostRequest;
import academy.devdojo.response.ProducerGetResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("v1/producers")
@Slf4j
public class ProducerController {
    private static final ProducerMapper MAPPER = ProducerMapper.INSTANCE;


    @GetMapping()
    public List<Producer> listAll(@RequestParam(required = false) String name) {
        List<Producer> producers = Producer.listAllProducers();
        if (name == null) return producers;

        return Producer.listAllProducers()
                .stream()
                .filter(prod -> prod.getName().equalsIgnoreCase(name))
                .toList();
    }

    @GetMapping("{id}")
    public Producer findById(@PathVariable Long id) {

        return Producer.listAllProducers()
                .stream()
                .filter(prod -> prod.getId().equals(id))
                .findFirst().orElse(null);
    }

    @PostMapping
    public ResponseEntity<ProducerGetResponse> save(@RequestBody ProducerPostRequest producerPostRequest) {
        var producer = MAPPER.toProducer(producerPostRequest);
        var response = MAPPER.toProducerGetResponse(producer);

        Producer.listAllProducers().add(producer);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }
}
