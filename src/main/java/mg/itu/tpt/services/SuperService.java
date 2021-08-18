/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mg.itu.tpt.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author RADO RANDRIAMANANA
 */
public class SuperService {
    public static java.sql.Date dateConversion(String date) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd-mm-yyyy");
        Date obdate = format.parse(date);
        java.sql.Date sqdate = new java.sql.Date(obdate.getTime());
        return sqdate;
    }

//    static java.sql.Date dateConversion(java.sql.Date naissance) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
}
