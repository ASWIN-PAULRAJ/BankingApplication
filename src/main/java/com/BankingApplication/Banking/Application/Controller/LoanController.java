package com.BankingApplication.Banking.Application.Controller;

import com.BankingApplication.Banking.Application.DTO.LoanDTO;
import com.BankingApplication.Banking.Application.Service.LoanService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loan")
public class LoanController {


    @Autowired
    LoanService loanService;

    //loan service injected

    @PostMapping
    public ResponseEntity<LoanDTO> createLoan(@Valid @RequestBody LoanDTO loanDTO){
        LoanDTO createdLoan = loanService.createLoan(loanDTO);
        return ResponseEntity.ok(createdLoan);

    }

    @GetMapping("/{loanId}")
    public ResponseEntity<LoanDTO> getLoanById(@PathVariable Long loanId){

        LoanDTO loanDTO = loanService.getLoanById(loanId);

        return ResponseEntity.ok(loanDTO);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<LoanDTO>> getLoansByUserId(@PathVariable Long userId){
        List<LoanDTO> loanDTOS = loanService.getLoansByUserId(userId);
        return ResponseEntity.ok(loanDTOS);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<LoanDTO>> getLoansByStatus(@PathVariable String status){
        List<LoanDTO> loanDTOS = loanService.getLoansByStatus(status);
        return ResponseEntity.ok(loanDTOS);

    }

    @GetMapping("/all")
    public ResponseEntity<List<LoanDTO>> getAllLoans(){
        List<LoanDTO> loanDTOS = loanService.getAllLoans();
        return ResponseEntity.ok(loanDTOS);
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<LoanDTO>> getLoansByType(@PathVariable String type){
        List<LoanDTO> loanDTOS = loanService.getLoansByType(type);
        return ResponseEntity.ok(loanDTOS);
    }

}
