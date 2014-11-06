package com.polytech.controller;

import com.polytech.exception.ExceptionHandler;
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
            File folder = new File("C:/xampp/htdocs/polytech_app_admin/public/public/app/upload/students/" + id);
            File[] listOfFiles = folder.listFiles();
            ArrayList<String> res = new ArrayList();
            for (File listOfFile : listOfFiles) {
                if (listOfFile.isFile()) {
                    res.add(listOfFile.getName());
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
            File fileUpload = new File("C:/xampp/htdocs/polytech_app_admin/public/public/app/upload/students/1/" + file.getOriginalFilename());
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(fileUpload));
            stream.write(bytes);
            stream.close();
            return true;

        } catch (IOException ex) {

            return ExceptionHandler.handle(ex);

        }
    }

}
