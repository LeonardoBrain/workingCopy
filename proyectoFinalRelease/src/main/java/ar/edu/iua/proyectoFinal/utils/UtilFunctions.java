package ar.edu.iua.proyectoFinal.utils;

import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class UtilFunctions {

    public String getCurrentTimeUsingDate(){

        Date date = new Date();
        String strDateFormat = "yyyy-MM-dd HH:mm:ss";
        DateFormat df = new SimpleDateFormat(strDateFormat);





        return df.format(date);



    }

}
