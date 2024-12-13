package it.andrea.OldFashionPound.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import it.andrea.OldFashionPound.model.OldBritishCurrency;
import it.andrea.OldFashionPound.model.request.OperatorRequest;
import it.andrea.OldFashionPound.service.opService.IOperationService;
import it.andrea.OldFashionPound.service.valService.IValidationService;
import it.andrea.OldFashionPound.utility.OperationEnum;

@RestController
public class OperationController {

    @Autowired
    private IOperationService iOperationService;

    @Autowired
    private IValidationService validationService;

    @PostMapping("/additionOperation")
    public OldBritishCurrency additionOperation(@RequestBody OperatorRequest request) {

        validationService.inputvalidation(request, OperationEnum.ADD);

        OldBritishCurrency response = iOperationService.additionOperation(validationService.getOldBritishCurrencyFromRequest(request, OperationEnum.ADD));
        return response;
    }
  
    @PostMapping("/subtractionOperation")
    public OldBritishCurrency subtractionOperation(@RequestBody OperatorRequest request) {

        validationService.inputvalidation(request, OperationEnum.SUB);

        OldBritishCurrency response = iOperationService.subtractionOperation(validationService.getOldBritishCurrencyFromRequest(request, OperationEnum.SUB));
        return response;

    }

    @PostMapping("/multiplicationOperation")
    public OldBritishCurrency multiplicationOperation(@RequestBody OperatorRequest request) {

        validationService.inputvalidation(request, OperationEnum.MUL);

        OldBritishCurrency response = iOperationService.multiplicationOperation(validationService.getOldBritishCurrencyFromRequest(request, OperationEnum.MUL), Integer.parseInt(request.getSecondOp()));
        return response;

    }

    @PostMapping("/divisionOperation")
    public OldBritishCurrency divisionOperation(@RequestBody OperatorRequest request) {

        validationService.inputvalidation(request, OperationEnum.DIV);

        OldBritishCurrency response = iOperationService.divisionOperation(validationService.getOldBritishCurrencyFromRequest(request, OperationEnum.DIV), Integer.parseInt(request.getSecondOp()));
        return response;

    }



}
