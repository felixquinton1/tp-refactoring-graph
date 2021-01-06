package org.acme.graph.model;

import java.util.List;

import com.bedatadriven.jackson.datatype.jts.serialization.GeometrySerializer;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;

/**
 * 
 * Un arc matérialisé par un sommet source et un sommet cible
 * 
 * @author MBorne
 *
 */
public class Edge {
	
	@JsonIdentityInfo(
        generator=ObjectIdGenerators.PropertyGenerator.class, 
        property="id"
	)
    
	
	/**
	 * Identifiant de l'arc
	 */
	private String id;

	/**
	 * Sommet initial
	 */
	private Vertex source;

	/**
	 * Sommet final
	 */
	private Vertex target;

	private LineString geometry;

	
	public Edge(Vertex source,Vertex target) {
		this.source = source;
		this.target = target;
		GeometryFactory gf = new GeometryFactory();
        this.geometry = (LineString)gf.createLineString(new Coordinate[] {
                getSource().getCoordinate(),
                getTarget().getCoordinate()
            });
		source.getOutEdges().add(this);
		target.getInEdges().add(this);
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public Vertex getTarget() {
		return target;
	}
	
	@JsonIdentityReference(alwaysAsId=true)
    public Vertex getSource() {
        return source;
    }
	

	/**
	 * dijkstra - coût de parcours de l'arc (distance géométrique)
	 * 
	 * @return
	 */
	public double getCost() {
		return geometry.getLength();
	}

	public void setGeometry(LineString geometry) {
		this.geometry = geometry;
	}

	@Override
	public String toString() {
		return id + " (" + source + "->" + target + ")";
	}
	
	
	@JsonSerialize(using = GeometrySerializer.class)
	public LineString getGeometry() {
        return geometry;
    }

}
