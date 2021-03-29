package com.jlxz.exam;

import org.apache.poi.xwpf.usermodel.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

public class Exam {
    public static void main(String[] args) {
        try {
            String path = "/Users/didi/Downloads/机器人技术1级、2020.6.docx";
            XWPFDocument docx = new XWPFDocument(XWPFDocument.openPackage(path));

            List<XWPFTable> tables = docx.getTables();
            for(XWPFTable table:tables){



                List<XWPFTableRow> rows = table.getRows();
                for(XWPFTableRow row:rows){
                    List<XWPFTableCell> cells = row.getTableCells();
                    for(XWPFTableCell cell:cells){
                        String s = cell.getText();
                        if(s.trim().length() > 0){

                            if(s.contains("试题编号")
                                    || s.contains("试题难度")
                                    || s.contains("考生答案")
                                    || s.contains("考生得分")
                                    || s.contains("是否评分")
                                    || s.contains("评价描述")
                                    || s.contains("试题解析")){

                            }else{
                                System.out.println(s);
                            }
                        }




                        List<XWPFParagraph> cellgraphs = cell.getParagraphs();
                        for(XWPFParagraph cellgraph:cellgraphs){
                            for(XWPFRun run:cellgraph.getRuns()){
                                List<XWPFPicture> pics = run.getEmbeddedPictures();
                                for(XWPFPicture pic:pics){
                                    byte[] bytes = pic.getPictureData().getData();
                                    String fileName = pic.getPictureData().getFileName();
                                    String longFileName = "exam/exam1/"+fileName;
                                    String fpath = "/Users/didi/llx/llx-api/src/main/resources/static/"+longFileName;

                                    OutputStream os = new FileOutputStream(new File(fpath));
                                    os.write(bytes);
                                    os.close();

                                    System.out.println(longFileName);
                                }
                            }
                        }



                        List<XWPFTable> cellTables = cell.getTables();
                        for(XWPFTable celltable:cellTables) {
                            List<XWPFTableRow> cellrows = celltable.getRows();
                            for(XWPFTableRow cellrow:cellrows) {
                                List<XWPFTableCell> cellcells = cellrow.getTableCells();
                                for(XWPFTableCell cellcell:cellcells) {
                                    String s1 = cellcell.getText();
                                    if(s1.trim().length()>0){
                                        System.out.println(s1);
                                    }
                                }
                            }
                        }




                    }
                }
            }

//            List<XWPFParagraph> graphs =  docx.getParagraphs();
//            for(XWPFParagraph graph:graphs){
//
//
//
//
//                String s = graph.getText();
//                if(s.trim().length() > 0){
//
//
//                    System.out.println(s);
//                }
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
