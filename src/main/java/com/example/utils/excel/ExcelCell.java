package com.example.utils.excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.util.List;
import java.util.function.Consumer;

/**
 * @Company
 * @Discription
 * @Author guoxiaojing
 * @CreateDate 2018/3/23 11:53
 * @Version 1.0
 */
public class ExcelCell {
    private Row row;

    private List<String> titles;

    ExcelCell(Row row, List<String> titles) {
        this.row = row;
        this.titles = titles;
    }

    public ExcelCell when(String name, Consumer<Cell> consumer) {
        Cell cell = row.getCell(titles.indexOf(name));
        consumer.accept(cell);
        return this;
    }
}
