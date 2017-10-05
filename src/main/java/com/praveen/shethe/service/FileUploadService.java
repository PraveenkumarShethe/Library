package com.praveen.shethe.service;

import com.praveen.shethe.model.DigitalFileInfo;
import com.praveen.shethe.repository.DigitalFileInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;

@Component
public class FileUploadService {

    @Autowired
    private ServletContext context;

    @Autowired
    private DigitalFileInfoRepository digitalFileInfoRepository;


    public void saveDigitalCopy(@RequestParam("file") MultipartFile inputFile) throws IOException {
        DigitalFileInfo digitalFileInfo = new DigitalFileInfo();
        HttpHeaders httpHeaders = new HttpHeaders();
        if (!inputFile.isEmpty()) {
            String originalFilename = inputFile.getOriginalFilename();
            File destinationFile = new File(context.getRealPath("/uploaded") + File.separator + originalFilename);
            inputFile.transferTo(destinationFile);
            digitalFileInfo.setFileName(destinationFile.getPath());
            digitalFileInfo.setFileSize(inputFile.getSize());
            httpHeaders.add("File Uploaded Successfully - ", originalFilename);
        }
        digitalFileInfoRepository.save(digitalFileInfo);
    }
}