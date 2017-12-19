package de.hybris.platform.customerreview.impl;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.customerreview.model.CustomerReviewModel;
import de.hybris.platform.customerreview.constants.CustomerReviewConstants;
import de.hybris.platform.customerreview.PennyCustomerReviewService;
import de.hybris.platform.customerreview.dao.PennyCustomerReviewDao;
import de.hybris.platform.customerreview.jalo.CustomerReview;
import de.hybris.platform.customerreview.jalo.CustomerReviewManager;

public class PennyCustomerReviewServiceImpl
	extends AbstractBusinessService implements PennyCustomerReviewService
{
	// Define a static variable for the list of curse words
	public static List<String> curseList = Collections.synchronizedList(new ArrayList<>());
	static {
		Files.readAllLines(Paths.get(PennyCustomerReviewServiceImpl.class
			.getResource("/curse.txt").getPath()), Charset.forName("utf-8"));
	}

	private PennyCustomerReviewDao pennyCustomerReviewDao;

	public PennyCustomerReviewDao getPennyCustomerReviewDao()
	{
		return pennyCustomerReviewDao;
	}

	@Required
	public void setPennyCustomerReviewDao(
		PennyCustomerReviewDao pennyCustomerReviewDao)
	{
		this.pennyCustomerReviewDao = pennyCustomerReviewDao;
	}

	@Override
	public int getReviewsByRatingRange(
		ProductModel product, double minRating, double maxRating)
	{
		return pennyCustomerReviewDao.findReviewsCountByRatingRange(
			product, minRating, maxRating);
	}

	@Override
	public CustomerReviewModel addReview(Double rating,
		String headline, String content, UserModel paramUserModel,
		ProductModel paramProductModel)
	{
		if (rating < 0)
		{
			throw new IllegalArgumentException("the rating of the comment added by you can't less than 0:" + rating);
		}
		this.curseList.forEach(curseWord ->{
			if (headline.contains(curseWord) ||
				content.contains(curseWord))
			{
				throw new IllegalArgumentException("the comment added can't contain a curse:" + curseWord);
			}
		});
		CustomerReview review = CustomerReviewManager.getInstance().createCustomerReview(rating, headline, content,
			User)getModelService().getSource(user), (Product)getModelService().getSource(product));
		return (CustomerReviewModel)getModelService().get(review);
	}
}
