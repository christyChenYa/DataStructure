package lab11;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * An Adjacency List implementation of the Graph ADT for undirected graphs.
 * 
 * @author Grant Braught
 * @author Dickinson College
 * @version April 27, 2016
 * 
 * @param <V>
 *            the type of object associated with the vertices.
 * @param <E>
 *            the type of object associated with the edges.
 */
public class CS232DirectedAdjacencyListGraph<V, E> extends
CS232AbstractGraph<V, E> {

	/*
	 * Holds the list of edges leaving each vertex. So, index v contains a list
	 * of the edges for which v is the start index.
	 */
	protected LinkedList<Edge<E>>[] edgeLists;

	/**
	 * Construct a new AdjacencyListGraph with the specified number of vertices.
	 * 
	 * @param numVertices
	 *            the number of vertices in the graph.
	 */
	public CS232DirectedAdjacencyListGraph(int numVertices) {
		super(numVertices);

		edgeLists = (LinkedList<Edge<E>>[]) new LinkedList<?>[numVertices];
		for (int i = 0; i < edgeLists.length; i++) {
			edgeLists[i] = new LinkedList<Edge<E>>();
		}
	}

	/*
	 * Helper method that gets the Edge object associated with an edge.
	 */
	private Edge<E> getEdgeObject(int v1, int v2) {
		// Use an iterator to search the list of edges for v1
		LinkedList<Edge<E>> list = edgeLists[v1];
		Iterator<Edge<E>> it = list.iterator();
		while (it.hasNext()) {
			Edge<E> edge = it.next();
			if (edge.equals(new Edge<E>(v1, v2, null))) {
				// found it.
				return edge;
			}
		}
		// Edge is not in the graph.
		return null;
	}
	
	public void addEdge(int v1, int v2, E value) {
		checkVertices(v1, v2);
		if (v1 == v2) {
			throw new IllegalArgumentException(
					"Self-edges are not allowed: v1 cannot equal v2.");
		}
		if (value == null) {
			throw new IllegalArgumentException("Edge value cannot be null.");
		}
		Edge<E> edge = getEdgeObject(v1, v2);
		if (edge != null) {
			// edge is already in the graph, just replace its value.
			edge.edgeObject = value;
		} else {
			// edge not in the graph.
			// Add the edge to the list for v1.
			edgeLists[v1].add(new Edge<E>(v1, v2, value));
			numEdges++;
		}
	}


	public E getEdge(int v1, int v2) {
		checkVertices(v1, v2);
		Edge<E> edge = getEdgeObject(v1, v2);
		if (edge != null) {
			// edge is in the graph.
			return edge.edgeObject;
		} else {
			// edge not in the graph.
			return null;
		}
	}

	public E removeEdge(int v1, int v2) {
		checkVertices(v1, v2);
		// Use an iterator to search the list of edges for v1
		LinkedList<Edge<E>> list = edgeLists[v1];
		Iterator<Edge<E>> it = list.iterator();
		while (it.hasNext()) {
			Edge<E> edge = it.next();
			if (edge.equals(new Edge<E>(v1, v2, null))) {
				// found it, so use the iterator to remove it.
				it.remove();
				numEdges--;
				return edge.edgeObject;
			}
		}
		// edge not in the list, so also not in the graph.
		return null;
	}
	
	public ArrayList<Integer> getNeighbors(int v) {
		checkVertex(v);
		ArrayList<Integer> neighbors = new ArrayList<Integer>();
		// Use an iterator to get all of the edges departing v
		LinkedList<Edge<E>> list = edgeLists[v];
		Iterator<Edge<E>> it = list.iterator();
		while (it.hasNext()) {
			neighbors.add(it.next().endVertex);
		}
		return neighbors;
	}
	/**
	 * Get the in degree of vertex v.
	 * 
	 * @param v
	 *            the vertex of which to compute the in degree.
	 * @return the in degree of vertex v.
	 */
	public int inDegree(int v) {
		checkVertex(v);
		int in = 0;
		/*
		 * For every vertex, vi, go through the list of edges leaving vi looking
		 * for an edge going to vertex v...
		 */
		for (int vi = 0; vi < numVertices(); vi++) {
			LinkedList<Edge<E>> edgesLeavingVi = edgeLists[vi];
			Iterator<Edge<E>> it = edgesLeavingVi.iterator();
			boolean foundEdgeToV = false;
			while (it.hasNext() && !foundEdgeToV) {
				if (it.next().endVertex == v) {
					in++;
					/*
					 * Can only have a single edge from vi to v so once we find
					 * one, there is no need to go though the rest of the edges
					 * leaving vi.
					 */
					foundEdgeToV = true;
				}
			}
		}
		return in;
	}

	/**
	 * Class used to hold the information about each edge in the graph.
	 */
	protected static class Edge<E> {
		public E edgeObject;
		public int startVertex; // not strictly necessary, but seems nice.
		public int endVertex;

		public Edge(int sv, int ev, E obj) {
			edgeObject = obj;
			startVertex = sv;
			endVertex = ev;
		}

		/*
		 * Check if two edges are equal in a directed graph.
		 */
		public boolean equals(Edge<E> e) {
			return (startVertex == e.startVertex && endVertex == e.endVertex);
		}
	}
}