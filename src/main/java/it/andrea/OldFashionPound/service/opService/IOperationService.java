package it.andrea.OldFashionPound.service.opService;


import java.util.List;

import it.andrea.OldFashionPound.model.OldBritishCurrency;

public interface IOperationService {

    OldBritishCurrency additionOperation(List<OldBritishCurrency> oldBritishCurrencyFromRequest);

    OldBritishCurrency subtractionOperation(List<OldBritishCurrency> oldBritishCurrencyFromRequest);

    OldBritishCurrency multiplicationOperation(List<OldBritishCurrency> oldBritishCurrencyFromRequest, int int1);

    OldBritishCurrency divisionOperation(List<OldBritishCurrency> oldBritishCurrencyFromRequest, int int1);


}