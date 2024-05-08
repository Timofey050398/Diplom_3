package apiclasses.request;

import constants.ApiConstants;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class UserDelete {

    @Step("Запрос удаления пользователя через АПИ")
    public static Response sendDeleteUser(User user){
        Response response = given()
                .header("Content-type", "application/json")
                .header("Authorization", UserLogin.getAccessToken(user))
                .delete(ApiConstants.USER_UPDATE);
                return response;
    }
}
