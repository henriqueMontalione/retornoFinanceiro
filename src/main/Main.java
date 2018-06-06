package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class Main {

	private static final String SEPARADOR_CSV = ",";

	public static void readCsv() throws FileNotFoundException {

		System.out.println("Iniciando a leitura do arquivo...");

		String diretorioCSV = "C:/Users/mariana/Downloads/acoes.csv";
		BufferedReader reader = new BufferedReader(new FileReader(diretorioCSV));
		BufferedReader reader2 = new BufferedReader(new FileReader(diretorioCSV));

		String linhaDoMeuCsv; // "teste,teste2,teste3,test4";
		//List<String> linhas = new ArrayList<>();
		// Map<String, Double> map = new HashMap();
		Map<String, String> map = new HashMap<>();
		String acao;
		String close;
		String data;

		try {

			reader.readLine(); // skip first line
			reader2.readLine();

			while ((linhaDoMeuCsv = reader.readLine()) != null) {

				String[] colunasDoMeuCsv = linhaDoMeuCsv.split(SEPARADOR_CSV);
				acao = colunasDoMeuCsv[0];
				data = colunasDoMeuCsv[1];
				close = colunasDoMeuCsv[2];

				String dataClose = data + ", " + close;

				//linhas.add(linhaDoMeuCsv);
				map.put(acao, dataClose);

			}

			while ((linhaDoMeuCsv = reader2.readLine()) != null) {
				String[] colunasDoMeuCsv = linhaDoMeuCsv.split(SEPARADOR_CSV);
				acao = colunasDoMeuCsv[0];
				data = colunasDoMeuCsv[1];
				close = colunasDoMeuCsv[2];

				String dataClose = data + ", " + close;

				String[] values = map.get(acao).split(",");

				Double value = Double.parseDouble(values[1]);

				if (value < Double.parseDouble(close)) { // condição: substituir valor do mapa pelo maior que estiver no
															// arquivo(linha)
					map.put(acao, dataClose);
				}
			}

			System.out.println("Fechamento Maximo: " + map);

		} catch (Exception exception) {
			exception.printStackTrace();
		}

		System.out.println("Finalizando a leitura do arquivo...");
	}
	
	
	
	public static void getMenorFechamento() throws FileNotFoundException {
		
	}
	
	
	

	public static void main(String[] args) throws FileNotFoundException {
		readCsv();
	}

}
