package it.andrea.OldFashionPound.service.valService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import it.andrea.OldFashionPound.model.OldBritishCurrency;
import it.andrea.OldFashionPound.model.request.OperatorRequest;
import it.andrea.OldFashionPound.utility.OperationEnum;

@Service
public class ValidationService implements IValidationService {

    @Value("${operation.config.pattern}")
    private String pattern;

    @Override
    public void inputvalidation(OperatorRequest request, OperationEnum operation) {
        boolean isValid = false; 
        switch (operation) {
            case ADD:
                // first and second value are a price

            case SUB:
                // first and second value are a price
                 isValid = verifyFormat(request.getFirstOp()) || verifyFormat(request.getSecondOp());
                if (!isValid) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The input fields must respect the following format Xp Ys Zd");
                }
                break;
            case MUL:
                // first value is price second is int
            case DIV:
                // first value is price second is int
                 isValid = verifyFormat(request.getFirstOp()) || isInteger(request.getSecondOp());
                if (!isValid) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                            "The first input field must respect the following format Xp Ys Zd, the second fild must is a int");
                }
                break;
            default:
                break;
        }

    }

    //this met. return a list of OldBritishCurrency from controller request
    @Override
    public List<OldBritishCurrency> getOldBritishCurrencyFromRequest(OperatorRequest request, OperationEnum operation) {
        List<OldBritishCurrency> oldBritishCurrencyList = new ArrayList<>(); 
        switch (operation) {
            case ADD:
            case SUB:
            OldBritishCurrency firstBritishCurrency = new OldBritishCurrency(); 
            OldBritishCurrency secondBritishCurrency = new OldBritishCurrency(); 

            firstBritishCurrency.convert(request.getFirstOp());
            secondBritishCurrency.convert(request.getSecondOp());

            oldBritishCurrencyList.add(firstBritishCurrency);
            oldBritishCurrencyList.add(secondBritishCurrency);
                break;
            case MUL:
            case DIV:
            OldBritishCurrency britishCurrency = new OldBritishCurrency(); 

            britishCurrency.convert(request.getFirstOp());

            oldBritishCurrencyList.add(britishCurrency);

                break;
            default:
                break;
        }
                return oldBritishCurrencyList;
    }

    //this met. confront pattern with string 
    private boolean verifyFormat(String op) {
        return op != null && op.matches(pattern);
       
    }

    //this met. verify if a string is a number
    public boolean isInteger(String str) {
        try {
            Integer.parseInt(str); 
            return true; 
        } catch (NumberFormatException e) {
            return false; 
        }
    }
    
}
