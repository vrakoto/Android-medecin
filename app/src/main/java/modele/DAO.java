package modele;


import com.example.sio.android_medecin.MainActivity;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class DAO {

    private static final String urlDep = "http://gaemedecins.appspot.com/Controleur/medParDep/listeDep";
    private static final String urlMed = "http://gaemedecins.appspot.com/Controleur/medParDep/listeMed/";
    public static List<String> getLesDeps() {
        List<String> lesDeps = new ArrayList<String>();
        try {
            URL myURL = new URL(urlDep);
            //création de l'arbre DOM
            Document doc;
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            doc = db.parse(myURL.openStream());
            Element racine = doc.getDocumentElement();
            NodeList listeDep = racine.getElementsByTagName("Departement");
            // récup des départements
            for (int i = 0; i < listeDep.getLength(); i++) {
                Node n = listeDep.item(i);
                lesDeps.add(n.getTextContent().trim());
            }

        } catch (SAXException ex) {
            Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, null, ex);

        }

        return lesDeps;
    }

    public static List<Med> getLesMeds(String dep) {
        List<Med> lesMeds = new ArrayList<Med>();
        try {
            URL myURL = new URL(urlMed + dep);
            //création de l'arbre DOM
            Document doc;
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            doc = db.parse(myURL.openStream());
            Element racine = doc.getDocumentElement();
            NodeList listeMed = racine.getElementsByTagName("Medecin");
            // récup des départements
            for (int i = 0; i < listeMed.getLength(); i++) {
                String nom = null;
                String prenom = null;
                String adresse = null;
                String tel = null;
                String spe = null;
                Node medecin = listeMed.item(i);
                NodeList lesProprietes = medecin.getChildNodes();
                for (int j = 0; j < lesProprietes.getLength(); j++) {
                    if (lesProprietes.item(j).getNodeName().equals("nom")) {
                        nom = lesProprietes.item(j).getTextContent().trim();
                    } else if (lesProprietes.item(j).getNodeName().equals("prenom")) {
                        prenom = lesProprietes.item(j).getTextContent().trim();
                    } else if (lesProprietes.item(j).getNodeName().equals("adresse")) {
                        adresse = lesProprietes.item(j).getTextContent().trim();
                    } else if (lesProprietes.item(j).getNodeName().equals("specialite")) {
                        spe = lesProprietes.item(j).getTextContent().trim();
                    } else if (lesProprietes.item(j).getNodeName().equals("tel")) {
                        tel = lesProprietes.item(j).getTextContent().trim();
                    }

                }
                lesMeds.add(new Med(nom, prenom, adresse, tel, spe, null));
            }

        } catch (SAXException ex) {
            Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, null, ex);

        }

        return lesMeds;
    }
}