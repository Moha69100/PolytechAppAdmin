/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.polytech.model;

import com.polytech.dao.Etudiant;
import com.polytech.dao.manager.EtudiantManager;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Mohamed CHOUCHANE
 * Last Update : 20/11/2014
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
                try{
                    Etudiant edt = new Etudiant();

                    // Les champs Identifiant, Nom et prénom sont obligatoire (Enfin c'est mois qui les décidé :) )
                    if(this.isValid(record, "Identifiant") && this.isValid(record, "NOM") && this.isValid(record, "Prénom")) 
                    {
                        // Affectation des champs obligation
                        edt.setCandidid(Integer.parseInt(record.get("Identifiant")));   
                        edt.setNom(record.get("NOM"));
                        edt.setPrenom(record.get("Prénom"));

                        // Test de chaque colonne + affectation
                        if(this.isValid(record, "Civilité")) edt.setCivilite(record.get("Civilité"));
                        if(this.isValid(record, "DATE NAISSANCE")) edt.setDatenaissance(formatter.parse(record.get("DATE NAISSANCE")));
                        if(this.isValid(record, "ADRESSE")) edt.setAdresse(record.get("ADRESSE"));
                        if(this.isValid(record, "CODE POSTAL")) edt.setCp(record.get("CODE POSTAL"));
                        if(this.isValid(record, "VILLE")) edt.setVille(record.get("VILLE"));
                        if(this.isValid(record, "PAYS")) edt.setPays(record.get("PAYS"));
                        if(this.isValid(record, "EMAIL")) edt.setEmail(record.get("EMAIL"));
                        if(this.isValid(record, "TELEPHONE")) edt.setTelfixe(record.get("TELEPHONE"));
                        if(this.isValid(record, "MOBILE")) edt.setTelportable(record.get("MOBILE"));
                        if(this.isValid(record, "ETAB_EN_COURS_PATRONYME")) edt.setEtab(record.get("ETAB_EN_COURS_PATRONYME"));
                        if(this.isValid(record, "ETAB_EN_COURS_VILLE")) edt.setEtabville(record.get("ETAB_EN_COURS_VILLE"));
                        if(this.isValid(record, "SEB_LIBELLE")) edt.setBactype(record.get("SEB_LIBELLE"));
                        if(this.isValid(record, "Mention BAC")) edt.setBacmention(record.get("Mention BAC"));
                        if(this.isValid(record, "BCA_ANNEE")) edt.setBacannee(Integer.parseInt(record.get("BCA_ANNEE")));
                        if(this.isValid(record, "BAC_LIBELLE")) edt.setBacoption(record.get("BAC_LIBELLE"));
                        emps.add(edt);
                    }
                } 
                finally
                {

                }
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
            finally
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
    
    /**
     * Méthode permeetant de vérifier si un champs du csv est vide. La méthode IsSet de la classe CSVRecord n'est pas suffisante. 
     * Il faut aussi vérifier que le champs n'est pas vide 
     * @param record Record Csv
     * @param column Nom de la colonnes
     * @return Valid = true sinon false
     */
    private boolean isValid(CSVRecord record, String column){
        return (record.isSet(column) && !record.get(column).isEmpty());
    }
}
