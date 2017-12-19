package de.hybris.platform.customerreview;

import java.util.List;

import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.customerreview.model.CustomerReviewModel;

public interface PennyCustomerReviewService
{
	/*
	 * Provide a way to get a product’s total number of customer reviews 
	 * whose ratings are within a given range (inclusive)
	 */
	int getReviewsByRatingRange(
		ProductModel pruduct, double minRating, double maxRating);
	
	/*
	 * Add the following additional checks before creating a customer review:
		a.	Your service should read a list of curse words. This list should not be defined in Java class. 
		b.	Check if Customer’s comment contains any of these curse words. If it does, throw an exception with a message.
		c.	Check if the rating is not < 0.  If it is < 0, throw an exception with a message.
	If all the rules are passed, go ahead and create the customer review.
	 */
	CustomerReviewModel addReview(Double rating,
		String headline, String content, UserModel paramUserModel,
		ProductModel paramProductModel);	
}
