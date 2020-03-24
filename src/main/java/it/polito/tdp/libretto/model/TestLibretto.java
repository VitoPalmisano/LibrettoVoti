package it.polito.tdp.libretto.model;

import java.time.LocalDate;

public class TestLibretto {

	Libretto lib;
	
	private void run() {
		lib = new Libretto(); // crea libretto vuoto
		
		// 1. Inserire alcuni voti
		Voto v1 = new Voto("Tecniche di Programmazione", 30, LocalDate.of(2020, 06, 15));
		Voto v2 = new Voto("Analisi II", 18, LocalDate.of(2020, 06, 28));
		
		lib.add(v1);
		lib.add(v2);
		if (lib.add(new Voto("Economia", 24, LocalDate.of(2020, 02, 14))) == false)
			System.out.println("Errore nell'inserimento di economia");
		
		System.out.println(this.lib);
		
		// 2. Stampa tutti i voti uguali a 28
		System.out.println(this.lib.stampaVotiUguali(28)); // e' una stringa e come tale puoì essere solo stampata
		
		System.out.println(this.lib.estraiVotiUguali(28)); // e' un pezzo di libretto, approccio, che in esercizi 
		                                                   // piu' complessi, ci tornera' utile
		// Cerca un esame superato, conoscendo il nome del corso
		
		String nomeCorso = "Analisi II";
		Voto votoAnalisi = lib.cercaNomeCorso(nomeCorso);
		System.out.println("Il voto di "+nomeCorso+" e' "+votoAnalisi.getVoto());
		
		Voto votoMancante = lib.cercaNomeCorso("Fisica 1");
		System.out.println("Il voto di Fisica 1 e' "+votoMancante);
		
		// 4. 5. 6. Verifica voti duplicati o voti in conflitto
		Voto economia2 = new Voto("Economia", 24, LocalDate.now());
		Voto economia3 = new Voto("Economia", 21, LocalDate.now());
		
		System.out.println("Economia con 24 e' duplicato: "
				+lib.isDuplicato(economia2)+"/ conflitto: "
				+lib.isConflitto(economia2));
		
		System.out.println("Economia con 21 e' duplicato: "
				+lib.isDuplicato(economia3)+"/ conflitto: "
				+lib.isConflitto(economia3));
		
		// 7. Migliora libretto
		
		Libretto migliorato = lib.creaLibrettoMigliorato();
		System.out.println("Miglioramento del libretto");
		System.out.println(lib);
		System.out.println(migliorato);
		
		// 8. Stampa in ordine alfabetico
		
		Libretto alfabetico = new Libretto(lib);
		alfabetico.ordinaPerCorso();
		System.out.println(alfabetico);
		
		// 8. Stampa in ordine di voto
		
		Libretto votiDecrescenti = new Libretto(lib);
		votiDecrescenti.ordinaPerVoto();
		System.out.println(votiDecrescenti);
		
		// 8. Elimina voti bassi
		lib.cancellaVotiScarsi();
		System.out.println(lib);
	}
	
	public static void main(String[] args) {
		
		/* Regola: NON SI LAVORA ALL'INTERNO DEL MAIN
		Lavorare all'inteerno di un metodo statico e' abbastanza limitativo, perche'
		dall'interno di questo metodo posso richiamare solo altri meotdi statici di altre classi
		e questo a volte puo' esserte scomodo
		*/
		
		TestLibretto test = new TestLibretto();
		test.run();

	}

}
