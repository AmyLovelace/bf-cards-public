package com.ms.bf.card.adapter.controller.card;
import com.ms.bf.card.application.port.in.CreateIn;
import lombok.extern.slf4j.Slf4j;

import com.ms.bf.card.adapter.controller.model.RestResponse;
import com.ms.bf.card.adapter.controller.model.card.CardRest;
import com.ms.bf.card.adapter.controller.processor.Processor;
import com.ms.bf.card.adapter.controller.processor.RequestProcessor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.concurrent.ExecutionException;

@Slf4j
@RestController
@RequestMapping("/api/v1/card/")
public class CardControllerAdapter {

    private static final String CREATE_CARD = "/create";
    private final CreateIn createCardIn;
    private final Processor processor;


    public CardControllerAdapter( CreateIn createCardIn1) {
        this.createCardIn = createCardIn1;
        this.processor = new RequestProcessor();
    }
    /*@GetMapping(GET_CARD)
    public RestResponse<CardRest> getCard(final HttpServletRequest httpServletRequest, @Valid @PathVariable("accountNumber") int accountNumber)throws ExecutionException, InterruptedException {
        log.info("Llamada al servicio account/{}", accountNumber);
        Card card = this.getCard.getCard(accountNumber);
        CardRest response = CardRest.toCardRest(card);
        log.info("Respuesta del servicio /{}: [{}]", accountNumber, response);
        return processor.processRequest(Processor.Enriched.of(httpServletRequest),
                res -> RestResponse.<CardRest>builder()
                        .data(response)
                        .id(res.getId())
                        .status(HttpStatus.OK.value())
                        .resource(httpServletRequest.getRequestURI())
                        .metadata(processor.buildMetadata(res.getReq()))
                        .build()
        );
    }*/
    @CrossOrigin
    @PostMapping(CREATE_CARD)
    public RestResponse<CardRest> createCard(final HttpServletRequest httpServletRequest, @Valid @RequestBody CardRest request )throws ExecutionException, InterruptedException{
        var response =  createCardIn.create(request.toCardDomain());
        return processor.processRequest(Processor.Enriched.of(httpServletRequest),
                res -> RestResponse.<CardRest>builder()
                        .data(CardRest.toCardRest(response))
                        .id(res.getId())
                       .status(HttpStatus.OK.value())
                       .resource(httpServletRequest.getRequestURI())
                       .metadata(processor.buildMetadata(res.getReq()))
                       .build()
        );

    }



}
