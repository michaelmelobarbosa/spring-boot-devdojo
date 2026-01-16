package academy.devdojo.controller;

import academy.devdojo.domain.Producer;
import academy.devdojo.mapper.ProducerMapper;
import academy.devdojo.request.ProducerPostRequest;
import academy.devdojo.request.ProducerPutRequest;
import academy.devdojo.response.ProducerGetResponse;
import academy.devdojo.service.ProducerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("v1/producers")
@Slf4j
@RequiredArgsConstructor
public class ProducerController {
    private final ProducerMapper mapper;
    private final ProducerService service;

    @GetMapping()
    public ResponseEntity<List<ProducerGetResponse>> listAll(@RequestParam(required = false) String name) {
        log.debug("Request received to list all producers: '{}'", name);

        var producers = service.findAll(name);
        var producerGetResponses = mapper.toProducerGetResponseList(producers);

        return ResponseEntity.ok(producerGetResponses);
    }

    @GetMapping("{id}")
    public ResponseEntity<ProducerGetResponse> findById(@PathVariable Long id) {
        log.debug("Request to find any producer by id: '{}'", id);

        var producer = service.findByIdOrThrowNotFound(id);
        var ProducerGetResponse = mapper.toProducerGetResponse(producer);

        return ResponseEntity.ok(ProducerGetResponse);
    }

    @PostMapping
    public ResponseEntity<ProducerGetResponse> save(@RequestBody ProducerPostRequest producerPostRequest) {
        log.debug("Request to save Producer: '{}'", producerPostRequest);
        var producer = mapper.toProducer(producerPostRequest);
        var producerSaved = service.save(producer);
        var producerGetResponse = mapper.toProducerGetResponse(producerSaved);

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

        Producer producerToUpdate = mapper.toProducer(producerPutRequest);

        service.update(producerToUpdate);
        return ResponseEntity.noContent().build();
    }
}
