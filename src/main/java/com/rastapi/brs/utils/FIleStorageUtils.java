package com.rastapi.brs.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Component
public class FIleStorageUtils {
    @Value("${file.storage.directory}")
    private String fileStoragePath;
    private String userHome=System.getProperty("user.home");

/*
* this function takes multipart file as input parameter and saves it
and then return the file location
 */

    public String StoreFile(MultipartFile multipartFile) throws IOException {
        String photoUrl=userHome + fileStoragePath;
        File directoryFile= new File(photoUrl);
        if(!directoryFile.exists()){
            directoryFile.mkdirs();
        }else{
            System.out.println("++++++++++++++ directory already exists ++++++");
        }

        String fileStorageLocation =photoUrl +File.separator + UUID.randomUUID()+"_"+multipartFile.getOriginalFilename();
        File fileToSave=new File(fileStorageLocation);
        multipartFile.transferTo(fileToSave);
        return fileStorageLocation;
    }
}
