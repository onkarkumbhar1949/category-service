package com.example.categorymodule.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VideosDto {
	private int videoId;
	private String title;	
	private String videoName;
	private String videoUrl;
	private String description;
	private LocalDate uploadDate;
	private String tags;
	

	@Override
	public String toString() {
		return "Videos [videoId=" + videoId + ", title=" + title + ", videoName=" + videoName + ", videoUrl=" + videoUrl
				+ ", description=" + description +  ", uploadDate="
				+ uploadDate + ", tags=" + tags + "]";
	}

	

}
