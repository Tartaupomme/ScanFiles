package nicolas.maven;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import nicolas.maven.services.ScanFilesService;
import java.util.Scanner;

public class ScanFiles {

	@SuppressWarnings("resource")
	public static void main(String[] args) {

		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		ScanFilesService service = (ScanFilesService) context.getBean("ScanFilesService");
		
		// récupération du nom du fichier à traiter
		Scanner textInput = new Scanner(System.in);
		System.out.println("Saisissez le nom du fichier à traiter");

		String name = textInput.next();
		service.setName(name);
		
		
		//récupération de la chaine de caractère 
		System.out.println("Saisissez le texte que doivent contenir les mots filtrés");

		String match = textInput.next();
		service.setMatch(match);

		//Initialisation du fichier output
		service.setOutputFile();

		//lecture du fichier et traitement
		service.fileRead();

		System.out.println("Traitement terminé");

	}
}