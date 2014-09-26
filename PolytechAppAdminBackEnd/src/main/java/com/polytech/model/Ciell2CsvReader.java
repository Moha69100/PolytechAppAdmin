/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.polytech.model;

import com.polytech.dao.Etudiant;
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
    
    /***
     * Méthode permetant de parser un fichier csv puis de l'enregister dans la base
     * @param bytes
     * @throws IOException
     * @throws FileNotFoundException
     * @throws ParseException 
     */
    public void parse(byte[] bytes) throws IOException, FileNotFoundException, ParseException{
        // Enregistrement du fichier dans un dossier temporaire
        File file = uploadFile(bytes);
        
        // Parse du fichier
        List<Etudiant> etudiants = readCSVToBean(new FileReader(file));
        
        // Enregistement des fichiers en base
        save(etudiants);
        
    }
    
    /***
     * Methode permetant d'uploader le fichier dans un dossier temporaire
     * @param bytes Tableau de bytes du fichier
     * @return Chemin du fichier
     * @throws FileNotFoundException
     * @throws IOException 
     */
    private File uploadFile(byte[] bytes) throws FileNotFoundException, IOException{
        String name = getCurrentTimeStamp();
        File file = new File("C:\\temp\\" + name + "_uploaded.csv");
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
                edt.setCandidid(Integer.parseInt(record.get("Identifiant")));
                edt.setNom(record.get("NOM"));
                edt.setPrenom(record.get("Prénom"));
                edt.setDatenaissance(formatter.parse(record.get("DATE NAISSANCE")));
                edt.setAdresse(record.get("ADRESSE"));
                edt.setCp(record.get("CODE POSTAL"));
                edt.setVille(record.get("VILLE"));
                edt.setPays(record.get("PAYS"));
                edt.setEmail(record.get("EMAIL"));
                edt.setTelfixe(record.get("TELEPHONE"));
                edt.setTelportable(record.get("MOBILE"));
                edt.setEtab(record.get("ETAB_EN_COURS_PATRONYME"));
                edt.setEtabville(record.get("ETAB_EN_COURS_VILLE"));
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
    
    private void save(List<Etudiant> etudiants) {
        for (Etudiant etudiant : etudiants) {
            
        }
    }
    
    public String getCurrentTimeStamp() {
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyyMMdd_HH_mm_ss");
        Date now = new Date();
        String strDate = sdfDate.format(now);
        return strDate;
    }
}