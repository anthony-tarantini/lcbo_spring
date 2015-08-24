package com.tarantini.lcbo.alkyhols;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
class AlkyholsController {
    private final AlkyholsService mAlkyholsService;

    @Autowired
    public AlkyholsController(final AlkyholsService alkyholsService) {
        mAlkyholsService = alkyholsService;
    }

    @RequestMapping(value = "/alkyhols", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public AlkyholsResponse getAllAlkyhols(@RequestParam(required = false, defaultValue = "1") final Integer page) {
        return mAlkyholsService.getAllAlkyhols(page);
    }

    @RequestMapping(value = "/alkyhols/{productId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public AlkyholResponse getAlkyholById(@PathVariable final Integer productId) {
        return mAlkyholsService.getAlkyholById(productId);
    }
}
