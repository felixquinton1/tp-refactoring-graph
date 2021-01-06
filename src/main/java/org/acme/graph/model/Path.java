package org.acme.graph.model;

import java.util.ArrayList;
import java.util.List;

public class Path {
	private List<Edge> listEdge = new ArrayList<>();
	
	public Path(){}
	
	public List<Edge> getListEdge() {
		return listEdge;
	}
	
	public void addEdge(Edge edge) {
        this.getListEdge().add(edge);
    }
	
}
