/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidades;

/**
 *
 * @author Mario
 */

import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

public class Fechas {
    public static String format(TemporalAccessor date) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return dtf.format(date);
    }
}
