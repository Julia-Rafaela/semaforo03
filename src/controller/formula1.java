package controller;

import java.util.concurrent.Semaphore;

import view.main;

public class formula1 extends Thread {
	private int idEscuderia;
	private Semaphore semaforoCorrida;
	private Semaphore semaforoEscuderia;
	public static int carrosnaespera = 0;

	public formula1 (int idEscuderia, Semaphore semaforoEscuderia, Semaphore semaforoCorrida) {
		this.idEscuderia = idEscuderia;
		this.semaforoCorrida= semaforoCorrida;
		this.semaforoEscuderia = semaforoEscuderia;
	}

	@Override
	public void run() {
// vai fazer duas vezes esse processo, enquanto um carro corre o outro espera
		for (int carro = 1; carro < 3; carro++) {
			try {
				semaforoCorrida.acquire();
				CarroCorrendo(carro);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				semaforoCorrida.release();
				System.out.println("O carro " + carro + " da escuderia " + idEscuderia + " saiu da pista");
				carrosnaespera++;
			}
		}
		if (carrosnaespera == 14) {
			Ordenação();
		}
	}

	private void CarroCorrendo(int carro) {

		System.out.println("O carro " + carro + " da escuderia " + idEscuderia + " está na pista");
		for (int i = 1; i < 4; i++) {
			int tempVolt = (int) ((Math.random() * 150) + 60);
			try {
				sleep(tempVolt * 20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			System.out.println("Escuderia: " + idEscuderia + " Carro: " + carro + " Volta: " + i + " Tempo: "
					+ tempVolt + " segundos");
			try {
				semaforoEscuderia.acquire();
				if (tempVolt < main.NVoltas[(2 * idEscuderia) - carro] //dois carros em cada escudeira, mas um não corre
						|| main.NVoltas[(2 * idEscuderia) - carro] == 0) {
					main.NVoltas[(2 * idEscuderia - 2 + carro) - 1] = tempVolt;
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				semaforoEscuderia.release();
			}

		}
	}

	public void Ordenação() {
		int aux;
		int auxiliar;
		for (int i = 0; i < 13; i++) {
			for (int j = i + 1; j < 14; j++) {
				if (main.NVoltas[i] > main.NVoltas[j]) {
					aux = main.NVoltas[i];
					main.NVoltas[i] = main.NVoltas[j];
					main.NVoltas[j] = aux;
					auxiliar = main.NVoltas[i];
					main.NVoltas[i] = main.NVoltas[j];
					main.NVoltas[j] = auxiliar;
				}
			}
		}
		for (int i = 0; i < 14; i++) {
			System.out.println("Posição " + (i + 1) + ": " + main.NVoltas[i] + main.NVoltas[i] + " segundos");
		}
	}

}
