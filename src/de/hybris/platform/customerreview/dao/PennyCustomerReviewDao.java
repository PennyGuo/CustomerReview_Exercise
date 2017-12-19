package de.hybris.platform.customerreview.dao;

import java.util.List;

import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.customerreview.model.CustomerReviewModel;

public interface PennyCustomerReviewDao
{
	int findReviewsCountByRatingRange(
		ProductModel product, double minRating, double maxRating);
}
