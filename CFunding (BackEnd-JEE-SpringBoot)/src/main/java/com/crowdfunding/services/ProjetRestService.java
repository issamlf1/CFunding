package com.crowdfunding.services;


import com.crowdfunding.entities.Projet;
import com.crowdfunding.entities.User;
import com.crowdfunding.metier.ProjetMetier;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.Servlet;
import javax.servlet.ServletContext;

@RestController
@CrossOrigin(origins = "*")
public class ProjetRestService {




    @Autowired
    private ProjetMetier projetMetier ;
    @Autowired
    private ServletContext context;

    @RequestMapping(value = "/projects" , method = RequestMethod.POST)
    public Projet saveProjet(@RequestParam("projet") String pr, @RequestParam("file") MultipartFile file) throws JsonProcessingException {

        Projet projet = new ObjectMapper().readValue(pr,Projet.class);
        Date currentDate = projet.getDateCre();
        // convert date to calendar
        Calendar c = Calendar.getInstance();
        c.setTime(currentDate);
        // manipulate date
        c.add(Calendar.DAY_OF_MONTH, Math.toIntExact(projet.getDuree()));
        projet.setDatFin(c.getTime());
        projet.setJourRestant( Math.toIntExact(projet.getDuree()));

        System.err.println(projet);
        boolean isExist =new File(context.getRealPath("/userProject/")).exists();
        if (!isExist) {
			new File(context.getRealPath("/userProject/")).mkdir();
		}
        String image= file.getOriginalFilename();
        String modified= FilenameUtils.getBaseName(image)+"_"+System.currentTimeMillis()+"."+FilenameUtils.getExtension(image);
        File serverfile  =new File(context.getRealPath("/userProject/"+File.separator+modified));
        try {
			FileUtils.writeByteArrayToFile(serverfile, file.getBytes());
		} catch (Exception e) {
			// TODO: handle exception
		}
        projet.setImage(modified);
        projet.setSrcimage(getImagesrc(modified));

        return projetMetier.saveProjet(projet);
    }

    @RequestMapping(value = "/filterProjects" , method = RequestMethod.POST)
    public List<Projet> projetsByUser(@RequestBody User user) {
        return projetMetier.projetsByUser(user);
    }


    @RequestMapping(value = "/projects" , method = RequestMethod.GET)
    public List<Projet> listProjets() {
        return projetMetier.listProjets();
    }

    @RequestMapping(value = "/deleteProj" , method = RequestMethod.POST)
    public void deleteProjet(@RequestBody Projet pr) {
        projetMetier.deleteProjet(pr);
    }

    @RequestMapping(value = "/oneProhet" , method = RequestMethod.POST)
    public Projet oneProjet(@RequestBody Projet pr) {
        return projetMetier.oneProjet(pr);
    }

//    @RequestMapping(value = "/projects" , method = RequestMethod.PUT)
//    public Projet updateProjet(@RequestBody Projet pr) {
//        return projetMetier.updateProjet(pr);
//    }

    @RequestMapping(value = "/getfilterone" , method = RequestMethod.GET)
    public List<Projet> getProjectbyfiltreOne(@RequestParam String titre,@RequestParam boolean etat) {
        return projetMetier.getProjectbyfiltreOne(titre, etat);
    }

    @RequestMapping(value = "/getfilterTwo" , method = RequestMethod.GET)
    public List<Projet> getProjectbyfiltreTwo(@RequestParam String categorie ,@RequestParam String titre,@RequestParam boolean etat) {
        return projetMetier.getProjectbyfiltreTwo(categorie ,titre, etat);
    }

    public String getImagesrc(String image){
        String imgsrc=null;
        String filesPath =context.getRealPath("/userProject");
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
}
