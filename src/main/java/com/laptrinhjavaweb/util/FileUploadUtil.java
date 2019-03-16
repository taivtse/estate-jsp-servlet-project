package com.laptrinhjavaweb.util;

import com.laptrinhjavaweb.constant.SystemConstant;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

public class FileUploadUtil {
    private static FileUploadUtil fileUploadUtil;

    private FileUploadUtil() {
    }

    public static FileUploadUtil getInstance() {
        if (fileUploadUtil == null) {
            fileUploadUtil = new FileUploadUtil();
        }
        return fileUploadUtil;
    }

    public void writeBase64(String base64, String parentFolder, String savedName, String extension) throws IOException {
        String rawData = base64.split(",")[1];
        byte[] decodedImg = Base64.getDecoder().decode(rawData);
        Path destinationFolder = Paths.get(SystemConstant.BASE_UPLOAD_PATH, parentFolder);
        createFolderIfNotExisted(destinationFolder);

        Path destinationFile = Paths.get(destinationFolder.toString(), savedName.concat(extension));
        Files.write(destinationFile, decodedImg);
    }

    private void createFolderIfNotExisted(Path path) {
        File folder = path.toFile();
        if (!folder.exists()) {
            folder.mkdirs();
        }
    }
}