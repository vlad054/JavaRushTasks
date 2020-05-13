package com.javarush.task.task31.task3101;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
Проход по дереву файлов
*/
public class Solution {
    public static void main(String[] args) throws Exception{
        String path = args[0];
        String resultFileAbsolutePath = args[1];
        File file = new File(resultFileAbsolutePath);

        //rename file from args[1]
        //File nFile = new File(path+"\\allFilesContent.txt");
        File nFile = new File(file.getParent()+"\\allFilesContent.txt");
        FileUtils.renameFile(file,nFile);


        File dir = new File(path);
        List<File> lFileAll = new ArrayList<>();

        //get list of all files with max size 50
        getFiles (dir,lFileAll,50);

        //sort
        Collections.sort(lFileAll);

        //write in allFilesContent
        String line = System.getProperty("line.separator");
        FileOutputStream fileOutputStream = new FileOutputStream(nFile);

        try {

//            FileOutputStream fileOutputStream = new FileOutputStream(nFile);

            for (File f : lFileAll) {
                FileInputStream fileInputStream = new FileInputStream(f);
                int b;
                while ((b = fileInputStream.read()) != -1) {
                    fileOutputStream.write(b);
                }
                fileInputStream.close();
                fileOutputStream.write(line.getBytes());
            }
        }
        catch (Exception e){

        }
        finally {
            fileOutputStream.close();
        }

    }

    //get all Files (<siz) in Dir (recursion)
    public static void getFiles (File dir, List<File> lFileAll, int siz){

        if (dir.isDirectory()) {
            File[] arFile = dir.listFiles();
            for (int i =0; i<arFile.length; i++){
                if (arFile[i].isDirectory()) {
                    getFiles(arFile[i], lFileAll,siz);
                }
                else {
                    if ((arFile[i].length() <= siz) && (!arFile[i].getName().equals("allFilesContent.txt"))) {
                        lFileAll.add(arFile[i]);
                    }
                }
            }

        }
        else{
                if ((dir.length()<=siz) && (dir.getName()!="allFilesContent.txt")) lFileAll.add(dir);
            }
    }
}
