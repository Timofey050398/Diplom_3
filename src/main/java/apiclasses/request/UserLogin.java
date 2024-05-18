package apiclasses.request;
import constants.ApiConstants;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.startsWith;

public class UserLogin implements User {
    private String email;
    private String password;

    @Override
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getPassword() {
        return password;
    }
    @Override
    public String getName(){
        return null;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserLogin() {
    }

    public UserLogin(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public UserLogin(String email) {
        this.email = email;
    }

    @Step("Запрос логина через АПИ")
    public static Response sendPostUserLogin(User user){
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(user)
                .when()
                .post(ApiConstants.USER_LOGIN);
        return response;
    }

    @Step("Get Access Token")
    public static String getAccessToken(User user){
        Response response = sendPostUserLogin(user);
        String accessToken = response.jsonPath().getString("accessToken");
        return accessToken;
    }
    @Step("Убедиться, что запрос логина прошел успешно")
    public static  void verifySuccessResponseBodyIsCorrect(Response response, String expectedEmail, String expectedName){
        response.then()
                .body("accessToken", startsWith("Bearer ")).and()
                .body("refreshToken", instanceOf(String.class)).and()
                .body("refreshToken", notNullValue()).and()
                .body("success", equalTo(true)).and()
                .body("user.email", equalTo(expectedEmail)).and()
                .body("user.name", equalTo(expectedName)).and()
                .statusCode(200);
    }
}
