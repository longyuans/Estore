package com.estore.service;

import com.estore.bean.Report;

import java.util.List;

public interface ReportService {
    /**
     * 查询简报
     */
    List<Report> queryAllReport();
}
