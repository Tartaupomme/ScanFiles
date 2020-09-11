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

	//name correspond au nom du ficher pass� en param�tre 
	private String name;

	public void setName(String name) {
		this.name = name;
	}

	//match est le texte qui doit �tre contenu dans un mot pour qu'il soit valide
	private String match;

	public void setMatch(String match) {
		this.match = match;
	}

	//outputFile est le nom du fichier output
	private File outputFile;

	//Creer le fichier output et d�finie la valeur de outputFile
	public void setOutputFile(){
		try {
			File outputFile = new File("Output-" + this.name);
			//On v�rifie que le fichier n'existe pas d�j�
			if (outputFile.createNewFile()) {
				this.outputFile = outputFile;
				System.out.println("Le fichier \"" + outputFile.getName() + "\" a bien �t� cr��");
			} else {
				System.out.println("Le fichier \"" + outputFile.getName() + "\" existe d�j�");
			}
		} catch (IOException e) {
			System.out.println("Erreur lors de la cr�ation du fichier output");
			e.printStackTrace();
		}
	}

	// V�rifie que le mot r�pond bien aux contraintes pass� en param�tre
	public boolean matchWord(String word) {
		return word.indexOf(this.match) != -1;
	}

	//r�cup�re les lignes du fichier pass� en param�tre 
	public void fileRead() {
		try {

			File myObj = new File(this.name);
			Scanner myReader = new Scanner(myObj);
			//r�cup�re les lignes une � une
			while (myReader.hasNextLine()) {
				String mot = myReader.nextLine();
				System.out.println("mot suivant : " + mot);
				
				//�crit le mot dans le fichier output
				this.fileWrite(mot);
			}
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("Erreur lors de la lecture du fichier");
			e.printStackTrace();
		}
	}
	
	// Ecris les mots pass�s en param�tres dans le fichier Output
	public void fileWrite(String word){
		try {
			
			// V�rification de la validit� du mot
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

