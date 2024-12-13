package it.andrea.OldFashionPound.service.valService;

import java.util.List;


import it.andrea.OldFashionPound.model.OldBritishCurrency;
import it.andrea.OldFashionPound.model.request.OperatorRequest;
import it.andrea.OldFashionPound.utility.OperationEnum;

public interface IValidationService {
    public void inputvalidation(OperatorRequest request, OperationEnum operation);

    public List<OldBritishCurrency> getOldBritishCurrencyFromRequest(OperatorRequest request, OperationEnum operation);
}
