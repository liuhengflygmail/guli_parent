package com.atguigu.demo.excel;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.List;

public class TestEasyExcel {
    public static void main(String[] args) {
        //实现Excel写
        //1.设置写入文件夹地址和Excel文件名称
//        String filename = "D:\\1.xlsx";
        //2.调用 easyExcel里面的方法实现写操作
        // write第一个参数:文件名称,第二个参数:实体类class
//        EasyExcel.write(filename, DemoData.class).sheet("学生列表").doWrite(getData());



        //实现读操作
        String filename = "D:\\1.xlsx";
        EasyExcel.read(filename, DemoData.class,new ExcelListener()).sheet().doRead();
    }
    private static List<DemoData> getData() {
        ArrayList<DemoData> list = new ArrayList<>();
        for (int i = 0; i <10; i++) {
            DemoData data = new DemoData();
            data.setSno(i);
            data.setSname("lusy"+ i);
            list.add(data);
        }
        return list;
    }
}
