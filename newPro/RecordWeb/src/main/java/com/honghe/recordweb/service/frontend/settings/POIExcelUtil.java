package com.honghe.recordweb.service.frontend.settings;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hssf.util.Region;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wuzhenzhen on 2016-06-05.
 */
public class POIExcelUtil {
    /**
     * @Title: exportExcel
     * @Description: 导出Excel的方法
     * @author: evan @ 2014-01-09
     * @param workbook
     * @param sheetNum (sheet的位置，0表示第一个表格中的第一个sheet)
     * @param sheetTitle  （sheet的名称）
     * @param headers    （表格的标题）
     * @param result   （表格的数据）
     * @param out  （输出流）
     * @throws Exception
     */
    public void exportExcel(HSSFWorkbook workbook, int sheetNum,
                            String sheetTitle, String[] headers, List<List<String>> result,
                            OutputStream out) throws Exception {
        // 生成一个表格
        HSSFSheet sheet = workbook.createSheet();
        workbook.setSheetName(sheetNum, sheetTitle);
        // 设置表格默认列宽度为20个字节
        sheet.setDefaultColumnWidth((short) 8.38);
        // 生成一个样式
        HSSFCellStyle style = workbook.createCellStyle();
        // 设置这些样式
//        style.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
//        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
//        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // 生成一个字体
        HSSFFont font = workbook.createFont();
        font.setColor(HSSFColor.BLACK.index);
        font.setFontHeightInPoints((short) 12);
//        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // 把字体应用到当前的样式
        style.setFont(font);
        // 指定当单元格内容显示不下时自动换行
        style.setWrapText(true);
        // 产生表格标题行
        HSSFRow row = sheet.createRow(0);
        HSSFCell cell0 = row.createCell(0);
//        cell0.setCellValue("节次\\星期"); // 表格的第一行第一列显示的数据
        cell0.setCellValue("");
        cell0.setCellStyle(style); // 样式，居中
        sheet.addMergedRegion(new CellRangeAddress(0, 1, 0,0));
        HSSFRow row1 = sheet.createRow(1);
        HSSFCell cell01 = row1.createCell(0);
        cell01.setCellStyle(style);
        for (int i = 0; i < headers.length; i++) {
            // 计算从那个单元格跨到那一格
            int cell_start = 0;
            int cell_mid = 0;
            int cell_end = 0;
            if (i == 0) {
                cell_start = 1;
                cell_mid = 2;
                cell_end = 3;
            } else {
                cell_start = i * 3 + 1;
                cell_mid = i * 3 + 2;
                cell_end = i * 3 + 3;
            }
            HSSFCell cell_header1 = row.createCell(cell_start);
            cell_header1.setCellStyle(style);
            sheet.addMergedRegion(new CellRangeAddress(0, 0, cell_start,cell_end));
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell_header1.setCellValue(text.toString());
            HSSFCell cell_header2 = row.createCell(cell_end);
            cell_header2.setCellStyle(style);
            //设置科目、教师 教学单位
            HSSFCell cell_header3 = row1.createCell(cell_start);
            cell_header3.setCellStyle(style);
            cell_header3.setCellValue("科目");
            HSSFCell cell_header4 = row1.createCell(cell_mid);
            cell_header4.setCellStyle(style);
            cell_header4.setCellValue("教师");
            HSSFCell cell_header5 = row1.createCell(cell_end);
            cell_header5.setCellStyle(style);
            cell_header5.setCellValue("教学单位");
        }
        // 遍历集合数据，产生数据行
        if (result != null) {
            int index = 2;
            for (List<String> m : result) {
                row = sheet.createRow(index);
                int cellIndex = 0;
                for (String str : m) {
                    HSSFCell cell = row.createCell(cellIndex);
//                    cell.setEncoding(HSSFCell.ENCODING_UTF_16);
                    cell.setCellValue(str.toString());
                    cellIndex++;
                }
                index++;
            }
        }
    }

    @SuppressWarnings("unchecked")
    /**
     * todo 加注释
     */
    public static void main(String[] args) {
        try {
            OutputStream out = new FileOutputStream("D:\\test.xls");
            List<List<String>> data = new ArrayList<List<String>>();
            for (int i = 1; i < 5; i++) {
                List rowData = new ArrayList();
                rowData.add(String.valueOf(i));
                rowData.add("东霖柏鸿");
                data.add(rowData);
            }
            String[] headers = { "星期一", "星期二","星期三", "星期四","星期五", "星期六","星期日" };
            HSSFWorkbook workbook = new HSSFWorkbook();
//            exportExcel(workbook, 0, "上海", headers, data, out);
//            exportExcel(workbook, 1, "深圳", headers, data, out);
//            exportExcel(workbook, 2, "广州", headers, data, out);
            //原理就是将所有的数据一起写入，然后再关闭输入流。
            workbook.write(out);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}