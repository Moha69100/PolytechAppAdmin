package com.polytech.controller;

import com.polytech.exception.ExceptionHandler;
import com.polytech.exception.SuccessHandler;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Epulapp
 */
@ComponentScan
@RestController
public class FileController {

    @RequestMapping(value = "/upload/student/files/{id}", method = RequestMethod.GET)
    public Object getFiles(@PathVariable String id) {
        try {
            String pathCV = "C:/Temp/test/student_" + id + "/CV";
            String pathMotiv = "C:/Temp/test/student_" + id + "/motiv";
            File CVfolder = new File(pathCV);
            File Motivfolder = new File(pathMotiv);
            ArrayList<Object> res = new ArrayList();
            if (CVfolder.isDirectory()) {
                File[] listOfFiles = CVfolder.listFiles();
                for (File listOfFile : listOfFiles) {
                    if (listOfFile.isFile()) {
                        ArrayList<Object> res2 = new ArrayList();
                        res2.add(listOfFile.getAbsolutePath());
                        res2.add("CV");
                        res2.add(pathCV);
                        res.add(res2);
                    }
                }
            }
            if (Motivfolder.isDirectory()) {
                File[] listOfFilesMotiv = Motivfolder.listFiles();
                for (File listOfFile : listOfFilesMotiv) {
                    if (listOfFile.isFile()) {
                        ArrayList<Object> res2 = new ArrayList();
                        res2.add(listOfFile.getAbsolutePath());
                        res2.add("motiv");
                        res2.add(pathMotiv);
                        res.add(res2);
                    }
                }
            }

            return res;
        } catch (Exception ex) {
            return ExceptionHandler.handle(ex);
        }

    }

    @RequestMapping(value = "/upload/student/{id}/type/{type}", method = RequestMethod.POST)
    public Object upload(@RequestParam("file") MultipartFile file, @PathVariable String id, @PathVariable String type) {
        try {
            byte[] bytes = file.getBytes();
            File path1 = new File("C:/Temp/test/student_" + id);
            if (!path1.isDirectory()) {
                path1.mkdir();
            }
            File path2 = new File("C:/Temp/test/student_" + id + "/" + type);
            if (!path2.isDirectory()) {
                path2.mkdir();
            }
            File fileUpload = new File(path2 + "/" + file.getOriginalFilename());
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(fileUpload));
            stream.write(bytes);
            stream.close();
            return true;

        } catch (IOException ex) {

            return ExceptionHandler.handle(ex);

        }
    }

}
