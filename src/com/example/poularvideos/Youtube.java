package com.example.poularvideos;

public interface Youtube {

	
String FEED_URL = "http://gdata.youtube.com/feeds/api/standardfeeds/most_viewed?v=2&alt=json&format=1&maxresults=10&time=today";
	
	/*
	 * json tags
	 */
	String ID = "id";
	String AUTHOR = "author";
	String NAME = "name";

	String ENTRY = "entry";
	String FEED = "feed";
	String TITLE = "title";
	String CONTENT = "content";

	String LINK = "link";
	String HREF = "href";

	String MEDIA_GROUP = "media$group";
	String MEDIA_THUMBNAIL = "media$thumbnail";
	String URL = "url";

	String UPDATED = "updated";

	String T = "$t";
}
