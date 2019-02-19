package id.co.maminfaruq.moviecatalogueapi.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseNow{

	@SerializedName("dates")
	private Dates dates;

	@SerializedName("page")
	private int page;

	@SerializedName("total_pages")
	private int totalPages;

	@SerializedName("results")
	private List<ResultsItemNow> results;

	@SerializedName("total_results")
	private int totalResults;

	public void setDates(Dates dates){
		this.dates = dates;
	}

	public Dates getDates(){
		return dates;
	}

	public void setPage(int page){
		this.page = page;
	}

	public int getPage(){
		return page;
	}

	public void setTotalPages(int totalPages){
		this.totalPages = totalPages;
	}

	public int getTotalPages(){
		return totalPages;
	}

	public void setResults(List<ResultsItemNow> results){
		this.results = results;
	}

	public List<ResultsItemNow> getResults(){
		return results;
	}

	public void setTotalResults(int totalResults){
		this.totalResults = totalResults;
	}

	public int getTotalResults(){
		return totalResults;
	}

	public ResponseNow(Integer page, Integer totalResults, Integer totalPages, List<ResultsItemNow> movieResults) {
		this.page = page;
		this.totalResults = totalResults;
		this.totalPages = totalPages;
		results = movieResults;
	}
}