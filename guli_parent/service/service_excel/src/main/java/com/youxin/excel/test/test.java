package com.youxin.excel.test;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.ExcelBuilder;
import com.youxin.excel.listeners.easyExcelListener;

import java.util.ArrayList;
import java.util.List;

public class test {
    public static void main(String[] args) {
       read();

    }
    public static void write(){
        String filename="H:\\myking1.xlsx";
        EasyExcel.write(filename,DoExcel.class).sheet("sb").doWrite(getlist());
    }
    public static void read(){

        String filename="H:\\myking1.xlsx";
        EasyExcel.read(filename,domo.class,new easyExcelListener()).sheet().doRead();

    }
    public static List getlist(){
        List result=new ArrayList();
        for (int i=1;i<=10;i++){
            DoExcel excel=new DoExcel();
            excel.setId(i);
            excel.setName("king:"+i);
            result.add(excel);
        }
        return result;
    }
}
