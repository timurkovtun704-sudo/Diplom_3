package api;
import config.Constants;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import static io.restassured.RestAssured.given;

public class UserClient {

    @Step("Создание пользователя через API")
    public ValidatableResponse createUser(User user) {
        return given()
                .contentType(ContentType.JSON)
                .baseUri(Constants.BASE_URL)
                .body(user)
                .when()
                .post(Constants.REGISTER_ENDPOINT)
                .then();
    }

    @Step("Логин пользователя через API для получения токена")
    public ValidatableResponse loginUser(User user) {
        return given()
                .contentType(ContentType.JSON)
                .baseUri(Constants.BASE_URL)
                .body(user)
                .when()
                .post(Constants.LOGIN_ENDPOINT)
                .then();
    }

    @Step("Удаление пользователя через API")
    public void deleteUser(String accessToken) {
        if (accessToken != null && !accessToken.isEmpty()) {
            given()
                    .header("Authorization", accessToken)
                    .baseUri(Constants.BASE_URL)
                    .when()
                    .delete(Constants.USER_ENDPOINT);
        }
    }
}
