package com.sqr.tt.domain;

import com.sqr.tt.entity.Response;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.sqr.tt.Commons.FAILURE;
import static com.sqr.tt.Commons.SUCCESS;

/**
 * Created by wyj on 09/05/2017.
 */
@Controller
public class FileUpload {

    private static final String UPLOADED_FOLDER = "./image/";

    static {
        File file = new File(UPLOADED_FOLDER);

        if (!file.exists()) {
            file.mkdirs();
        }
    }

    @PostMapping("/upload")
    @ResponseBody
    public Response<String> singleFileUpload(@RequestParam("file") MultipartFile file,
                                             RedirectAttributes redirectAttributes) {

        Response<String> response = new Response<>();


        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");

            response.setStatus(FAILURE);
            response.setMessage("file invalid");
            return response;
        }

        try {

            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            System.out.println("path = " + path.toAbsolutePath().toString());

            Files.write(path, bytes);


            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded '" + file.getOriginalFilename() + "'");

        } catch (IOException e) {
            e.printStackTrace();
            response.setData(e.toString());
            response.setStatus(FAILURE);
            return response;
        }

        response.setData(file.getOriginalFilename());
        response.setStatus(SUCCESS);

        return response;
    }
}
