package com.laptrinhjavaweb.api.common;

import com.laptrinhjavaweb.constant.SystemConstant;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

@WebServlet("/api/image/*")
public class ImageApi extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String fileName = req.getPathInfo().substring(1);
        Path filePath = Paths.get(SystemConstant.BASE_UPLOAD_PATH, fileName);

        FileInputStream fileInputStream = new FileInputStream(filePath.toFile());
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);

        OutputStream outputStream = resp.getOutputStream();
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
        int ch;
        while ((ch = bufferedInputStream.read()) != -1) {
            bufferedOutputStream.write(ch);
        }

        bufferedInputStream.close();
        fileInputStream.close();

        bufferedOutputStream.close();
        outputStream.close();
    }
}