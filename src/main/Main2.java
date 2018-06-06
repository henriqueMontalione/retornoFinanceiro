package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Main2 {
	private static String diretorioCSV = "C:/Users/mariana/Downloads/acoes.csv";
	private static String linhaDoMeuCsv; // "teste,teste2,teste3,test4";

	private static Map<String, String> map = new HashMap<>();
	private static String acao;
	private static String close;
	private static String data;
	private static String dataClose;
	private static BufferedReader reader;

	private static final String SEPARADOR_CSV = ",";

	public static void LoadMap() {
		try {
			reader = new BufferedReader(new FileReader(diretorioCSV));
			reader.readLine();

			while ((linhaDoMeuCsv = reader.readLine()) != null) {

				String[] colunasDoMeuCsv = linhaDoMeuCsv.split(SEPARADOR_CSV);
				acao = colunasDoMeuCsv[0];
				data = colunasDoMeuCsv[1];
				close = colunasDoMeuCsv[2];
				dataClose = data + ", " + close;

				map.put(acao, dataClose);
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(map);
	}

	public static void getMaiorFechamento() throws FileNotFoundException {
		try {
			reader = new BufferedReader(new FileReader(diretorioCSV));
			reader.readLine();

			while ((linhaDoMeuCsv = reader.readLine()) != null) {
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
			reader.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}

		

		System.out.println("Finalizando a leitura do arquivo...");
	}

	public static void getMenorFechamento() throws FileNotFoundException {
		try {
			reader = new BufferedReader(new FileReader(diretorioCSV));
			reader.readLine();

			while ((linhaDoMeuCsv = reader.readLine()) != null) {
				String[] colunasDoMeuCsv = linhaDoMeuCsv.split(SEPARADOR_CSV);
				acao = colunasDoMeuCsv[0];
				data = colunasDoMeuCsv[1];
				close = colunasDoMeuCsv[2];

				String dataClose = data + ", " + close;

				String[] values = map.get(acao).split(",");

				Double value = Double.parseDouble(values[1]);

				if (value > Double.parseDouble(close)) { // condição: substituir valor do mapa pelo maior que estiver no
															// arquivo(linha)
					map.put(acao, dataClose);
				}
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("Fechamento Minimo: " + map);

		System.out.println("Finalizando a leitura do arquivo...");
		
	}
	
	
	
	
	
	
	
	public static void main(String[] args) throws FileNotFoundException {
		LoadMap();
		getMaiorFechamento();
		getMenorFechamento();
	}

}
