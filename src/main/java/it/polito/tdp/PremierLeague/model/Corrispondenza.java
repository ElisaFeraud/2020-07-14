package it.polito.tdp.PremierLeague.model;

public class Corrispondenza {
     Team team;
     int peso;
	public Corrispondenza(Team team, int peso) {
		this.team = team;
		this.peso = peso;
	}
	public Team getTeam() {
		return team;
	}
	public void setTeam(Team team) {
		this.team = team;
	}
	public int getPeso() {
		return peso;
	}
	public void setPeso(int peso) {
		this.peso = peso;
	}
	@Override
	public String toString() {
		return team + " "+ peso+"\n";
	}
	
     
}
