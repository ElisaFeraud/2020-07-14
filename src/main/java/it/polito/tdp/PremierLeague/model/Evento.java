package it.polito.tdp.PremierLeague.model;

public class Evento implements Comparable<Evento>{
      
	enum EventType {
		VITTORIA, //la squadra del reporter ha vinto
		SCONFITTA, //la squadra del reporter ha perso
		PAREGGIO //la squadra del reporter ha pareggiato
		};
	
	private int T;
	private EventType esitoPartita;
	private Team squadra;
	public Evento(int t, EventType esitoPartita, Team squadra) {
		T = t;
		this.esitoPartita = esitoPartita;
		this.squadra = squadra;
	}
	public int getT() {
		return T;
	}
	public void setT(int t) {
		T = t;
	}
	public EventType getEsitoPartita() {
		return esitoPartita;
	}
	public void setEsitoPartita(EventType esitoPartita) {
		this.esitoPartita = esitoPartita;
	}
	public Team getSquadra() {
		return squadra;
	}
	public void setSquadra(Team squadra) {
		this.squadra = squadra;
	}
	@Override
	public int compareTo(Evento other) {
		// TODO Auto-generated method stub
		return this.T-other.T;
	}
	
	
	
}
