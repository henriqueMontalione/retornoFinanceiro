package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main2 {
	private static final String SEPARADOR_CSV = ",";
	private static String diretorioCSV = "C:/Users/henrique/Downloads/acoes.csv";
	private static String linhaDoMeuCsv;

	private static Map<String, String> map = new HashMap<>();
	
	private static String acao;
	private static String close;
	private static String data;
	private static String dataClose;
	private static String volume;
	private static BufferedReader reader;

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

				if (value < Double.parseDouble(close)) { // se valor mapa < arquivo(linha) atualiza map														
					map.put(acao, dataClose);
				}
			}
			System.out.println("Fechamento Maximo: " + map);
			reader.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
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

				if (value > Double.parseDouble(close)) {
					map.put(acao, dataClose);
				}
			}
			reader.close();
			System.out.println("Fechamento Minimo: " + map);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void getVolumeAverage() {
		try {
			reader = new BufferedReader(new FileReader(diretorioCSV));
			reader.readLine();
			
			Integer volumeOGXP3 = 0;
			Integer volumePETR4 = 0;
			Integer volumeVALE5 = 0;
			
			int contOGXP3 = 0;
			int contPETR4 = 0;
			int contVALE5 = 0;
			
			while ((linhaDoMeuCsv = reader.readLine()) != null) {
				String[] colunasDoMeuCsv = linhaDoMeuCsv.split(SEPARADOR_CSV);
				acao = colunasDoMeuCsv[0];
				data = colunasDoMeuCsv[1];
				close = colunasDoMeuCsv[2];
				volume = colunasDoMeuCsv[3];

				if ((acao.equals("OGXP3")) && (!volume.equals("0"))) {
					contOGXP3++;
					volumeOGXP3 += Integer.parseInt(volume);
				}
				if ((acao.equals("PETR4")) && (!volume.equals("0"))) {
					contPETR4++;
					volumePETR4 += Integer.parseInt(volume);
				}
				if ((acao.equals("VALE5")) && (!volume.equals("0"))) {
					contVALE5++;
					volumeVALE5 += Integer.parseInt(volume);
				}
			}
			Integer volumeAverageOGXP3 = volumeOGXP3 / contOGXP3;
			Integer volumeAveragePETR4 = volumePETR4 / contPETR4;
			Integer volumeAverageVALE5 = volumeVALE5 / contVALE5;
			
			System.out.println("volume medio ação OGXP3:" + volumeAverageOGXP3);
			System.out.println("volume medio ação PETR4:" + volumeAveragePETR4);
			System.out.println("volume medio ação VALE5:" + volumeAverageVALE5);

			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		LoadMap();
		getMaiorFechamento();
		getMenorFechamento();
		getVolumeAverage();
	}
}
