package it.polito.tdp.PremierLeague.model;

import java.util.List;
import java.util.PriorityQueue;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;

public class Simulatore {
       //coda degli eventi
	PriorityQueue<Evento> queue;
	  //modello del mondo
	List<Match> partiteTotali;
	Graph<Match,DefaultWeightedEdge> grafo;
	  //parametri di input
	int Nreporter;
	int Xsoglia;
	int T=-1;
	//public 
	public Simulatore(Graph<Match,DefaultWeightedEdge> grafo, int N, int X ) {
		this.grafo = grafo;
		this.Nreporter=N;
		this.Xsoglia=X;
	}
	public void init() {
		queue = new PriorityQueue<Evento>();
	//	this.queue.add(new Evento(T,Nreport))
	}
	public void run() {
		
	}
	
}
