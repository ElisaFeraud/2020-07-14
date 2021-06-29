package it.polito.tdp.PremierLeague.model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import it.polito.tdp.PremierLeague.db.PremierLeagueDAO;


public class Model {
	PremierLeagueDAO dao;
	Map<Integer,Team> idMap;
	Graph<Team,DefaultWeightedEdge> grafo;
	public Model() {
		dao = new PremierLeagueDAO();
		idMap = new HashMap<>();
		dao.listAllTeams(idMap);
		dao.addVittorie(idMap);
		// dao.addSconfitta(idMap);
		dao.addPareggio(idMap);
		dao.getVittorieFuori(idMap);
		dao.addPareggioFuori(idMap);
		 
	}
	public void creaGrafo() {
		 this.grafo = new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);
		 Graphs.addAllVertices(this.grafo, idMap.values());
		 
		 for(Team t1: this.grafo.vertexSet()) {
			 for(Team t2: this.grafo.vertexSet()) {
				 int punteggio1 = t1.getVittorie()+t1.getPareggio() + t1.getPareggioFuori() +t1.getVittorieFuoriCasa();
				 if(!t1.getTeamID().equals(t2.getTeamID()) ) {
					 int punteggio2 = t2.getVittorie()+t2.getPareggio()+ t2.getPareggioFuori()+ t2.getVittorieFuoriCasa();
					// System.out.println(""+punteggio1+"  "+punteggio2);
					if(Math.abs(punteggio1-punteggio2)!=0) {
					 if(punteggio1>punteggio2) {
						DefaultWeightedEdge e = this.grafo.getEdge(t1,t2);
			    			if(e==null) {
			    				Graphs.addEdgeWithVertices(grafo,t1,t2,punteggio1-punteggio2);
			    			    
			    			}
			 		 }
					 else if(punteggio2>punteggio1) {
						 DefaultWeightedEdge e = this.grafo.getEdge(t2,t1);
			    			if(e==null) {
			    				Graphs.addEdgeWithVertices(grafo,t2,t1,punteggio2-punteggio1);
			    			    
			    			}
				 }}
					
			 }
		 }}
	}
	public List<Team> getAllTeams() {
		return dao.getAllTeams();
	}
	
	 public String infoGrafo() {
		 return "Grafo creato con "+ this.grafo.vertexSet().size()+ " vertici e " + this.grafo.edgeSet().size()+" archi\n";
	 }
public List<Corrispondenza> getVerticiAdiacentiPeggiori(Team team){
		 
		 List<Team> result = Graphs.neighborListOf(this.grafo, team);
		List<Corrispondenza> vicini = new LinkedList<>();
		for(DefaultWeightedEdge d: this.grafo.edgesOf(team)) {
			Team team2=null;			
		if(this.grafo.getEdgeSource(d).equals(team) && !this.grafo.getEdgeTarget(d).equals(team))
				 team2 =this.grafo.getEdgeTarget(d);
			  int peso =  (int) this.grafo.getEdgeWeight(d);
			  Corrispondenza c= new Corrispondenza(team2,peso);
			  if(team2!=null)
			  vicini.add(c);
			 
		}
		 vicini.sort(new CorrispondenzaPerPeso());
		 return  vicini;
	 }
public List<Corrispondenza> getVerticiAdiacentiMigliori(Team team){
	 
	 List<Team> result = Graphs.neighborListOf(this.grafo, team);
	List<Corrispondenza> vicini = new LinkedList<>();
	for(DefaultWeightedEdge d: this.grafo.edgesOf(team)) {
		Team team2=null;			
	if(!this.grafo.getEdgeSource(d).equals(team) && this.grafo.getEdgeTarget(d).equals(team))
			 team2 =this.grafo.getEdgeSource(d);
		  int peso =  (int) this.grafo.getEdgeWeight(d);
		  Corrispondenza c= new Corrispondenza(team2,peso);
		  if(team2!=null)
		  vicini.add(c);
		 
	}
	 vicini.sort(new CorrispondenzaPerPeso());
	 return  vicini;
}
}

