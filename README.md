# Libretto Universitario

_Obiettivo: ripasso sulle Collection (in particolare, `List`) e sulla gestione di oggetti e riferimenti._

Si realizzi un programma per la gestione semplificata dei voti della carriera universitaria.
Il programma si basa su una classe `Voto` (nome corso, voto ottenuto, data esame) 
ed una classe `Libretto` (che gestisce un elenco di `Voto`). La classe `Libretto` deve 
avere un metodo `add()` per il caricamento degli esami.

Implementare le seguenti funzionalità, e sviluppare un programma di test (classe `TestLibretto`, 
metodo `main`) che le attivi:

1. inserire nel `Libretto` un elenco di 10 oggetti `Voto` a piacere
2. stampare tutti i corsi in cui il voto è pari a 25
3. ricercare nel `Libretto` il voto di un esame, dato il nome del corso
4. creare un nuovo oggetto `Voto`, e verificare se tale valutazione esiste già  nel `Libretto` (stesso 
	esame con stesso voto)
5. creare un nuovo oggetto `Voto`, e verificare se esiste un conflitto con il `Libretto` (stesso 
	esame e voto diverso)
6. modificare il metodo `Libretto.add()` in modo da evitare di inserire valutazioni duplicate (stesso
	esame con stesso voto) o in conflitto
7. creare un libretto "migliorato" in cui ciascun voto maggiore o uguale di 18 viene incrementato
	di 1 punto, e ciascun voto maggiore o ugale di 24 viene incrementato di 2 punti (senza superare il 30).
	Tenere _separati_ due libretti, e stamparli _entrambi_.
8. stampare il libretto in ordine alfabetico di esame, ed in ordine numerico decrescente di voto
9. cancellare dal libretto tutti i voti inferiori a 24

_Nota 1_: pur nella sua semplicità, ciascun punto di questo esercizio permette di illustrare
delle "trappole" comuni che si incontrano nella gestione delle liste.

_Nota 2_: per il momento, la classe `Libretto` viene richiamata da un semplice `main` di 
prova (`TestLibretto.main`). Dovrà tuttavia essere progettata in modo da poter essere richiamata 
anche nel contesto di un'interfaccia grafica. A tal fine, creare le classi su indicate in un package 
dal nome `it.polito.tdp.libretto.model`.