package com.example.demo.excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

@Slf4j
public class EasyExcelExport {
    /**
     * EasyExcel输出通用
     *
     * @param response
     * @param title
     * @param companyName
     * @param list
     * @param cla
     */
    public static void export(HttpServletResponse response, String title, String companyName, List list, Class cla) {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = null;
        try {
            fileName = URLEncoder.encode(title + companyName, "UTF-8").replaceAll("\\+", "%20");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        try {
            // 标题样式
            WriteCellStyle headWriteCellStyle = CustomWriteCellStyleUtil.getHeadStyle();
            // 内容样式
            WriteCellStyle contentWriteCellStyle = CustomWriteCellStyleUtil.getContentStyle();

            // 这个策略是 头是头的样式 内容是内容的样式 其他的策略可以自己实现
            HorizontalCellStyleStrategy horizontalCellStyleStrategy =
                    new HorizontalCellStyleStrategy(headWriteCellStyle, contentWriteCellStyle);

            EasyExcel.write(response.getOutputStream(), cla)
                    .registerWriteHandler(horizontalCellStyleStrategy)
                    .registerWriteHandler(new CustomCellWriteHandler())
                    .sheet("data").doWrite(list);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("FileExportController.easyExcelExport", e);
        }
    }

    public static void exportMager(HttpServletResponse response, String title,
                                   String companyName, List list, Class cla,  int[] mergeColumnIndex ) {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = null;
        try {
            fileName = URLEncoder.encode(title + companyName, "UTF-8").replaceAll("\\+", "%20");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        try {
            // 标题样式
            WriteCellStyle headWriteCellStyle = CustomWriteCellStyleUtil.getHeadStyle();
            // 内容样式
            WriteCellStyle contentWriteCellStyle = CustomWriteCellStyleUtil.getContentStyle();

            // 这个策略是 头是头的样式 内容是内容的样式 其他的策略可以自己实现
            HorizontalCellStyleStrategy horizontalCellStyleStrategy =
                    new HorizontalCellStyleStrategy(headWriteCellStyle, contentWriteCellStyle);

            EasyExcel.write(response.getOutputStream(), cla)
                    .registerWriteHandler(horizontalCellStyleStrategy)
                    .registerWriteHandler(new CustomCellWriteHandler())
                    .registerWriteHandler(new ExcelFillCellMergeStrategy(1,mergeColumnIndex))
                    .sheet("data").doWrite(list);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("FileExportController.easyExcelExport", e);
        }
    }


}
