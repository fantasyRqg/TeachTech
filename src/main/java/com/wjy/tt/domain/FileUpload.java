package com.wjy.tt.domain;

import com.wjy.tt.Response;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.wjy.tt.Commons.FAILURE;
import static com.wjy.tt.Commons.SUCCESS;

/**
 * Created by wjy on 09/05/2017.
 */
@Controller
public class FileUpload {

    private static final String UPLOADED_FOLDER = "/avator/";

    static {
        File file = new File(UPLOADED_FOLDER);

        if (!file.exists()) {
            file.mkdirs();
        }
    }

    @PostMapping("/upload")
    @ResponseBody
    public Response<String> singleFileUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {


        Response<String> response = new Response<>();


        if (file.isEmpty()) {

            response.setStatus(FAILURE);
            response.setMessage("file invalid");
            return response;
        }

        Path path = Paths.get(request.getServletContext().getRealPath(UPLOADED_FOLDER) + file.getOriginalFilename());
        try {

            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            System.out.println("path = " + path.toAbsolutePath().toString());

            Files.write(path, bytes);


        } catch (IOException e) {
            e.printStackTrace();
            response.setData(e.toString());
            response.setStatus(FAILURE);
            return response;
        }

        response.setData(path.getFileName().toString());
        response.setStatus(SUCCESS);

        return response;
    }
}
