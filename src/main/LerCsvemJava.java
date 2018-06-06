package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.Normalizer;

public class TestandoBuffered {

	private static final String SEPARADOR_CSV = ";";
	

	public static void lendo2() throws FileNotFoundException {

		System.out.println("Iniciando a leitura do arquivo...");
		
		String diretorioCSV = "C:/Users/henrique.montalione/Downloads/ss.csv";
		
		BufferedReader reader = new BufferedReader(new FileReader(diretorioCSV));
		
		String linhaDoMeuCsv ; // "teste,teste2,teste3,test4";
		
		try {
			while((linhaDoMeuCsv = reader.readLine()) != null){
				String[] colunasDoMeuCsv = linhaDoMeuCsv.split(SEPARADOR_CSV);
				int colunaCPF = colunasDoMeuCsv.length - 2;
				String penultima = colunasDoMeuCsv[colunaCPF];
				//String colunaLimpa = Long.valueOf(normalize(penultima)).toString();
				String colunaLimpa = removeLeadingZero(penultima);
				System.out.println(colunaLimpa);				
			}
		} catch(Exception exception){
			exception.printStackTrace();
		}
		
		System.out.println("Finalizando a leitura do arquivo...");
		
	}

	public static void main(String[] args) throws IOException {
		lendo2();
	}

	public static String removeLeadingZero(String value){
		return normalize(value).replaceAll("^0*", "");
	}
	
	public static String normalize(String value) {
        return Normalizer.normalize(value, Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}]", "").replaceAll("[^0-9A-Za-z]", "");
    }
	
}
