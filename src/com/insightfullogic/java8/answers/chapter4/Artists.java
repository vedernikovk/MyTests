package com.insightfullogic.java8.answers.chapter4;

import java.util.List;
import java.util.Optional;

import com.insightfullogic.java8.examples.chapter1.Artist;

public class Artists {
	
	private List<Artist> artists;
	
	public Artists(List<Artist> artists) {
		this.artists = artists;
	}
	
	public Optional<Artist> getArtist(int index) {
		if (index < 0 || index >= artists.size()) {
			return Optional.empty();
		}
		
		return Optional.of(artists.get(index));
	}

	public Optional<String> getArtistName(int index) {
		Optional<Artist> artist = getArtist(index);
		return artist.isPresent() ? Optional.of(artist.get().getName()) : Optional.empty();
	}
}