package com.sasha.grodno.website.service.convert;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


import org.springframework.core.convert.converter.Converter;

public class DateTimeConverter implements Converter<String, Date> {

    @Override
    public Date convert(String dateTime) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = formatter.parse(dateTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    public String convertToString(Date date){
        String dateString = null;
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        try{
            dateString = formatter.format( date);
        }catch (Exception e ){
            e.printStackTrace();
        }
        return dateString;
    }

}