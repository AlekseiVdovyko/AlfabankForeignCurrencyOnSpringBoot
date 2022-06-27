package ru.edu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.edu.service.CompareValue;
import ru.edu.service.CurrencyCache;
import ru.edu.service.CurrencyInfo;
import java.util.Collection;

@Controller
@RequestMapping(value="/currency", consumes = MediaType.ALL_VALUE)
public class CurrencyController {

    String requestTimePast = "2019-05-23";
    String requestTimePresent = "2022-06-13";

    private CurrencyCache currencyCache;
    private CompareValue compare;
    private String authorName;
    private String gifHttp;

    @Value("${author.name}")
    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    @Autowired
    public void setCurrencyCache(CurrencyCache currencyCache) {
        this.currencyCache = currencyCache;
    }

    @Autowired
    public void setCompareValue(CompareValue compare) {
        this.compare = compare;
    }

    @GetMapping
    public ModelAndView info() {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/hello.jsp");
        modelAndView.addObject("authorName", authorName);
        return modelAndView;
    }

    @GetMapping("/all")
    public ModelAndView getAllCurrency() {

        currencyCache.setCache(requestTimePast);
        currencyCache.setCache(requestTimePresent);

        CurrencyInfo pastCurrencyInfo = currencyCache.getCurrencyInfo(requestTimePast);
        CurrencyInfo presentCurrencyInfo = currencyCache.getCurrencyInfo(requestTimePresent);

        gifHttp = compare.compareValue(pastCurrencyInfo.getValue(), presentCurrencyInfo.getValue());

        Collection<CurrencyInfo> info = currencyCache.getAll();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/all_currency.jsp");
        modelAndView.addObject("currencyList", info);

        modelAndView.addObject("authorName", authorName);

        modelAndView.addObject("gifHttp", gifHttp);

        return modelAndView;
    }
}
