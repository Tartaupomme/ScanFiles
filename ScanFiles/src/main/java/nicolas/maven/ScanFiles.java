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
		
		// r�cup�ration du nom du fichier � traiter
		Scanner textInput = new Scanner(System.in);
		System.out.println("Saisissez le nom du fichier � traiter");

		String name = textInput.next();
		service.setName(name);
		
		
		//r�cup�ration de la chaine de caract�re 
		System.out.println("Saisissez le texte que doivent contenir les mots filtr�s");

		String match = textInput.next();
		service.setMatch(match);

		//Initialisation du fichier output
		service.setOutputFile();

		//lecture du fichier et traitement
		service.fileRead();

		System.out.println("Traitement termin�");

	}
}