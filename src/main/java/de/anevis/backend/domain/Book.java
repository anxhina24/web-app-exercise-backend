package de.anevis.backend.domain;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.*;

@Entity
@Table(name = "books")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@SuppressWarnings("unused")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String title;

	@Column(nullable = false)
	private Integer firstPublishYear;

	@Column(nullable = false)
	private Integer numberOfPagesMedian;

	@JsonProperty("small_cover")
	@Column(nullable = false)
	private String smallCover;

	@JsonProperty("medium_cover")
	@Column(nullable = false)
	private String mediumCover;

	@JsonProperty("large_cover")
	@Column(nullable = false)
	private String largeCover;

	@Column(nullable = false)
	private String authorName;

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getFirstPublishYear() {
		return firstPublishYear;
	}

	public void setFirstPublishYear(Integer firstPublishYear) {
		this.firstPublishYear = firstPublishYear;
	}

	public Integer getNumberOfPagesMedian() {
		return numberOfPagesMedian;
	}

	public void setNumberOfPagesMedian(Integer numberOfPagesMedian) {
		this.numberOfPagesMedian = numberOfPagesMedian;
	}

	public BookCovers getCovers() {
		return new BookCovers(smallCover, mediumCover, largeCover);
	}

	public void setCovers(Object covers) {
		if (covers instanceof Map) {
			Map<String, String> coversMap = (Map<String, String>) covers;
			this.smallCover = coversMap.get("small_cover");
			this.mediumCover = coversMap.get("medium_cover");
			this.largeCover = coversMap.get("large_cover");
		}
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	@Override
	public String toString() {
		return "Book{" +
				"id=" + id +
				", title='" + title + '\'' +
				", firstPublishYear=" + firstPublishYear +
				", numberOfPagesMedian=" + numberOfPagesMedian +
				", smallCover='" + smallCover + '\'' +
				", mediumCover='" + mediumCover + '\'' +
				", largeCover='" + largeCover + '\'' +
				", authorName='" + authorName + '\'' +
				'}';
	}

	public record BookCovers(String S, String M, String L) {
	}

}
