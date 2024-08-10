package com.example.ilservice.controller;

import com.example.ilservice.exception.IlAllReadyExistException;
import com.example.ilservice.exception.IlNotFoundException;
import com.example.ilservice.model.Il;
import com.example.ilservice.service.ILService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/iller")
@AllArgsConstructor
public class ILController {

    private final ILService ilService;

    @GetMapping()
    public ResponseEntity<List<Il>> getIller(@RequestParam(required = false) String name) {

        return new ResponseEntity<>(ilService.getIller(name), OK);
    }



    @GetMapping("/{id}")
    public ResponseEntity<Il> getIl(@PathVariable("id") String ilid) {


            return new ResponseEntity<>(getIlById(ilid), OK);


    }


    @PostMapping
    public ResponseEntity<Il> createIl(@RequestBody Il newIl) {

        return new ResponseEntity<>(ilService.createIl(newIl), CREATED);

    }


    @PutMapping("/{id}")
    public ResponseEntity<Void> changeIl(@PathVariable("id") String id, @RequestBody Il newIl) {



        ilService.updateIl(id, newIl);

        return new ResponseEntity<>(OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIl(@PathVariable String id) {

        ilService.deleteIl(id);
        return new ResponseEntity<>(OK);
    }

    private Il getIlById(String ilid) {

        return ilService.getIlById(ilid);


    }
    @ExceptionHandler(IlNotFoundException.class)
    public ResponseEntity<String> handleIlNotFoundException(IlNotFoundException ex){
        return new ResponseEntity<>(ex.getMessage(),NOT_FOUND);
    }
    @ExceptionHandler(IlAllReadyExistException.class)
    public ResponseEntity<String> handleIlAllReadyExistException(IlAllReadyExistException ex){
        return new ResponseEntity<>(ex.getMessage(),CONFLICT);
    }

}
