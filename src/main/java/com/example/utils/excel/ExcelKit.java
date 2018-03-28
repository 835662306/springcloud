package com.example.utils.excel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * @Company
 * @Discription
 * @Author guoxiaojing
 * @CreateDate 2018/3/23 11:53
 * @Version 1.0
 */
public class ExcelKit {

    private static Logger logger = LoggerFactory.getLogger(ExcelKit.class);

    private File file;

    public ExcelKit() {

    }

    public static ExcelKit load(File file){
        ExcelKit kit = new ExcelKit();
        kit.file = file;
        return kit;
    }

    /**
     *
     * @param sheet sheet的坐标  0开始
     * @param rowIdx  表头坐标 0开始
     * @param function  回调函数
     * @param <E>  返回值
     * @return
     */
    public <E> List<E> read(int sheet, int rowIdx, Function<ExcelCell, E> function) {
        List<E> list = new ArrayList<>();
        Workbook workbook = null;
        try {
            POIFSFileSystem system = new POIFSFileSystem(new FileInputStream(file));
            workbook = new HSSFWorkbook(system);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("读取文件出错:"+e);
        }
        Sheet sheetAt = workbook.getSheetAt(sheet);
        Row title = sheetAt.getRow(rowIdx);
        List<String> titles = new ArrayList<>();
        title.cellIterator().forEachRemaining(cell -> titles.add(cell.getStringCellValue()));
        int rowNum = sheetAt.getLastRowNum();
        logger.info("准备导入"+rowNum+"条数据");
        for(int i = rowIdx+1; i < rowNum; i++){
            Row row = sheetAt.getRow(i);
            logger.info("正在导入第"+i+"条数据");
            E apply = function.apply(new ExcelCell(row, titles));
            list.add(apply);
        }
        return list;
    }
}
