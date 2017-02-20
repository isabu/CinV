package net.cinv.Data;

import java.util.ArrayList;
import java.util.List;

import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;

import com.franz.agraph.repository.AGCatalog;
import com.franz.agraph.repository.AGRepository;
import com.franz.agraph.repository.AGRepositoryConnection;
import com.franz.agraph.repository.AGServer;

public class UIBConnection {

	public static String SERVER_URL = "http://localhost:10035";
    public static String CATALOG_ID = "";
    public static String REPOSITORY_ID = "UIB2012";
    public static String USERNAME = "isalinas";
    public static String PASSWORD = "plus92";
    public static String TEMPORARY_DIRECTORY = "";
    
    
    public static AGRepositoryConnection connect(boolean close)
            throws Exception {
    	// Tests getting the repository up.
        println("\nStarting connect().");
        AGServer server = new AGServer(SERVER_URL, USERNAME, PASSWORD);
        try {
            println("Server version: " + server.getVersion());
            println("Server build date: " + server.getBuildDate());
            println("Server revision: " + server.getRevision());
            println("Available catalogs: " + server.listCatalogs());
        } catch (Exception e) {
            throw new Exception("Got error when attempting to connect to server at "
                                + SERVER_URL + ": " + e);
        }

        AGCatalog catalog = server.getCatalog(CATALOG_ID); // open catalog

        if (catalog == null) {
            throw new Exception("Catalog " + CATALOG_ID + " does not exist. Either "
                            + "define this catalog in your agraph.cfg or modify the CATALOG_ID "
                            + "in this tutorial to name an existing catalog.");
        }

        println("Available repositories in catalog " + 
                (catalog.getCatalogName()) + ": " + 
                catalog.listRepositories());
        closeAll();
        AGRepository repo = catalog.openRepository(REPOSITORY_ID);
        println("Got a repository.");
        repo.initialize();
        println("Initialized repository.");
        println("Repository is writable? " + repo.isWritable());
        AGRepositoryConnection conn = repo.getConnection();
        closeBeforeExit(conn);
        println("Got a connection.");
        println("Repository " + (repo.getRepositoryID()) +
                " is up! It contains " + (conn.size()) +
                " statements."              
                );
        List<String> indices = conn.listValidIndices();
        println("All valid triple indices: " + indices);
        indices = conn.listIndices();
        println("Current triple indices: " + indices);
        if (close) {
            // tidy up
        	conn.close();
        	repo.shutDown();
            return null;
        }
        return conn;
    }
    
	public static void println(Object x) {
	    System.out.println(x);
	}
	
	static void close(RepositoryConnection conn) {
        try {
            conn.close();
        } catch (Exception e) {
            System.err.println("Error closing repository connection: " + e);
            e.printStackTrace();
        }
    }
    
    private static List<RepositoryConnection> toClose = new ArrayList<RepositoryConnection>();
    
    /**
     * This is just a quick mechanism to make sure all connections get closed.
     */
    private static void closeBeforeExit(RepositoryConnection conn) {
        toClose.add(conn);
    }
	
	private static void closeAll() {
        while (toClose.isEmpty() == false) {
            RepositoryConnection conn = toClose.get(0);
            close(conn);
            while (toClose.remove(conn)) {}
        }
    }
	
	public static String testcon()
            throws Exception {
    	// Tests getting the repository up.
        String testcon = "Starting connect().";
        AGServer server = new AGServer(SERVER_URL, USERNAME, PASSWORD);
        try {
        	testcon = testcon.concat("<p>Server version: " + server.getVersion() + "</p>");
        	testcon = testcon.concat("<p>Server build date: " + server.getBuildDate() + "</p>");
        	testcon = testcon.concat("<p>Server revision: " + server.getRevision() + "</p>");
        	testcon = testcon.concat("<p>Available catalogs: " + server.listCatalogs() + "</p>");
        } catch (Exception e) {
            throw new Exception("Got error when attempting to connect to server at "
                                + SERVER_URL + ": " + e);
        }

        AGCatalog catalog = server.getCatalog(CATALOG_ID); // open catalog

        if (catalog == null) {
            throw new Exception("Catalog " + CATALOG_ID + " does not exist. Either "
                            + "define this catalog in your agraph.cfg or modify the CATALOG_ID "
                            + "in this tutorial to name an existing catalog.");
        }

        testcon = testcon.concat("<p>Available repositories in catalog " + 
                (catalog.getCatalogName()) + ": " + 
                catalog.listRepositories() + "</p>");
        closeAll();
        
        try {
	        AGRepository repo = catalog.openRepository(REPOSITORY_ID);
	        testcon = testcon.concat("<p>Got a repository.</p>");
	        repo.initialize();
	        testcon = testcon.concat("<p>Initialized repository.</p>");
	        testcon = testcon.concat("<p>Repository is writable? " + repo.isWritable() + "</p>");
	        AGRepositoryConnection conn = repo.getConnection();
	        closeBeforeExit(conn);
	        testcon = testcon.concat("<p>Got a connection.");
	        testcon = testcon.concat("<p>Repository " + (repo.getRepositoryID()) +
	                " is up! It contains " + (conn.size()) +
	                " statements." +
	                "</p>"
	                );
	        List<String> indices = conn.listValidIndices();
	        testcon = testcon.concat("<p>All valid triple indices: " + indices + "</p>");
	        indices = conn.listIndices();
	        testcon = testcon.concat("<p>Current triple indices: " + indices + "</p>");
	            // tidy up
	        	conn.close();
	        	repo.shutDown();
        } catch (RepositoryException e) {
        	testcon = testcon.concat("<p>RepositoryExcetion: " + e.getMessage());
        	throw new Exception("Got error when attempting to operate repository "
                    + e);
        }
        return testcon;
    }
	
}
