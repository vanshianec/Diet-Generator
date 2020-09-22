package diet.dietgenerator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DietGeneratorApplication {

    static {
        org.apache.tomcat.jni.Library.loadLibrary("jniortools");
    }

    public static void main(String[] args) {
        SpringApplication.run(DietGeneratorApplication.class, args);
    }

}
