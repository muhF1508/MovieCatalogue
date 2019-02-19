package id.co.maminfaruq.moviecatalogueapi.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.lang.annotation.Annotation;

@Entity(tableName = "movie")
public class ResultsItemNow {

	@PrimaryKey
	@ColumnInfo(name = "id")
	@SerializedName("id")
	private int id;

	@ColumnInfo(name = "overview")
	@SerializedName("overview")
	private String overview;

	@ColumnInfo(name = "original_language")
	@SerializedName("original_language")
	private String originalLanguage;

	@ColumnInfo(name = "original_title")
	@SerializedName("original_title")
	private String originalTitle;

//	@SerializedName("video")
//	private boolean video;

//	@ColumnInfo(name = "title")
//	@SerializedName("title")
//	private String title;

//	@SerializedName("genre_ids")
//	private List<Integer> genreIds;

	@ColumnInfo(name = "poster_path")
	@SerializedName("poster_path")
	private String posterPath;

//	@SerializedName("backdrop_path")
//	private String backdropPath;

	@ColumnInfo(name = "release_date")
	@SerializedName("release_date")
	private String releaseDate;

	@ColumnInfo(name = "vote_average")
	@SerializedName("vote_average")
	private double voteAverage;

//	@SerializedName("popularity")
//	private double popularity;


//	@SerializedName("adult")
//	private boolean adult;

	@ColumnInfo(name = "vote_count")
	@SerializedName("vote_count")
	private int voteCount;

	public ResultsItemNow(int id, String overview, String originalLanguage, String originalTitle, String posterPath, String releaseDate, double voteAverage, int voteCount) {
		this.id = id;
		this.overview = overview;
		this.originalLanguage = originalLanguage;
		this.originalTitle = originalTitle;
		this.posterPath = posterPath;
		this.releaseDate = releaseDate;
		this.voteAverage = voteAverage;
		this.voteCount = voteCount;
	}

	public int getId() {
		return id;
	}

	public String getOverview() {
		return overview;
	}

	public String getOriginalLanguage() {
		return originalLanguage;
	}

	public String getOriginalTitle() {
		return originalTitle;
	}

	public String getPosterPath() {
		return posterPath;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public double getVoteAverage() {
		return voteAverage;
	}

	public int getVoteCount() {
		return voteCount;
	}
}