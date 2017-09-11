package com.cyj.mystock.job;

import com.cyj.mystock.thread.QueryStockThread;

import java.text.SimpleDateFormat;
import java.util.Date;

public class QueryStockJob {

    public void execute() {
        QueryStockThread thread = QueryStockThread.getInstance();
        QueryStockThread.IsBreak=true;
        SimpleDateFormat format = new SimpleDateFormat("HHmm");
        Date date = new Date();

        String nowDateValue = format.format(date);
        if("1515".equals(nowDateValue)){
            QueryStockThread.IsBreak=false;
        }else {
            new Thread(thread).start();
        }
    }

}
