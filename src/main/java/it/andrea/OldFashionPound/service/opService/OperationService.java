package it.andrea.OldFashionPound.service.opService;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import it.andrea.OldFashionPound.model.OldBritishCurrency;

@Service
public class OperationService implements IOperationService {

    @Value("${operation.conversion.p.s}")
    private int convertP;

    @Value("${operation.conversion.s.d}")
    private int convertS;

    @Override
    public OldBritishCurrency additionOperation(List<OldBritishCurrency> oldBritishCurrencyFromRequest) {
        OldBritishCurrency response = new OldBritishCurrency();
        Integer p = 0, s = 0, d = 0;
        for (OldBritishCurrency op : oldBritishCurrencyFromRequest) {
            
            p += op.getP();
            s += op.getS();
            d += op.getD();
        }

        response.setP(p);
        response.setS(s);
        response.setD(d);
        convertValue(response);

        response.setDiplayValue(response.toString());

        return response;

    }

    @Override
    public OldBritishCurrency subtractionOperation(List<OldBritishCurrency> oldBritishCurrencyFromRequest) {
        OldBritishCurrency response = new OldBritishCurrency();

        int p = 0, s = 0;
        int op = convertValueForOp(oldBritishCurrencyFromRequest.get(0));

        for (int i = 0 ; i < oldBritishCurrencyFromRequest.size(); i++) {
            if(i != 0){
                op -= convertValueForOp(oldBritishCurrencyFromRequest.get(i));
            }
            
        }

        response.setP(p);
        response.setS(s);
        response.setD(op);
        convertValue(response);

        response.setDiplayValue(response.toString());

        return response;

    }

    @Override
    public OldBritishCurrency multiplicationOperation(List<OldBritishCurrency> oldBritishCurrencyFromRequest, int mulOp) {
        OldBritishCurrency response = new OldBritishCurrency();

        int p = 0, s = 0, d = 0;
        for (OldBritishCurrency op : oldBritishCurrencyFromRequest) {
            p = op.getP() * mulOp;
            s = op.getS() * mulOp;
            d = op.getD() * mulOp;
        }

        response.setP(p);
        response.setS(s);
        response.setD(d);
        convertValue(response);

        response.setDiplayValue(response.toString());

        return response;

    }

    @Override
    public OldBritishCurrency divisionOperation(List<OldBritishCurrency> oldBritishCurrencyFromRequest, int divOp) {
        OldBritishCurrency response = new OldBritishCurrency();
        OldBritishCurrency rest = new OldBritishCurrency();

        int div = convertValueForOp(oldBritishCurrencyFromRequest.get(0));
        int p = 0, s = 0, d = 0, pR = 0, sR = 0, dR = 0;
        
        d = div / divOp; 

        dR = div - (d * divOp); 
        
        response.setP(p);
        response.setS(s);
        response.setD(d);


        convertValue(response);
        response.setDiplayValue(response.toString());

        rest.setP(pR);
        rest.setS(sR);
        rest.setD(dR);

        convertValue(rest);
        rest.setDiplayValue(rest.toString());

        response.setRest(rest);

        return response;

    }

    //this method returns the value of the amount entered in pennies
    private int convertValueForOp(OldBritishCurrency oldBritishCurrencyFromRequest) {
        return oldBritishCurrencyFromRequest.getD() + (((oldBritishCurrencyFromRequest.getP() * convertP) + oldBritishCurrencyFromRequest.getS()) * convertS);
    }

    //this method convert value in corret format
    private void convertValue(OldBritishCurrency oldBritishCurrencyFromRequest) {

        if (Math.abs(oldBritishCurrencyFromRequest.getD()) >= convertS) {
            int result = oldBritishCurrencyFromRequest.getD() / convertS;
            int r = oldBritishCurrencyFromRequest.getD() - (result*convertS);

            oldBritishCurrencyFromRequest.setS(oldBritishCurrencyFromRequest.getS() + result);
            oldBritishCurrencyFromRequest.setD(r);
        }

        if (Math.abs(oldBritishCurrencyFromRequest.getS()) >= convertP) {
            int result = oldBritishCurrencyFromRequest.getS() / convertP;
            int r = oldBritishCurrencyFromRequest.getS() - (result*convertP);

            oldBritishCurrencyFromRequest.setP(oldBritishCurrencyFromRequest.getP() +  result);
            oldBritishCurrencyFromRequest.setS(r);

        }

    }
}
