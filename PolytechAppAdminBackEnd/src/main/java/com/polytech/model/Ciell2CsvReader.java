/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.polytech.model;

import com.polytech.dao.Etudiant;
import com.polytech.dao.manager.EtudiantManager;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

/**
 *
 * @author Epulapp
 */
public class Ciell2CsvReader {
    
    private EtudiantManager edtManager = new EtudiantManager();
     
    /***
     * Méthode permetant de parser un fichier csv puis de l'enregister dans la base
     * @param bytes
     * @throws IOException
     * @throws FileNotFoundException
     * @throws ParseException 
     */
    public List<Etudiant> parse(byte[] bytes) throws IOException, FileNotFoundException, ParseException, Exception{
        // Enregistrement du fichier dans un dossier temporaire
        File file = uploadFile(bytes);
        
        // Parse du fichier
        List<Etudiant> etudiants = readCSVToBean(new FileReader(file));
        
        // Enregistement des fichiers en base
        List<Etudiant> saveEtudiants = save(etudiants);
        
        // Suppresion du fichier 
        file.delete();
        
        // Retour de la liste
        return saveEtudiants;
    }
    
    /***
     * Methode permetant d'uploader le fichier dans un dossier temporaire
     * @param bytes Tableau de bytes du fichier
     * @return Chemin du fichier
     * @throws FileNotFoundException
     * @throws IOException 
     */
    private File uploadFile(byte[] bytes) throws FileNotFoundException, IOException{
        String uri = System.getProperty("catalina.base") + "\\" + getCurrentTimeStamp() + "_uploaded.csv";
        System.out.println("Uri : " + uri);
        File file = new File(uri);
        BufferedOutputStream stream =  new BufferedOutputStream(new FileOutputStream(file));
        stream.write(bytes);
        stream.close();
        return file;
    }
    
    
    /***
     * Méthode permetant de parser un fichier CSV
     * @param fileReader : Fichier à parser
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ParseException
     */
    private List<Etudiant> readCSVToBean(FileReader fileReader) throws FileNotFoundException, IOException, ParseException {
        
        try
        {
            // Definition du formatter de la date dans le fichier
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            
            // Définition du délimiter
            CSVFormat format = CSVFormat.RFC4180.withHeader().withDelimiter(';');
            
            // Parse du fichier
            CSVParser parser = new CSVParser(fileReader, format);
            
            // Liste d'étudiant
            List<Etudiant> emps = new ArrayList<Etudiant>();
            
            // Parcours de la liste et création des étudiants
            for(CSVRecord record : parser){
                Etudiant edt = new Etudiant();
                if(record.isSet("Identifiant")) edt.setCandidid(Integer.parseInt(record.get("Identifiant")));    
                if(record.isSet("Civilité")) edt.setCivilite(record.get("Civilité"));
                if(record.isSet("NOM")) edt.setNom(record.get("NOM"));
                if(record.isSet("Prénom")) edt.setPrenom(record.get("Prénom"));
                if(record.isSet("DATE NAISSANCE")) edt.setDatenaissance(formatter.parse(record.get("DATE NAISSANCE")));
                if(record.isSet("ADRESSE")) edt.setAdresse(record.get("ADRESSE"));
                if(record.isSet("CODE POSTAL")) edt.setCp(record.get("CODE POSTAL"));
                if(record.isSet("VILLE")) edt.setVille(record.get("VILLE"));
                if(record.isSet("PAYS")) edt.setPays(record.get("PAYS"));
                if(record.isSet("EMAIL")) edt.setEmail(record.get("EMAIL"));
                if(record.isSet("TELEPHONE")) edt.setTelfixe(record.get("TELEPHONE"));
                if(record.isSet("MOBILE")) edt.setTelportable(record.get("MOBILE"));
                if(record.isSet("ETAB_EN_COURS_PATRONYME")) edt.setEtab(record.get("ETAB_EN_COURS_PATRONYME"));
                if(record.isSet("ETAB_EN_COURS_VILLE")) edt.setEtabville(record.get("ETAB_EN_COURS_VILLE"));
                if(record.isSet("SEB_LIBELLE")) edt.setBactype(record.get("SEB_LIBELLE"));
                if(record.isSet("Mention BAC")) edt.setBacmention(record.get("Mention BAC"));
                if(record.isSet("BCA_ANNEE")) edt.setBacannee(Integer.parseInt(record.get("BCA_ANNEE")));
                if(record.isSet("BAC_LIBELLE")) edt.setBacoption(record.get("BAC_LIBELLE"));
                emps.add(edt);
            }
            
            parser.close();
            
            // Retour de la liste
            return emps;
        }
        finally
        {
            
        }
    }
    
    /*
     * Méthode permetant d'enregistrer les étudiants dans la bdd 
     * @param etudiants
     * @throws Exception 
     */
    private List<Etudiant> save(List<Etudiant> etudiants) throws Exception {
        List<Etudiant> list = new ArrayList<Etudiant>();
        for (Etudiant etudiant : etudiants) {
            try {       
                edtManager.addEtudiant(etudiant);
                list.add(etudiant);
            }
            catch(Exception ex)
            {
                
            }
        }
        
        return list;
    }
    
    /*
     * Méthode permettant de retourner le timestamp courant 
     * @return 
     */
    public String getCurrentTimeStamp() {
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyyMMdd_HH_mm_ss");
        Date now = new Date();
        String strDate = sdfDate.format(now);
        return strDate;
    }
}
