package it.polito.tdp.libretto.model;

import java.util.*;

/**
 * Memorizza e gestisce un insieme di voti superati.
 * 
 * @author Vito
 *
 */
public class Libretto {

	private List<Voto> voti = new ArrayList<>();
	
	/**
	 * Crea un libretto nuovo (e vuoto)
	 */
	public Libretto() {
		super();
	}
	
	/**
	 * Copy constructor
	 * "Shallow" (copia superficiale), fa una copia dell'oggetto corrente, ma non fa una copia degli oggetti contenuti in esso
	 * 
	 * Alternativa sarebbe "Deep" (copia profonda), che costa piu' tempo ed ha oggetti 'diversi'
	 * @param lib
	 */
	public Libretto(Libretto lib) {
		super();
		this.voti.addAll(lib.voti);
	}

	/*
	/**
	 * Crea un libretto nuovo, vuoto
	 *
	public Libretto() {
		this.voti = new ArrayList<Voto>();
	}
	
	Se il costruttore non deve fare null'altro che
	creare le strutture dati su cui lavorano le variabili, allora
	e' piu' comodo accoppiare l'inizializzazione 
	della variabile con la dichiarazione della variabile stessa.
	Il costruttore ha senso di esistere se deve fare anche altre
	operazioni oltre l'inizializzazione di strutture dati.
	
	*/
	
	/**
	 * Aggiunge un nuovo voto al libretto
	 * 
	 * @param v Voto da aggiungere
	 * @return {@code true} se ha inserito il voto, {@code false} 
	 * se non l'ha inserito, perche' era in conflitto, oppure era un duplicato.
	 */
	public boolean add(Voto v) { // Il metodo add delega l'operazione di aggiunta all'ArrayList
		
		if(this.isConflitto(v) || this.isDuplicato(v)) {
			// non inserire il voto
			return false; // segnala al chiamante che non ha avuto successo
		}
		else {
			// inserisci il voto, perche' non e' in conflitto, ne' duplicato
			this.voti.add(v);
			return true;
		}
	}
	
	/**
	 * Dato un libretto, restituisce una stringa nella quale
	 * vi sono solamente i voti pari al valore specificato
	 * @param voto valore specificato
	 * @return stringa formattata per visualizzare il sotto-libretto
	 */
	public String stampaVotiUguali(int voto) {
		String s = "";
		for(Voto v : this.voti) {
			if(v.getVoto() == voto) {
				s += v.toString()+"\n";
			}
		}
		return s;
	}
	
	/**
	 * Genera un nuovo libretto, a partire da quello esistente,
	 * che conterra' esclusivamente i voti con votazione pari a quella specificata.
	 * @param voto votazione specificata
	 * @return nuovo Libretto "ridotto"
	 */
	public Libretto estraiVotiUguali(int voto) {
		Libretto nuovo = new Libretto();
		for(Voto v : this.voti) {
			if(v.getVoto() == voto) {
				nuovo.add(v);
			}
		}
		return nuovo;
	}
	
	public String toString() {
		
		String s = "";
		
		for(Voto v : this.voti) {
			s += v.toString()+"\n";
		}
		return s;
	}
	
	/**
	 * Dato il nome di un corso, ricerca se quell'esame esiste
	 * nel libretto, ed in caso affermativo restituisce l'oggetto
	 * {@link Voto} corrispondente.
	 * Se l'esame non esiste restituisce null.
	 * @param nomeCorso nome dell'esame da cercare
	 * @return il {@link Voto} corrispondente, oppure {@code null} se non esiste
	 */
	public Voto cercaNomeCorso(String nomeCorso) {
		/*
		for (Voto v : this.voti) {
			if(nomeCorso.equals(v.getCorso())) {
				return v;
			}
		}
		
		return null;
		*/
		
		int pos = this.voti.indexOf(new Voto(nomeCorso, 0, null)); 
		// Il metodo indexOf utilizza il metodo equals dell'oggetto in questione (Voto),
		// il quale, nel nostro caso, e' stato ridefinito come l'uguaglianza fra gli attributi corso
		
		if(pos==-1)
			return null;
		else
			return this.voti.get(pos);
	}
	
	/**
	 * Ritorna {@code true} se il corso specificato da {@code v}
	 * esiste nel libretto, con la stessa valutazione.
	 * Se non esiste, o se la valutazione e' diversa, ritorna {@false}.
	 * @param v il {@link Voto} da ricercare
	 * @return l'esistenza di un duplicato
	 */
	public boolean isDuplicato(Voto v) {
		Voto esiste = this.cercaNomeCorso(v.getCorso());
		
		if(esiste == null) // non l'ho trovato ==> non e' duplicato
			return false;
		
		/*
		if(esiste.getVoto()==v.getVoto())
			return true;
		else
			return false;
		*/
		
		return (esiste.getVoto() == v.getVoto());
	}
	
	/**
	 * Determina se esiste un voto con lo stesso nome corso,
	 * ma con valutazione diversa.
	 * @param v
	 * @return
	 */
	public boolean isConflitto(Voto v) {
		Voto esiste = this.cercaNomeCorso(v.getCorso());
		
		if(esiste == null)
			return false;
		
		return (esiste.getVoto() != v.getVoto());
	}
	
	/**
	 * Restituisce un nuovo libretto,  migliorando
	 * i voti del libretto attuale
	 * @return
	 */
	public Libretto creaLibrettoMigliorato() {
		Libretto nuovo = new Libretto();
		
		for(Voto v : this.voti) {
			// Voto v2 = v; // non sto creando un nuovo oggetto voto cosi', ma solo un nuovo riferimento
			
			Voto v2 = new Voto(v);
			// oppure Voto v2 = v.clone();
			
			// Voto v3 = new Voto(v.getCorso(), v.getVoto(), v.getData());
			// Non creo una copia come v3, perche' in un fututro potrei dover aggiungere un nuovo attributo 
			// alla classe Voto per esempio e dovrei cambiare righe di codice riguardanti il Voto, al di fuori
			// della sua classe.
			// E' il Voto che deve darmi tutte le informazioni che lo riguardano
			
			if(v2.getVoto()>= 24) {
				v2.setVoto(v2.getVoto()+2);
				if(v2.getVoto()>30)
					v2.setVoto(30);
			}
			else if (v2.getVoto()>=18) {
				v2.setVoto(v2.getVoto()+1);
			}
			
			nuovo.add(v2);
		}
		
		return nuovo;
	}
	
	/**
	 * riordina i voti presenti nel libretto corrente
	 * alfabeticamente per corso
	 */
	public void ordinaPerCorso() {
		Collections.sort(this.voti); // Utilizzo l'ordinamento naturale della classe Voto
		// OPPURE this.voti.sort(null) // .sort(null) ordina gli elementi secondo il comparatore naturale
	}
	
	public void ordinaPerVoto() {
		Collections.sort(this.voti, new ComparatoreVotiPerValutazione()); // Utilizzo il comparatore specificato
		// OPPURE this.voti.sort(new ComparatoreVotiPerValutazione());
		
		
		// COMPARATOR definito con ANONYMOUS INLINE CLASS
		// Posso evitare di creare una nuova classe Comparatore
		// generandola invece all'interno del metodo .sort()
		// Questo e' utile quando la classe in questione mi serve in un solo punto del programma
		// Se ho bisogno di richiamare il Comparatore in piu' punti del programma, mi conviene 
		// creare una cnuova classe
		this.voti.sort(new Comparator<Voto>() {

				@Override
				public int compare(Voto o1, Voto o2) {
					return o2.getVoto() - o1.getVoto();
				}
			}
		);
		
		// OPPURE
		// Comparator definito con una "lambda function"
		// equivalente al precedente metodo utilizzato
		// Posso utilizzare questa sintassi, perche' il metodo sort si aspetta un comparatore, e questo e' riassunto nel '->'
		this.voti.sort( (Voto o1, Voto o2) -> ( o2.getVoto() - o1.getVoto() ) );
	}
	
	/**
	 * Elimina dal libretto tutti i voti <24
	 */
	public void cancellaVotiScarsi() {
		
		// Metodo piu' efficiente
		Iterator<Voto> iter = this.voti.iterator();
		while(iter.hasNext()) {
			Voto v = iter.next();
			if(v.getVoto()<24)
				iter.remove();
		}
		
		// Metodo alternativo
		List<Voto> daRimuovere = new ArrayList<>();
		for(Voto v : this.voti) {
			if (v.getVoto()<24) {
				daRimuovere.add(v); // SALVO cio' che devo cancellare
			}
		}
		
		this.voti.removeAll(daRimuovere);
		
		/*
		for(Voto v : daRimuovere) {
			this.voti.remove(v); // Cancello cio' che ho salvato
			// ATT!! Qui non sto iterando la lista dalla quale rimuovo oggetti => questa operazione e' lecita
		}
		*/
	}
}
