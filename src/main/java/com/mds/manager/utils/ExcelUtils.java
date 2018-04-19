package com.mds.manager.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFDataFormat;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

public class ExcelUtils {

    // 设置cell编码解决中文高位字节截断
    // private static int XLS_ENCODING = HSSFCell.ENCODING_UTF_16;
    // 定制浮点数格式
    private static String NUMBER_FORMAT = "#,##0.00";
    // 定制日期格式
    private static String DATE_FORMAT = "m/d/yy"; // "m/d/yy h:mm"
    private OutputStream out = null;
    private SXSSFWorkbook workbook = null;
    private SXSSFSheet sheet = null;
    private SXSSFRow row = null;
    private static Workbook book = null;

    /**
     * 初始化Excel
     */
    public ExcelUtils() {
        this.workbook = new SXSSFWorkbook(1000);
        this.sheet = (SXSSFSheet) workbook.createSheet();
    }

    /**
     * 初始化Excel
     *
     * @param out
     */
    public ExcelUtils(OutputStream out) {
        this.out = out;
        this.workbook = new SXSSFWorkbook(1000);
        this.sheet = (SXSSFSheet) workbook.createSheet();
    }

    /**
     * 读取excel 只支持 office 2003--2007 07以上版本需要使用 XSSFWorkbook
     *
     * @param inputStream
     * @return book
     */
    public static Workbook getWorkbook(InputStream inputStream) {
        try {
            if (book == null) {
                book = new HSSFWorkbook(inputStream);

            } else {
                return book;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return book;
    }

    /**
     * 导出Excel文件
     *
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void export() throws FileNotFoundException, IOException {
        try {
            workbook.write(out);
        } catch (FileNotFoundException e) {
            throw new IOException("生成导出Excel文件出错!");
        } catch (IOException e) {
            throw new IOException("写入Excel文件出错!");
        } finally {
            if (out != null) {
                out.flush();
                out.close();
            }
        }
    }

    /**
     * 设置列宽
     *
     * @param index
     *            列号
     * @param width
     *            宽度
     */
    public void setColumnWidth(int index, int width) {
        sheet.setColumnWidth(index, width);
    }

    /**
     * 增加一行
     *
     * @param index
     *            行号
     *
     */
    public void createRow(int index) {
        this.row = (SXSSFRow) this.sheet.createRow(index);
    }

    /**
     * 设置列名显示头
     *
     * @param headerList
     * @return
     */
    public void createHeadColumn(List<String> headerList) {
        this.row = (SXSSFRow) this.sheet.createRow(0);
        for (int i = 0; i < headerList.size(); i++) {
            this.setCell(i, headerList.get(i));

        }
    }

    /**
     * 设置单元格(int)
     *
     * @param index
     *            列号
     * @param value
     *            单元格填充值
     *
     */
    public void setCell(int index, int value) {
        SXSSFCell cell = (SXSSFCell) this.row.createCell(index);
        cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
        cell.setCellValue(value);
    }

    /**
     * 设置单元格(BigDecimal)
     *
     * @param index
     *            列号
     * @param value
     *            单元格填充值
     *
     */
    public void setCell(int index, BigDecimal value) {
        SXSSFCell cell = (SXSSFCell) this.row.createCell(index);
        cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
        if (value == null) {
            cell.setCellValue("0.00");
        } else {
            cell.setCellValue(value.doubleValue());
        }
        XSSFCellStyle cellStyle = (XSSFCellStyle) workbook.createCellStyle(); // 建立新的cell样式
        XSSFDataFormat format = (XSSFDataFormat) workbook.createDataFormat();
        cellStyle.setDataFormat(format.getFormat(NUMBER_FORMAT)); // 设置cell样式为定制的浮点数格式
        cell.setCellStyle(cellStyle); // 设置该cell浮点数的显示格式
    }

    /**
     * 设置单元格(double)
     *
     * @param index
     *            列号
     * @param value
     *            单元格填充值
     *
     */
    public void setCell(int index, double value) {
        SXSSFCell cell = (SXSSFCell) this.row.createCell(index);
        cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
        cell.setCellValue(value);
        XSSFCellStyle cellStyle = (XSSFCellStyle) workbook.createCellStyle(); // 建立新的cell样式
        XSSFDataFormat format = (XSSFDataFormat) workbook.createDataFormat();
        cellStyle.setDataFormat(format.getFormat(NUMBER_FORMAT)); // 设置cell样式为定制的浮点数格式
        cell.setCellStyle(cellStyle); // 设置该cell浮点数的显示格式
    }

    /**
     * 设置单元格(String)
     *
     * @param index
     *            列号
     * @param value
     *            单元格填充值
     *
     */
    public void setCell(int index, String value) {
        SXSSFCell cell = (SXSSFCell) this.row.createCell(index);
        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
        cell.setCellValue(value);
    }

    /**
     * 设置单元格(日期)
     *
     * @param index
     *            列号
     * @param value
     *            单元格填充值
     */
    public void setCell(int index, Calendar value) {
        SXSSFCell cell = (SXSSFCell) this.row.createCell(index);
        cell.setCellValue(value.getTime());
        XSSFCellStyle cellStyle = (XSSFCellStyle) workbook.createCellStyle(); // 建立新的cell样式
        cellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat(DATE_FORMAT)); // 设置cell样式为定制的日期格式
        cell.setCellStyle(cellStyle); // 设置该cell日期的显示格式
    }

    /**
     *
     * @return bytes
     */
    public byte[] getBytesFromWorkbook() {
        byte[] bytes = null;
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            this.workbook.write(out);
            bytes = out.toByteArray();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return bytes;
    }

    public static String NO_DEFINE = "no_define";// 未定义的字段
    public static String DEFAULT_DATE_PATTERN = "yyyy年MM月dd日 HH:mm:ss";// 默认日期格式
    public static int DEFAULT_COLOUMN_WIDTH = 17;

    /**
     * 导出Excel 2007 OOXML (.xlsx)格式
     *
     * @param title
     *            标题行
     * @param headMap
     *            属性-列头
     * @param jsonArray
     *            数据集
     * @param datePattern
     *            日期格式，传null值则默认 年月日
     * @param colWidth
     *            列宽 默认 至少17个字节
     * @param out
     *            输出流
     */
    public static void exportExcelX(String title, Map<String, String> headMap, JSONArray jsonArray, String datePattern,
                                    int colWidth, OutputStream out) {
        if (datePattern == null)
            datePattern = DEFAULT_DATE_PATTERN;
        // 声明一个工作薄
        SXSSFWorkbook workbook = new SXSSFWorkbook(1000);// 缓存
        workbook.setCompressTempFiles(true);
        // 表头样式
        CellStyle titleStyle = workbook.createCellStyle();
        titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        Font titleFont = workbook.createFont();
        titleFont.setFontHeightInPoints((short) 20);
        titleFont.setBoldweight((short) 700);
        titleStyle.setFont(titleFont);
        // 列头样式
        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        headerStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        headerStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        headerStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        headerStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        headerStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        Font headerFont = workbook.createFont();
        headerFont.setFontHeightInPoints((short) 12);
        headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        headerStyle.setFont(headerFont);
        // 单元格样式
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        Font cellFont = workbook.createFont();
        cellFont.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
        cellStyle.setFont(cellFont);
        // 生成一个(带标题)表格
        SXSSFSheet sheet = workbook.createSheet();
        // 设置列宽
        int minBytes = colWidth < DEFAULT_COLOUMN_WIDTH ? DEFAULT_COLOUMN_WIDTH : colWidth;// 至少字节数
        int[] arrColWidth = new int[headMap.size()];
        // 产生表格标题行,以及设置列宽
        String[] properties = new String[headMap.size()];
        String[] headers = new String[headMap.size()];
        int ii = 0;
        for (Iterator<String> iter = headMap.keySet().iterator(); iter.hasNext();) {
            String fieldName = iter.next();

            properties[ii] = fieldName;
            headers[ii] = headMap.get(fieldName);

            int bytes = fieldName.getBytes().length;
            arrColWidth[ii] = bytes < minBytes ? minBytes : bytes;
            sheet.setColumnWidth(ii, arrColWidth[ii] * 256);
            ii++;
        }
        // 遍历集合数据，产生数据行
        int rowIndex = 0;
        for (Object obj : jsonArray) {
            if (rowIndex == 65535 || rowIndex == 0) {
                if (rowIndex != 0)
                    sheet = workbook.createSheet();// 如果数据超过了，则在第二页显示

                SXSSFRow titleRow = sheet.createRow(0);// 表头 rowIndex=0
                titleRow.createCell(0).setCellValue(title);
                titleRow.getCell(0).setCellStyle(titleStyle);
                sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, headMap.size() - 1));

                SXSSFRow headerRow = sheet.createRow(1); // 列头 rowIndex =1
                for (int i = 0; i < headers.length; i++) {
                    headerRow.createCell(i).setCellValue(headers[i]);
                    headerRow.getCell(i).setCellStyle(headerStyle);

                }
                rowIndex = 2;// 数据内容从 rowIndex=2开始
            }
            JSONObject jo = (JSONObject) JSONObject.toJSON(obj);
            SXSSFRow dataRow = sheet.createRow(rowIndex);
            for (int i = 0; i < properties.length; i++) {
                SXSSFCell newCell = dataRow.createCell(i);

                Object o = jo.get(properties[i]);
                String cellValue = "";
                if (o == null)
                    cellValue = "";
                else if (o instanceof Date)
                    cellValue = new SimpleDateFormat(datePattern).format(o);
                else if (o instanceof Float || o instanceof Double)
                    cellValue = new BigDecimal(o.toString()).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
                else
                    cellValue = o.toString();

                newCell.setCellValue(cellValue);
                newCell.setCellStyle(cellStyle);
            }
            rowIndex++;
        }
        // 自动调整宽度
        /*
         * for (int i = 0; i < headers.length; i++) { sheet.autoSizeColumn(i); }
         */
        try {
            workbook.write(out);
            workbook.close();
            workbook.dispose();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Web 导出excel
    public static void downloadExcelFile(String title, Map<String, String> headMap, JSONArray ja,
                                         HttpServletResponse response) {
        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            exportExcelX(title, headMap, ja, null, 0, os);
            byte[] content = os.toByteArray();
            InputStream is = new ByteArrayInputStream(content);
            // 设置response参数，可以打开下载页面
            response.reset();

            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
            response.setHeader("Content-Disposition",
                    "attachment;filename=" + new String((title + ".xlsx").getBytes(), "iso-8859-1"));
            response.setContentLength(content.length);
            ServletOutputStream outputStream = response.getOutputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            BufferedOutputStream bos = new BufferedOutputStream(outputStream);
            byte[] buff = new byte[8192];
            int bytesRead;
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);

            }
            bis.close();
            bos.close();
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
