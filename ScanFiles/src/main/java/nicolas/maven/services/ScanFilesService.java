package nicolas.maven.services;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.IOException;
import java.io.FileWriter;   // Import the FileWriter class

/**
 * @author npaul
 *
 */

@Service("ScanFilesService")
public class ScanFilesService {

	//name correspond au nom du ficher passé en paramètre 
	private String name;

	public void setName(String name) {
		this.name = name;
	}

	//match est le texte qui doit être contenu dans un mot pour qu'il soit valide
	private String match;

	public void setMatch(String match) {
		this.match = match;
	}

	//outputFile est le nom du fichier output
	private File outputFile;

	//Creer le fichier output et définie la valeur de outputFile
	public void setOutputFile(){
		try {
			File outputFile = new File("Output-" + this.name);
			//On vérifie que le fichier n'existe pas déjà
			if (outputFile.createNewFile()) {
				this.outputFile = outputFile;
				System.out.println("Le fichier \"" + outputFile.getName() + "\" a bien été créé");
			} else {
				System.out.println("Le fichier \"" + outputFile.getName() + "\" existe déjà");
			}
		} catch (IOException e) {
			System.out.println("Erreur lors de la création du fichier output");
			e.printStackTrace();
		}
	}

	// Vérifie que le mot répond bien aux contraintes passé en paramètre
	public boolean matchWord(String word) {
		return word.indexOf(this.match) != -1;
	}

	//récupère les lignes du fichier passé en paramètre 
	public void fileRead() {
		try {

			File myObj = new File(this.name);
			Scanner myReader = new Scanner(myObj);
			//récupère les lignes une à une
			while (myReader.hasNextLine()) {
				String mot = myReader.nextLine();
				System.out.println("mot suivant : " + mot);
				
				//écrit le mot dans le fichier output
				this.fileWrite(mot);
			}
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("Erreur lors de la lecture du fichier");
			e.printStackTrace();
		}
	}
	
	// Ecris les mots passés en paramètres dans le fichier Output
	public void fileWrite(String word){
		try {
			
			// Vérification de la validité du mot
			if (matchWord(word)) {
				FileWriter myWriter = new FileWriter(this.outputFile, true);
				myWriter.write(word);
				myWriter.write("\r\n");
				myWriter.close();
			}
			else {
				System.out.println("Le mot " + word + " ne correspond pas ");
			}

		} catch (IOException e) {
			System.out.println("Erreur lors de l'ecriture dans le fichier output");
			e.printStackTrace();
		}

	}

}

