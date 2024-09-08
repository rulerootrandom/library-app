package com.rootjm.libarayapp.controller.calculator;

import com.rootjm.libarayapp.dto.calculator.request.CalculateMultiplyRequest;
import com.rootjm.libarayapp.dto.calculator.request.CalculatorAddRequest;
import org.springframework.web.bind.annotation.*;

@RestController
public class CalculatorController {

    @GetMapping("/add")
    public int addTowNumbers(CalculatorAddRequest request) {

        return request.getNumber1() + request.getNumber2();
    }

    @PostMapping("/multiply")
    public int multiplyTwoNumbers(@RequestBody CalculateMultiplyRequest request) {
        return request.getNumber1() * request.getNumber2();
    }

}
