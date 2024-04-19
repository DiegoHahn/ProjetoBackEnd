package satc.intermediario.demo.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import satc.intermediario.demo.controller.CoordenadasController;

import java.util.Scanner;

@Component
public class Principal implements CommandLineRunner {

    @Autowired
    private CoordenadasController coordenadasController;

    @Override
    public void run(String... args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Digite o nome da cidade para obter as coordenadas geográficas:");
            String city = scanner.nextLine();
            coordenadasController.getCoordinates(city)
                    .subscribe(coordinates -> System.out.println("Coordenadas: " + coordinates));
        }
    }
}
