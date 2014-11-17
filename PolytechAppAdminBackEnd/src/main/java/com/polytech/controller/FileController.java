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

    @RequestMapping(value = "/upload/student/{id}", method = RequestMethod.GET)
    public Object getFiles(@PathVariable String id) {
        try {
            File CVfolder = new File("C:/Temp/test/student_" + id + "/cv");
            String pathCV = "C:/Temp/test/student_" + id + "/cv";
            File Motivfolder = new File("C:/Temp/test/student_" + id + "/motiv");
            String pathMotiv = "C:/Temp/test/student_" + id + "/motiv";
            File[] listOfFiles = CVfolder.listFiles();
            ArrayList<Object> res = new ArrayList();
            File[] listOfFilesMotiv = Motivfolder.listFiles();

            for (File listOfFile : listOfFiles) {
                if (listOfFile.isFile()) {
                    ArrayList<Object> res2 = new ArrayList();
                    res2.add(listOfFile.getAbsolutePath());
                    res2.add("CV");
                    res2.add(pathCV);
                    res.add(res2);
                }
            }
            for (File listOfFile : listOfFilesMotiv) {
                if (listOfFile.isFile()) {
                    ArrayList<Object> res2 = new ArrayList();
                    res2.add(listOfFile.getAbsolutePath());
                    res2.add("motiv");
                    res2.add(pathMotiv);
                    res.add(res2);
                }
            }
            return res;
        } catch (Exception ex) {
            return ExceptionHandler.handle(ex);
        }

    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public Object upload(@RequestParam("file") MultipartFile file) {
        try {
            byte[] bytes = file.getBytes();
            File fileUpload = new File("C:/Temp/test/student_1/" + file.getOriginalFilename());
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(fileUpload));
            stream.write(bytes);
            stream.close();
            return true;

        } catch (IOException ex) {

            return ExceptionHandler.handle(ex);

        }
    }

}
