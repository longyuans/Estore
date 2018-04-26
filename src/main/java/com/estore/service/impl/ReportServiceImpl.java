package com.estore.service.impl;

import com.estore.bean.Report;
import com.estore.dao.ReportMapper;
import com.estore.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private ReportMapper reportMapper;

    @Override
    public List<Report> queryAllReport() {
        return reportMapper.selectAllReport();
    }
}
