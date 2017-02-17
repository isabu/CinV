package net.cinv.CV;

import net.cinv.Data.UIBConnection;

import com.franz.agraph.repository.AGRepositoryConnection;
import com.opensymphony.xwork2.ActionSupport;

public class Search extends ActionSupport {
	
	private String keywords = "";
	private String resultado;
	
	public String execute() throws Exception {

        //if (isInvalid(getKeyword())) return INPUT;
        if (keywords.isEmpty()) {
        	resultado = "";
        } else {
        	//AGRepositoryConnection con = UIBConnection.connect(true);
        	//resultado = "Resultados de la búsqueda...";
        	resultado = UIBConnection.testcon();
        }
        	
        return SUCCESS;
    }
	

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }
    
    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

}
