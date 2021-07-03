package com.techproed.excelautomation;

import org.apache.poi.ss.usermodel.*;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Day14_WriteExcel {
//EXCEL IS NOT MANDATORY TO GET THE TEST DATA
//BUT IT IS EASY WAY TO GET THE LARGE AMOUNT OF TEST DATA

    @Test
    public void writeExcel() throws IOException {

//        Store the path of the  file in a string
        String filePath="./src/test/java/resources/Capitals.xlsx";
//        Open the file
        FileInputStream fileInputStream=new FileInputStream(filePath);
//        Open the workbook using fileinputstream
        Workbook workbook= WorkbookFactory.create(fileInputStream);

//        Open the first worksheet
        Sheet sheet= workbook.getSheetAt(0);
//        Go to first row
        Row firstRow =sheet.getRow(0);
//        SO FAR READ EXCEL AND WRITE EXCEL STEPS ARE THE SAME

//        Create a cell on the 2nd index on the first row
        Cell populationCell=firstRow.createCell(2);
//        Write “POPULATION” on that cell -> R0C2
        populationCell.setCellValue("POPULATION");

//        Create a cell on the 2nd row 3rd cell, and write 150000
        sheet.getRow(1).createCell(2).setCellValue("150000");
//        Create a cell on the 3rd row 3rd cell, and write 250000
        sheet.getRow(2).createCell(2).setCellValue("250000");
//        Create a cell on the 4th row 3rd cell, and write 54000
        sheet.getRow(3).createCell(2).setCellValue("54000");
        sheet.getRow(4).createCell(2).setCellValue("10000000");


//        Write and save the workbook
        FileOutputStream fileOutputStream=new FileOutputStream(filePath);
        workbook.write(fileOutputStream);

//        Close the file
        fileInputStream.close();
        fileOutputStream.close();
//        Close the workbook
        workbook.close();






    }
}
