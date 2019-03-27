package pl.com.bottega.ecommerce.sales.domain.invoicing;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.Id;
import pl.com.bottega.ecommerce.sales.domain.productscatalog.ProductData;
import pl.com.bottega.ecommerce.sharedkernel.Money;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.*;
import static pl.com.bottega.ecommerce.sales.domain.productscatalog.ProductType.FOOD;
import static pl.com.bottega.ecommerce.sales.domain.productscatalog.ProductType.STANDARD;

public class DefaultTaxCalculatorTest {

    private DefaultTaxCalculator defaultTaxCalculator;

    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() {
        this.defaultTaxCalculator = new DefaultTaxCalculator();
    }

    @Test
    public void shouldReturnTaxDescriptionForFood(){
        ProductData productData = new ProductData(Id.generate(), new Money(new BigDecimal(1),Money.DEFAULT_CURRENCY), "Cheese", FOOD, new Date());
        RequestItem item = new RequestItem(productData,500,new Money(new BigDecimal(1),Money.DEFAULT_CURRENCY));

        Tax tax = defaultTaxCalculator.calculate(item);

        assertEquals(tax.getDescription(),"7% (F)");
    }

    @Test
    public void shouldReturnTaxDescriptionForStandard(){
        ProductData productData = new ProductData(Id.generate(), new Money(new BigDecimal(1),Money.DEFAULT_CURRENCY), "Stick", STANDARD, new Date());
        RequestItem item = new RequestItem(productData,900,new Money(new BigDecimal(1),Money.DEFAULT_CURRENCY));

        Tax tax = defaultTaxCalculator.calculate(item);

        assertEquals(tax.getDescription(),"23%");
    }


}
