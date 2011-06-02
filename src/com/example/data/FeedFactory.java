package com.example.data;

import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;

public class FeedFactory extends BaseResponseFactory
{
	private Feed mFeed = null;
	private ArrayList<Entry> mEntryList = new ArrayList<Entry>();
	private ArrayList<Link> mFeedLinkList = null;
	private ArrayList<Link> mEntryLinkList = null;
	private ArrayList<Category> mCategoryList = null;
	private ArrayList<Content> mContentList = null;
	private ArrayList<Thumbnail> mThumbnailList = null;
	private Entry mEntry = null;
	private Author mAuthor = null;
	private Comments mComments = null;
	private Group mGroup = null;
	private Generator mGenerator = null;

	@Override
	public void onStartTag(XmlPullParser parser) throws Exception
	{
		String strName = parser.getName();
		int iDepth = parser.getDepth();

		if(strName.equals("feed") && iDepth == 1)
		{
			mFeed = new Feed();
			mFeedLinkList = new ArrayList<Link>();
		}
		else if(strName.equals("entry") && iDepth == 2)
		{
			mEntry = new Entry();
			mCategoryList = new ArrayList<Category>();
			mEntryLinkList = new ArrayList<Link>();
		}
		else if(strName.equals("category"))
		{
			Category category = new Category();
			category.setScheme(getAttribute(parser, "scheme"));
			category.setTerm(getAttribute(parser, "term"));
			category.setLabel(getAttribute(parser, "label"));
			if(iDepth == 2){
				mFeed.setCategory(category);
			}else if(iDepth == 3){
				mCategoryList.add(category);
			}else if(iDepth == 4){
				mGroup.setCategory(category);
			}
		}
		else if(strName.equals("link"))
		{
			Link link = new Link();
			link.setRel(getAttribute(parser, "rel"));
			link.setType(getAttribute(parser, "type"));
			link.setHref(getAttribute(parser, "href"));
			if(iDepth == 2){
				mFeedLinkList.add(link);
			}else if(iDepth == 3){
				mEntryLinkList.add(link);
			}
		}
		else if(strName.equals("author"))
		{
			mAuthor = new Author();
		}
		else if(strName.equals("generator") && iDepth ==2)
		{
			mGenerator = new Generator();
			mGenerator.setUri(getAttribute(parser, "uri"));
			mGenerator.setVersion(getAttribute(parser, "version"));
		}
		else if(strName.equals("comments") && iDepth == 3)
		{
			mComments = new Comments();
		}
		else if(strName.equals("feedLink") && iDepth == 4)
		{
			FeedLink feedLink = new FeedLink();
			feedLink.setRel(getAttribute(parser, "rel"));
			feedLink.setHref(getAttribute(parser, "href"));
			feedLink.setCountHint(convertLong(getAttribute(parser, "countHint")));
			mComments.setFeedLink(feedLink);
		}
		else if(strName.equals("group") && iDepth == 3)
		{
			mGroup = new Group();
			mContentList = new ArrayList<Content>();
			mThumbnailList = new ArrayList<Thumbnail>();
		}
		else if(strName.equals("content") && iDepth == 4)
		{
			Content content = new Content();
			content.setUrl(getAttribute(parser, "url"));
			content.setType(getAttribute(parser, "type"));
			String strDefault = getAttribute(parser, "isDefault");
			if(strDefault != null && strDefault.equals("true"))
			{
				content.set_default(false);
			}
			else
			{
				content.set_default(false);
			}
			content.setExpression(getAttribute(parser, "expression"));
			content.setDuration(convertInt(getAttribute(parser, "duration")));
			content.setFormat(convertInt(getAttribute(parser, "expression")));
		}
		else if(strName.equals("player") && iDepth == 4)
		{
			Player player = new Player();
			player.setUrl(getAttribute(parser, "url"));
			mGroup.setPlayer(player);
		}
		else if(strName.equals("thumbnail") && iDepth == 4)
		{
			Thumbnail thumbnail = new Thumbnail();
			thumbnail.setUrl(getAttribute(parser, "url"));
			thumbnail.setHeight(convertInt(getAttribute(parser, "height")));
			thumbnail.setWidth(convertInt(getAttribute(parser, "width")));
			thumbnail.setTime(getAttribute(parser, "time"));
			mThumbnailList.add(thumbnail);
		}
		else if(strName.equals("duration") && iDepth == 4)
		{
			mGroup.setDuration(convertLong(getAttribute(parser, "seconds")));
		}
		else if(strName.equals("rating") && iDepth == 3)
		{
			Rating rating = new Rating();
			rating.setMin(convertInt(getAttribute(parser, "min")));
			rating.setMax(convertInt(getAttribute(parser, "max")));
			rating.setNumRaters(convertInt(getAttribute(parser, "numRaters")));
			rating.setAverage(convertInt(getAttribute(parser, "average")));
		}
		else if(strName.equals("statistics") && iDepth == 3)
		{
			Statistics stat = new Statistics();
			stat.setViewCount(convertLong(getAttribute(parser, "viewCount")));
			stat.setVideoWatchCount(convertLong(getAttribute(parser, "videoWatchCount")));
			stat.setSubscriberCount(convertLong(getAttribute(parser, "subscriberCount")));
			stat.setLastWebAccess(getAttribute(parser, "lastWebAccess"));
			stat.setFavoriteCount(convertLong(getAttribute(parser, "favoriteCount")));
			mEntry.setStatistics(stat);
		}
	}

	@Override
	protected void onEndTag(XmlPullParser parser)
	{
		String strName = parser.getName();
		int iDepth = parser.getDepth();

		if(strName.equals("feed"))
		{
			mFeed.setLink(mFeedLinkList);
			mFeed.setEntry(mEntryList);
		}
		else if(strName.equals("entry"))
		{
			mEntry.setCategory(mCategoryList);
			mEntry.setLink(mEntryLinkList);
			mEntryList.add(mEntry);
			mEntry = null;
		}
		else if(strName.equals("group"))
		{
			mGroup.setThumbnail(mThumbnailList);
			mGroup.setContent(mContentList);
			mEntry.setGroup(mGroup);
			mThumbnailList = null;
			mGroup = null;
		}
		else if(strName.equals("id"))
		{
			if(iDepth == 2)
			{
				mFeed.setId(mText);
			}
			else if(iDepth == 3)
			{
				mEntry.setId(mText);
			}
		}
		else if(strName.equals("updated"))
		{
			if(iDepth == 2)
			{
				mFeed.setUpdated(mText);
			}
			else if(iDepth == 3)
			{
				mEntry.setUpdated(mText);
			}
		}
		else if(strName.equals("title"))
		{
			if(iDepth == 2)
			{
				mFeed.setTitle(mText);
			}
			else if(iDepth == 3)
			{
				mEntry.setTitle(mText);
			}
			else if(iDepth == 4)
			{
				mGroup.setTitle(mText);
			}
		}
		else if(strName.equals("logo"))
		{
			mFeed.setLogo(mText);
		}
		else if(strName.equals("author"))
		{
			if(iDepth == 2)
			{
				mFeed.setAuthor(mAuthor);
			}
			else if(iDepth == 3)
			{
				mEntry.setAuthor(mAuthor);
			}
		}
		else if(strName.equals("generator") && iDepth ==2)
		{
			mGenerator.setGenerator(mText);
			mFeed.setGenarator(mGenerator);
		}
		else if(strName.equals("name") && iDepth == 4)
		{
			mAuthor.setName(mText);
		}
		else if(strName.equals("uri") && iDepth == 4)
		{
			mAuthor.setUri(mText);
		}
		else if(strName.equals("totalResults") && iDepth == 2)
		{
			mFeed.setTotalResults(convertInt(mText));
		}
		else if(strName.equals("startIndex") && iDepth == 2)
		{
			mFeed.setStartIndex(convertInt(mText));
		}
		else if(strName.equals("itemPerPage") && iDepth == 2)
		{
			mFeed.setItemPerPage(convertInt(mText));
		}
		else if(strName.equals("published") && iDepth == 3)
		{
			mEntry.setPublished(mText);
		}
		else if(strName.equals("content"))
		{
			mEntry.setContent(mText);
		}
		else if(strName.equals("comments") && iDepth == 3)
		{
			mEntry.setComments(mComments);
			mComments = null;
		}
		else if(strName.equals("description") && iDepth == 4)
		{
			mGroup.setDescription(mText);
		}
		else if(strName.equals("keywords") && iDepth ==4)
		{
			mGroup.setKeywords(mText);
		}
	}

	@Override
	protected BaseData getResponse()
	{
		return mFeed;
	}
}
