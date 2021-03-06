package utilitaire;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import fenetre.Ecran;

public class Insert {
	private Ecran e;

	/**
	 * insert dans la bdd le fichier choisi
	 * @param e l'ecran ou le fichier est present
	 */
	public static void insert(Ecran e) {
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("GTS file", "gts");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(e);
		if (returnVal == JFileChooser.APPROVE_OPTION && chooser.getSelectedFile().getPath().endsWith(".gts")) {
			try {
				copy(chooser.getSelectedFile().getPath(), Parametre.workspace + "/model/" + chooser.getSelectedFile().getName());

				GestionBDD.insertModel("model/" + chooser.getSelectedFile().getName());
				e.getBarreAjout().refreshImg();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
	}
	
	/**
	 * insert dans la bdd les fichiers choisis
	 * @param liens la liste des fichiers choisis
	 * @param e l'ecran ou les fichiers choisis sont presents
	 */
	public static void insert(List<File> liens,Ecran e){
		for(File lien : liens){
			String nom;
			if(lien.getPath().endsWith(".gts")){
				try {
					if(lien.getPath().equals( Parametre.workspace + "/model/" + lien.getName())){
						copy(lien.getPath(), Parametre.workspace + "/model/" + lien.getName());
					}
					GestionBDD.insertModel("model/" + lien.getName());
					e.getBarreAjout().refreshImg();
				} catch (Exception ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}
			}
		}
		
	}
	
	/**
	 * copie un fichier
	 * @param source le chemin du fichier source
	 * @param destination le chemin de destination de la copie
	 * @throws IOException
	 */
	public static void copy(String source, String destination) throws IOException {

		FileInputStream fis = null;
		FileOutputStream fos = null;

		try {
			byte buffer[] = new byte[1024 * 64];
			int taille = 0;

			fis = new FileInputStream(source);
			fos = new FileOutputStream(destination);

			while ((taille = fis.read(buffer)) != -1) {
				fos.write(buffer, 0, taille);
			}

		} finally {

			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {

				}

			}

			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
				}
			}
		}
	}

}
