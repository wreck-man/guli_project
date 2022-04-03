package com.youxin.excel.listeners;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.Map;

public class easyExcelListener extends AnalysisEventListener {
    @Override
    public void invoke(Object o, AnalysisContext analysisContext) {
        System.out.println("用户信息："+o.toString());
    }

    @Override
    public void invokeHeadMap(Map headMap, AnalysisContext context) {
        System.out.println("表头信息："+headMap);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
