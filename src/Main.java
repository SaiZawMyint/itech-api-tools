import com.itech.api.v1.tools.API;
import org.apache.log4j.PropertyConfigurator;

public class Main {
    static class Request {
        String email;
        String password;

        public Request(String email, String password) {
            this.email = email;
            this.password = password;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
    public static void main(String[] args) {
        PropertyConfigurator.configure("log4j.properties");
        API api = new API();
        String accessToken = "EAAKZCZAYhca2EBAAWTyLxwkgDMsI1JP5ziOn7Ibj1eRfdfrp4ugNpu6la51UAduEXUFoOCWLaDibudZCrP7ryp7kV7G6YKK9pZCaVxKQPVcjAgPmzj6OpnIctySZB6ndZBGx0vLpUVesaMZCNxcUbQQ6cjGRWAsd6IIkY3FbVqY0vJO06XZBdyObpCXu0JdHhsvHoIlvBZCj3iGerCJ2NZAUBqT0Tz7LYfFiQZD";
        api.get("https://graph.facebook.com/me?access_token=".concat(accessToken)).then(response->{

            return response;
        }).exception(e -> {
            return e;
        });
    }
}