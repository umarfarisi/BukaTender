package isshukan.com.bukatender.support.utils;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author Muhammad Umar Farisi
 * @created 25/05/2017
 */
public class Formatter {
    public static String priceFormatter(double price){
        NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("id","id"));
        return formatter.format(price);
    }
    public static String dateFormatter(long date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("d MMMM yyyy",new Locale("id","id") );
        return dateFormat.format(new Date(date));
    }
}
