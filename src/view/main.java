package view;

import java.util.concurrent.Semaphore;

import controller.formula1;

public class main {


		
		 public static String[] infVoltas = { "Escuderia 1 | Carro 1 | Melhor volta: ",
				"Escuderia 1 | Carro 2 | Melhor volta: ", "Escuderia 2 | Carro 1 | Melhor volta: ",
				"Escuderia 2 | Carro 2 | Melhor volta: ", "Escuderia 3 | Carro 1 | Melhor volta: ",
				"Escuderia 3 | Carro 2 | Melhor volta: ", "Escuderia 4 | Carro 1 | Melhor volta: ",
				"Escuderia 4 | Carro 2 | Melhor volta: ", "Escuderia 5 | Carro 1 | Melhor volta: ",
				"Escuderia 5 | Carro 2 | Melhor volta: ", "Escuderia 6 | Carro 1 | Melhor volta: ",
				"Escuderia 6 | Carro 2 | Melhor volta: ", "Escuderia 7 | Carro 1 | Melhor volta: ",
				"Escuderia 7 | Carro 2 | Melhor volta: " };
		
		 public static int[] NVoltas = new int[14];

			public static void main(String[] args) {

		
			int largadaCar = 5;
			int carrosnaEscuderia = 1;
			Semaphore semaforoCorrida = new Semaphore(largadaCar);
			Semaphore semaforoEscuderia = new Semaphore(carrosnaEscuderia);

			for (int idEscuderia = 1; idEscuderia < 8; idEscuderia++) {
				Thread escuderia = new formula1(idEscuderia, semaforoCorrida, semaforoEscuderia);
				escuderia.start();
			}
	}
}
