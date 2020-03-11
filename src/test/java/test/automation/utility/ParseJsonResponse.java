package test.automation.utility;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ParseJsonResponse {
	private static  String JSON__NAME = "Name";
	private static  String JSON_DESCRIPTION = "Description";
	private static  String JSON__PROMOTIONS = "Promotions";
	private static  String JSON_CAN_We_RELIST = "CanRelist";
	private JsonElement jsonElement;

	public ParseJsonResponse(String jsonString) {
		JsonParser jsonParser = new JsonParser();
		jsonElement = jsonParser.parse(jsonString);
	}

	private JsonObject getJsonRootObject() {
		JsonObject rootJson = null;
		if (jsonElement.isJsonObject())
			rootJson = jsonElement.getAsJsonObject();
		return rootJson;
	}

	public Boolean CanWeRelist() {
		return getJsonRootObject().get(JSON_CAN_We_RELIST).getAsBoolean();
	}

	public String getNameFromRootObject() {
		return getJsonRootObject().get(JSON__NAME).getAsString();
	}

	private JsonArray getPromotions() {
		JsonArray arrPromotions;
		arrPromotions = getJsonRootObject().get(JSON__PROMOTIONS).getAsJsonArray();
		return arrPromotions;
	}

	private JsonObject getPromotionName(String promotionName) {
		String name;
		JsonObject jsonObjectPromotion = null;
		for (JsonElement elePromotion : getPromotions()) {
			name = elePromotion.getAsJsonObject().get(JSON__NAME).getAsString();
			if (name.compareTo(promotionName) == 0)
				jsonObjectPromotion = (JsonObject) elePromotion;
		}
		return jsonObjectPromotion;
	}

	public String getPromotionDescription(String promotionName) {
		return getPromotionName(promotionName).get(JSON_DESCRIPTION).getAsString();
	}

}
