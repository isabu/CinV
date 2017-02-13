package net.cinv.CV;

import com.opensymphony.xwork2.ActionSupport;

public class Search extends ActionSupport {
	
	public String execute() throws Exception {

        //if (isInvalid(getKeyword())) return INPUT;
        
        return SUCCESS;
    }
	
    private String keywords;

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

}
