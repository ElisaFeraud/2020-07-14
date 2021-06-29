package it.polito.tdp.PremierLeague.model;

public class Team {
	Integer teamID;
	String name;
    int punteggio;
    int vittorie;
    int sconfitte;
    int pareggio;
    int vittorieFuoriCasa;
    int pareggioFuori;
	public Team(Integer teamID, String name) {
		super();
		this.teamID = teamID;
		this.name = name;
	}
	
	public Integer getTeamID() {
		return teamID;
	}
	public void setTeamID(Integer teamID) {
		this.teamID = teamID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	

	@Override
	public String toString() {
		return name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((teamID == null) ? 0 : teamID.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Team other = (Team) obj;
		if (teamID == null) {
			if (other.teamID != null)
				return false;
		} else if (!teamID.equals(other.teamID))
			return false;
		return true;
	}
	
	public int getPunteggio() {
		return punteggio;
	}

	public int getVittorie() {
		return vittorie;
	}

	public void setVittorie(int vittorie) {
		this.vittorie= vittorie;
	}

	public int getSconfitte() {
		return sconfitte;
	}

	public void setSconfitte(int sconfitte) {
		this.sconfitte = sconfitte;
	}

	public int getPareggio() {
		return pareggio;
	}

	public void setPareggio(int pareggio) {
		this.pareggio =pareggio;
	}

	public void calcolaPunteggio() {
		punteggio = this.vittorie +this.pareggio;
	}

	public int getVittorieFuoriCasa() {
		return vittorieFuoriCasa;
	}

	public void setVittorieFuoriCasa(int vittorieFuoriCasa) {
		this.vittorieFuoriCasa = vittorieFuoriCasa;
	}

	public int getPareggioFuori() {
		return pareggioFuori;
	}

	public void setPareggioFuori(int pareggioFuori) {
		this.pareggioFuori = pareggioFuori;
	}
	
	
}
