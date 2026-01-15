package academy.devdojo.controller;

import academy.devdojo.domain.Producer;
import academy.devdojo.mapper.ProducerMapper;
import academy.devdojo.request.ProducerPostRequest;
import academy.devdojo.request.ProducerPutRequest;
import academy.devdojo.response.ProducerGetResponse;
import academy.devdojo.service.ProducerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@RestController
@RequestMapping("v1/producers")
@Slf4j
public class ProducerController {
    private static final ProducerMapper MAPPER = ProducerMapper.INSTANCE;
    private ProducerService service;

    public ProducerController(){
        this.service = new ProducerService();
    }


    @GetMapping()
    public ResponseEntity<List<ProducerGetResponse>> listAll(@RequestParam(required = false) String name) {
        log.debug("Request received to list all producers: '{}'", name);

        var producers = service.findAll(name);
        var producerGetResponses = MAPPER.toProducerGetResponseList(producers);

        return ResponseEntity.ok(producerGetResponses);
    }

    @GetMapping("{id}")
    public ResponseEntity<ProducerGetResponse> findById(@PathVariable Long id) {
        log.debug("Request to find any producer by id: '{}'", id);

        var producer = service.findByIdOrThrowNotFound(id);
        var ProducerGetResponse = MAPPER.toProducerGetResponse(producer);

        return ResponseEntity.ok(ProducerGetResponse);
    }

    @PostMapping
    public ResponseEntity<ProducerGetResponse> save(@RequestBody ProducerPostRequest producerPostRequest) {
        log.debug("Request to save Producer: '{}'", producerPostRequest);
        var producer = MAPPER.toProducer(producerPostRequest);
        var producerSaved = service.save(producer);
        var producerGetResponse = MAPPER.toProducerGetResponse(producerSaved);

        return ResponseEntity.status(HttpStatus.CREATED).body(producerGetResponse);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deteleById(@PathVariable Long id) {
        log.debug("Request to delete Producer by id: '{}'", id);

        service.delete(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping()
    public ResponseEntity<Void> update(@RequestBody ProducerPutRequest producerPutRequest) {
        log.debug("Request to update Producer: '{}'", producerPutRequest);

        Producer producerToUpdate = MAPPER.toProducer(producerPutRequest);

        service.update(producerToUpdate);
        return ResponseEntity.noContent().build();
    }
}
