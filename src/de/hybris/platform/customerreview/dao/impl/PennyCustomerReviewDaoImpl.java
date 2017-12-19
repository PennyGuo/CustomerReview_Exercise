package de.hybris.platform.customerreview.dao.impl;

import java.util.Collections;
import java.util.List;

import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.customerreview.dao.PennyCustomerReviewDao;
import de.hybris.platform.customerreview.model.CustomerReviewModel;
import de.hybris.platform.servicelayer.internal.dao.AbstractItemDao;

public class PennyCustomerReviewDaoImpl extends AbstractItemDao
	implements PennyCustomerReviewDao
{

	@Override
	public int findReviewsCountByRatingRange(
		ProductModel product, double minRating, double maxRating)
	{
		String query = "SELECT {" + Item.PK + "} FROM {" + "CustomerReview" + "} WHERE {" + "product" + "}=?product and {rating} >= ?minRating and {rating} <= ?maxRating";
		FlexibleSearchQuery fsQuery = new FlexibleSearchQuery(query);
		fsQuery.addQueryParameter("product", product);
		fsQuery.addQueryParameter("minRating", minRating);
		fsQuery.addQueryParameter("maxRating", maxRating);
		fsQuery.setResultClassList(Collections.singletonList(CustomerReviewModel.class));
		
		SearchResult<CustomerReviewModel> searchResult = getFlexibleSearchService().search(fsQuery);
		return searchResult.size();
	}
}
