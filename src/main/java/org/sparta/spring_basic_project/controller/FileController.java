package org.sparta.spring_basic_project.controller;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.io.FilenameUtils;
import org.sparta.spring_basic_project.entity.MediaTypeUtiles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
public class FileController {
    @PostMapping( "/fileUpload")
    public String fileUpload(@RequestBody
                             MultipartFile uploadFile,
                             HttpServletRequest req) {

        System.out.println("HelloController fileUpload " + new Date());
        List<String> allowedExtensions = Arrays.asList("jpg", "jpeg", "png", "gif");
        String path = "/Users/hyunacho/Downloads";
        String filename = uploadFile.getOriginalFilename();
        String extension = FilenameUtils.getExtension(filename);

        // 허용되지 않은 파일 형식인 경우 에러 반환
        if (!allowedExtensions.contains(extension.toLowerCase())) {
            return "Only jpg, jpeg, png, gif files are allowed to upload.";
        }
        String filepath = path + "/" + filename;

        System.out.println(filepath);

        File file = new File(filepath);
        try {
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
            bos.write(uploadFile.getBytes());
            bos.close();

        } catch (Exception e) {
            return "file upload fail";
        }
        return "file upload success";



    }
    @Autowired
    ServletContext servletContext;

    @RequestMapping(value = "/fileDownload")
    public ResponseEntity<InputStreamResource> download(String filename, HttpServletRequest req) throws Exception{
        System.out.println("HelloController download " + new Date());

        String path = "/Users/hyunacho/Downloads";
        MediaType mediaType = MediaTypeUtiles.getMediaTypeForFileName(this.servletContext, filename);
        System.out.println("filename: " + filename);
        System.out.println("mediaType: " + mediaType);

        File file = new File(path + File.separator + filename);

        InputStreamResource isr = new InputStreamResource(new FileInputStream(file));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
                .contentType(mediaType)
                .contentLength(file.length())
                .body(isr);
    }
}
