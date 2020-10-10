package com.crowdfunding.services;


import com.crowdfunding.entities.Projet;
import com.crowdfunding.entities.User;
import com.crowdfunding.metier.UserMetier;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.FileInputStream;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class UserRestService {
    @Autowired
    private ServletContext context;
    @Autowired
    private UserMetier userMetier ;

    @RequestMapping(value = "/users" , method = RequestMethod.POST)
    public User saveClient(@RequestBody User us) {
        return userMetier.saveUser(us);
    }

    @RequestMapping(value = "/saveusers" , method = RequestMethod.POST)
    public User saveClient(@RequestParam("user") String us, @RequestParam("file") MultipartFile file)  throws JsonProcessingException {
        User user = new ObjectMapper().readValue(us,User.class);

        // manipulate date

        boolean isExist =new File(context.getRealPath("/userProfile/")).exists();
        if (!isExist) {
            new File(context.getRealPath("/userProfile/")).mkdir();
        }
        String image= file.getOriginalFilename();
        String modified= FilenameUtils.getBaseName(image)+"_"+System.currentTimeMillis()+"."+FilenameUtils.getExtension(image);
        File serverfile  =new File(context.getRealPath("/userProfile/"+File.separator+modified));
        try {
            FileUtils.writeByteArrayToFile(serverfile, file.getBytes());
        } catch (Exception e) {
            // TODO: handle exception
        }
        user.setImage(modified);
        user.setSrcimage(getImagesrc(modified));


        return userMetier.saveUser(user);
    }

    @RequestMapping(value = "/users" , method = RequestMethod.GET)
    public List<User> listUser() {
        return userMetier.listUser();
    }

    @RequestMapping(value = "/users" , method = RequestMethod.PUT)
    public User updateUser(@RequestBody  User u) {
        return userMetier.updateUser(u);
    }

    @RequestMapping(value = "/user" , method = RequestMethod.GET)
    public User getUserByEmail(@RequestParam String email) {
        return userMetier.getUserByEmail(email);
    }

    public String getImagesrc(String image){
        String imgsrc=null;
        String filesPath =context.getRealPath("/userProfile");
        File fileFolder = new File(filesPath);
        for (int i=0;i<fileFolder.listFiles().length;i++) {
            if (fileFolder.listFiles()[i].getName().equals(image)) {
                String encodeBase64 = null;
                try {
                    String extension =FilenameUtils.getExtension(image);
                    FileInputStream fileInputStream = new FileInputStream(fileFolder.listFiles()[i]);
                    byte[] bytes = new byte[(int) fileFolder.listFiles()[i].length()];
                    fileInputStream.read(bytes);
                    encodeBase64 = Base64.getEncoder().encodeToString(bytes);
                    imgsrc="data:image/"+extension+";base64,"+encodeBase64;
                    fileInputStream.close();
                }catch (Exception e) {

                }

            }
        }
        return imgsrc;

    }
    @RequestMapping(value = "/UpdateUsers" , method = RequestMethod.POST)
    public User changeState(@RequestBody User us) {
        return userMetier.changeState(us);
    }

}
